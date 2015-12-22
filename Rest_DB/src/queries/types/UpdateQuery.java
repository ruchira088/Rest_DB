package queries.types;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import general.Constants;

/**
 * The abstract parent class of queries which update existing documents.
 */
public abstract class UpdateQuery extends DataQuery 
{
	/** The update selectors */
	private Set<String> m_updateSelectors = new HashSet<String>();
	
	/**
	 * Constructor
	 * 
	 * @param p_request
	 * 	The {@link HttpServletRequest}
	 */
	public UpdateQuery(HttpServletRequest p_request) 
	{
		super(p_request);
		init();
	}
	
	/**
	 * Initializes the object.
	 */
	private void init()
	{
		Object object = getParameterMap().get(Constants.SELECTOR_KEY);
		
		if(object != null)
		{
			if(object instanceof String[])
			{
				String[] values = (String[]) object;
				
				for(String value : values)
				{
					m_updateSelectors.add(value);
				}
			} else
			{
				m_updateSelectors.add(object.toString());
			}			
		}
	}
	
	/**
	 * Gets the update selectors
	 * 
	 * @return
	 * 	The update selectors
	 */
	public Set<String> getUpdateSelectors()
	{
		return m_updateSelectors;
	}

}
