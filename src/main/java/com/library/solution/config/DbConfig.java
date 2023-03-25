package com.library.solution.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
@PropertySource("classpath:hibernate.properties")
public class DbConfig {

    @Value("${hibernate.driver_class}")
    private String driverClass;

    @Value("${hibernate.connection.url}")
    private String url;

    @Value("${hibernate.connection.username}")
    private String userName;

    @Value("${hibernate.connection.password}")
    private String password;

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.show_sql}")
    private String showSql;

    @Value("${hibernate.current_session_context_class}")
    private String currentSessionContextClass;

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getShowSql() {
        return showSql;
    }

    public void setShowSql(String showSql) {
        this.showSql = showSql;
    }

    public String getCurrentSessionContextClass() {
        return currentSessionContextClass;
    }

    public void setCurrentSessionContextClass(String currentSessionContextClass) {
        this.currentSessionContextClass = currentSessionContextClass;
    }
}
