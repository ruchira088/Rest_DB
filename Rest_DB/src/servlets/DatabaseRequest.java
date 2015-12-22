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

import general.Constants;
import queries.handlers.QueryHandler;
import queries.types.GetQuery;
import queries.types.PatchQuery;
import queries.types.PostQuery;
import queries.types.PutQuery;
import queries.types.Query;

/**
 * Handles requests from clients.
 *
 */
@WebServlet("/database/*")
public class DatabaseRequest extends HttpServlet 
{
	/** Serial Version UID */
	private static final long serialVersionUID = -5182371201498622413L;

	@Override
	protected void doGet(HttpServletRequest p_request, HttpServletResponse p_response)
			throws ServletException, IOException 
	{
		createResponse(p_response, new GetQuery(p_request));
	}

	@Override
	protected void doPost(HttpServletRequest p_request, HttpServletResponse p_response) throws ServletException, IOException 
	{
		createResponse(p_response, new PostQuery(p_request));
	}
	
	@Override
	protected void doPut(HttpServletRequest p_request, HttpServletResponse p_response) throws ServletException, IOException 
	{
		createResponse(p_response, new PutQuery(p_request));
	}
	
	/**
	 * Handles PATCH requests.
	 * 
	 * @param p_request
	 * 	The {@link HttpServletRequest} object that contains the request the client made of the servlet
	 * @param p_response
	 * 	The {@link HttpServletResponse} object that contains the response the servlet returns to the client
	 * 
	 * @throws ServletException
	 * 	If the request for the PATCH cannot be handled
	 * @throws IOException
	 * 	If an input or output error occurs while the servlet is handling the PATCH request
	 */
	protected void doPatch(HttpServletRequest p_request, HttpServletResponse p_response) throws ServletException, IOException 
	{
		createResponse(p_response, new PatchQuery(p_request));
	}
	
	@Override
	protected void service(HttpServletRequest p_request, HttpServletResponse p_response) throws ServletException, IOException {
		switch (p_request.getMethod()) 
		{
			case Constants.PATCH_METHOD:
				doPatch(p_request, p_response);
				break;
			default:
				super.service(p_request, p_response);
		}
	}

	/**
	 * Creates the response from the query.
	 * 
	 * @param p_response
	 * 	The {@link HttpServletResponse} object that contains the response the servlet returns to the client
	 * @param p_query
	 * 	The {@link Query}
	 * 
	 * @throws IOException
	 * 	If an input or output exception occurs
	 */
	private void createResponse(HttpServletResponse p_response, Query p_query) throws IOException 
	{
		QueryHandler<?> queryHandler = QueryHandler.getQueryHandler(p_query);
		Set<DBObject> results = queryHandler.performQuery();
		
		p_response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		p_response.setContentType(MediaType.APPLICATION_JSON);
		
		PrintWriter printWriter = p_response.getWriter();
		
		if(p_query instanceof GetQuery)
		{
			printWriter.println(formatResults(results));			
		}
		else
		{
			printWriter.println(results.iterator().next());
		}
		
		printWriter.flush();
	}
	
	/**
	 * Formats the results.
	 * 
	 * @param p_results
	 * 	{@link Set} containing the results.
	 * 
	 * @return
	 * 	{@link String} of the formatted results.
	 */
	private String formatResults(Set<DBObject> p_results)
	{
		StringBuilder resultsBuilder = new StringBuilder();
		
		resultsBuilder.append("{");
		resultsBuilder.append("\"results\" : ");
		resultsBuilder.append(p_results);
		resultsBuilder.append(",");
		resultsBuilder.append("\"count\" : ");
		resultsBuilder.append(p_results.size());
		resultsBuilder.append("}");
		
		return resultsBuilder.toString();
		
	}
}
