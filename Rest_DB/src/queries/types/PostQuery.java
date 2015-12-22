package queries.types;

import javax.servlet.http.HttpServletRequest;

import queries.HttpMethod;

/**
 * Represents a POST query.
 *
 */
public class PostQuery extends DataQuery
{
	/**
	 * Constructor
	 * 
	 * @param p_request
	 * 	The {@link HttpServletRequest}
	 */
	public PostQuery(HttpServletRequest p_request)
	{
		super(p_request);
	}

	@Override
	public HttpMethod getHttpMethod()
	{
		return HttpMethod.POST;
	}

}
