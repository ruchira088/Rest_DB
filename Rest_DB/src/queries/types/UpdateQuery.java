package queries.types;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import general.Constants;

public abstract class UpdateQuery extends DataQuery 
{
	private Set<String> m_updateSelectors = new HashSet<String>();
	
	public UpdateQuery(HttpServletRequest p_request) 
	{
		super(p_request);
		init();
	}
	
	private void init()
	{
		Object object = getParameterMapValues().get(Constants.SELECTOR_KEY);
		
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
	
	public Set<String> getUpdateSelectors()
	{
		return m_updateSelectors;
	}

}
