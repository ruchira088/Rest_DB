package queries;

import queries.handlers.GetQueryHandler;
import queries.handlers.PostQueryHandler;
import queries.handlers.PutQueryHandler;
import queries.handlers.QueryHandler;

public enum HttpMethod 
{
	GET(GetQueryHandler.class), POST(PostQueryHandler.class), PUT(PutQueryHandler.class);

	private Class<? extends QueryHandler<?>> m_queryHandler;

	private HttpMethod(Class<? extends QueryHandler<?>> p_queryHandler) 
	{
		m_queryHandler = p_queryHandler;
	}

	public Class<? extends QueryHandler<?>> getQueryHandler() 
	{
		return m_queryHandler;
	}
}
