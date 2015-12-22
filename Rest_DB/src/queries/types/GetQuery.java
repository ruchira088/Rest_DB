package queries.types;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mongodb.BasicDBObject;

import general.DBUtils;
import queries.HttpMethod;

/**
 * Represents a GET query.
 *
 */
public class GetQuery extends Query
{
	/**
	 * Constructor
	 * 
	 * @param p_request
	 * 	The {@link HttpServletRequest}
	 */
	public GetQuery(HttpServletRequest p_request)
	{
		super(p_request);
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
	public GetQuery(String p_dbName, String p_collectionName, BasicDBObject p_queryValues)
	{
		super(p_dbName, p_collectionName, p_queryValues);
	}
	
	protected BasicDBObject createBasicDBObject(HttpServletRequest p_request) 
	{	
		Map<String, String[]> parameterMap = p_request.getParameterMap();

		return DBUtils.createBasicDBObject(parameterMap);
	}
	
	@Override
	public HttpMethod getHttpMethod()
	{
		return HttpMethod.GET;
	}

}
