package general;

import java.util.Map;

import com.mongodb.BasicDBObject;

public class DBUtils 
{
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
