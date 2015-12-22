package queries.types;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import com.mongodb.BasicDBObject;

import queries.HttpMethod;

/**
 * The abstract parent class for all queries.
 *
 */
public abstract class Query 
{
	/** The delimiter for the path */
	private static final String DELIMITER = "/";
	
	/** The name of the database */
	private String m_dbName = null;
	
	/** The name of the collection */
	private String m_collectionName = null;
	
	/** The query values */
	private BasicDBObject m_queryValues = new BasicDBObject();
		
	/**
	 * Creates a {@link BasicDBObject} object from the request.
	 * 
	 * @param p_request
	 * 	The HTTP servlet request
	 * 
	 * @return
	 * 	{@link BasicDBObject} created from the request.
	 */
	protected abstract BasicDBObject createBasicDBObject(HttpServletRequest p_request);
	
	/**
	 * Gets the {@link HttpMethod} applicable for the query.
	 * 
	 * @return
	 * 	The {@link HttpMethod} applicable for the query
	 */
	public abstract HttpMethod getHttpMethod();
	
	/**
	 * Constructor
	 * 
	 * @param p_request
	 * 	The {@link HttpServletRequest}
	 */
	public Query(HttpServletRequest p_request)
	{
		init(p_request);
	}
	
	/**
	 * Constructor
	 * 
	 * @param p_dbName
	 * 	Name of the database
	 * @param p_collectionName
	 * 	Name of the collection
	 * @param p_queryValues
	 * 	The query values
	 */
	public Query(String p_dbName, String p_collectionName, BasicDBObject p_queryValues)
	{
		m_dbName = p_dbName;
		m_collectionName = p_collectionName;
		m_queryValues = p_queryValues;
	}
	
	/**
	 * Initializes the object.
	 * 
	 * @param p_request
	 * 	The {@link HttpServletRequest}
	 */
	private void init(HttpServletRequest p_request) 
	{
		String pathInfo = p_request.getPathInfo();

		if (pathInfo != null && !pathInfo.isEmpty()) {

			StringTokenizer stringTokenizer = new StringTokenizer(pathInfo, DELIMITER);

			try {
				m_dbName = stringTokenizer.nextToken();
				m_collectionName = stringTokenizer.nextToken();
			} 
			catch (NoSuchElementException noSuchElementException) 
			{
				// It's OK.
			}
		}
		
		m_queryValues = createBasicDBObject(p_request);
	}

	/**
	 * Gets the name of the database.
	 * 
	 * @return
	 * 	Name of the database
	 */
	public String getDatabaseName() 
	{
		return m_dbName;
	}

	/**
	 * Gets the name of the collection.
	 * 
	 * @return
	 * 	Name of the collection
	 */
	public String getCollectionName() 
	{
		return m_collectionName;
	}

	/**
	 * Gets the query values.
	 * 
	 * @return
	 * 	The query values
	 */
	public BasicDBObject getQueryValues() 
	{
		return m_queryValues;
	}
	
}
