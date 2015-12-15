package queries;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mongodb.BasicDBObject;

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
		
		BasicDBObject basicDBObject = new BasicDBObject();
		
		for (String key: parameterMap.keySet())
		{
			String[] values = parameterMap.get(key);
			
			if(values.length > 1)
			{
				basicDBObject.put(key, values);
			} 
			else
			{
				basicDBObject.put(key, values[0]);
			}
		}

		return basicDBObject;
	}

	@Override
	public HttpMethod getHttpMethod()
	{
		return HttpMethod.GET;
	}

}
