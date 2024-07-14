function updateQuantity(i, change, Titolo, Prezzo, Piattaforma) {
    var currentQuantity = parseInt(document.getElementById('quantita' + i).innerText);
    var newQuantity = currentQuantity + change;

    if (newQuantity < 1 || newQuantity > 10) {
        return; // Limita la quantità tra 1 e 10
    }

    // Invia la richiesta al server tramite AJAX
    var xhr = new XMLHttpRequest();
    var url = "./carrelloJSON?action=add&titolo=" + encodeURIComponent(Titolo) + "&prezzo=" + encodeURIComponent(Prezzo) + "&console=" + encodeURIComponent(Piattaforma) + "&quantita=" + encodeURIComponent(newQuantity);
    xhr.open("GET", url, true);
    let somma = 0;
    let totaleElementi = 0;
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            // Aggiorna la quantità visualizzata senza ricaricare la pagina
            document.getElementById('quantita' + i).innerText = newQuantity;
            var response = JSON.parse(xhr.responseText);

            for (var j = 0; j < response.length; j++) { //aggiorno riepilogo
            	
            	let quanTemp = parseInt(response[j].quantita);
            	let prezzoTemp = parseFloat (response[j].prezzo) * quanTemp;
            	
            	var copia = document.getElementById("el" + i);
                var quantita = document.getElementById("quantita" + i);
                var immagine = document.getElementById("immagine" + i);

                var titolo = copia.children[0];
                var piattaforma = copia.children[1];
                var prezzo = copia.children[2];

                titolo.innerText = response[j].titolo;
                piattaforma.innerText = "Console: "+response[j].console;
                prezzo.innerText = response[j].prezzo+"€";
                immagine.src = './getFoto?titolo=' + response[j].titolo;
                quantita.innerText = response[j].quantita;
                
                somma += prezzoTemp;
                totaleElementi += quanTemp;
                
            }
            console.log(totaleElementi);
            console.log(somma);
            document.getElementById("quanTotale").innerText = "Totale articoli: "+totaleElementi.toString();
            document.getElementById("prezzoTotale").innerText = "Totale: "+somma.toString()+"€";
        }
    };

    xhr.send();
}
