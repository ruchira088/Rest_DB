package queries.types;

import javax.servlet.http.HttpServletRequest;

import queries.HttpMethod;

/**
 * Represents a PUT query.
 *
 */
public class PutQuery extends UpdateQuery 
{
	/**
	 * Constructor
	 * 
	 * @param p_request
	 * 	The {@link HttpServletRequest}
	 */
	public PutQuery(HttpServletRequest p_request) 
	{
		super(p_request);
	}

	@Override
	public HttpMethod getHttpMethod() 
	{
		return HttpMethod.PUT;
	}

}
