package queries.types;

import java.io.ByteArrayOutputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

import general.DBUtils;

public abstract class DataQuery extends Query
{
	private BasicDBObject m_parameterMapValues = null;
	
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
	
	private void init(HttpServletRequest p_request)
	{
		m_parameterMapValues = DBUtils.createBasicDBObject(p_request.getParameterMap());
	}
	
	public BasicDBObject getParameterMapValues()
	{
		return m_parameterMapValues;
	}
}
