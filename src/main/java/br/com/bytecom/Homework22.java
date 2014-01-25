package br.com.bytecom;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import freemarker.template.Configuration;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author clairton
 */
public class Homework22 {

    public static void main(String args[]) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemakerStyle.class, "/");

        StringWriter writer = new StringWriter();
        try {
            MongoClient client = new MongoClient("localhost", 27017);

            DB database = client.getDB("students");
            DBCollection collection = database.getCollection("grades");

            DBCursor cur = collection.find(new BasicDBObject("type", "homework")).sort(new BasicDBObject("student_id", 1).append("score", 1));

            int id = -1;
            int i = 0;
            while (cur.hasNext()) {
                DBObject doc = cur.next();
                int idStudent = (Integer) doc.get("student_id");
                if (idStudent != id) {
                    i++;
                    id = idStudent;
                    System.out.println("##############################");
                    System.out.println(doc);
//                    collection.remove(doc);
                   } else {
                    System.out.println(doc);
                }

            }
            System.out.println("Removido " + i);

        } catch (UnknownHostException ex) {
            Logger.getLogger(HelloWorldFreemakerStyle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
