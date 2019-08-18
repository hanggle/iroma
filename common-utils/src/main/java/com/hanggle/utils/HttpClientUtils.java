package com.hanggle.utils;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.*;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.*;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.pool.PoolStats;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * @author hanggle
 */
@Slf4j
public class HttpClientUtils {
    /**
     * 每个路由基础的连接数
     */
    private final static int CONNECT_TIMEOUT = 4000;
    /**
     * 传输超时毫秒
     */
    private final static int SOCKET_TIMEOUT = 10000;
    /**
     * 获取请求超时毫秒
     */
    private final static int REQUESTCONNECT_TIMEOUT = 3000;
    /**
     * 最大连接数
     */
    private final static int CONNECT_TOTAL = 200;
    /**
     * 每个路由基础的连接数
     */
    private final static int CONNECT_ROUTE = 20;
    /**
     * 响应报文解码字符集
     */
    private final static String ENCODE_CHARSET = "UTF-8";
    private final static String RESP_CONTENT = "通信失败";
    private static PoolingHttpClientConnectionManager connManager;
    private static CloseableHttpClient httpClient;
    private static ConnectionConfig config;

    static {
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = createSSLConnSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("http", plainsf).register("https", sslsf).build();
        config = ConnectionConfig.custom().setCharset(Charset.forName(ENCODE_CHARSET)).build();
        connManager = new PoolingHttpClientConnectionManager(registry);
        // 将最大连接数增加到200
        connManager.setMaxTotal(CONNECT_TOTAL);
        // 将每个路由基础的连接增加到20
        connManager.setDefaultMaxPerRoute(CONNECT_ROUTE);
        // 可用空闲连接过期时间,重用空闲连接时会先检查是否空闲时间超过这个时间，如果超过，释放socket重新建立
        connManager.setValidateAfterInactivity(30000);
        // 设置socket超时时间
        SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(SOCKET_TIMEOUT).build();
        connManager.setDefaultSocketConfig(socketConfig);

        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUESTCONNECT_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();

        HttpRequestRetryHandler httpRequestRetryHandler = (exception, executionCount, context) -> {
            // 如果已经重试了3次，就放弃
            if (executionCount >= 3) {
                return false;
            }
            // 如果服务器丢掉了连接，那么就重试
            if (exception instanceof NoHttpResponseException) {
                return true;
            }
            // 不要重试SSL握手异常
            if (exception instanceof SSLHandshakeException) {
                return false;
            }
            // 超时
            if (exception instanceof InterruptedIOException) {
                return true;
            }
            // 目标服务器不可达
            if (exception instanceof UnknownHostException) {
                return false;
            }
            // 连接被拒绝
            if (exception instanceof ConnectTimeoutException) {
                return false;
            }
            // ssl握手异常
            if (exception instanceof SSLException) {
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            // 如果请求是幂等的，就再次尝试
            return !(request instanceof HttpEntityEnclosingRequest);
        };
        httpClient = HttpClients.custom().setConnectionManager(connManager).setDefaultRequestConfig(requestConfig)
                .setRetryHandler(httpRequestRetryHandler).setDefaultConnectionConfig(config).build();
        if (connManager != null && connManager.getTotalStats() != null) {
            log.info("now client pool " + connManager.getTotalStats().toString());
        }
    }
    public static String get(String url, String param) {
        return get(url, param, ENCODE_CHARSET);
    }

    /**
     * 发送HTTP_GET请求
     *
     * @see 1)该方法会自动关闭连接,释放资源
     * @see 2)方法内设置了连接和读取超时时间,单位为毫秒,超时或发生其它异常时方法会自动返回"通信失败"字符串
     * @see 3)请求参数含中文时,经测试可直接传入中文,HttpClient会自动编码发给Server,应用时应根据实际效果决定传入前是否转码
     * @see 4)该方法会自动获取到响应消息头中[Content-Type:text/html; charset=GBK]的charset值作为响应报文的解码字符集
     * @see  5)若响应消息头中无Content-Type属性,则会使用HttpClient内部默认的ISO-8859-1作为响应报文的解码字符集
     * @param url 请求地址(含参数)
     * @return 远程主机响应正文
     */
    public static String get(String url, String param, String charset) {
        if (null != param) {
            url += "?" + param;
        }
        // 响应内容
        String respContent = RESP_CONTENT ;
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            // 执行GET请求
            response = httpClient.execute(httpget, HttpClientContext.create());
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                respContent = EntityUtils.toString(entity, charset == null? ENCODE_CHARSET:charset);
                EntityUtils.consume(entity);
            }
        } catch (ConnectTimeoutException cte) {
            log.error("请求通信[{}]时连接超时,堆栈轨迹如下{}", url, Throwables.getStackTraceAsString(cte));
        } catch (SocketTimeoutException ste) {
            log.error("请求通信[{}]时读取超时,堆栈轨迹如下{}", url, Throwables.getStackTraceAsString(ste));
        } catch (ClientProtocolException cpe) {
            log.error("请求通信[{}]时协议异常,堆栈轨迹如下{}", url, Throwables.getStackTraceAsString(cpe));
        } catch (ParseException pe) {
            log.error("请求通信[{}]时解析异常,堆栈轨迹如下{}", url, Throwables.getStackTraceAsString(pe));
        } catch (IOException ioe) {
            log.error("请求通信[{}]时网络异常,堆栈轨迹如下{}", url, Throwables.getStackTraceAsString(ioe));
        } catch (Exception e) {
            log.error("请求通信[{}]时偶遇异常,堆栈轨迹如下{}", url, Throwables.getStackTraceAsString(e));
        } finally {
            try {
                if (response != null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpget.releaseConnection();
        }
        return respContent;
    }
    public static String post(String reqURL, String param) {
        return post(reqURL, param, "");
    }
    /**
     * 发送HTTP_POST请求 type: 默认是表单请求，
     * @see 1)该方法允许自定义任何格式和内容的HTTP请求报文体
     * @see 2)该方法会自动关闭连接,释放资源
     * @see 3)方法内设置了连接和读取超时时间,单位为毫秒,超时或发生其它异常时方法会自动返回"通信失败"字符串
     * @see 4)请求参数含中文等特殊字符时,可直接传入本方法,并指明其编码字符集encodeCharset参数,方法内部会自
    动对其转码
     * @see 5)该方法在解码响应报文时所采用的编码,取自响应消息头中的[Content-Type:text/html; charset=GBK]的
    charset值
     * @see 6)若响应消息头中未指定Content-Type属性,则会使用HttpClient内部默认的ISO-8859-1
     * @param url 请求地址
     * @param param 请求参数,若有多个参数则应拼接为param11=value11&22=value22&33=value33的形式
     * @param isJson 编码字符集,编码请求数据时用之,此参数为必填项(不能为""或null)
     * @return 远程主机响应正文
     */
    public static String post(String url, String param, String isJson) {
        String result = RESP_CONTENT ;
        // 设置请求和传输超时时间
        HttpPost httpPost = new HttpPost(url);
        // 这就有可能会导致服务端接收不到POST过去的参数,比如运行在Tomcat6.0.36中的Servlet,所以我们手工指定CONTENT_TYPE头消息
        if (isJson != null && isJson.length() > 0) {
            httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json; charset=" + ENCODE_CHARSET );
        } else {
            httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=" + ENCODE_CHARSET );
        }
        CloseableHttpResponse response = null;
        try {
            if (param != null) {
                StringEntity entity = new StringEntity(param, ENCODE_CHARSET );
                httpPost.setEntity(entity);
            }
            log.info("开始执行请求：" + url);
            response = httpClient.execute(httpPost, HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                result = EntityUtils.toString(entity, ContentType.getOrDefault(entity).getCharset());
                log.info("执行请求完毕：" + result);
                EntityUtils.consume(entity);
            }
        } catch (ConnectTimeoutException cte) {
            log.error("请求通信[{}]时连接超时,堆栈轨迹如下{}", url, Throwables.getStackTraceAsString(cte));
        } catch (SocketTimeoutException ste) {
            log.error("请求通信[{}]时读取超时,堆栈轨迹如下{}", url, Throwables.getStackTraceAsString(ste));
        } catch (ClientProtocolException cpe) {
            log.error("请求通信[{}]时协议异常,堆栈轨迹如下{}", url, Throwables.getStackTraceAsString(cpe));
        } catch (ParseException pe) {
            log.error("请求通信[{}]时解析异常,堆栈轨迹如下{}", url, Throwables.getStackTraceAsString(pe));
        } catch (IOException ioe) {
            log.error("请求通信[{}]时网络异常,堆栈轨迹如下{}", url, Throwables.getStackTraceAsString(ioe));
        } catch (Exception e) {
            log.error("请求通信[{}]时偶遇异常,堆栈轨迹如下{}", url, Throwables.getStackTraceAsString(e));
        } finally {
            try {
                if (response != null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpPost.releaseConnection();
        }
        return result;
    }

    /**
     * SSL的socket工厂创建
     * @return
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        // 创建TrustManager() 用于解决javax.net.ssl.SSLPeerUnverifiedException: peer not authenticated
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            @Override
            public void checkClientTrusted(X509Certificate[] arg0, String authType) {
            }
            @Override
            public void checkServerTrusted(X509Certificate[] arg0, String authType) {
            }
        };
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
            sslContext.init(null, new TrustManager[] {trustManager}, null);
            // 创建SSLSocketFactory , // 不校验域名 ,取代以前验证规则
            sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        } catch (Exception e) {
            log.error("createSSLConnSocketFactory fail! case:{}", Throwables.getStackTraceAsString(e));
        }
        return sslsf;
    }
    public static Map<HttpRoute, PoolStats> getConnManagerStats() {
        if (connManager != null) {
            Set<HttpRoute> routeSet = connManager.getRoutes();
            if (routeSet != null && !routeSet.isEmpty()) {
                Map<HttpRoute, PoolStats> routeStatsMap = new HashMap<HttpRoute, PoolStats>();
                for (HttpRoute route : routeSet) {
                    PoolStats stats = connManager.getStats(route);
                    routeStatsMap.put(route, stats);
                }
                return routeStatsMap;
            }
        }
        return null;
    }
    public static PoolStats getConnManagerTotalStats() {
        if (connManager != null) {
            return connManager.getTotalStats();
        }
        return null;
    }
    /**
     * 关闭系统时关闭httpClient
     */
    public static void releaseHttpClient() {
        try {
            httpClient.close();
        } catch (IOException e) {
            log.error("common-utils[]HttpClientUtils[]releaseHttpClient fail! case:{}", Throwables.getStackTraceAsString(e));
        } finally {
            if (connManager != null) {
                connManager.shutdown();
            }
        }
    }
}
