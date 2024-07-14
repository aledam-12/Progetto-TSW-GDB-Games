package control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import model.OrdineCopia;
import model.ProdottiDAO;

public class SearchbarJSON extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        String console = req.getParameter("console");
        ProdottiDAO pdao = new ProdottiDAO();
        ArrayList<OrdineCopia> prodotti = new ArrayList<>();
		try {
			prodotti = pdao.cercaProdottiLimite(search, console);
			JSONArray jArray = new JSONArray();
			for (OrdineCopia prod : prodotti) {
			    JSONObject jsonObject = new JSONObject();
			    jsonObject.put("titolo", prod.getTitoloVideogioco());
			    jsonObject.put("prezzo", prod.getPrezzo());
			    jsonObject.put("console", prod.getNomeConsole());
			    jsonObject.put("quantita", Integer.toString(prod.getQuantità()));
			    jArray.put(jsonObject);
			}
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");

	        PrintWriter out = resp.getWriter();
	        out.print(jArray.toString());
	        out.flush();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        	
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }
}