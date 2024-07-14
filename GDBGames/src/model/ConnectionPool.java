package model;

import java.sql.*;
import java.util.List;
import java.util.LinkedList;

public class ConnectionPool
{
    private static List<Connection> connDB; 

    static  
        {
            connDB = new LinkedList<Connection>(); 
            try{
                    Class.forName("com.mysql.cj.jdbc.Driver"); 
               }
           catch(Exception e) 
                {	e.printStackTrace();
                    System.out.println("ERRORE: Driver di database non trovato!" ); 
                }}
    
    private static Connection connessioneDB() throws SQLException  
        {
            Connection c = null;
            String ip = "localhost";
            String port = "3306";
            String db = "gdbgames";
            String username = "root";
            String password = "pw";
            c = DriverManager.getConnection("jdbc:mysql://"+ ip+":"+ port+"/"+db+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
            c.setAutoCommit(false); 
            return c; 
        }

    public static synchronized Connection getConnection() throws SQLException    
        {                                                                     
            Connection conn;

            if(!connDB.isEmpty()) 
                {
                    conn = (Connection) connDB.get(0); 
                    ConnectionPool.connDB.remove(0);
                    try{
                            if(conn.isClosed()) 
                                {	
                                    conn = ConnectionPool.getConnection(); 
                                }   
                       }
                    catch(SQLException e)
                        {	conn.close();
                            conn = getConnection(); 
                        }
                }
            else
                {
                    conn = connessioneDB(); 
                }
            return conn;
        }

    public static synchronized void rilasciaConnessione(Connection c) 
        {if(c != null)
    	ConnectionPool.connDB.add(c);
        }
}