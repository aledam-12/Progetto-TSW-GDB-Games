package model;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.ConsoleBean;

public interface Console {
	public ArrayList<ConsoleBean> leggiConsole () throws SQLException;
	public void aggiungiConsole (String nomeConsole) throws SQLException;
}
