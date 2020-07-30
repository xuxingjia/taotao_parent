package com.study.demo.taotao_common.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

@Component
public class RedisPoolUtils implements JedisInterface {


    @Autowired
    private Jedis jedis;

    @Override
    public long setnx(String key, String value) {
        try {
            return jedis.setnx(key, value);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            jedis.close();
        }
    }

    @Override
    public String getrange(String key, int start, int end) {
        try {
            return jedis.getrange(key, start, end);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            jedis.close();
        }
    }

    @Override
    public String mSet(String... value) {
        try {
            return jedis.mset(value);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            jedis.close();
        }
    }

    @Override
    public String setex(String key, int time, String value) {
        try {
            return jedis.setex(key, time, value);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            jedis.close();
        }
    }

    @Override
    public String set(String key, String value) {
        try {
            return jedis.set(key, value);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            jedis.close();
        }
    }

    @Override
    public String get(String key) {
        try {
            return jedis.get(key);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            jedis.close();
        }
    }

    @Override
    public long pTtl(String key) {
        try {
            return jedis.pttl(key);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            jedis.close();
        }
    }

    @Override
    public long strLen(String key) {
        try {
            return jedis.strlen(key);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            jedis.close();
        }
    }

    @Override
    public String hmset(String group_key, Map<String, String> value) {
        try {
            return jedis.hmset(group_key, value);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            jedis.close();
        }
    }

    @Override
    public List<String> hmget(String group_key, String... key) {
        try {
            return jedis.hmget(group_key, key);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            jedis.close();
        }
    }

    @Override
    public long hset(String group_key, String key, String value) {
        try {
            return jedis.hset(group_key, key, value);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            jedis.close();
        }
    }

    @Override
    public Map<String, String> hGetAll(String group_key) {
        try {
            return jedis.hgetAll(group_key);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            jedis.close();
        }
    }

    @Override
    public String hget(String group_key, String key) {
        try {
            return jedis.hget(group_key, key);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            jedis.close();
        }
    }

    @Override
    public boolean hExists(String group_key, String key) {
        try {
            return jedis.hexists(group_key, key);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            jedis.close();
        }
    }

    @Override
    public boolean exists(String key) {
        try {
            return jedis.exists(key);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            jedis.close();
        }
    }

    @Override
    public long del(String key) {
        try {
            return jedis.del(key);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            jedis.close();
        }
    }

    @Override
    public long hDel(String group_key, String key) {
        try {
            return jedis.hdel(group_key, key);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            jedis.close();
        }
    }
}
