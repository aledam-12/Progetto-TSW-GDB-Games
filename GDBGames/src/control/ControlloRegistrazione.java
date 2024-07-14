package control;

import java.nio.charset.StandardCharsets;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ClienteDAO;
import model.FormSanifier;
import model.beans.ClienteBean;

public class ControlloRegistrazione extends HttpServlet {
	public ControlloRegistrazione () {
		super();
	}
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		FormSanifier fs = new FormSanifier();
		ClienteBean cliente = new ClienteBean();
		ClienteDAO cdao = new ClienteDAO();
		cliente.setNome(fs.sanitizeString(request.getParameter("nome")));
		cliente.setCognome(fs.sanitizeString(request.getParameter("cognome")));
		cliente.setVia(fs.sanitizeString(request.getParameter("Via")));
		cliente.setCivico(Integer.parseInt(request.getParameter("NCivico")));
		cliente.setCitta(fs.sanitizeString(request.getParameter("città")));
		cliente.setCap(Integer.parseInt(request.getParameter("cap")));
		cliente.setProvincia((fs.sanitizeString(request.getParameter("provincia"))));
		cliente.setEmail(fs.sanitizeString(request.getParameter("emailCliente")));
		cliente.setPw(toHash(request.getParameter("pwCliente")));
		cliente.setStato("user");
		HttpSession session = request.getSession(true);
		
		try {

			boolean feedbackReg = false;

			if (!cdao.isRegistrato(cliente)) {
			 	cdao.inserisciCliente(cliente);

				feedbackReg = true;
				session.setAttribute("feedbackReg", feedbackReg);
			}
			else{
			 	session.setAttribute("feedbackReg", feedbackReg);				
			}


			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Login.jsp");
			request.setAttribute("stato", cdao.isRegistrato(cliente)); //stato per i feedback
			rd.forward(request, response);
			
			} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
	
	}
    private String toHash(String password) {		//metodo per criptare la password
    	String hashString = null;
    	try {
    		java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
    		byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
    		hashString = "";
    		for (int i = 0; i < hash.length; i++) {
    			hashString += Integer.toHexString(
    					(hash[i] & 0xFF) | 0x100).toLowerCase().substring(1,3); 
    		}
    	} catch (java.security.NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
    	return hashString;


		
    }
}