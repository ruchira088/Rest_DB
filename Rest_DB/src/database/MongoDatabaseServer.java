package database;

import java.net.UnknownHostException;
import java.util.HashSet;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import queries.Query;

public class MongoDatabaseServer
{
	private MongoClient m_mongoClient = null;
	
	/** Nullary Constructor */
	public MongoDatabaseServer()
	{
	}
	
	/**
	 * Constructor
	 * 
	 * @param p_hostname
	 * 	Hostname of the database
	 * @param p_port
	 * 	Port number of the database
	 */
	public MongoDatabaseServer(String p_hostname, int p_port)
	{
		m_mongoClient =  connectToMongoClient(p_hostname, p_port);
	}
	
	/**
	 * Gets the {@link MongoClient}.
	 * 
	 * @return
	 * 	The {@link MongoClient}
	 */
    private MongoClient getMongoClient()
    {
    	if(m_mongoClient == null)
    	{
    		m_mongoClient = connectToMongoClient(Constants.HOSTNAME, Constants.PORT);
    	}

        return m_mongoClient;
    }
    
    /**
     * Connects to the {@link MongoClient}.
     * 
     * @param p_hostname
	 * 	Hostname of the MongoDB server
	 * @param p_port
	 * 	Port number of the database
	 * 
     * @return
     * 	The {@link MongoClient}
     */
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
    
    /**
     * Closes the {@link MongoClient}.
     */
    public void close()
    {
    	if(m_mongoClient != null)
    	{
    		m_mongoClient.close();
    	}
    }
    
    /**
     * Fetches a {@link DB}.
     * 
     * @param p_dbName
     * 	The name of the database
     * 
     * @return
     * 	The {@code DB}
     */
    private DB getDB(String p_dbName)
    {
    	return getMongoClient().getDB(p_dbName);
    }
    
    /**
     * Fetches a {@link DBCollection}.
     * 
     * @param p_dbName
     * 	The name of the database
     * @param p_collectionName
     * 	The name of the collection
     * 
     * @return
     * 	The {@link DBCollection}
     */
    private DBCollection getDBCollection(String p_dbName, String p_collectionName)
    {
    	return getDB(p_dbName).getCollection(p_collectionName);
    }
    
//    public HashSet<DBObject> doQuery(String p_dbName, String p_collectionName, String p_field, String p_value)
//    {
//    	HashMap<String,String> hashMap = new HashMap<String, String>(1);
//    	hashMap.put(p_field, p_value);
//    	
//    	return doQuery(p_dbName, p_collectionName, hashMap);
//    }
//    
//    public HashSet<DBObject> doQuery(String p_dbName, String p_collectionName, Map<?, ?> p_query)
//    {
//    	DBCollection dbCollection = getDBCollection(p_dbName, p_collectionName);
//    	DBCursor dbCursor = dbCollection.find(new BasicDBObject(p_query));
//    	
//    	HashSet<DBObject> results = new HashSet<DBObject>();
//    	
//    	while(dbCursor.hasNext())
//    	{
//    		DBObject dbObject = dbCursor.next();
//    		dbObject.removeField(Constants.ID_FIELD);
//    		results.add(dbObject);
//    	}
//    	
//    	return results;
//    }
    
    public HashSet<DBObject> doQuery(Query p_query)
    {
    	DBCollection dbCollection = getDBCollection(p_query.getDatabaseName(), p_query.getCollectionName());
    	DBCursor dbCursor = dbCollection.find(p_query.getQueryValues());
    	
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
