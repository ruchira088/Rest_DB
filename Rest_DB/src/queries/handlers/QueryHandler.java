package queries.handlers;

import java.util.HashSet;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import database.MongoDatabaseServer;
import queries.types.Query;

public abstract class QueryHandler<T extends Query>
{	
	private Query m_query = null;
	
	protected abstract Set<DBObject> execute(DBCollection p_dbCollection);
	
	public Set<DBObject> performQuery()
	{
		MongoDatabaseServer mongoDatabase = new MongoDatabaseServer();
		DBCollection dbCollection = mongoDatabase.getDBCollection(getQuery().getDatabaseName(), getQuery().getCollectionName());
		
		Set<DBObject> results = null;
		
		try
		{
			results = execute(dbCollection);
		}
		catch(Exception exception)
		{
			results = createFailResult();
		}
		finally
		{
			mongoDatabase.close();			
		}
		
		return results;
	}

	public static <Q extends Query, H extends QueryHandler<?>> QueryHandler<?> getQueryHandler(Q p_query)
	{		
		H queryHandler = null;
		Class<? extends QueryHandler<?>> queryHandlerClass = p_query.getHttpMethod().getQueryHandler();
		
		try
		{
			queryHandler = (H) queryHandlerClass.newInstance();
			queryHandler.setQuery(p_query);
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		}
		return queryHandler;
	}
	
	protected Set<DBObject> createFailResult()
	{
		Set<DBObject> resultSet = new HashSet<DBObject>();
		resultSet.add(new BasicDBObject("result", "fail"));
	
		return resultSet;
	}

	protected void setQuery(Query p_query)
	{
		m_query = p_query;
	}
	
	protected T getQuery()
	{
		return (T) m_query;
	}
}
