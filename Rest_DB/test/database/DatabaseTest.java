package database;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import database.Constants;
import org.junit.Assert;
import org.junit.Test;

import java.net.UnknownHostException;

public class DatabaseTest
{
    @Test
    public void something()
    {
        MongoClient mongoClient = null;

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

    }
}
