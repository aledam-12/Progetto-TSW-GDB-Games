package model;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import model.beans.AcquistoBean;
import model.beans.ClienteBean;


public class Fattura {
	private final float rientroX = 32.314f;
	private final float righeTabella[] = {460, 420, 380, 320, 280}; //per asse Y
	private String Directory = "C:/Users/alexa/git/Progetto-TSW-GDB-Games/WebContent/pdf";
	private String pathTemplate = Directory + "/template fattura.pdf";
	private String path = Directory + "/fattura ";
	
	private void scriviPrimaIntestazione (LocalDate data, int nFattura, PDPageContentStream stream, PDPage pagina) throws IOException{
		float altezza = pagina.getCropBox().getHeight();
		stream.beginText();
		stream.setFont(PDType1Font.HELVETICA, 11);
		stream.newLineAtOffset(rientroX, altezza - 125.212f);
		stream.showText(data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
		stream.newLineAtOffset(rientroX + 149.425f, 0);
		stream.showText(Integer.toString(nFattura));
		stream.endText();
	}
	private void scriviIntestazioneIndirizzo (AcquistoBean acquisto, int nFattura, PDPageContentStream stream, PDPage pagina) throws IOException {
		float altezza = pagina.getCropBox().getHeight();
		stream.beginText();
		stream.setFont(PDType1Font.HELVETICA, 11);
		stream.newLineAtOffset(rientroX, altezza - 210f);
		stream.showText(acquisto.getVia());
		stream.newLineAtOffset(0, -14f);
		stream.showText(acquisto.getCitta() + ", " + acquisto.getCap());
		stream.endText();
	}
	private void scriviTabellaFinale (float iva, float totale, PDPageContentStream stream, PDPage pagina) throws IOException{
		float OffsetTabellaX = 453f;
		float OffsetTabellaY = 198;
		stream.beginText();
		stream.setFont(PDType1Font.HELVETICA, 11);
		stream.newLineAtOffset(OffsetTabellaX, OffsetTabellaY);
		stream.showText(Float.toString(iva)+"%");
		stream.newLineAtOffset(0, -14.5f);
		stream.showText(Float.toString(totale)+"€");
		stream.endText();
	}
	private void scriviTabellaCentrale (PDPageContentStream stream, PDPage pagina, List<OrdineCopia> ordini) throws IOException {
		int i = 0;	
		for (OrdineCopia ord : ordini) {
			scriviRiga(ord, i, pagina, stream);
			i++;
			}
	}
	private void scriviDescrizione (String titoloVideogioco, String nomeConsole, PDPageContentStream stream, PDPage pagina, float offsetRiga) throws IOException{
		stream.beginText();
		String descrizione = titoloVideogioco + ", "+ nomeConsole;
		int lunghezza = descrizione.length();
		int righe = lunghezza / 50;
		int finale = righe * 50;
		int offset = 0;
		if (lunghezza < 32) {		//rimpicciolisce grandezza font in base alla lunghezza della descrizione
			stream.setFont(PDType1Font.HELVETICA, 10);
			stream.newLineAtOffset(120, offsetRiga);
		}
		else if (lunghezza < 64) {
			stream.newLineAtOffset(90, offsetRiga);
			stream.setFont(PDType1Font.HELVETICA, 9);
		}
		else {
			stream.newLineAtOffset(60, offsetRiga);
			stream.setFont(PDType1Font.HELVETICA, 8);
		}	
		while (offset < righe) {
			int lower = offset * 50;
			int upper = offset * 50 + 50;
			stream.showText(descrizione.substring(lower, upper));
			++offset;
			stream.newLineAtOffset(0, - 10f);
		}
		stream.showText(descrizione.substring(finale));
		stream.endText();
	}
	private void scriviRiga (OrdineCopia ord, int cont, PDPage pagina, PDPageContentStream stream) throws IOException{
		stream.beginText();
		stream.newLineAtOffset(60, righeTabella[cont]);
		stream.showText(Integer.toString(ord.getQuantità()));
		stream.endText();
		scriviDescrizione (ord.getTitoloVideogioco(), ord.getNomeConsole(), stream, pagina, righeTabella[cont]);
		stream.beginText();
		stream.newLineAtOffset(380, righeTabella[cont]);
		stream.showText(Float.toString(ord.getPrezzo())+"€");
		stream.newLineAtOffset(120, 0);
		stream.showText(Float.toString(ord.getPrezzoTotale())+"€");
		stream.endText();
	}
	private void scriviPagina (PDDocument documento, PDPage pagina, ArrayList<OrdineCopia> ordini, AcquistoBean acquisto) throws IOException{
		PDPageContentStream stream = new PDPageContentStream(documento, pagina, PDPageContentStream.AppendMode.APPEND, true);
		scriviPrimaIntestazione(acquisto.getdataAcquito(), acquisto.getnFattura(), stream, pagina);
		scriviIntestazioneIndirizzo(acquisto, acquisto.getnFattura(), stream, pagina);
		scriviTabellaCentrale(stream, pagina, ordini);
		scriviTabellaFinale (ordini.get(0).getPercIva(),(float) ordini.stream().mapToDouble(OrdineCopia::getPrezzoTotale).sum(), stream, pagina);
		stream.close();
	}
	
	public boolean creaFattura (int id, ClienteBean cliente) throws IOException, SQLException{
		File temp = new File (path+ id+".pdf");
		if (temp.exists()) return false; //se il file esiste termina così non viene ricreato
		
		else {
			AcquistoDAO adao = new AcquistoDAO();
			ProdottiDAO pdao = new ProdottiDAO();
			AcquistoBean acquisto = adao.leggiDaId(id);
			PDDocument documento = PDDocument.load(new File (pathTemplate));
			PDDocument fattura = new PDDocument();
			ArrayList <OrdineCopia> ordini = pdao.leggiDaNFattura(acquisto);
			PDPage paginaTemplate = documento.getPage(0);
			fattura.addPage(paginaTemplate);
			scriviPagina(fattura, fattura.getPage(0), ordini, acquisto);
			fattura.save(temp);
			fattura.close();
			documento.close();
			return true;
		}
				
	} 
}