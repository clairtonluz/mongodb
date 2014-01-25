package br.com.bytecom;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class ImportTweets {
	public static void main(String[] args) throws UnknownHostException {
		final String screenName = args.length > 0 ? args[0] : "ClairtonLuz";
		
		List<DBObject> tweets = getLatestTweets(screenName);
		
		MongoClient client = new MongoClient();
		DBCollection tweetsCollection = client.getDB("course").getCollection("tweeter");
		
		for (DBObject cur : tweets){
			cur.put("screen_name", screenName);
			massageTweetId(cur);
			tweetsCollection.update(new BasicDBObject("_id" , cur.get("_id")), cur, true, false);
		}
		 
		System.out.println("Tweet count: " + tweetsCollection.count());
		
		client.close();
	}

	private static void massageTweetId(DBObject cur) {
		Object id = cur.get("id");
		cur.removeField("id");
		cur.put("_id", id);
	}

	private static List<DBObject> getLatestTweets(String screenName) {

		try {
			URL url = new URL("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=" + screenName + "$include_rts=1");
			
			InputStream is = url.openStream();
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			
			int retVal;
			while((retVal = is.read()) != -1){
				os.write(retVal);
			}
			final String tweetsString = os.toString();
			
			return (List<DBObject>) JSON.parse(tweetsString);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
