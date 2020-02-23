package com.hanggle.frames.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hanggle.frames.base.ExpireTime;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: <br/>
 * @Author: zh <br/>
 * @Date: 2018/10/27 <br/>
 */
@Component
public class RedisUtils {
    /**
     * 默认过期时长，单位：秒
     */
    public final long DEFAULT_EXPIRE = 60 * 60 * 24;
    private final String SET_IF_NOT_EXIST = "NX";
    private final String SET_WITH_EXPIRE_TIME = "PX";
    private DefaultRedisScript<String> redisScript;
    private RedisSerializer<String> argsSerializer;
    private RedisSerializer resultSerializer;
    private final Long EXEC_RESULT = 1L;
    {
        redisScript = new DefaultRedisScript<String>();
        redisScript.setResultType(String.class);
        argsSerializer = new StringRedisSerializer();
        resultSerializer = new StringRedisSerializer();
    }
    /**
     * 不设置过期时长
     */
    public final long NOT_EXPIRE = -1;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    /**
     *
     * @param key
     * @param requestId
     * @param expireTime
     * @return
     */
    public boolean getLock(String key, String requestId, String expireTime) {
        ClassPathResource classPathResource = new ClassPathResource("script/getLock.lua");
        redisScript.setScriptSource(new ResourceScriptSource(classPathResource));
        Object result = stringRedisTemplate.execute(redisScript, Collections.singletonList(key), expireTime);
        return EXEC_RESULT.equals(result);
    }

    /**
     * 删除缓存<br>
     * 根据key精确匹配删除
     * @param key 删除的key
     */
    public void del(String... key){
        if(key!=null && key.length > 0){
            if(key.length == 1){
                stringRedisTemplate.delete(key[0]);
            }else{
                stringRedisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 批量删除<br>
     * （该操作会执行模糊查询，请尽量不要使用，以免影响性能或误删）
     * @param pattern 删除字段
     */
    public void batchDel(String... pattern){
        for (String kp : pattern) {
            stringRedisTemplate.delete(stringRedisTemplate.keys(kp + "*"));
        }
    }

    /**
     * 取得缓存（int型）
     * @param key key
     * @return data
     */
    public Integer getInt(String key){
        String value = stringRedisTemplate.boundValueOps(key).get();
        if(StringUtils.isNotBlank(value)){
            return Integer.valueOf(value);
        }
        return null;
    }

    /**
     * 取得缓存（字符串类型）
     * @param key key
     * @return data
     */
    public String getStr(String key){
        return stringRedisTemplate.boundValueOps(key).get();
    }

    /**
     * 取得缓存（字符串类型）
     * @param key key
     * @return data
     */
    public String getStr(String key, boolean retain){
        String value = stringRedisTemplate.boundValueOps(key).get();
        if(!retain){
            stringRedisTemplate.delete(key);
        }
        return value;
    }

    /**
     * 获取缓存<br>
     * 注：基本数据类型(Character除外)，请直接使用get(String key, Class<T> clazz)取值
     * @param key key
     * @return data
     */
    public Object getObj(String key){
        return stringRedisTemplate.boundValueOps(key).get();
    }

    /**
     * 获取缓存<br>
     * 注：java 8种基本类型的数据请直接使用get(String key, Class<T> clazz)取值
     * @param key key
     * @param retain    是否保留
     * @return data
     */
    public Object getObj(String key, boolean retain){
        Object obj = stringRedisTemplate.boundValueOps(key).get();
        if(!retain){
            stringRedisTemplate.delete(key);
        }
        return obj;
    }

    /**
     * 获取缓存<br>
     * 注：该方法暂不支持Character数据类型
     * @param key   key
     * @param clazz 类型
     * @return data
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz) {
        return (T)stringRedisTemplate.boundValueOps(key).get();
    }

    /**
     * 获取缓存json对象<br>
     * @param key   key
     * @param clazz 类型
     * @return data
     */
    public <T> T getJson(String key, Class<T> clazz) {
        return JsonMapper.fromJsonString(stringRedisTemplate.boundValueOps(key).get(), clazz);
    }

    /**
     * 将value对象写入缓存
     * @param key key
     * @param value value
     * @param time 失效时间(秒)
     */
    public void set(String key,Object value,Long time){
        if(value.getClass().equals(String.class)){
            stringRedisTemplate.opsForValue().set(key, value.toString());
        }else if(value.getClass().equals(Integer.class)){
            stringRedisTemplate.opsForValue().set(key, value.toString());
        }else if(value.getClass().equals(Double.class)){
            stringRedisTemplate.opsForValue().set(key, value.toString());
        }else if(value.getClass().equals(Float.class)){
            stringRedisTemplate.opsForValue().set(key, value.toString());
        }else if(value.getClass().equals(Short.class)){
            stringRedisTemplate.opsForValue().set(key, value.toString());
        }else if(value.getClass().equals(Long.class)){
            stringRedisTemplate.opsForValue().set(key, value.toString());
        }else if(value.getClass().equals(Boolean.class)){
            stringRedisTemplate.opsForValue().set(key, value.toString());
        }else{
            stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
        }
        if(time > 0){
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }

    /**
     * 将value对象以JSON格式写入缓存
     * @param key
     * @param value
     * @param time 失效时间(秒)
     */
    public void setJson(String key,Object value,ExpireTime time){
        stringRedisTemplate.opsForValue().set(key, JsonMapper.toJsonString(value));
        if(time.getTime() > 0){
            stringRedisTemplate.expire(key, time.getTime(), TimeUnit.SECONDS);
        }
    }

    /**
     * 更新key对象field的值
     * @param key   缓存key
     * @param field 缓存对象field
     * @param value 缓存对象field值
     */
    public void setJsonField(String key, String field, String value){
        JSONObject obj = JSON.parseObject(stringRedisTemplate.boundValueOps(key).get());
        obj.put(field, value);
        stringRedisTemplate.opsForValue().set(key, obj.toJSONString());
    }


    /**
     * 递减操作
     * @param key
     * @param by
     * @return
     */
    public double decr(String key, double by){
        return stringRedisTemplate.opsForValue().increment(key, -by);
    }

    /**
     * 递增操作
     * @param key
     * @param by
     * @return
     */
    public double incr(String key, double by){
        return stringRedisTemplate.opsForValue().increment(key, by);
    }

    /**
     * 获取double类型值
     * @param key
     * @return
     */
    public double getDouble(String key) {
        String value = stringRedisTemplate.boundValueOps(key).get();
        if(StringUtils.isNotBlank(value)){
            return Double.valueOf(value);
        }
        return 0d;
    }

    /**
     * 设置double类型值
     * @param key
     * @param value
     * @param time 失效时间(秒)
     */
    public void setDouble(String key, double value, ExpireTime time) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
        if(time.getTime() > 0){
            stringRedisTemplate.expire(key, time.getTime(), TimeUnit.SECONDS);
        }
    }

    /**
     * 设置double类型值
     * @param key
     * @param value
     * @param time 失效时间(秒)
     */
    public void setInt(String key, int value, ExpireTime time) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
        if(time.getTime() > 0){
            stringRedisTemplate.expire(key, time.getTime(), TimeUnit.SECONDS);
        }
    }

    /**
     * 将map写入缓存
     * @param key
     * @param map
     * @param time 失效时间(秒)
     */
    public <T> void setMap(String key, Map<String, T> map, ExpireTime time){
        stringRedisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 将map写入缓存
     * @param key
     * @param time 失效时间(秒)
     */
    @SuppressWarnings("unchecked")
    public <T> void setMap(String key, T obj, ExpireTime time){
        Map<String, String> map = (Map<String, String>)JsonMapper.parseObject(obj, Map.class);
        stringRedisTemplate.opsForHash().putAll(key, map);
    }



    /**
     * 向key对应的map中添加缓存对象
     * @param key
     * @param map
     */
    public <T> void addMap(String key, Map<String, T> map){
        stringRedisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 向key对应的map中添加缓存对象
     * @param key   cache对象key
     * @param field map对应的key
     * @param value     值
     */
    public void addMap(String key, String field, String value){
        stringRedisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 向key对应的map中添加缓存对象
     * @param key   cache对象key
     * @param field map对应的key
     * @param obj   对象
     */
    public <T> void addMap(String key, String field, T obj){
        stringRedisTemplate.opsForHash().put(key, field, obj);
    }

    /**
     * 获取map缓存
     * @param key
     * @param clazz
     * @return
     */
    public <T> Map<String, T> mget(String key, Class<T> clazz){
        BoundHashOperations<String, String, T> boundHashOperations = stringRedisTemplate.boundHashOps(key);
        return boundHashOperations.entries();
    }

    /**
     * 获取map缓存
     * @param key
     * @param clazz
     * @return
     */
    public <T> T getMap(String key, Class<T> clazz){
        BoundHashOperations<String, String, String> boundHashOperations = stringRedisTemplate.boundHashOps(key);
        Map<String, String> map = boundHashOperations.entries();
        return JsonMapper.parseObject(map, clazz);
    }

    /**
     * 获取map缓存中的某个对象
     * @param key
     * @param field
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getMapField(String key, String field, Class<T> clazz){
        return (T)stringRedisTemplate.boundHashOps(key).get(field);
    }

    /**
     * 指定缓存的失效时间
     *
     * @author FangJun
     * @date 2016年8月14日
     * @param key 缓存KEY
     * @param time 失效时间(秒)
     */
    public void expire(String key, ExpireTime time) {
        if(time.getTime() > 0){
            stringRedisTemplate.expire(key, time.getTime(), TimeUnit.SECONDS);
        }
    }

    /**
     * 添加set
     * @param key
     * @param value
     */
    public void sadd(String key, String... value) {
        stringRedisTemplate.boundSetOps(key).add(value);
    }

    /**
     * set重命名
     * @param oldkey
     * @param newkey
     */
    public void srename(String oldkey, String newkey){
        stringRedisTemplate.boundSetOps(oldkey).rename(newkey);
    }

    /**
     * 短信缓存
     * @author fxl
     * @date 2016年9月11日
     * @param key
     * @param value
     * @param time
     */
    public void setIntForPhone(String key,Object value,int time){
        stringRedisTemplate.opsForValue().set(key, JsonMapper.toJsonString(value));
        if(time > 0){
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }
    /**
     * 模糊查询keys
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern){
        return stringRedisTemplate.keys(pattern);
    }

    // ==================================================

    public Object get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 判断key是否存在
     * @param key key
     * @return true：存在 false：不存在
     */
    public boolean existsKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }
    /**
     * 重名名key，如果newKey已经存在，则newKey的原值被覆盖
     *
     * @param oldKey key
     * @param newKey key
     */
    public void renameKey(String oldKey, String newKey) {
        stringRedisTemplate.rename(oldKey, newKey);
    }

    /**
     * newKey不存在时才重命名
     *
     * @param oldKey key
     * @param newKey key
     * @return 修改成功返回true
     */
    public boolean renameKeyNotExist(String oldKey, String newKey) {
        return stringRedisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 删除key
     *
     * @param key key
     */
    public void deleteKey(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 删除多个key
     *
     * @param keys key
     */
    public void deleteKey(String... keys) {
        Set<String> kSet = Stream.of(keys).map(k -> k).collect(Collectors.toSet());
        stringRedisTemplate.delete(kSet);
    }

    /**
     * 删除Key的集合
     *
     * @param keys key
     */
    public void deleteKey(Collection<String> keys) {
        Set<String> kSet = keys.stream().map(k -> k).collect(Collectors.toSet());
        stringRedisTemplate.delete(kSet);
    }

    /**
     * 设置key的生命周期
     *
     * @param key
     * @param time
     * @param timeUnit
     */
    public void expireKey(String key, long time, TimeUnit timeUnit) {
        stringRedisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 指定key在指定的日期过期
     *
     * @param key
     * @param date
     */
    public void expireKeyAt(String key, Date date) {
        stringRedisTemplate.expireAt(key, date);
    }

    /**
     * 查询key的生命周期
     *
     * @param key key
     * @param timeUnit 时间单元
     * @return 时间
     */
    public long getKeyExpire(String key, TimeUnit timeUnit) {
        return stringRedisTemplate.getExpire(key, timeUnit);
    }
    /**
     * 将key设置为永久有效
     *
     * @param key key
     */
    public void persistKey(String key) {
        stringRedisTemplate.persist(key);
    }

    /**
     * 向list中增加值
     * @param key key
     * @param obj 存储的值
     * @return 返回在list中的下标
     */
    public long addList(String key, String obj) {
        return stringRedisTemplate.boundListOps(key).rightPush(obj);
    }

    /**
     * 向list中增加值
     * @param key key
     * @param obj obj
     * @return 返回在list中的下标
     */
    public long addList(String key, String... obj) {
        return stringRedisTemplate.boundListOps(key).rightPushAll(obj);
    }

    /**
     *
     * 输出list
     * @param key List的key
     * @param s 开始下标
     * @param e 结束的下标
     * @return
     */
    public List<String> getList(String key, long s, long e) {
        return stringRedisTemplate.boundListOps(key).range(s, e);
    }

    /**
     * 输出完整的list
     * @param key
     */
    public List<String> getList(String key) {
        return stringRedisTemplate.boundListOps(key).range(0, getListSize(key));
    }

    /**
     * 获取list集合中元素的个数
     * @param key key
     * @return 长度
     */
    public long getListSize(String key) {
        return stringRedisTemplate.boundListOps(key).size();
    }

    /**
     * 将value对象以JSON格式写入缓存
     * @param key
     * @param value
     * @param time 失效时间(秒)
     */
    //public void setJson(String key,Object value,ExpireTime time){
    //    stringRedisTemplate.opsForValue().set(key, JsonMapper.toJsonString(value));
    //    if(time.getTime() > 0){
    //        stringRedisTemplate.expire(key, time.getTime(), TimeUnit.SECONDS);
    //    }
    //}
}
