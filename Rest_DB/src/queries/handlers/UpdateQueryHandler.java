package queries.handlers;

import java.util.Set;

import com.mongodb.BasicDBObject;

import queries.types.UpdateQuery;

public abstract class UpdateQueryHandler<T extends UpdateQuery> extends DataQueryHandler<T>
{
	private BasicDBObject m_selectorObject = null;

	private void init() 
	{
		m_selectorObject = new BasicDBObject();
		
		Set<String> updateSelectors = getQuery().getUpdateSelectors();
		BasicDBObject queryValues = getQuery().getQueryValues();
		
		for(String key: updateSelectors)
		{
			m_selectorObject.put(key, queryValues.get(key));
		}
	}
	
	protected BasicDBObject getSelectorObject()
	{
		if(m_selectorObject == null)
		{
			init();
		}
		
		return m_selectorObject;
	}
}
