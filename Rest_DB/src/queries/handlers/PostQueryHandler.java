package queries.handlers;

import java.util.Set;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import queries.types.PostQuery;

/**
 *	Handler for POST requests.
 *
 */
public class PostQueryHandler extends DataQueryHandler<PostQuery>
{
	@Override
	protected Set<DBObject> execute(DBCollection p_dbCollection)
	{
		p_dbCollection.insert(getQuery().getQueryValues());
		
		return createSuccessResult();
	}
}
