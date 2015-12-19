package queries.types;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mongodb.BasicDBObject;

import general.DBUtils;
import queries.HttpMethod;

public class GetQuery extends Query
{
	public GetQuery(HttpServletRequest p_request)
	{
		super(p_request);
	}
	
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
