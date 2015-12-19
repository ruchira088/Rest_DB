package queries.handlers;

import java.util.HashSet;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import queries.types.DataQuery;

public abstract class DataQueryHandler<T extends DataQuery> extends QueryHandler<T> 
{
	protected Set<DBObject> createSuccessResult()
	{
		Set<DBObject> resultSet = new HashSet<DBObject>();
		resultSet.add(new BasicDBObject("result", "success"));
	
		return resultSet;
	}
	

}
