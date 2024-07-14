package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.VideogiocoBean;

public class VideogiocoDAO implements Videogioco{
	
    public synchronized void modificaprod(String titolo, int pegi) throws SQLException
    {
        Connection c = null;
        PreparedStatement ps = null;
        String query = "UPDATE gdbgames.videogioco SET pegi = ? WHERE titolo = ?";
        try{
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement(query);
            ps.setInt(1, pegi);
            ps.setString(2, titolo);
            ps.executeUpdate();
            c.commit();
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
	
	public synchronized void modificaprod(String titolo, String descrizione, int pegi) throws SQLException
    {
        Connection c = null;
        PreparedStatement preparedStatement = null;
        String query = "UPDATE gdbgames.videogioco SET pegi = ?, descrizione = ? WHERE titolo = ?";
        try{
            c = ConnectionPool.getConnection();
            preparedStatement = c.prepareStatement(query);
            preparedStatement.setInt(1, pegi);
            preparedStatement.setString(2, descrizione);
            preparedStatement.setString(3, titolo);
            preparedStatement.executeUpdate();
            c.commit();
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
	public synchronized ArrayList <VideogiocoBean> leggiTutti () throws SQLException {
		ArrayList <VideogiocoBean> videogiochi = new ArrayList <>();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT * FROM videogioco");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				VideogiocoBean temp = new VideogiocoBean();
				temp.setDescrizione(rs.getString("descrizione"));
				temp.setTitolo(rs.getString("titolo"));
				temp.setPegi(rs.getInt("pegi"));
				videogiochi.add(temp);
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
		return videogiochi;
	}
	public synchronized VideogiocoBean leggiVideogioco(String titolo) throws SQLException {
		VideogiocoBean videogioco = new VideogiocoBean();
		Connection conn = null;
		PreparedStatement ps = null;
		String SQL = "SELECT * FROM videogioco WHERE titolo = ?";
		try { conn = ConnectionPool.getConnection();
		ps = conn.prepareStatement(SQL);
		ps.setString(1, titolo);
		ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				videogioco.setDescrizione(rs.getString("descrizione"));
				videogioco.setPegi(rs.getInt("pegi"));
				videogioco.setTitolo(titolo);
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
		return videogioco;
	}
	public synchronized void inserisciVideogioco(VideogiocoBean videogioco) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String SQL = "INSERT INTO videogioco (pegi, descrizione, titolo) VALUES (?, ?, ?)";
		try {
			conn = ConnectionPool.getConnection();
			ps = conn.prepareStatement(SQL);
			ps.setInt (1, videogioco.getPegi());
			ps.setString(2,videogioco.getDescrizione());
			ps.setString(3,videogioco.getTitolo());
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
}
