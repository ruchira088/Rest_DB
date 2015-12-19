package queries.types;

import javax.servlet.http.HttpServletRequest;

import queries.HttpMethod;

public class PostQuery extends DataQuery
{
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
