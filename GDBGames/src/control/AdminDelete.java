package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdineCopia;
import model.ProdottiDAO;
import model.beans.CopiaBean;


public class AdminDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminDelete() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottiDAO pdao = new ProdottiDAO();
		int quantit‡Rimozione = Integer.parseInt(request.getParameter("quantita"));
		CopiaBean copia = new CopiaBean();
		copia.setTitoloVideogioco(request.getParameter("titolo"));
		copia.setNomeConsole(request.getParameter("console"));
		copia.setPrezzo(Float.parseFloat(request.getParameter("prezzo")));
		OrdineCopia ord = new OrdineCopia(quantit‡Rimozione, copia);
		try {
			int totale = pdao.getQuantit‡(copia);
			if (quantit‡Rimozione <= 0)
			{ RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/admin/admin.jsp");
			rd.forward(request, response);}
			int i = 0;
			while (i < quantit‡Rimozione && i < totale) {
				pdao.cancellaCopia(pdao.leggiOrdineCopia(ord)); //tali passaggi perchÈ serve un ID univoco
				i++;
			}
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/admin/admin.jsp");
			rd.forward(request, response);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
