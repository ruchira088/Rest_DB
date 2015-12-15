package queries;

import javax.servlet.http.HttpServletRequest;

public class PostQuery extends DataQuery
{
	public PostQuery(HttpServletRequest p_request)
	{
		super(p_request);
	}

	@Override
	protected HttpMethod getHttpMethod()
	{
		return HttpMethod.POST;
	}

}