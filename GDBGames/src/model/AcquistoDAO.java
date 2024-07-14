package model;


import model.beans.AcquistoBean;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
	
    public class AcquistoDAO implements Acquisto{
        private static final String TABLE_NAME = "acquisto";
        private static final List<String> ORDERS = new ArrayList<>(Arrays.asList("username", "dataOrdine")); 
        
        public synchronized ArrayList<AcquistoBean> cercaOrdini (LocalDate inizio, LocalDate fine, String email) throws SQLException {
        	String SQL = "SELECT * FROM acquisto WHERE dataAcquisto <= ? AND dataAcquisto >= ? ";
        	if (email != null && !email.contentEquals("")) SQL += "AND emailCliente = ?";
        	Connection connection = null;
        	PreparedStatement ps = null;
        	ArrayList <AcquistoBean> acquisti = new ArrayList <>();
        	try {
        		connection = ConnectionPool.getConnection();
        		ps = connection.prepareStatement(SQL);
        		ps.setString(1, fine.toString());
        		ps.setString(2, inizio.toString());
        		if(email != null && !email.contentEquals("")) ps.setString(3, email);		
        		ResultSet rs = ps.executeQuery();
        		while (rs.next()) {
        			AcquistoBean temp = new AcquistoBean();
        			temp.setemailcliente(rs.getString("emailCliente"));
        			temp.setnCarta(rs.getInt("ncarta"));
        			temp.setnFattura(rs.getInt("nFattura"));
        			temp.setdataAcquisto(rs.getDate("dataAcquisto").toLocalDate());
        			temp.setPrezzoTotale(rs.getFloat("prezzoTotale"));
        			temp.setVia(rs.getString("via"));
        			temp.setCap(rs.getInt("cap"));
        			temp.setCitta(rs.getString("città"));
        			acquisti.add(temp);
        		}
        	}
        	catch (Exception e) {
        		e.printStackTrace();
        	}
        	return acquisti;
        }

        public synchronized AcquistoBean leggiDaId(int id) throws SQLException {
            Connection connection = null;
            AcquistoBean acquistoBean = new AcquistoBean();
            PreparedStatement preparedStatement = null;
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE nFattura = ?";
        	try { connection = ConnectionPool.getConnection(); 
        	preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                setOrders(resultSet, acquistoBean);
            }
        }
		finally {
        	try {
                if (preparedStatement!= null)
                    {
                        preparedStatement.close();
                    }
            } finally {
                        ConnectionPool.rilasciaConnessione(connection);
                      }
     }
        	return acquistoBean;
   }
        
        public synchronized Collection<AcquistoBean> leggiPerEmail(String code) throws SQLException {
            Collection<AcquistoBean> ordini = new ArrayList<>();
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE emailCliente = ?";

            try { connection = ConnectionPool.getConnection(); 
            	preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, code);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    AcquistoBean acquistoBean = new AcquistoBean();
                    setOrders(resultSet, acquistoBean);
                    ordini.add(acquistoBean);
                }
            }
    		finally {
            	try {
                    if (preparedStatement!= null)
                        {
                            preparedStatement.close();
                        }
                } finally {
                            ConnectionPool.rilasciaConnessione(connection);
                          }
         }
            return ordini;
        }


        public Collection<AcquistoBean> leggiTuttiOrdini(String order) throws SQLException {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            Collection<AcquistoBean> ordini = new ArrayList<>();

            StringBuilder query = new StringBuilder("SELECT * FROM " + TABLE_NAME);

            try {
                connection = ConnectionPool.getConnection();

                for (String s: ORDERS)
                    if (s.equals(order))
                        query.append(" ORDER BY ").append(s);

                preparedStatement = connection.prepareStatement(query.toString());

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    AcquistoBean acquistoBean = new AcquistoBean();

                    setOrders(resultSet,acquistoBean);

                    ordini.add(acquistoBean);
                }

            } finally {
                if (preparedStatement!= null)
                    preparedStatement.close();
                if (connection != null)
                    ConnectionPool.rilasciaConnessione(connection);
            }

            return ordini;
        }


        public synchronized void  inserisciOrdine(AcquistoBean acquistoBean) throws SQLException {
            String query = "INSERT INTO " + TABLE_NAME + " (emailCliente, prezzoTotale, dataAcquisto, cap, via, città, nCarta, nFattura) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        	Connection connection = null;
        	PreparedStatement preparedStatement = null;
   
            try  {
            	connection = ConnectionPool.getConnection(); 
            	preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, acquistoBean.getemailcliente());
                preparedStatement.setFloat(2, acquistoBean.getPrezzoTotale());
                preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
                preparedStatement.setInt(4, acquistoBean.getCap());
                preparedStatement.setString(5, acquistoBean.getVia()); 
                preparedStatement.setString(6, acquistoBean.getCitta()); 	
                preparedStatement.setInt(7, acquistoBean.getnCarta());
                preparedStatement.setInt(8, acquistoBean.getnFattura());
                preparedStatement.executeUpdate();
                connection.commit();
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                	int idInserito = generatedKeys.getInt(1);
                	acquistoBean.setnFattura(idInserito);	//si sfrutta passaggio per riferimento per avere l'ID della fattura generata tramite Auto-Increment
            }	
           
           }
    		finally {
    			try {
    				if (preparedStatement != null)
    					preparedStatement.close();
    			} finally {
    				ConnectionPool.rilasciaConnessione(connection);
    			}
    		}
        }

        private void setOrders(ResultSet resultSet, AcquistoBean acquistoBean) throws SQLException {
            acquistoBean.setnFattura(resultSet.getInt("nFattura"));
            acquistoBean.setemailcliente(resultSet.getString("emailCliente"));
            acquistoBean.setPrezzoTotale(resultSet.getFloat("prezzoTotale"));
            acquistoBean.setdataAcquisto(resultSet.getDate("dataAcquisto").toLocalDate());
            acquistoBean.setCap(Integer.parseInt(resultSet.getString("cap")));
            acquistoBean.setVia(resultSet.getString("via"));
            acquistoBean.setCitta(resultSet.getString("città")); 
            acquistoBean.setnCarta(resultSet.getInt("nCarta")); 
        }
    }
