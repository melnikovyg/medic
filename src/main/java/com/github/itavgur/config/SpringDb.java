package com.github.itavgur.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableJpaRepositories(value = "com.github.itavgur.repository")
@EnableTransactionManagement
public class SpringDb {

    @Configuration
    @Profile("prod")
    @PropertySource("classpath:program.properties")
    static class Prod {
    }

    @Configuration
    @Profile("dev")
    @PropertySource({"classpath:program.dev.properties"})
    static class Dev {
    }

    @Value("${DB_DRIVER}")
    private String dbDriver;
    @Value("${DB_URL}")
    private String dbUrl;
    @Value("${SQL_INIT_SCRIPT}")
    private Resource initDbScript;
    @Value("${SQL_POPULATE_SCRIPT}")
    private Resource populateDbScript;
    @Value("${INIT_DB}")
    private Boolean initDb;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "dataSource")
    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(dbUrl);
        return dataSource;
    }

    @Bean
    public DataSourceInitializer getDataSourceInitializer() {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(getDataSource());
        if (initDb) {
            initializer.setDatabasePopulator(getDatabasePopulator());
        }
        return initializer;
    }

    private DatabasePopulator getDatabasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(initDbScript);
        populator.addScript(populateDbScript);
        return populator;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan("com.github.itavgur.model");
        em.setDataSource(getDataSource());
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        ((HibernateJpaVendorAdapter) vendorAdapter).setShowSql(true);
        em.setJpaVendorAdapter(vendorAdapter);

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        jpaProperties.put("hibernate.show_sql", true);
        jpaProperties.put("hibernate.format_sql", "false");

        em.setJpaProperties(jpaProperties);

        return em;
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager getTransactionManager() {
        JpaTransactionManager jtm = new JpaTransactionManager();
        jtm.setEntityManagerFactory(getEntityManagerFactory().getObject());
        return jtm;
    }


}
