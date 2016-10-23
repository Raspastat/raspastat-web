package com.shepherdjerred.raspastat.web.api;

import org.json.simple.JSONObject;

import static com.shepherdjerred.raspastat.web.Main.jedis;

public class Status {

    public JSONObject getStatus() {
        JSONObject json = new JSONObject();
        json.put("target", Double.valueOf(jedis.getJedis().get("status:target")));
        json.put("current", Float.valueOf(jedis.getJedis().get("status:current")));
        json.put("hudmitity", Float.valueOf(jedis.getJedis().get("status:humidity")));
        json.put("mode", jedis.getJedis().get("status:mode"));
        json.put("enabled", Boolean.valueOf(jedis.getJedis().get("status:enabled")));
        json.put("tolerance", Double.valueOf(jedis.getJedis().get("status:tolerance")));
        json.put("period", Long.valueOf(jedis.getJedis().get("status:period")));
        json.put("delay", Long.valueOf(jedis.getJedis().get("status:delay")));
        return json;
    }

}
