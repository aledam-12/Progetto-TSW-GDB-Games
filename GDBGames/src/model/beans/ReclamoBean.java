package model.beans;
import java.io.Serializable;
import java.time.LocalDate;

public class ReclamoBean implements Serializable
{
    private static final long serialVersionUID = 1L;

    LocalDate dataReclamo;
    int nReclamo;
    String titolo;
    String contenuto;
    String emailCliente;

    public ReclamoBean()
        {
            dataReclamo = LocalDate.of(1999,1,1);
            nReclamo = 0;
            titolo = " ";
            contenuto = " ";
            emailCliente = " ";
        }
    
    public LocalDate getDataReclamo()
        {
            return dataReclamo;
        }
    public void setdatareclamo(LocalDate datareclamo)
        {
            this.dataReclamo = datareclamo;
        }
    
    public int getnReclamo()
        {
            return nReclamo;
        }
    public void setnReclamo(int nReclamo)
        {
            this.nReclamo = nReclamo;
        }
    
    public String getTitolo()
        {
            return titolo;
        }
    public void settitolo(String titolo)
        {
            this.titolo = titolo;
        }

    public String getContenuto()
        {
            return contenuto;
        }
    public void setcontenuto(String contenuto)
        {
            this.contenuto = contenuto;
        }

    public String getEmailCliente()
        {
            return emailCliente;
        } 
    public void setEmailCliente(String emailCliente)
        {
            this.emailCliente = emailCliente;
        }
    
    @Override
    public String toString()
        {
            return dataReclamo + ", " + nReclamo + ", " + titolo + ", " + contenuto + ", " + emailCliente;           
        }
}