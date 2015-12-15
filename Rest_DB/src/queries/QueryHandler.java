package queries;

import java.lang.reflect.Constructor;
import java.util.Map;

import database.operations.DBOperation;

public abstract class QueryHandler implements DBOperation
{
	public static Map<HttpMethod, Class<? extends QueryHandler>> s_queryMappings;
	
	static
	{
		s_queryMappings.put(HttpMethod.GET, GetQueryHandler.class);
	}
	
	public static <T extends Query, H extends QueryHandler> QueryHandler getQueryHandler(T p_query)
	{
		H queryHandler = null;
		
		Class<? extends QueryHandler> queryHandlerClass = s_queryMappings.get(p_query.getHttpMethod());
		
		try
		{
			Constructor<? extends QueryHandler> constructor = queryHandlerClass.getConstructor(p_query.getClass());
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
