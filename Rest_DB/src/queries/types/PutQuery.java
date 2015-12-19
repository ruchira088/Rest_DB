package queries.types;

import javax.servlet.http.HttpServletRequest;

import queries.HttpMethod;

public class PutQuery extends UpdateQuery 
{
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
