package queries.types;

import javax.servlet.http.HttpServletRequest;

import queries.HttpMethod;

public class PatchQuery extends UpdateQuery 
{
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
