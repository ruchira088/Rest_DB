package queries.handlers;

import java.util.Set;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import queries.types.PutQuery;

/**
 * Handler for PUT requests.
 * 
 */
public class PutQueryHandler extends UpdateQueryHandler<PutQuery>
{
	@Override
	protected Set<DBObject> execute(DBCollection p_dbCollection) 
	{
		p_dbCollection.update(getSelectorObject(), getQuery().getQueryValues());
		
		return createSuccessResult(getQuery().getQueryValues());
	}
}
