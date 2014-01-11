package br.com.bytecom;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
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
public class HelloWorldMongoDBSparkFreemakerStyle {

    public static void main(String args[]) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemakerStyle.class, "/");

        Spark.get(new Route("/") {

            @Override
            public Object handle(Request rqst, Response rspns) {

                StringWriter writer = new StringWriter();
                try {
                    MongoClient client = new MongoClient("localhost", 27017);

                    DB database = client.getDB("bytecom");
                    DBCollection collection = database.getCollection("client");

                    DBObject document = collection.findOne();

                    Template helloTemplate = configuration.getTemplate("hello.ftl");

                    helloTemplate.process(document, writer);

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
