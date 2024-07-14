package model.beans;
import java.io.Serializable;

public class ClienteBean implements Serializable 
{
    private static final long serialVersionUID = 1L;

    String email;
    String nome;
    String cognome;
    String pw;
    String via;
    int civico;
    String citta;
    String provincia;
    int cap;
    String stato;

    public ClienteBean()
        {
            email = " ";
            nome = " ";
            cognome = " ";
            pw = " ";
            via = " ";
            civico = 0;
            citta = " ";
            provincia = " ";
            cap = 0;
            stato = " ";
        }

    public String getEmail()    
        {
            return email;
        }
    public void setEmail(String email)
        {
            this.email = email;
        }
    
    public String getNome()
        {
            return nome;
        }
    public void setNome(String nome)
        {
            this.nome = nome;
        }

    public String getCognome()
        {
            return cognome;
        }
    public void setCognome(String cognome)
        {
            this.cognome = cognome;
        }

    public String getPw()   
        {
            return pw;
        }
    public void setPw(String pw)
        {
            this.pw = pw;
        }

        public String getVia()    
        {
            return via;
        }
    public void setVia(String via)
        {
            this.via = via;
        }

    public int getCivico()    
        {
            return civico;
        }
    public void setCivico(int civico)
        {
            this.civico = civico;
        }

    public String getCitta()    
        {
            return citta;
        }
    public void setCitta(String citta)
        {
            this.citta = citta;
        }

    public String getProvincia()    
        {
            return provincia;
        }
    public void setProvincia(String provincia)
        {
            this.provincia = provincia;
        }

    public int getCap()    
        {
            return cap;
        }
    public void setCap(int cap)
        {
            this.cap = cap;
        }

    public String getStato()    
        {
            return stato;
        }
    public void setStato(String stato)
        {
            this.stato = stato;
        }

    @Override
    public String toString()
        {
            return email + ", " + nome + ", " + cognome +", " + pw +", " + via+", " + civico +", " + citta +", " + provincia +", " + cap +", " + stato;
        }
}
