package queries;

import queries.handlers.GetQueryHandler;
import queries.handlers.PatchQueryHandler;
import queries.handlers.PostQueryHandler;
import queries.handlers.PutQueryHandler;
import queries.handlers.QueryHandler;

/**
 * Enums representing HTTP methods.
 *
 */
public enum HttpMethod 
{
	GET(GetQueryHandler.class), POST(PostQueryHandler.class), 
	PUT(PutQueryHandler.class), PATCH(PatchQueryHandler.class);

	/** The query handler */
	private Class<? extends QueryHandler<?>> m_queryHandler;

	/**
	 * Constructor
	 * 
	 * @param p_queryHandler
	 * 	The query handler class
	 */
	private HttpMethod(Class<? extends QueryHandler<?>> p_queryHandler) 
	{
		m_queryHandler = p_queryHandler;
	}

	/**
	 * Gets the query handler.
	 * 
	 * @return
	 * 	The query handler.
	 */
	public Class<? extends QueryHandler<?>> getQueryHandler() 
	{
		return m_queryHandler;
	}
}
