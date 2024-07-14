package model;
import model.beans.ClienteBean;
import java.sql.*;
import java.util.ArrayList;

public interface Cliente
{
    public void inserisciCliente(ClienteBean c) throws SQLException;
    public ClienteBean leggiCliente(String e) throws SQLException;
    public boolean isRegistrato (ClienteBean cliente) throws SQLException;
    public boolean controlloFattura (ClienteBean cliente, int id) throws SQLException;
    public ArrayList <ClienteBean> leggiTutti () throws SQLException;
    public void modifiCliente(String email, String via, int civico) throws SQLException;
    public void modifiCliente(String email, String città, String provincia, int cap) throws SQLException;
}