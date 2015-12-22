package database;

import java.util.HashMap;

import org.junit.Test;

import com.mongodb.util.JSON;

public class DatabaseTest
{
    @Test
    public void query()
    {
/*        MongoClient mongoClient = null;

        try
        {
            mongoClient = new MongoClient(Constants.HOSTNAME, Constants.PORT);
        }
        catch (UnknownHostException p_unknownHostException)
        {
            Assert.fail(p_unknownHostException.getMessage());
        }

        DB db = mongoClient.getDB("CardGame");
        DBCollection dbCollection = db.getCollection("users");
        DBCursor dbCursor = dbCollection.find();

        System.out.println(dbCursor.next());
        
        dbCursor.close();
        
        BasicDBObject basicDBObject = new BasicDBObject("username", "cat");
        DBCursor cursor = dbCollection.find(basicDBObject);
        
        System.out.println(cursor.next());*/
    	
    	MongoDatabaseServer mongoDatabase = new MongoDatabaseServer();
    	
    	HashMap<String,String> hashMap = new HashMap<String, String>();
    	hashMap.put("username", "jenny");
    	
    	//HashSet<DBObject> results = mongoDatabase.doQuery("CardGame", "users", "username", "jenny");
//    	HashSet<DBObject> results = mongoDatabase.doQuery(new GetQuery("CardGame", "users", new BasicDBObject(hashMap)));
//    	
//    	System.out.println(results.toString());
    }
    
    @Test
    public void JsonTest()
    {
//    	HashMap<String,String[]> hashMap = new HashMap<String, String[]>();
//    	hashMap.put("colour", new String[]{"white", "ginger"});
//    	hashMap.put("name", new String[]{"Jenny"});
//    	
//    	JSONObject jsonObject = new JSONObject(hashMap);
//    	System.out.println(jsonObject.toString());
//    	System.out.println(StandardCharsets.UTF_8.name());
    	String JsonString = "{\"colour\":[\"white\",\"ginger\"],\"name\":\"Jenny\"}";
    	
    	Object object = JSON.parse(JsonString);
    	System.out.println(object.getClass());
    	System.out.println(object);
    	
    	JsonString = "{\"name\":\"Jenny\"}";
    	
    	object = JSON.parse(JsonString);
    	System.out.println(object.getClass());
    	System.out.println(object);
    	
    }
    
    
}
