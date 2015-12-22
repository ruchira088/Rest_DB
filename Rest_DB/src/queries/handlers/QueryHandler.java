package queries.handlers;

import java.util.HashSet;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import database.MongoDatabaseServer;
import queries.types.Query;

/**
 * The abstract parent class for query handlers.
 *
 * @param <T> The applicable {@link Query} type.
 * 
 */
public abstract class QueryHandler<T extends Query>
{	
	private Query m_query = null;
	
	/**
	 * Executes the query of the sub class handler.
	 * 
	 * @param p_dbCollection
	 * 	The {@link DBCollection}
	 * 
	 * @return
	 * 	The results as a {@link Set} of {@link DBObject}s.
	 */
	protected abstract Set<DBObject> execute(DBCollection p_dbCollection);
	
	/**
	 * Performs the query and returns the results as a {@link Set} of {@link DBObject}s.
	 * 
	 * @return
	 *	The results as a {@link Set} of {@link DBObject}s
	 */
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

	/**
	 * Gets the applicable {@link QueryHandler} for the passed-in {@link Query}.
	 * 
	 * @param p_query
	 * 	The query
	 * 
	 * @return
	 * 	The applicable {@link QueryHandler}
	 */
	public static QueryHandler<?> getQueryHandler(Query p_query)
	{		
		QueryHandler<?> queryHandler = null;
		Class<? extends QueryHandler<?>> queryHandlerClass = p_query.getHttpMethod().getQueryHandler();
		
		try
		{
			queryHandler = queryHandlerClass.newInstance();
			queryHandler.setQuery(p_query);
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		}
		return queryHandler;
	}
	
	/**
	 * Creates a fail result.
	 * 
	 * @return
	 * 	A {@link Set} containing a single {@link DBObject} which represents a FAILURE
	 */
	protected Set<DBObject> createFailResult()
	{
		Set<DBObject> resultSet = new HashSet<DBObject>();
		resultSet.add(new BasicDBObject("result", "fail"));
	
		return resultSet;
	}

	/**
	 * Sets the query.
	 * 
	 * @param p_query
	 * 	The query
	 */
	protected void setQuery(Query p_query)
	{
		m_query = p_query;
	}
	
	/**
	 * Gets the query.
	 * 
	 * @return
	 * 	The query
	 */
	protected T getQuery()
	{
		return (T) m_query;
	}
}
