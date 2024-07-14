package control;



import java.io.File;

import java.io.IOException;
import java.sql.SQLException;

import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.ProdottiDAO;
import model.VideogiocoDAO;
import model.beans.CopiaBean;
import model.beans.VideogiocoBean;
import model.ConsoleDAO;
import model.FormSanifier;

@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class AdminInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminInput() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String type = request.getParameter("type");
	FormSanifier fs = new FormSanifier();
	if (type.equals("copia")) {
		CopiaBean copia = new CopiaBean();
		copia.setPercIva(Float.parseFloat(fs.sanitizeFloat(request.getParameter("iva"))));
		copia.setNomeConsole(fs.sanitizeString(request.getParameter("console")));
		ConsoleDAO cdao = new ConsoleDAO();
    	try {
			cdao.aggiungiConsole(copia.getNomeConsole());
		} catch (Exception e) {
			e.printStackTrace();
		}
		copia.setPrezzo((Float.parseFloat(fs.sanitizeFloat(request.getParameter("prezzo")))));
		copia.setTitoloVideogioco(fs.sanitizeString(request.getParameter("titolo")));
		copia.setStato(false);
		ProdottiDAO pdao = new ProdottiDAO();
		int quantità = Integer.parseInt(request.getParameter("quantita"));
		for (int i = 0; i < quantità; i++) {
			try {pdao.inscopia(copia);}
			catch (SQLException e) {e.printStackTrace();}
				}
		}
	else if (type.equals("videogioco")) {
		VideogiocoBean videogioco = new VideogiocoBean();
		videogioco.setDescrizione(fs.sanitizeString(request.getParameter("descrizione")));
		videogioco.setPegi(Integer.parseInt(request.getParameter("pegi")));
		videogioco.setTitolo(fs.sanitizeString(request.getParameter("titolo")));
		VideogiocoDAO vdao = new VideogiocoDAO();
		try {vdao.inserisciVideogioco(videogioco);}
		catch(Exception e) {e.getMessage();}
		

		
		
		//per caricare le foto
		
		String id = request.getParameter("titolo");
		
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + "/foto"; 
	    File fileSaveDir = new File(savePath);
	    if (!fileSaveDir.exists()) 
            {
		        fileSaveDir.mkdir();
	        }
	    Collection<Part> parts = request.getParts();
	    for (Part part : parts) 
            { 	if (part.getName().equals("fotoVideogioco")) {
		        String fileName = extractFileName(part);
		        if (fileName != null && !fileName.equals("") && controlloEstensione(fileName)) 
                    {
			            part.write(savePath + File.separator + fileName);
			            try {
				                ControlloFoto.updateImg(id, savePath + File.separator + fileName);
			                } catch (SQLException sqlException) 
                                {
				                   sqlException.printStackTrace();
			                    }
        			}
	        }}
	}
	else if(type.equals("changeIVA")) {
		ProdottiDAO pdao = new ProdottiDAO();
		float iva = Float.parseFloat(fs.sanitizeFloat(request.getParameter("percIva")));
		try{pdao.cambiaIVA(iva);}
		catch (SQLException e) {e.printStackTrace();}
		}
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/admin/admin.jsp");
		rd.forward(request, response);
	}
private String extractFileName(Part part) 
{
String contentDisp = part.getHeader("content-disposition");
String[] items = contentDisp.split(";");
for (String s : items) 
    {
        if (s.trim().startsWith("filename")) 
            {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
    }
return "";
	}			

private boolean controlloEstensione (String fileName) {
	String [] AllowedExtension = {"xbm","tif","tiff","jfif","ico","gif","svg","jpeg","svgz","jpg","webp","png","bmp","pjp","apng","pjpeg","avif"};
	
	String estensione = fileName.substring(fileName.lastIndexOf(".")+1);
	for (int i = 0; i < AllowedExtension.length; i++) {
	if (estensione.equals(AllowedExtension[i])) return true;
	}
	return false;
	}
}


