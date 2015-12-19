package queries.types;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import com.mongodb.BasicDBObject;

import queries.HttpMethod;

public abstract class Query 
{
	private static final String DELIMITER = "/";
	
	private String m_dbName = null;
	
	private String m_collectionName = null;
	
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
	
	public abstract HttpMethod getHttpMethod();
	
	public Query(HttpServletRequest p_request)
	{
		init(p_request);
	}
	
	public Query(String p_dbName, String p_collectionName, BasicDBObject p_queryValues)
	{
		m_dbName = p_dbName;
		m_collectionName = p_collectionName;
		m_queryValues = p_queryValues;
	}
	
	private void init(HttpServletRequest p_request) 
	{
		String pathInfo = p_request.getPathInfo();

		if (pathInfo != null && !pathInfo.isEmpty()) {

			StringTokenizer stringTokenizer = new StringTokenizer(pathInfo, DELIMITER);

			try {
				m_dbName = stringTokenizer.nextToken();
				m_collectionName = stringTokenizer.nextToken();
			} catch (NoSuchElementException noSuchElementException) 
			{
				// It's OK.
			}
		}
		
		m_queryValues = createBasicDBObject(p_request);
	}

	public String getDatabaseName() 
	{
		return m_dbName;
	}

	public String getCollectionName() 
	{
		return m_collectionName;
	}

	public BasicDBObject getQueryValues() 
	{
		return m_queryValues;
	}
	
}
