package br.com.bytecom;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
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
public class HelloWordSparkFreemakerStyle {

    public static void main(String args[]) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemakerStyle.class, "/");

        Spark.get(new Route("/") {

            @Override
            public Object handle(Request rqst, Response rspns) {
                StringWriter writer = new StringWriter();
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");
                    Map<String, Object> helloMap = new HashMap<String, Object>();
                    helloMap.put("name", "Clairton");

                    helloTemplate.process(helloMap, writer);

                } catch (IOException ex) {
                    Logger.getLogger(HelloWorldFreemakerStyle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TemplateException ex) {
                    Logger.getLogger(HelloWorldFreemakerStyle.class.getName()).log(Level.SEVERE, null, ex);
                }
                return writer;
            }
        });

    }
}
