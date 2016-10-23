package com.shepherdjerred.raspastat.web.util.redis;

import redis.clients.jedis.Jedis;

public class JedisWrapper {

    private Jedis jedis;


    public JedisWrapper() {
        jedis = new Jedis("localhost");
    }

    public Jedis getJedis() {
        return jedis;
    }
}