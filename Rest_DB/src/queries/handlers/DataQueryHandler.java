package queries.handlers;

import java.util.HashSet;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import queries.types.DataQuery;

/**
 * The abstract parent class for query handlers where respective queries contain data in the body
 * of the request.
 * 
 */
public abstract class DataQueryHandler<T extends DataQuery> extends QueryHandler<T> 
{
	/**
	 * Creates a successful result.
	 * 
	 * @return
	 * 	A {@link Set} containing a single {@link DBObject} which represents a SUCCESS
	 */
	protected Set<DBObject> createSuccessResult()
	{
		Set<DBObject> resultSet = new HashSet<DBObject>();
		BasicDBObject result = new BasicDBObject("result", "success");
		resultSet.add(result);
	
		return resultSet;
	}
}
