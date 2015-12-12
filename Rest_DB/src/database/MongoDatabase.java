package database;

import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class MongoDatabase
{
    public static MongoClient getMongoClient()
    {
        MongoClient mongoClient = null;

        try
        {
            mongoClient =  new MongoClient(Constants.HOSTNAME, Constants.PORT);
        }
        catch (UnknownHostException unknownHostException)
        {
            unknownHostException.printStackTrace();
        }

        return mongoClient;
    }
}
