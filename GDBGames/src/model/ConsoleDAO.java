package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.ConsoleBean;


public class ConsoleDAO implements Console
{
    public synchronized ArrayList<ConsoleBean> leggiConsole () throws SQLException
    {
	    String sql = "SELECT * FROM console";
	    ArrayList <ConsoleBean> console = new ArrayList <>();
	    Connection conn = null;
	    PreparedStatement ps = null;
	    try { 
                conn = ConnectionPool.getConnection(); 
	            ps = conn.prepareStatement(sql); 
            	ResultSet rs = ps.executeQuery();
	            while (rs.next()) 
                    {
		                ConsoleBean cons = new ConsoleBean();
		                cons.setNome(rs.getString("nome"));
		                console.add(cons);
	                }
	        }
	    finally {
		    try {
			    if (ps != null)
                    {
				        ps.close();
                    }
		        } finally {
			        ConnectionPool.rilasciaConnessione(conn);
		                  }
	            }
	    return console;
    }
    public synchronized void aggiungiConsole (String nomeConsole) throws SQLException {
    	String SQL = "INSERT INTO console VALUES (?)";
    	Connection conn = null;
    	PreparedStatement ps = null;
    	PreparedStatement ps1 = null;
    	String SQL1 = "SELECT * from console WHERE nome = ?";
    	try { 
            conn = ConnectionPool.getConnection(); 
            ps = conn.prepareStatement(SQL);
            ps1 = conn.prepareStatement(SQL1);
            ps1.setString(1, nomeConsole);
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
            	conn.rollback();
            }
            else {
            ps.setString(1, nomeConsole);
        	ps.executeUpdate(); 
        	conn.commit();
        	}
        }
    finally {
	    try {
		    if (ps != null)
                {
			        ps.close();
                }
		    if (ps1 != null) {ps1.close();}
	        } finally {
		        ConnectionPool.rilasciaConnessione(conn);
	                  }
            }
    	
    	
    	
    }
}