/**
 * Created by rtripathi on 1/17/15.
 */
package com.rajeesht.playground.spark;

import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting spark");

        Main service = new Main();
        service.start();
    }

    private void start () {
        port(8081);
        get("/hello", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "hello world";
            }
        });
    }
}
