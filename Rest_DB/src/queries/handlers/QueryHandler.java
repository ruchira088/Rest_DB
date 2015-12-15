package queries.handlers;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import database.MongoDatabaseServer;
import queries.HttpMethod;
import queries.Query;

public abstract class QueryHandler<T extends Query>
{
	public static Map<HttpMethod, Class<? extends QueryHandler<?>>> s_queryMappings = new HashMap<HttpMethod, Class<? extends QueryHandler<?>>>();
	
	static
	{
		s_queryMappings.put(HttpMethod.GET, GetQueryHandler.class);
		s_queryMappings.put(HttpMethod.POST, PostQueryHandler.class);
	}	
	
	protected abstract T getQuery();

	protected abstract Set<DBObject> execute(DBCollection p_dbCollection);
	
	public Set<DBObject> performQuery()
	{
		MongoDatabaseServer mongoDatabase = new MongoDatabaseServer();
		DBCollection dbCollection = mongoDatabase.getDBCollection(getQuery().getDatabaseName(), getQuery().getCollectionName());
		
		Set<DBObject> results = execute(dbCollection);
		mongoDatabase.close();
		
		return results;
	}

	public static <Q extends Query, H extends QueryHandler<?>> QueryHandler<?> getQueryHandler(Q p_query)
	{		
		H queryHandler = null;
		
		Class<? extends QueryHandler<?>> queryHandlerClass = s_queryMappings.get(p_query.getHttpMethod());
		
		try
		{
			Constructor<? extends QueryHandler<?>> constructor = queryHandlerClass.getConstructor(p_query.getClass());
			queryHandler = (H) constructor.newInstance(p_query);
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		}
		return queryHandler;
	}
}
