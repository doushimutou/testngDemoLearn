//package com.ayt.utils;
//
///**
// * @Auther: ayt
// * @Date: 2018/8/12 22:59
// * @Description: Don't worry ,just try
// * 配置数据源
// */
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@MapperScan(basePackages = "com.apedad.example.dao")
//@Configuration
//public class DynamicDataSourceConfiguration {
//
//    @Bean
//    @ConfigurationProperties(prefix = "multiple.datasource.master")//此处的"multiple.datasource.master"需要你在application.properties中配置，详细信息看下面贴出的application.properties文件。
//    public DataSource dbMaster() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "multiple.datasource.slave1")
//    public DataSource dbSlave1() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//
//    }
//
//    /**
//     * 核心动态数据源
//     *
//     * @return 数据源实例
//     */
//    @Bean
//    public DataSource dynamicDataSource() {
//        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
//        dataSource.setDefaultTargetDataSource(dbMaster());
//        Map<Object, Object> dataSourceMap = new HashMap<>(4);
//        dataSourceMap.put(DataSourceKey.MASTER, dbMaster());
//        dataSourceMap.put(DataSourceKey.SLAVE1, dbSlave1());
//        dataSource.setTargetDataSources(dataSourceMap);
//        return dataSource;
//    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
//        //此处设置为了解决找不到mapper文件的问题
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
//        return sqlSessionFactoryBean.getObject();
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory());
//    }
//
//    /**
//     * 事务管理
//     *
//     * @return 事务管理实例
//     */
//    @Bean
//    public PlatformTransactionManager platformTransactionManager() {
//        return new DataSourceTransactionManager(dynamicDataSource());
//    }
//}