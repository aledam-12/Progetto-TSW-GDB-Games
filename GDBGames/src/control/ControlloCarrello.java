package control;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;

import model.Carrello;
import model.OrdineCopia;
import model.beans.CopiaBean;

public class ControlloCarrello extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		Carrello cart = (Carrello) request.getSession().getAttribute("carrello");
		if (cart == null) {
			cart = new Carrello();
			request.getSession().setAttribute("carrello", cart);
		} 
		String action = request.getParameter("action");
		if (action.equals("add") && action != null) {
			try { int quantità = 1;
			if(request.getParameter("quantita") != null) quantità = Integer.parseInt(request.getParameter("quantita"));
        	String titolo = request.getParameter("titolo");
        	String console = request.getParameter("console");
        	float prezzo = Float.parseFloat(request.getParameter("prezzo"));
        	CopiaBean copiaTemp = new CopiaBean();
        	copiaTemp.setNomeConsole(console);
        	copiaTemp.setTitoloVideogioco(titolo);
        	copiaTemp.setPrezzo(prezzo);
			OrdineCopia temp = new OrdineCopia(quantità,copiaTemp);
			if (cart.viewCart().contains(temp)) {
                cart.removeFromCart(temp);
				cart.addToCart(temp, quantità); 
				request.setAttribute("Status", cart.viewCart().size()==5); //se ci sono 5 elementi manda avviso

			}
			else {
			cart.addToCart(temp, quantità);
			request.getSession().setAttribute("carrello", cart);
			RequestDispatcher view = request.getServletContext().getRequestDispatcher("/catalogo.jsp");
			view.forward(request, response);}
				}
			catch (Exception e) {
				e.printStackTrace();}
		}
		if (action.equals("view")) {
			request.getSession().setAttribute("prodottiCarrello", cart.viewCart());
			try {
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/carrello.jsp");
			rd.forward(request, response);
			}
			catch (Exception e) {e.printStackTrace();}
		}
		if (action.equals("remove")) {
        	String titolo = request.getParameter("titolo");
        	String console = request.getParameter("console");
        	float prezzo = Float.parseFloat(request.getParameter("prezzo"));
        	CopiaBean copiaTemp = new CopiaBean();
        	copiaTemp.setNomeConsole(console);
        	copiaTemp.setTitoloVideogioco(titolo);
        	copiaTemp.setPrezzo(prezzo);
			OrdineCopia temp = new OrdineCopia(copiaTemp);
			try {
				cart.removeFromCart(temp);
				request.getSession().setAttribute("carrello",cart);
				RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/carrello.jsp?action=view");
				rd.forward(request, response);
			} 
			catch (Exception e) {e.printStackTrace();} }
    }   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {doGet(request,response);}
}
