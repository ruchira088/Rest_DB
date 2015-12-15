package database.operations;

import java.util.Set;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public interface DBOperation
{
	public Set<DBObject> performQuery(DBCollection p_dbCollection);
}
