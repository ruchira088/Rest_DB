package queries;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.StringJoiner;

@WebServlet("/database")
public class Query extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest p_request, HttpServletResponse p_response) throws ServletException, IOException
    {
        Map<String, String[]> parameterMap = p_request.getParameterMap();
        PrintWriter printWriter = p_response.getWriter();
        printWriter.print(showParameters(parameterMap));
        printWriter.flush();
    }

    private String showParameters(Map<String, String[]> p_parameterMap)
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (String key: p_parameterMap.keySet())
        {
            String[] values = p_parameterMap.get(key);

            stringBuilder.append(key + " : " + Arrays.deepToString(values) + "\n");
        }

        return stringBuilder.toString();
    }

}
