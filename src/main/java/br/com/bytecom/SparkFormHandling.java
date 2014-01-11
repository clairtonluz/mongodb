package br.com.bytecom;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 *
 * @author clairton
 */
public class SparkFormHandling {

    public static void main(String args[]) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");

        Spark.get(new Route("/") {

            @Override
            public Object handle(Request rqst, Response rspns) {

                Map<String, Object> fruitsMap = new HashMap<String, Object>();
                fruitsMap.put("fruits", Arrays.asList("apple", "orange", "banana", "peach"));
                try {
                    Template fruitPickeTemplate = configuration.getTemplate("fruitPicker.ftl");
                    StringWriter writer = new StringWriter();
                    fruitPickeTemplate.process(fruitsMap, writer);
                    return writer;
                } catch (IOException ex) {
                    Logger.getLogger(SparkFormHandling.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TemplateException ex) {
                    Logger.getLogger(SparkFormHandling.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        });

        Spark.post(new Route("/favorite_fruit") {

            @Override
            public Object handle(Request request, Response rspns) {
                final String fruit = request.queryParams("fruit");
                if (fruit == null) {
                    return "Why don't you pick one?";
                } else {
                    return "You favorite fruit is " + fruit;
                }
            }
        });
    }
}
