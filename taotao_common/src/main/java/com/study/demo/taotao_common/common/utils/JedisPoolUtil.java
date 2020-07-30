package com.study.demo.taotao_common.common.utils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
@PropertySource(value = "classpath:redis.properties")
public class JedisPoolUtil {

    @Value("${REDIS_HOST}")
    private String host;

    @Value("${REDIS_PORT}")
    private int port;

    @Value("${REDIS_PASSWORD}")
    private String password;

    @Bean
    public Jedis getJedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        poolConfig.setBlockWhenExhausted(true);
        poolConfig.setMaxTotal(GenericObjectPoolConfig.DEFAULT_MAX_TOTAL*8);
        //最大连接数, 默认8个
        poolConfig.setMaxIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE*5);
        poolConfig.setEvictorShutdownTimeoutMillis(6000);
        poolConfig.setMinIdle(GenericObjectPoolConfig. DEFAULT_MIN_IDLE*2);
        poolConfig.setMaxWaitMillis(6000);
        JedisPool jedisPool = new JedisPool(poolConfig, host, port);
        Jedis jedis = jedisPool.getResource();
//        jedis.auth(password);
        return jedis;
    }
}