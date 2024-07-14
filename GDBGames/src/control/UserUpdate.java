package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ClienteDAO;
import model.FormSanifier;
import model.beans.ClienteBean;


public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UserUpdate() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	FormSanifier fs = new FormSanifier();
	ClienteDAO cdao = new ClienteDAO();
	String via = fs.sanitizeString(request.getParameter("via"));
	int cap = Integer.parseInt(request.getParameter("cap"));
	String città = fs.sanitizeString(request.getParameter ("citta"));
	int civico = Integer.parseInt(request.getParameter("civico"));
	String provincia = fs.sanitizeString(request.getParameter("provincia"));
	ClienteBean cliente = (ClienteBean) request.getSession(false).getAttribute("cliente");
	String email = cliente.getEmail();
	try {
		cdao.modifiCliente(email, via, civico);
		cdao.modifiCliente(email, città, provincia, cap);
		request.getSession(false).removeAttribute("cliente");
		request.getSession(false).setAttribute("cliente", cdao.leggiCliente(email));
	} catch (SQLException e) {
		e.printStackTrace();
		}
	RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/utenteLoggato/account.jsp");
	rd.forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
