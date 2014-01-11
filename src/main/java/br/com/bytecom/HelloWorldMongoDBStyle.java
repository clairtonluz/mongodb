package br.com.bytecom;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;

/**
 *
 * @author clairton
 */
public class HelloWorldMongoDBStyle {
    public static void main(String args[]) throws UnknownHostException{
        MongoClient client = new MongoClient("localhost", 27017);
        
        DB database = client.getDB("bytecom");
        DBCollection collection = database.getCollection("client");
        
        DBObject document = collection.findOne();
        
        System.out.println(document);
    }
}
