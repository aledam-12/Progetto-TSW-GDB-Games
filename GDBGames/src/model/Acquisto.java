package model;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import model.beans.AcquistoBean;

public interface Acquisto {
	public ArrayList<AcquistoBean> cercaOrdini (LocalDate inizio, LocalDate fine, String email) throws SQLException;
	public AcquistoBean leggiDaId(int id) throws SQLException;
	public Collection<AcquistoBean> leggiPerEmail(String code) throws SQLException;
	public Collection<AcquistoBean> leggiTuttiOrdini(String order) throws SQLException;
	public void inserisciOrdine(AcquistoBean acquistoBean) throws SQLException;
}
