package queries.handlers;

import java.util.Set;

import com.mongodb.BasicDBObject;

import queries.types.UpdateQuery;

/**
 * The abstract parent class for query handlers where the respective queries update existing documents.
 * 
 */
public abstract class UpdateQueryHandler<T extends UpdateQuery> extends DataQueryHandler<T>
{
	/** The selector object */
	private BasicDBObject m_selectorObject = null;

	/**
	 * Initializes the object.
	 */
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
	
	/**
	 * Gets the selector object.
	 * 
	 * @return
	 * 	The selector object
	 */
	protected BasicDBObject getSelectorObject()
	{
		if(m_selectorObject == null)
		{
			init();
		}
		
		return m_selectorObject;
	}
}
