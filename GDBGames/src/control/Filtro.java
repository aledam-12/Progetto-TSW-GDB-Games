package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ClienteDAO;
import model.beans.ClienteBean;

public class Filtro implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		ClienteDAO cdao = new ClienteDAO();
		String role = (String) session.getAttribute("auth");
		ClienteBean cliente = (ClienteBean) session.getAttribute("cliente");
		String amministratoreURI = request.getContextPath()+"/admin";
		String userLoggedURI = request.getContextPath()+"/utenteLoggato";
		String fattureURI = request.getContextPath() + "/pdf";
		boolean isLogged = session != null && session.getAttribute("auth") != null;
		if (request.getRequestURI().startsWith(amministratoreURI)) {
			if (!isLogged) {response.sendRedirect(request.getContextPath()+ "/Login.jsp"); return;}
			else if (!role.equals("admin")) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN); 	return;}
			else chain.doFilter(req,resp);		//filtro per le risorse amministratore
		}
		
		else if (request.getRequestURI().startsWith(userLoggedURI)) {
			if (!isLogged) {response.sendRedirect(request.getContextPath()+ "/Login.jsp"); return;}
			else chain.doFilter(req,resp);		//filtro per le risorse utente loggato
		}
		
		else if (request.getRequestURI().startsWith(fattureURI)) {
			int id = Integer.parseInt(request.getParameter("id"));		
			if (!isLogged)  {response.sendRedirect(request.getContextPath()+ "/Login.jsp"); return;}	
			else if (!role.equals("admin")) {															
				/*se l'utente è loggato allora i vari attributi della sessione non saranno nulli*/
				try {
					if (!cdao.controlloFattura(cliente, id)) {response.sendError(HttpServletResponse.SC_FORBIDDEN); return;} //l'utente è un cliente a cui non è intestata la fattura con quell'ID
					else response.sendRedirect(request.getContextPath()+ "/creaFattura?id="+id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		else response.sendRedirect(request.getContextPath()+ "/creaFattura?id="+id);
	}
	else chain.doFilter(request, response); //filtro per le risorse accessibili a tutti
  }
}
