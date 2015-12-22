package queries.handlers;

import java.util.Set;

import org.bson.BSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import queries.types.PatchQuery;

/**
 * Handler for PATCH requests.
 * 
 */
public class PatchQueryHandler extends UpdateQueryHandler<PatchQuery>
{
	@Override
	protected Set<DBObject> execute(DBCollection p_dbCollection) 
	{
		DBObject dbObject = p_dbCollection.find(getSelectorObject()).next();
		BasicDBObject updatedDBObject = new BasicDBObject(dbObject.toMap());
		updatedDBObject.putAll((BSONObject)getQuery().getQueryValues());
		p_dbCollection.update(dbObject, updatedDBObject);
		
		return createSuccessResult();
	}

}
