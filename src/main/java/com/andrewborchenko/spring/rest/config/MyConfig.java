package com.andrewborchenko.spring.rest.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/*аналогия application context*/
@Configuration
/*аналогия  <context:component-scan base-package*/
@ComponentScan(basePackages = "com.andrewborchenko.spring.rest")
/*аналогия  <mvc:annotation-driven/>*/
@EnableWebMvc
/*аналогия <tx:annotation-driven transaction-manager="transactionManager" />*/
@EnableTransactionManagement
public class MyConfig {
    /*для связи с БД*/
    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("org.postgresql.Driver");
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/my_db");
            dataSource.setUser("postgres");
            dataSource.setPassword("password");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
    /*для создания бина session factory*/

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.andrewborchenko.spring.rest.entity");
        Properties hibernateProp = new Properties();
        hibernateProp.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        hibernateProp.setProperty("hibernate.show_sql", "true");

        sessionFactory.setHibernateProperties(hibernateProp);
        return sessionFactory;
    }

    /*для закрытия/открытия транзакций*/
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
