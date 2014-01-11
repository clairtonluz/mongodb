package br.com.bytecom;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 *
 * @author clairton
 */
public class HelloWordSparkStyle {
    public static void main(String args[]){
          Spark.get(new Route("/") {
              
              @Override
              public Object handle(Request rqst, Response rspns) {
                  return "Hello world from Spark";
              }
          });
    }

}
