package queries.handlers;

import java.util.HashSet;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import queries.PostQuery;

public class PostQueryHandler extends QueryHandler<PostQuery>
{
	private PostQuery m_postQuery;
	
	public PostQueryHandler(PostQuery p_postQuery)
	{
		m_postQuery = p_postQuery;
	}
	
	@Override
	protected PostQuery getQuery()
	{
		return m_postQuery;
	}

	@Override
	protected Set<DBObject> execute(DBCollection p_dbCollection)
	{
		Set<DBObject> set = new HashSet<DBObject>();
		DBObject result = null;
		
		try
		{
			p_dbCollection.insert(getQuery().getQueryValues());
			result = new BasicDBObject("result", "success");
		}
		catch(Exception exception)
		{
			result = new BasicDBObject("result", "fail");
		}
		
		set.add(result);		
		return set;
	}

}
