package control;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Carrello;
import model.OrdineCopia;
import model.beans.CopiaBean;

//la servlet agisce solo quando c'è da modificare la quantità di un prodotto

public class ControlloCarrelloJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ControlloCarrelloJSON() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
//json build 
            ArrayList<OrdineCopia> prodotti = cart.viewCart();
            
			JSONArray jArray = new JSONArray();
            for (OrdineCopia prod : prodotti) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("titolo", prod.getTitoloVideogioco());
		        jsonObject.put("prezzo", prod.getPrezzo());
		        jsonObject.put("console", prod.getNomeConsole());
		        jsonObject.put("quantita", Integer.toString(prod.getQuantità()));
		        jArray.put(jsonObject);
            
            }
		request.getSession().removeAttribute("carrello");
        request.getSession().setAttribute("carrello", cart);


		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jArray.toString());
        out.flush();
        
		}
			}
			catch (Exception e) {
			e.printStackTrace();	
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
