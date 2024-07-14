package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.ClienteDAO;
import model.beans.ClienteBean;
public class LoginJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginJSON() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost (request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		ClienteBean cliente = new ClienteBean();
		cliente.setEmail(email);
		ClienteDAO cdao = new ClienteDAO();
		try {
			JSONObject temp = new JSONObject();
			temp.put("status", cdao.isRegistrato(cliente));
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();
	        out.print(temp);
	        out.flush();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
