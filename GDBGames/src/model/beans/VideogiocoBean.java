package model.beans;
import java.io.Serializable;

public class VideogiocoBean implements Serializable
{
    private static final long serialVersionUID = 1L;

    String titolo;
    String descrizione;
    int pegi;

    public VideogiocoBean()
        {
            titolo = " ";
            descrizione = " ";
            pegi = 0;
        }

    public String getTitolo()
        {
            return titolo;
        }
    public void setTitolo(String titolo)
        {
            this.titolo = titolo;
        }

    public String getDescrizione()
        {
            return descrizione;
        }
    public void setDescrizione(String descrizione)
        {
            this.descrizione = descrizione;
        }

    public int getPegi()
        {
            return pegi;
        }
    public void setPegi(int pegi)
        {
            this.pegi = pegi;
        }

    @Override
    public String toString()
        {
            return titolo + ", " + descrizione + ", "+ pegi;
        }
}