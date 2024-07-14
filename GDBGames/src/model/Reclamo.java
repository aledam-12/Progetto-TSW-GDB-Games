package model;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.ReclamoBean;

public interface Reclamo {
	public void inseriscireclamo(ReclamoBean r) throws SQLException;
	public ArrayList<ReclamoBean> leggiReclami () throws SQLException;
	public boolean cancellareclamo(int n) throws SQLException;
}
