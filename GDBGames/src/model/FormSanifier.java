package model;

public class FormSanifier {
	public String sanitizeString (String input) {
		 return input.replaceAll("<", "").replaceAll(">", "").replaceAll("&", "").replaceAll("\"", "").replaceAll("'", "");
	} 
	public String sanitizeFloat (String number) {
		return number.replaceAll("[^0-9.]", "");
	}
}
