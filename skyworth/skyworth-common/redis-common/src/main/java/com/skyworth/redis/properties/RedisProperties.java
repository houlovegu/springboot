package com.skyworth.redis.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName RedisProperties
 * @Description TODO
 * @Author sky
 * @Date 2023/5/11 16:36
 * @Version 1.0
 **/
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    private int database;

    private String host;

    private String password;

    private int port;

    private long timeout;

    private long shutDownTimeout;

    private int maxIdle;

    private int minIdle;

    private int maxActive;

    private long maxWait;

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public long getShutDownTimeout() {
        return shutDownTimeout;
    }

    public void setShutDownTimeout(long shutDownTimeout) {
        this.shutDownTimeout = shutDownTimeout;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }
}
