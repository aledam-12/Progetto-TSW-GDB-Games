package model;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.VideogiocoBean;

public interface Videogioco {
	public VideogiocoBean leggiVideogioco(String titolo) throws SQLException;
	public void inserisciVideogioco(VideogiocoBean videogioco) throws SQLException;
	public ArrayList <VideogiocoBean> leggiTutti () throws SQLException;
	public void modificaprod(String titolo, String descrizione, int pegi) throws SQLException;
	public void modificaprod(String titolo, int pegi) throws SQLException;
}
