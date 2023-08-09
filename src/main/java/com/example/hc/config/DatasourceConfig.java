package com.example.hc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Vyacheslav Savinov
 * @since 09.02.2023
 */

@Configuration
public class DatasourceConfig {

    @Primary
    @Bean(name = "intDataSource")
    @ConfigurationProperties(prefix = "int.datasource")
    public DataSource integrationDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mainDataSource")
    @ConfigurationProperties(prefix = "main.datasource")
    public DataSource mainDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "intJdbcTemplate")
    public JdbcTemplate integrationJdbcTemplate(@Qualifier("intDataSource") DataSource intDataSource) {
        return new JdbcTemplate(intDataSource);
    }

    @Bean(name = "mainJdbcTemplate")
    public JdbcTemplate mainJdbcTemplate(@Qualifier("mainDataSource") DataSource mainDataSource) {
        return new JdbcTemplate(mainDataSource);
    }
}
