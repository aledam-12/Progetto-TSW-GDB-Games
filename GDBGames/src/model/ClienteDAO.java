package model;
import model.beans.AcquistoBean;
import model.beans.ClienteBean;
import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO implements Cliente
{	public synchronized boolean controlloFattura (ClienteBean cliente, int id) throws SQLException{
	AcquistoDAO adao = new AcquistoDAO();
	AcquistoBean acquisto = adao.leggiDaId(id);
	if (acquisto.getemailcliente().equals(cliente.getEmail())) return true;
	else return false;
}
	
	public synchronized ArrayList <ClienteBean> leggiTutti () throws SQLException {
	ArrayList <ClienteBean> clienti = new ArrayList<>();
	String SQL = "SELECT * FROM cliente";
	Connection conn = null;
	PreparedStatement ps = null;
	try {
		conn = ConnectionPool.getConnection();
		ps = conn.prepareStatement(SQL);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ClienteBean temp = new ClienteBean();
			temp.setEmail(rs.getString("email"));
			temp.setNome(rs.getString("nome"));
			temp.setCognome(rs.getString("cognome"));
			temp.setCitta(rs.getString("città"));
			temp.setCap(rs.getInt("cap"));
			temp.setVia(rs.getString("via"));
			temp.setCivico(rs.getInt("civico"));
			temp.setProvincia(rs.getString("provincia"));
			temp.setStato(rs.getString("stato"));
			clienti.add(temp);
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
 }	return clienti;
	}
    public synchronized void inserisciCliente(ClienteBean c) throws SQLException
        {
            Connection conn = null;
            PreparedStatement PrepareStatement = null;
            String query = "INSERT INTO cliente(email, nome, cognome, pw, via, civico, città, provincia, cap, stato) VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try{
                    conn = ConnectionPool.getConnection();
                    PreparedStatement ps= conn.prepareStatement(query);
                    ps.setString(1, c.getEmail());
                    ps.setString(2, c.getNome());
                    ps.setString(3, c.getCognome());
                    ps.setString(4, c.getPw());
                    ps.setString(5, c.getVia());
                    ps.setInt(6, c.getCivico());
                    ps.setString(7, c.getCitta());
		            ps.setString(8, c.getProvincia());
                    ps.setInt(9, c.getCap());
		            ps.setString(10, c.getStato());
                    ps.executeUpdate();
                    conn.commit();
               } finally {
                            try {
                                    if (PrepareStatement != null)
                                        {
                                            PrepareStatement.close();
                                        }
                                } finally {
                                            ConnectionPool.rilasciaConnessione(conn);
                                          }
                         }
        }
    
    public synchronized ClienteBean leggiCliente(String e) throws SQLException 
        {
		    ClienteBean cliente = new ClienteBean();
		    Connection conn = null;
		    PreparedStatement ps = null;
		    String SQL = "SELECT * FROM cliente WHERE email = ?";
		    try { 
                    conn = ConnectionPool.getConnection();
		            ps = conn.prepareStatement(SQL);
		            ps.setString(1, e);
		            ResultSet rs = ps.executeQuery();
			        if(rs.next()) 
                        {
			    cliente.setEmail(e);
			    cliente.setNome(rs.getString("nome"));
                            cliente.setCognome(rs.getString("cognome"));
                            cliente.setPw(rs.getString("pw"));
                            cliente.setVia(rs.getString("via"));
                            cliente.setCivico(rs.getInt("civico"));
                            cliente.setCitta(rs.getString("città"));
                            cliente.setProvincia(rs.getString("provincia"));
                            cliente.setCap(rs.getInt("cap"));
			    cliente.setStato(rs.getString("stato"));
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
		return cliente;
	    }

    public synchronized void modifiCliente(String email, String via, int civico) throws SQLException
        {
            Connection conn = null;
            PreparedStatement ps = null;
            String query = "UPDATE gdbgames.cliente SET via = ?, civico = ? WHERE email = ?";
            try{
                conn = ConnectionPool.getConnection();
                ps = conn.prepareStatement(query);
                ps.setString(1, via);
                ps.setInt(2, civico);
                ps.setString(3, email);
                ps.executeUpdate();
                conn.commit();
                }finally {
                            try {
                                    if (ps != null)
                                        {
                                            ps.close();
                                        }
                                } finally {
                                            ConnectionPool.rilasciaConnessione(conn);
                                          }
                         }
        }

    public synchronized void modifiCliente(String email, String città, String provincia, int cap) throws SQLException
        {
            Connection conn = null;
            PreparedStatement ps = null;
            String query = "UPDATE gdbgames.cliente SET città = ?, provincia = ?, cap = ? WHERE email = ?";
            try{
                conn = ConnectionPool.getConnection();
                ps = conn.prepareStatement(query);
                ps.setString(1, città);
                ps.setString(2, provincia);
                ps.setInt(3, cap);
                ps.setString(4, email);
                ps.executeUpdate();
                conn.commit();
                }finally {
                            try {
                                    if (ps != null)
                                        {
                                            ps.close();
                                        }
                                } finally {
                                            ConnectionPool.rilasciaConnessione(conn);
                                          }
                         }
        }



    public synchronized boolean isRegistrato (ClienteBean cliente) throws SQLException {
    	boolean status = false;
    	String SQL = "SELECT email FROM cliente WHERE email = ?";
    	Connection conn = null;
    	PreparedStatement ps = null;
    	try {
    		conn = ConnectionPool.getConnection();
    		ps = conn.prepareStatement(SQL);
    		ps.setString(1, cliente.getEmail());
    		ResultSet rs = ps.executeQuery();
    		if (rs.next()) {
    		status = true;
    		} 
    		else status = false;
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
    	return status;
    }
}

