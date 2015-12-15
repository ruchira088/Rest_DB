package queries.handlers;

import java.util.HashSet;
import java.util.Set;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import database.Constants;
import queries.GetQuery;

public class GetQueryHandler extends QueryHandler<GetQuery>
{
	private GetQuery m_getQuery;
	
	public GetQueryHandler(GetQuery p_getQuery)
	{
		m_getQuery = p_getQuery;
	}

	@Override
	protected Set<DBObject> execute(DBCollection p_dbCollection)
	{
		DBCursor dbCursor = p_dbCollection.find(getQuery().getQueryValues());
    	
    	HashSet<DBObject> results = new HashSet<DBObject>();
    	
    	while(dbCursor.hasNext())
    	{
    		DBObject dbObject = dbCursor.next();
    		dbObject.removeField(Constants.ID_FIELD);
    		results.add(dbObject);
    	}
    	
    	return results;
	}

	@Override
	protected GetQuery getQuery()
	{
		return m_getQuery;
	}
}
