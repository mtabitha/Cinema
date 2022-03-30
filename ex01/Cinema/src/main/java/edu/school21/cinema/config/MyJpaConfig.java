package edu.school21.cinema.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("edu.school21.cinema.repositories")
@PropertySource("classpath:../application.properties")
public class MyJpaConfig {

    @Value("${db.driverClassName}")
    public String driverClassName;
    @Value("${db.url}")
    public String url;
    @Value("${db.username}")
    public String username;
    @Value("${db.password}")
    public String password;

    @Bean
    DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

//    @Bean
//    DatabasePopulator databasePopulator() {
//        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
//        databasePopulator.addScripts(
////                new ClassPathResource("sql/schema.sql"),
//                new ClassPathResource("sql/data.sql")
//        );
//        return databasePopulator;
//    }
//
//    @Bean
//    DataSourceInitializer dataSourceInitializer() {
//
//        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
//        dataSourceInitializer.setDataSource(dataSource());
//        dataSourceInitializer.setDatabasePopulator(databasePopulator());
//        return dataSourceInitializer;
//    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
            factory.setDataSource(dataSource());
            factory.setPackagesToScan("edu.school21.cinema.model");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            vendorAdapter.setDatabase(Database.POSTGRESQL);
        factory.setJpaVendorAdapter(vendorAdapter);
        Properties properties = new Properties();
            properties.setProperty("hibernate.hbm2ddl.auto","update");
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
            properties.setProperty("hibernate.show_sql", "true");
        factory.setJpaProperties(properties);
        return factory;
    }

    @Bean
    JpaTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

}
