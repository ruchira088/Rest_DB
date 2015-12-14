package queries;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.mongodb.DBObject;

import database.MongoDatabaseServer;

@WebServlet("/database/*")
@MultipartConfig
public class DatabaseRequest extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest p_request, HttpServletResponse p_response)
			throws ServletException, IOException 
	{
		Query query = new Query(p_request);
		
		MongoDatabaseServer mongoDatabase = new MongoDatabaseServer();
		HashSet<DBObject> results = mongoDatabase.doQuery(query);
		
		p_response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		p_response.setContentType(MediaType.APPLICATION_JSON);
		
		PrintWriter printWriter = p_response.getWriter();
		printWriter.println(results);
		printWriter.flush();
		
		mongoDatabase.close();
	}

	@Override
	protected void doPost(HttpServletRequest p_request, HttpServletResponse p_response) throws ServletException, IOException 
	{
		ServletInputStream inputStream = p_request.getInputStream();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		int copy = IOUtils.copy(inputStream, outputStream);
		
		
		System.out.println(outputStream.toString());
		
		Query query = new Query(p_request);
		
		System.out.println(query.getDatabaseName());
		System.out.println(query.getCollectionName());
		System.out.println(query.getQueryValues());
		
	}



	/**
	 * Creates a JSON parameter map.
	 * 
	 * @param p_parameterMap
	 * 	The parameter map
	 * 
	 * @return
	 * 	JSON parameter map
	 */
	private Map<String, ?> createJsonParameterMap(Map<String, String[]> p_parameterMap) 
	{		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		for (String key: p_parameterMap.keySet())
		{
			String[] values = p_parameterMap.get(key);
			
			if(values.length > 1)
			{
				hashMap.put(key, values);
			} 
			else
			{
				hashMap.put(key, values[0]);
			}
		}
		
		return hashMap;
	}

}