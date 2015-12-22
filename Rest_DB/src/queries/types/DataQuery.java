package queries.types;

import java.io.ByteArrayOutputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

import general.DBUtils;

/**
 * The abstract parent class for queries which contain data in the body.
 * 
 */
public abstract class DataQuery extends Query
{
	/** The parameter map */
	private BasicDBObject m_parameterMapValues = null;
	
	/**
	 * Constructor
	 * 
	 * @param p_request
	 * 	The {@link HttpServletRequest}
	 */
	public DataQuery(HttpServletRequest p_request)
	{
		super(p_request);
		init(p_request);
	}

	@Override
	protected BasicDBObject createBasicDBObject(HttpServletRequest p_request)
	{
		BasicDBObject basicDBObject = new BasicDBObject();
		String JsonString = null;
		
		try
		{
			ServletInputStream inputStream = p_request.getInputStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			IOUtils.copy(inputStream, outputStream);
			JsonString = outputStream.toString();

			basicDBObject = (BasicDBObject) JSON.parse(JsonString);
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
			System.out.println(JsonString);
			exception.printStackTrace();
		}
		
		return basicDBObject;
	}
	
	/**
	 * Initializes the object.
	 * 
	 * @param p_request
	 * 	The {@link HttpServletRequest}
	 */
	private void init(HttpServletRequest p_request)
	{
		m_parameterMapValues = DBUtils.createBasicDBObject(p_request.getParameterMap());
	}
	
	/**
	 * Gets the parameter map.
	 * 
	 * @return
	 * 	The parameter map
	 */
	public BasicDBObject getParameterMap()
	{
		return m_parameterMapValues;
	}
}
