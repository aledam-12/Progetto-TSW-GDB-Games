package model;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.AcquistoBean;
import model.beans.CopiaBean;
import model.beans.VideogiocoBean;

public interface Prodotti
{
	public ArrayList <OrdineCopia> cercaProdotti (String titolo, String console) throws SQLException; 
	public ArrayList <OrdineCopia> cercaProdottiLimite (String titolo, String console) throws SQLException;
	public CopiaBean leggiOrdineCopia (OrdineCopia ord) throws SQLException;
	public ArrayList <OrdineCopia> getProdotti () throws SQLException;
	public void cambiaIVA (float IVA) throws SQLException;
	public ArrayList <OrdineCopia> leggiDaNFattura(AcquistoBean acquisto) throws SQLException; 
	public void UpdateCopia (OrdineCopia copia, AcquistoBean acquisto) throws SQLException;
	public int getQuantità (CopiaBean copia) throws SQLException;
	public void inserisciProd(VideogiocoBean v) throws SQLException;
	public void inscopia(CopiaBean copia) throws SQLException;
	public boolean cancellaprod(String titolo) throws SQLException;
	public void cancellaCopia(CopiaBean copia) throws SQLException;
	public CopiaBean leggiCopia(int codice) throws SQLException;
	public ArrayList<CopiaBean> leggiTutteCopie (String sort) throws SQLException;
	public void modificaprod(String m, String d) throws SQLException;
	public void modificaCopia(String m, int s) throws SQLException;
	public void modificaCopiaIva(String m, float iva) throws SQLException;
	public void modificaCopia(CopiaBean copia, String newConsole, float newPrezzo) throws SQLException;
	public void modificaCopia(String m, int s, float pre) throws SQLException;
	public void modificaCopia(String m, float iva, float pre) throws SQLException;
	public void modificaCopia(String m, int s, float iva, float pre) throws SQLException;
	public void modificaCopiaIva(String m, int s, float iva) throws SQLException;
	
	
}