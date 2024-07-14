package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdineCopia;
import model.ProdottiDAO;

public class Searchbar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Searchbar() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottiDAO pdao = new ProdottiDAO();
		String console = request.getParameter("console");
		String search = request.getParameter("search");
		if (search == null) search = "";
		ArrayList<OrdineCopia> prodotti;
		try {
			prodotti = pdao.cercaProdotti(search, console);
			request.getSession().setAttribute("prodotti", prodotti);
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/catalogo.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
