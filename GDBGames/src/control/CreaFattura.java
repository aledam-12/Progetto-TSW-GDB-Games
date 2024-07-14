package control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ClienteDAO;
import model.Fattura;
import model.beans.ClienteBean;

public class CreaFattura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreaFattura() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int timeout = 2500;
		String RedPage = "/pdf/fattura "+id+".pdf";
		ClienteBean cliente = (ClienteBean) request.getSession(false).getAttribute("cliente");
		ClienteDAO cdao = new ClienteDAO();
		Fattura fattura = new Fattura();
		try {
		if (!cliente.getStato().equals("admin")) {
			if (!cdao.controlloFattura(cliente, id)) response.setStatus(HttpServletResponse.SC_FORBIDDEN);}
		if (fattura.creaFattura(id, cliente)) {
			request.setAttribute("feedback", true);
			Thread.sleep(timeout);
			if (cliente.getStato().equals("admin")) RedPage = "/admin/dettagliOrdine?id="+id;
			else RedPage = "/utenteLoggato/dettagliOrdine?id="+id;} 
		}
		catch (Exception e) {
			e.printStackTrace();}
        RequestDispatcher rd = request.getServletContext().getRequestDispatcher(RedPage);
        rd.forward(request, response);
	}
}
