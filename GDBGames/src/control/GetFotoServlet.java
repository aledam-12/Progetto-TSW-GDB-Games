package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetFotoServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
       
    public GetFotoServlet()  
        {
            super();
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
        {
		    String id = (String) request.getParameter("titolo");
		    if (id != null)
		        {
			        byte[] by = ControlloFoto.caricamento(id);
		
			        ServletOutputStream out = response.getOutputStream();
			        if(by != null)
			            {
			            	out.write(by);
			            	response.setContentType("image/jpeg");			
			            }
			        out.close();
		        }
	    }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
            {
                doGet(request, response);
            }
}
