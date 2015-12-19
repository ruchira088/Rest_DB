package queries.handlers;

import java.util.Set;

import com.mongodb.BasicDBObject;

import queries.types.UpdateQuery;

public abstract class UpdateQueryHandler<T extends UpdateQuery> extends DataQueryHandler<T>
{
	private BasicDBObject m_selectorObject;
	
	public UpdateQueryHandler()
	{
		init();
	}

	private void init() 
	{
		Set<String> updateSelectors = getQuery().getUpdateSelectors();
		BasicDBObject queryValues = getQuery().getQueryValues();
		
		for(String key: updateSelectors)
		{
			m_selectorObject.put(key, queryValues.get(key));
		}
	}
	
	protected BasicDBObject getSelectorObject()
	{
		return m_selectorObject;
	}
}
