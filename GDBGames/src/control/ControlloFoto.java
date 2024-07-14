package control;
import java.sql.*;

import model.ConnectionPool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ControlloFoto
{						
    public synchronized static byte[] caricamento(String t)
        {
            Connection c = null;
            PreparedStatement st = null;
            byte b[] = null;
            try{
                    c = ConnectionPool.getConnection();
                    String query = "SELECT img FROM videogioco WHERE titolo = ?";
                    st = c.prepareStatement(query);
                    st.setString(1, t);
                    ResultSet risultato = st.executeQuery();
                    if (risultato.next()) 
                    {	
                        b = risultato.getBytes("img");
                    }
               } catch (SQLException sqlException) 
                    {
                        System.out.println(sqlException);
                    }
                    finally {
                            try {
                                if (st != null)
                                    {
                                        st.close();
                                    }
                                } catch (SQLException sqlException) 
                                    {
                                        System.out.println(sqlException);
                                    } finally {
                                            if (c != null)
                                                { 
                                                    ConnectionPool.rilasciaConnessione(c);
                                                }
                                              }
                            }
                    return b;
        }
    
    public synchronized static void updateImg(String titolo, String path) throws SQLException 
        {
            Connection c = null;
            PreparedStatement st = null;
            try {
                    c = ConnectionPool.getConnection();
                    String query = "UPDATE videogioco SET img = ? WHERE titolo = ?";
                    st = c.prepareStatement(query);
                    File file = new File(path);
                    try {
                        FileInputStream fis = new FileInputStream(file);
                        st.setBinaryStream(1, fis, fis.available());
                        st.setString(2, titolo);
                        st.executeUpdate();
                        c.commit();
                        } catch (FileNotFoundException e) 
                            {
                                System.out.println(e);
                            } catch (IOException e) 
                                {
                                    System.out.println(e);
                                }
                } finally {
                        try {
                            if (st != null)
                                {
                                    st.close();
                                }
                            } catch (SQLException sqlException) 
                                {
                                    System.out.println(sqlException);
                                } finally {
                                    if (c != null)
                                        {
                                            ConnectionPool.rilasciaConnessione(c);
                                        }
                                          }
                          }
        }
}
