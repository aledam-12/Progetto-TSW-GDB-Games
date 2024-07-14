package model;
import java.io.Serializable;

import model.beans.CopiaBean;
public class OrdineCopia extends CopiaBean implements Serializable  {
	private static final long serialVersionUID = 1L;
	private int quantit�;
public int getQuantit�() {
	return this.quantit�;
}
public void setQuantit� (int q) {
	if (q < 0) return;
	else this.quantit� = q;
}
	public OrdineCopia (int quantit�, CopiaBean a) {
	super();
	setCodiceAcquisto(a.getCodiceAcquisto());
	setCodiceCopia(a.getCodiceCopia());
	setTitoloVideogioco(a.getTitoloVideogioco());
	setNomeConsole(a.getNomeConsole());
	setPrezzo(a.getPrezzo());
	setStato(false);
	setPercIva(a.getPercIva());
	setQuantit�(quantit�);
	}
	public OrdineCopia (CopiaBean a) {
	super();
	setCodiceAcquisto(a.getCodiceAcquisto());
	setCodiceCopia(a.getCodiceCopia());
	setTitoloVideogioco(a.getTitoloVideogioco());
	setNomeConsole(a.getNomeConsole());
	setPrezzo(a.getPrezzo());
	setStato(false);
	setPercIva(a.getPercIva());
	}
	public float getPrezzoTotale() {
		return this.getPrezzo() * this.quantit�;
	}
    @Override
    public boolean equals(Object b) {		
    	if (b==null) return false;
    	if (b.getClass() != this.getClass()) return false;
    	OrdineCopia altraCopia = (OrdineCopia) b;
    		if (this.getNomeConsole().equals(altraCopia.getNomeConsole())&&
    				this.getTitoloVideogioco().equals(altraCopia.getTitoloVideogioco()) && this.getPrezzo() == altraCopia.getPrezzo())
    		return true;
    		else return false;
    	}
	@Override public String toString() {
		return super.toString() + "quantit�: "+ this.quantit�;
	}
}
