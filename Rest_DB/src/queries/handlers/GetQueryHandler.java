package queries.handlers;

import java.util.HashSet;
import java.util.Set;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import general.Constants;
import queries.types.GetQuery;

public class GetQueryHandler extends QueryHandler<GetQuery>
{
	@Override
	protected Set<DBObject> execute(DBCollection p_dbCollection)
	{
		DBCursor dbCursor = p_dbCollection.find(getQuery().getQueryValues());
    	
    	HashSet<DBObject> results = new HashSet<DBObject>();
    	
    	while(dbCursor.hasNext())
    	{
    		DBObject dbObject = dbCursor.next();
    		dbObject.removeField(Constants.ID_KEY);
    		results.add(dbObject);
    	}
    	
    	return results;
	}
}
