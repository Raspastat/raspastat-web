package com.shepherdjerred.raspastat.web;

import com.shepherdjerred.raspastat.web.api.Status;
import com.shepherdjerred.raspastat.web.util.redis.JedisWrapper;
import com.shepherdjerred.raspastat.web.util.template.ThymeleafTemplateEngine;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static spark.Spark.get;
import static spark.Spark.*;

public class Main {

    public static JedisWrapper jedis;

    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/assets");
        jedis = new JedisWrapper();

        get("/", (request, response) -> {
            Map<String, String> model = new HashMap<>();
            return new ModelAndView(model, "index");
        }, new ThymeleafTemplateEngine());

        post("/api/set", (request, response) -> {
            JSONObject requestJson = (JSONObject) new JSONParser().parse(request.body());
            UUID requestUuid = UUID.randomUUID();
            jedis.getJedis().set("api#" + requestUuid.toString() + ":operation", "set");
            jedis.getJedis().set("api#" + requestUuid.toString() + ":data", "70");
            jedis.getJedis().publish("api", requestUuid.toString());

            JSONObject responseJson = new JSONObject();
            responseJson.put("success", true);
            responseJson.put("operation", requestJson.get("operation"));
            responseJson.put("value", requestJson.get("value"));
            response.type("application/json");
            return responseJson.toJSONString();
        });

        get("/api/status", (request, response) -> {
            response.type("application/json");
            return new Status().getStatus().toJSONString();
        });
    }
}