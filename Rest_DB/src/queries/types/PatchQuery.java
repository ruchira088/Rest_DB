package queries.types;

import javax.servlet.http.HttpServletRequest;

import queries.HttpMethod;

/**
 * Represents a PATCH query.
 *
 */
public class PatchQuery extends UpdateQuery 
{
	/**
	 * Constructor
	 * 
	 * @param p_request
	 * 	The {@link HttpServletRequest}
	 */
	public PatchQuery(HttpServletRequest p_request) 
	{
		super(p_request);
	}

	@Override
	public HttpMethod getHttpMethod() 
	{
		return HttpMethod.PATCH;
	}

}
