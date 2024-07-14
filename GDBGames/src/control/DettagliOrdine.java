package control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AcquistoDAO;
import model.ClienteDAO;
import model.ProdottiDAO;
import model.beans.AcquistoBean;
import model.beans.ClienteBean;

public class DettagliOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DettagliOrdine() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AcquistoDAO adao = new AcquistoDAO();
		ProdottiDAO pdao = new ProdottiDAO();
		ClienteDAO cdao = new ClienteDAO();
		String id = request.getParameter("id");
		String path = "/admin/visualizzaDettaglio.jsp";
		try {
		AcquistoBean acquisto = adao.leggiDaId(Integer.parseInt(id));
		ClienteBean cliente = (ClienteBean) request.getSession(false).getAttribute("cliente"); //gli ordini sono accessibili solo se si è loggati
		request.setAttribute("Ordine", acquisto);												// la sessione sarà necessariamente già istanziata
		request.setAttribute("DettagliOrdine", 	pdao.leggiDaNFattura(acquisto));
		request.setAttribute("Cliente", cdao.leggiCliente(acquisto.getemailcliente()));
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher(path);
		if (cliente.getStato().equals("user"))
		{
			if (!cliente.getEmail().contentEquals(acquisto.getemailcliente()))
				response.sendError(HttpServletResponse.SC_FORBIDDEN); //per evitare che un cliente acceda a fatture che non gli appartengono
			else rd.forward(request,response);
		}
		else {rd.forward(request, response);}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
