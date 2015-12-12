package database;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MongoDatabase
{
	private MongoClient m_mongoClient = null;
	
	public MongoDatabase()
	{
		
	}
	
	public MongoDatabase(String p_hostname, int p_port)
	{
		m_mongoClient =  connectToMongoClient(p_hostname, p_port);
	}
	
    private MongoClient getMongoClient()
    {
    	if(m_mongoClient == null)
    	{
    		m_mongoClient = connectToMongoClient(Constants.HOSTNAME, Constants.PORT);
    	}

        return m_mongoClient;
    }
    
    private MongoClient connectToMongoClient(String p_hostname, int p_port)
    {
    	MongoClient mongoClient = null;
    	
    	try
		{
    		mongoClient =  new MongoClient(p_hostname, p_port);
		}
		catch (UnknownHostException unknownHostException)
		{
			unknownHostException.printStackTrace();
		}
    	
    	return mongoClient;
    }
    
    private DB getDB(String p_dbName)
    {
    	return getMongoClient().getDB(p_dbName);
    }
    
    private DBCollection getDBCollection(String p_dbName, String p_collectionName)
    {
    	return getDB(p_dbName).getCollection(p_collectionName);
    }
    
    public HashSet<DBObject> doQuery(String p_dbName, String p_collectionName, String p_field, String p_value)
    {
    	HashMap<String,String> hashMap = new HashMap<String, String>(1);
    	hashMap.put(p_field, p_value);
    	
    	return doQuery(p_dbName, p_collectionName, hashMap);
    }
    
    public HashSet<DBObject> doQuery(String p_dbName, String p_collectionName, Map<?, ?> p_query)
    {
    	DBCollection dbCollection = getDBCollection(p_dbName, p_collectionName);
    	DBCursor dbCursor = dbCollection.find(new BasicDBObject(p_query));
    	
    	HashSet<DBObject> results = new HashSet<DBObject>();
    	
    	while(dbCursor.hasNext())
    	{
    		DBObject dbObject = dbCursor.next();
    		dbObject.removeField(Constants.ID_FIELD);
    		results.add(dbObject);
    	}
    	
    	return results;
    }
}
