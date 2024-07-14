package control;

import model.ReclamoDAO;
import model.beans.ClienteBean;
import model.beans.ReclamoBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import model.FormSanifier;



public class Reclamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FormSanifier fs = new FormSanifier();
		ClienteBean cliente = (ClienteBean) request.getSession(false).getAttribute("cliente");
		String titolo = fs.sanitizeString(request.getParameter("titolo"));
		String descrizione = fs.sanitizeString(request.getParameter("descrizione"));
		ReclamoDAO ReclamoDAO = new ReclamoDAO();
		ReclamoBean reclamo = new ReclamoBean();
		reclamo.setEmailCliente(cliente.getEmail());
		reclamo.settitolo(titolo);
		reclamo.setcontenuto(descrizione);
		try {
			ReclamoDAO.inseriscireclamo(reclamo);
		} catch (SQLException e) {
			e.printStackTrace();
		}


		boolean reclamoToken = true;
		request.setAttribute("reclamoFlag", reclamoToken);
	    response.sendRedirect("account.jsp");
	
	}
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    }
}