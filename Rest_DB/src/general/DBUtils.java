package general;

import java.util.Map;

import com.mongodb.BasicDBObject;

/**
 * Contains general utility functions used through out this project.
 * 
 */
public class DBUtils 
{
	/**
	 * Creates a {@link BasicDBObject} from the passed in {@link Map}.
	 * 
	 * @param parameterMap
	 * 	The parameterMap
	 * @return
	 * 	{@link BasicDBObject} containing all the values from the passed in {@link Map}
	 */
	public static BasicDBObject createBasicDBObject(Map<String, String[]> parameterMap)
	{
		BasicDBObject basicDBObject = new BasicDBObject();
		
		for (String key: parameterMap.keySet())
		{
			String[] values = parameterMap.get(key);
			
			if(values.length > 1)
			{
				basicDBObject.put(key, values);
			} 
			else
			{
				basicDBObject.put(key, values[0]);
			}
		}

		return basicDBObject;
	}
}
