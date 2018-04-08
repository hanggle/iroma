package com.oskyhang;

import com.hanggle.mybatis.SqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * description: <br/>
 * author: zh <br/>
 * date: 2018/3/15 <br/>
 */

@Configuration
@MapperScan(basePackages="com.oskyhang.*.mapper", sqlSessionTemplateRef = "test1SqlSessionTemplate")
public class DatasourceConfig {

    @Bean(name = "test1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "test1SqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // TODO
        // 加载全局的配置文件
        bean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:sqlMapConfig.xml"));

        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:com/oskyhang/system/mapper/*.xml");
        bean.setMapperLocations(resources);

        return bean.getObject();
    }

    @Bean(name = "test1TransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("test1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "test1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
