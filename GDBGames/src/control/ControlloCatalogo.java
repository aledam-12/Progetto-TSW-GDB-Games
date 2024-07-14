package control;
import java.io.IOException;
import java.sql.SQLException;

import model.OrdineCopia;
import model.ProdottiDAO;
import model.VideogiocoDAO;
import model.beans.CopiaBean;

import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	public class ControlloCatalogo extends HttpServlet 
	{    
		ProdottiDAO pdao = new ProdottiDAO();
		private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
	                    VideogiocoDAO vdao = new VideogiocoDAO();
	                    String titolo = request.getParameter("titolo");
	                    String console = request.getParameter("console");
	                    float prezzo = Float.parseFloat(request.getParameter("prezzo"));
	                    CopiaBean temp = new CopiaBean();
	                    temp.setTitoloVideogioco(titolo);
	                    temp.setPrezzo(prezzo);
	                    temp.setNomeConsole(console);
	                    request.removeAttribute("copia");
	                    request.setAttribute("copia", new OrdineCopia(temp));
	                    request.removeAttribute("videogioco");
	                    try {
	                    	request.setAttribute("videogioco",vdao.leggiVideogioco(titolo));
						} catch (SQLException e) {
							e.printStackTrace();
						}
						RequestDispatcher disp = request.getServletContext().getRequestDispatcher("/DettaglioVideogioco.jsp");
						disp.forward(request,response);
						            
	                            
		}
		protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			doGet(request,response);
		}
		
	}
