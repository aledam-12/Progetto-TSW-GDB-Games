package model;
import model.beans.*;
import java.util.*;
import java.sql.*;

public class ProdottiDAO implements Prodotti
{	
	
	public synchronized ArrayList <OrdineCopia> cercaProdotti (String titolo, String console) throws SQLException  {
	String SQL = "SELECT count(*) as quantità, nomeConsole, titoloVideogioco, percIva, prezzo FROM copia WHERE stato = 0 AND titoloVideogioco LIKE ? AND nomeConsole LIKE ? " + " GROUP BY nomeConsole, titoloVideogioco, prezzo, percIva";
	ArrayList <OrdineCopia> prodotti = new ArrayList<>();
	Connection conn = null;
	PreparedStatement ps = null;
	try  {
		conn = ConnectionPool.getConnection();
		ps = conn.prepareStatement(SQL);
		ps.setString(1, "%"+titolo+"%");
		ps.setString(2, "%"+console+"%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			CopiaBean temp2 = new CopiaBean();
			temp2.setNomeConsole(rs.getString("nomeConsole"));
			temp2.setTitoloVideogioco(rs.getString("titoloVideogioco"));
			temp2.setPercIva(rs.getFloat("percIva"));
			temp2.setPrezzo(rs.getFloat("prezzo"));
			OrdineCopia temp = new OrdineCopia(rs.getInt("quantità"), temp2);
			prodotti.add(temp);	}
	}
	finally {
		try {
			if (ps != null)
				ps.close();
		} finally {
			ConnectionPool.rilasciaConnessione(conn);
		}
	}
	return prodotti;
	}
	public synchronized ArrayList <OrdineCopia> cercaProdottiLimite (String titolo, String console) throws SQLException  {
	String SQL = "SELECT count(*) as quantità, nomeConsole, titoloVideogioco, percIva, prezzo FROM copia WHERE stato = 0 AND titoloVideogioco LIKE ? AND nomeConsole LIKE ? " + " GROUP BY nomeConsole, titoloVideogioco, prezzo, percIva"
	+ " LIMIT 3";
	ArrayList <OrdineCopia> prodotti = new ArrayList<>();
	Connection conn = null;
	PreparedStatement ps = null;
	try  {
		conn = ConnectionPool.getConnection();
		ps = conn.prepareStatement(SQL);
		ps.setString(1, "%"+titolo+"%");
		ps.setString(2, "%"+console+"%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			CopiaBean temp2 = new CopiaBean();
			temp2.setNomeConsole(rs.getString("nomeConsole"));
			temp2.setTitoloVideogioco(rs.getString("titoloVideogioco"));
			temp2.setPercIva(rs.getFloat("percIva"));
			temp2.setPrezzo(rs.getFloat("prezzo"));
			OrdineCopia temp = new OrdineCopia(rs.getInt("quantità"), temp2);
			prodotti.add(temp);	}
	}
	finally {
		try {
			if (ps != null)
				ps.close();
		} finally {
			ConnectionPool.rilasciaConnessione(conn);
		}
	}
	return prodotti;
	}
	public synchronized CopiaBean leggiOrdineCopia (OrdineCopia ord) throws SQLException {
		String SQL = "SELECT * FROM copia WHERE titoloVideogioco = ? AND nomeConsole = ? AND prezzo = ? AND stato = 0";
		CopiaBean copia = new CopiaBean();
		Connection conn = null;
		PreparedStatement ps = null;
			try { conn = ConnectionPool.getConnection();
			ps = conn.prepareStatement(SQL);
			ps.setString(1, ord.getTitoloVideogioco());
			ps.setString(2, ord.getNomeConsole());
			ps.setFloat(3, ord.getPrezzo());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
	            copia.setCodiceCopia(rs.getInt("codiceCopia"));
	            copia.setNomeConsole(rs.getString("nomeConsole"));
	            copia.setPercIva(rs.getFloat("percIva"));
	            copia.setPrezzo(rs.getFloat("prezzo"));
	            copia.setTitoloVideogioco(rs.getString("titoloVideogioco"));
				}
			}
			finally {
				try {
					if (ps != null)
						ps.close();
				} finally {
					ConnectionPool.rilasciaConnessione(conn);
				}
			}
		return copia;
	}
	
	
	public synchronized ArrayList <OrdineCopia> getProdotti () throws SQLException  {
	String SQL = "SELECT count(*) as quantità, nomeConsole, titoloVideogioco, percIva, prezzo FROM copia WHERE stato = 0 " + "GROUP BY nomeConsole, titoloVideogioco, prezzo, percIva";
	ArrayList <OrdineCopia> prodotti = new ArrayList<>();
	Connection conn = null;
	PreparedStatement ps = null;
	try  {
		conn = ConnectionPool.getConnection();
		ps = conn.prepareStatement(SQL);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			CopiaBean temp2 = new CopiaBean();
			temp2.setNomeConsole(rs.getString("nomeConsole"));
			temp2.setTitoloVideogioco(rs.getString("titoloVideogioco"));
			temp2.setPercIva(rs.getFloat("percIva"));
			temp2.setPrezzo(rs.getFloat("prezzo"));
			OrdineCopia temp = new OrdineCopia(rs.getInt("quantità"), temp2);
			prodotti.add(temp);		}
	}
	finally {
		try {
			if (ps != null)
				ps.close();
		} finally {
			ConnectionPool.rilasciaConnessione(conn);
		}
	}
	return prodotti;
	}
	
	public synchronized void cambiaIVA (float IVA) throws SQLException{
		String SQL = "UPDATE gdbgames.copia SET percIva = ? WHERE stato = 0"; //modifica solo le copie invendute
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionPool.getConnection();
			ps = conn.prepareStatement(SQL);
			ps.setFloat(1, IVA);
			ps.executeUpdate();
			conn.commit();
		}
		finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				ConnectionPool.rilasciaConnessione(conn);
			}
		}
	}
	
	public synchronized ArrayList <OrdineCopia> leggiDaNFattura(AcquistoBean acquisto) throws SQLException {
		String SQL = "SELECT count(*) as quantità, nomeConsole, titoloVideogioco, percIva,codiceAcquisto, prezzo FROM copia WHERE codiceAcquisto = ? "
		    	+ "GROUP BY nomeConsole, titoloVideogioco, prezzo, percIva";
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList <OrdineCopia> ProdottiAcquistati = new ArrayList <>();
		try {													//l'iva è uguale per lo stesso tipo di videogioco acquistato nello stesso ordine
		conn = ConnectionPool.getConnection();
		ps = conn.prepareStatement(SQL);
		ps.setInt(1, acquisto.getnFattura());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			CopiaBean temp = new CopiaBean();
			temp.setNomeConsole(rs.getString("nomeConsole"));
			temp.setTitoloVideogioco(rs.getString("titoloVideogioco"));
			temp.setPercIva(rs.getFloat("percIva"));
			temp.setCodiceAcquisto(acquisto.getnFattura());
			temp.setPrezzo(rs.getFloat("prezzo"));
			OrdineCopia finale = new OrdineCopia (rs.getInt("quantità"), temp);
			ProdottiAcquistati.add(finale);
			}
		}
		finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				ConnectionPool.rilasciaConnessione(conn);
			}
		}
		return ProdottiAcquistati;
	}
	public synchronized void UpdateCopia (OrdineCopia copia, AcquistoBean acquisto) throws SQLException {
		String sql = "UPDATE copia SET stato = 1, codiceAcquisto = ? WHERE titoloVideogioco = ? AND prezzo = ? AND nomeConsole = ? AND stato = 0 LIMIT ?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionPool.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, acquisto.getnFattura());
			ps.setString(2, copia.getTitoloVideogioco());
			ps.setFloat(3, copia.getPrezzo());
			ps.setString(4, copia.getNomeConsole());
			ps.setInt(5, copia.getQuantità());
			ps.executeUpdate();
			conn.commit();
		}
		finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				ConnectionPool.rilasciaConnessione(conn);
			}
		}
	} 
	public synchronized int getQuantità (CopiaBean copia) throws SQLException
        {
		    String sql = "SELECT count(*) as quantità, nomeConsole, titoloVideogioco, prezzo FROM copia WHERE stato = 0 "
				    + "AND titoloVideogioco = ? "
			    	+ "AND nomeConsole = ? "
				    + "AND prezzo = ? "
			    	+ "GROUP BY nomeConsole, titoloVideogioco, prezzo";
		    Connection conn = null;
		    PreparedStatement ps = null;
		    int quantità = 0;
		    try {
			    conn = ConnectionPool.getConnection();
			    ps = conn.prepareStatement(sql);
			    ps.setString(1, copia.getTitoloVideogioco());
			    ps.setString(2, copia.getNomeConsole());
			    ps.setFloat(3, copia.getPrezzo());
			    ResultSet rs = ps.executeQuery();
			    while (rs.next()) 
                {
				    quantità = rs.getInt("quantità");
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
		    return quantità;
	    }


    
    public synchronized void inserisciProd(VideogiocoBean v) throws SQLException
        {
            Connection c = null;
            PreparedStatement PrepareStatement = null;
            String query = "INSERT INTO videogioco(titolo, descrizione, pegi) VALUE(?, ?, ?)";
            try{
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps= c.prepareStatement(query);
                    ps.setString(1, v.getTitolo());
                    ps.setString(2, v.getDescrizione());
                    ps.setInt(3, v.getPegi());
                    ps.executeUpdate();
                    c.commit();
               } finally {
                            try {
                                    if (PrepareStatement != null)
                                        {
                                            PrepareStatement.close();
                                        }
                                } finally {
                                            ConnectionPool.rilasciaConnessione(c);
                                          }
                         }
        }



     
    public synchronized void inscopia(CopiaBean copia) throws SQLException
        {
            Connection c = null;
            PreparedStatement ps = null;
            String query = "INSERT INTO copia(stato, percIva, prezzo, titoloVideogioco, nomeConsole) VALUE(?, ?, ?, ?, ?)";
                try {   
            	c = ConnectionPool.getConnection();
                    ps= c.prepareStatement(query);
                    ps.setBoolean(1, false);
                    ps.setFloat(2, copia.getPercIva());
                    ps.setFloat(3, copia.getPrezzo());
                    ps.setString(4, copia.getTitoloVideogioco());
                    ps.setString(5, copia.getNomeConsole());
                    ps.executeUpdate();
                    c.commit();
                    }
                    finally {
                        try {
                                if (ps != null)
                                    {
                                        ps.close();
                                    }
                            } finally {
                                        ConnectionPool.rilasciaConnessione(c);
                                      }
                     }
        }


     
    public synchronized boolean cancellaprod(String titolo) throws SQLException
        {
            Connection c = null;
            PreparedStatement ps = null;
            int risultato = 0;
            String query ="DELETE FROM videogioco WHERE titolo = ? ";
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement(query);
                    ps.setString(1, titolo);
                    ps.executeUpdate();   
                    ps.close();
                    ConnectionPool.rilasciaConnessione(c);
                    return risultato != 0;
        }



     
    public synchronized void cancellaCopia(CopiaBean copia) throws SQLException
        {
                    Connection c = null;
                    PreparedStatement ps = null;
                    String query ="DELETE FROM copia WHERE stato = 0 && codiceCopia = ?";
                            c = ConnectionPool.getConnection();
                            ps = c.prepareStatement(query);
                            ps.setInt(1, copia.getCodiceCopia());
                            ps.executeUpdate();      
                            c.commit();
                            ps.close();
                            ConnectionPool.rilasciaConnessione(c);
        }
    

     
	public synchronized CopiaBean leggiCopia(int codice) throws SQLException 
        {
		    CopiaBean copia = new CopiaBean();
		    Connection conn = null;
		    PreparedStatement ps = null;
		    String SQL = "SELECT * FROM copia WHERE codiceCopia = ?";
		    try { 
                    conn = ConnectionPool.getConnection();
		            ps = conn.prepareStatement(SQL);
		            ps.setInt(1, codice);
		            ResultSet rs = ps.executeQuery();
			        if(rs.next()) 
                        {
				            copia.setCodiceCopia(codice);
				            copia.setNomeConsole(rs.getString("nomeConsole"));
				            copia.setPercIva(rs.getFloat("percIva"));
				            copia.setPrezzo(rs.getFloat("prezzo"));
				            copia.setTitoloVideogioco(rs.getString("titoloVideogioco"));
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
		return copia;
	    }


     
    public synchronized ArrayList<CopiaBean> leggiTutteCopie (String sort) throws SQLException
        {
		    String sql = "SELECT * FROM copia WHERE stato = 0";
		    ArrayList <CopiaBean> copie = new ArrayList <>();
		    Connection conn = null;
		    PreparedStatement ps = null;
		    if (sort != null && !sort.equals("")) 
                {
			        sql += " ORDER BY " + sort;
		        }	
		    try { 
                    conn = ConnectionPool.getConnection(); 
		            ps = conn.prepareStatement(sql); 
	            	ResultSet rs = ps.executeQuery();
		            while (rs.next()) 
                        {
			                CopiaBean copia = new CopiaBean();
			                copia.setCodiceCopia(rs.getInt("codiceCopia"));
			                copia.setTitoloVideogioco(rs.getString("titoloVideogioco"));
			                copia.setPercIva(rs.getFloat("percIva"));
			                copia.setPrezzo(rs.getFloat("prezzo"));
			                copia.setNomeConsole(rs.getNString("nomeConsole"));
			                copie.add(copia);
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
		    return copie;
        }


     

    public synchronized void modificaprod(String m, String d) throws SQLException
        {
            Connection c = null;
	        PreparedStatement preparedStatement = null;
            String query = "UPDATE 'gdbgames'.'videogioco' SET descrizione = ? WHERE titolo = ?";
            try{
                c = ConnectionPool.getConnection();
                preparedStatement = c.prepareStatement(query);
                preparedStatement.setString(1, d);
                preparedStatement.setString(2, m);
                preparedStatement.executeUpdate();
                }finally {
			                try {
				                if (preparedStatement != null)
                                    {
					                    preparedStatement.close();
                                    }
			                    } finally {
				                        ConnectionPool.rilasciaConnessione(c);
			                              }
		                  }

        } 



     
    public synchronized void modificaCopia(String m, int s) throws SQLException
        {
            Connection c = null;
	        PreparedStatement preparedStatement = null;
            String query = "UPDATE 'gdbgames'.'copia' SET stato = ? WHERE titoloVideogioco = ?";
            try{
                c = ConnectionPool.getConnection();
                preparedStatement = c.prepareStatement(query);
                preparedStatement.setInt(1, s);
                preparedStatement.setString(2, m);
                }finally {
			                try {
				                if (preparedStatement != null)
                                    {
					                    preparedStatement.close();
                                    }
			                    } finally {
				                        ConnectionPool.rilasciaConnessione(c);
			                              }
		                  }

        }
     
    public synchronized void modificaCopiaIva(String m, float iva) throws SQLException
        {
            Connection c = null;
	        PreparedStatement preparedStatement = null;
            String query = "UPDATE 'gdbgames'.'copia' SET percIva = ? WHERE titoloVideogioco = ?";
            try{
                c = ConnectionPool.getConnection();
                preparedStatement = c.prepareStatement(query);
                preparedStatement.setFloat(1, iva);
                preparedStatement.setString(2, m);
                }finally {
			                try {
				                if (preparedStatement != null)
                                    {
					                    preparedStatement.close();
                                    }
			                    } finally {
				                        ConnectionPool.rilasciaConnessione(c);
			                              }
		                  }

        }
     
    public synchronized void modificaCopia(CopiaBean copia, String newConsole, float newPrezzo) throws SQLException
        {
            Connection c = null;
	        PreparedStatement preparedStatement = null;
            String query = "UPDATE gdbgames.copia SET prezzo = ?, nomeConsole = ? WHERE titoloVideogioco = ? AND nomeConsole = ? AND stato = 0";
            try{
                c = ConnectionPool.getConnection();
                preparedStatement = c.prepareStatement(query);
                preparedStatement.setFloat(1, newPrezzo);
                preparedStatement.setString(2, newConsole);
                preparedStatement.setString(3, copia.getTitoloVideogioco());
                preparedStatement.setString(4, copia.getNomeConsole());
                preparedStatement.executeUpdate();
                
                }finally {
			                try {
				                if (preparedStatement != null)
                                    {
					                    preparedStatement.close();
                                    }
			                    } finally {
				                       ConnectionPool.rilasciaConnessione(c);
			                              }
		                  }
        }
     
    public synchronized void modificaCopia(String m, int s, float pre) throws SQLException
        {
            Connection c = null;
	        PreparedStatement ps = null;
            String query = "UPDATE 'gdbgames'.'copia' SET stato = ?, prezzo = ? WHERE titoloVideogioco = ?";
            try{
                c = ConnectionPool.getConnection();
                ps = c.prepareStatement(query);
                ps.setInt(1, s);
                ps.setFloat(2, pre);
                ps.setString(3, m);
                }finally {
			                try {
				                if (ps != null)
                                    {
					                    ps.close();
                                    }
			                    } finally {
				                        ConnectionPool.rilasciaConnessione(c);
			                              }
		                  }

        }
     
    public synchronized void modificaCopia(String m, float iva, float pre) throws SQLException
        {
            Connection c = null;
	        PreparedStatement preparedStatement = null;
            String query = "UPDATE 'gdbgames'.'copia' SET percIva = ?, prezzo = ? WHERE titoloVideogioco = ?";
            try{
                c = ConnectionPool.getConnection();
                preparedStatement = c.prepareStatement(query);
                preparedStatement.setFloat(1, iva);
                preparedStatement.setFloat(2, pre);
                preparedStatement.setString(3, m);
                }finally {
			                try {
				                if (preparedStatement != null)
                                    {
					                    preparedStatement.close();
                                    }
			                    } finally {
				                        ConnectionPool.rilasciaConnessione(c);
			                              }
		                  }

        }
     
    public synchronized void modificaCopia(String m, int s, float iva, float pre) throws SQLException
        {
            Connection c = null;
	        PreparedStatement preparedStatement = null;
            String query = "UPDATE 'gdbgames'.'copia' SET stato = ?, percIva = ?, prezzo = ? WHERE titoloVideogioco = ?";
            try{
                c = ConnectionPool.getConnection();
                preparedStatement = c.prepareStatement(query);
                preparedStatement.setInt(1, s);
                preparedStatement.setFloat(2, iva);
                preparedStatement.setFloat(3, pre);
                preparedStatement.setString(4, m);
                }finally {
			                try {
				                if (preparedStatement != null)
                                    {
					                    preparedStatement.close();
                                    }
			                    } finally {
				                        ConnectionPool.rilasciaConnessione(c);
			                              }
		                  }

        }



	public synchronized void modificaCopiaIva(String m, int s, float iva) throws SQLException 
        {
		    Connection c = null;
	        PreparedStatement preparedStatement = null;
            String query = "UPDATE 'gdbgames'.'copia' SET percIva = ? WHERE titoloVideogioco = ?";
            try{
                c = ConnectionPool.getConnection();
                preparedStatement = c.prepareStatement(query);
                preparedStatement.setFloat(1, iva);
                preparedStatement.setString(2, m);
                }finally {
			                try {
				                if (preparedStatement != null)
                                    {
					                    preparedStatement.close();
                                    }
			                    } finally {
				                       ConnectionPool.rilasciaConnessione(c);
			                              }
		                  }		
	    }
}
