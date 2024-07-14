package model.beans;
import java.io.Serializable;
import java.time.LocalDate;

public class AcquistoBean implements Serializable   
{
    private static final long serialVersionUID = 1L;

    String emailcliente;
    int nCarta;
    int nFattura;
    LocalDate dataAcquisto;
    String via;
    int cap;
    String citta;
    float prezzoTotale;

    public AcquistoBean()
        {
            emailcliente = " ";
            nCarta = 0;
            nFattura = 0;
            dataAcquisto = LocalDate.of(1999,1,1);
            via = " ";
            cap = 0;
            citta = " ";
            prezzoTotale = 0.0f;
        }

    public String getemailcliente()
        {
            return emailcliente;
        }
    public void setemailcliente(String emailcliente)
        {
            this.emailcliente = emailcliente;
        }

    public int getnCarta()
        {
            return nCarta;
        }
    public void setnCarta(int ncarta)
        {
            this.nCarta = ncarta;
        }

    public int getnFattura()
        {
            return nFattura;
        }
    public void setnFattura(int nFattura)
        {
            this.nFattura = nFattura;
        }

    public LocalDate getdataAcquito()
        {
            return dataAcquisto;
        }
    public void setdataAcquisto(LocalDate dataAcquisto)
        {
            this.dataAcquisto = dataAcquisto;
        }

    public String getVia()
        {
            return via;
        }
    public void setVia(String via)
        {
            this.via = via;
        }

    public int getCap()
        {
            return cap;
        }
    public void setCap(int cap)
        {
            this.cap = cap;
        }

    public String getCitta()
        {
            return citta;
        }   
    public void setCitta(String citta)
        {
            this.citta = citta;
        }
    
    public float getPrezzoTotale()
        {
            return prezzoTotale;
        }
    public void setPrezzoTotale(float prezzototale)  
        {
            this.prezzoTotale = prezzototale;
        }

    @Override
    public String toString()
        {
            return nFattura + ", " + dataAcquisto + ", " + via + ", " + cap + ", " + citta + ", " + prezzoTotale; 
        }
}