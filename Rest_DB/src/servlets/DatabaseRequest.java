package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import com.mongodb.DBObject;

import queries.handlers.QueryHandler;
import queries.types.GetQuery;
import queries.types.PostQuery;
import queries.types.PutQuery;
import queries.types.Query;

@WebServlet("/database/*")
public class DatabaseRequest extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest p_request, HttpServletResponse p_response)
			throws ServletException, IOException 
	{
		GetQuery getQuery = new GetQuery(p_request);		
		createResponse(p_response, getQuery);
	}

	@Override
	protected void doPost(HttpServletRequest p_request, HttpServletResponse p_response) throws ServletException, IOException 
	{
		PostQuery postQuery = new PostQuery(p_request);		
		createResponse(p_response, postQuery);
	}
	
	@Override
	protected void doPut(HttpServletRequest p_request, HttpServletResponse p_response) throws ServletException, IOException 
	{
		PutQuery putQuery = new PutQuery(p_request);
		
	}
	
	private void createResponse(HttpServletResponse p_response, Query p_query) throws IOException 
	{
		QueryHandler<?> queryHandler = QueryHandler.getQueryHandler(p_query);
		Set<DBObject> results = queryHandler.performQuery();
		
		p_response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		p_response.setContentType(MediaType.APPLICATION_JSON);
		
		PrintWriter printWriter = p_response.getWriter();
		printWriter.println(results);
		printWriter.flush();
	}
}
