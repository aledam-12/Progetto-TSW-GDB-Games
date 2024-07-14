package model.beans;
import java.io.Serializable;

public class CopiaBean implements Serializable
{
    private static final long serialVersionUID = 1L;

    boolean stato;
    float percIva;
    float prezzo;
    int codiceCopia;
    int codiceAcquisto;
    String titoloVideogioco;
    String nomeConsole;

    public CopiaBean()
        {
            stato = false;
            percIva = 0.0f;
            prezzo = 0.0f;
            codiceCopia = 0;
            codiceAcquisto = 0;
            titoloVideogioco = " ";
            nomeConsole = " ";
        }

            public boolean getStato()
                {
                    return stato;
                }
            public void setStato(boolean stato)
                {
                    this.stato = stato;
                }

            public float getPercIva()
                {
                    return percIva;
                }
            public void setPercIva(float percIva)
                {
                    this.percIva = percIva;
                }

            public float getPrezzo()
                {
                    return prezzo;
                }
            public void setPrezzo(float prezzo)
                {
                    this.prezzo = prezzo;
                }

            public int getCodiceCopia()
                {
                    return codiceCopia;
                }
            public void setCodiceCopia(int codiceCopia)
                {
                    this.codiceCopia = codiceCopia;
                }

            public int getCodiceAcquisto()
                {
                    return codiceAcquisto;
                }
            public void setCodiceAcquisto(int codiceAcquisto)
                {
                    this.codiceAcquisto = codiceAcquisto;
                }

            public String getTitoloVideogioco()
                {
                    return titoloVideogioco;
                }
            public void setTitoloVideogioco(String titoloVideogioco)
                {
                    this.titoloVideogioco = titoloVideogioco;
                }

            public String getNomeConsole()
                {
                    return nomeConsole;
                }
            public void setNomeConsole(String nomeConsole)
                {
                    this.nomeConsole = nomeConsole;
                }
                
            @Override
            public String toString()
                {
                    return stato + ", " + prezzo + ", " + codiceCopia + ", " + codiceAcquisto + ", " + titoloVideogioco + ", " + nomeConsole;
                }
            @Override
            public boolean equals(Object b) {		
            	if (b==null) return false;
            	if (b.getClass() != this.getClass()) return false;
            	CopiaBean altraCopia = (CopiaBean) b;
            	if (this.codiceCopia == altraCopia.getCodiceCopia()) return true;
            	else {
            		if (this.nomeConsole.equals(altraCopia.getNomeConsole())&&
            				this.titoloVideogioco.equals(altraCopia.getTitoloVideogioco()))
            		return true;
            		else return false;
            	}
            	
            }
}