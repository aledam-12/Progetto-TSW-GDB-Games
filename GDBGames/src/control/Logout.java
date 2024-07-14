package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Logout() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("feedback");
        session.removeAttribute("cambioForm");
        session.removeAttribute("auth");
        session.removeAttribute("cliente");
        session.removeAttribute("nomeCliente");
        session.removeAttribute("carrello");
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/Login.jsp");    		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
