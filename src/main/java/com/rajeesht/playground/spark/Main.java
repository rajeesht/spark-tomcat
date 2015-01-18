/**
 * Created by rtripathi on 1/17/15.
 */
package com.rajeesht.playground.spark;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.servlet.SparkApplication;

import java.util.HashMap;

import static spark.Spark.*;

public class Main implements SparkApplication {
    public static void main(String[] args) {
        System.out.println("Starting spark");

        Main service = new Main();
        service.init();
    }

    public void init() {
        port(8081);

        get("/hello", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "hello world";
            }
        });

        post("/login", "application/json", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                Gson gson = new Gson();
                HashMap<String, String> status = new HashMap<String, String>();
                User user = gson.fromJson(request.body(), User.class);
                if (user.getUsername() == null || user.getUsername().isEmpty()) {
                    status.put("status", "NoUsername");
                    return gson.toJson(status);
                }

                if (user.getPassword() == null || user.getPassword().isEmpty()) {
                    status.put("status", "NoPassword");
                    return gson.toJson(status);
                }
                status.put("status", "Ok");
                return gson.toJson(status);
            }
        });
    }

    private class User {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
