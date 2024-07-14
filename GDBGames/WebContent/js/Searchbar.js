let formConsole = document.getElementById("Searchbar Console");
let formTitolo = document.getElementById("Searchbar Titolo");
var xhr = new XMLHttpRequest();
formTitolo.addEventListener('input', function (event) {
	let titolo = formTitolo.value;
	let piattaforma = formConsole.value;
	if (titolo === "") {
	rimuoviRighe();				//resetta la ricerca quando l'input è vuoto
	return;
	} 
	xhr.open("POST", "./SearchbarJSON", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.setRequestHeader("Accept", "application/json");
	xhr.send("search=" + encodeURIComponent(titolo) + "&console=" + encodeURIComponent(piattaforma));
});
formConsole.addEventListener('change', function (event) {
	xhr.open("POST", "./SearchbarJSON", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.setRequestHeader("Accept", "application/json");
	let titolo = formTitolo.value;
	let piattaforma = formConsole.value;
	xhr.send("search=" + encodeURIComponent(titolo) + "&console=" + encodeURIComponent(piattaforma));
});

function rimuoviRighe() {
    // Rimuovere tutte le righe esistenti nella tabella
    let tabella = document.getElementById("SearchResult");
	while (tabella.rows.length >= 1) {
        tabella.deleteRow(0);
    }
}

xhr.onreadystatechange = function() {
  if (xhr.readyState === 4 && xhr.status === 200) {
    var response = JSON.parse(xhr.responseText);

    var table = document.getElementById("SearchResult");
    table.style.color = "white";
    rimuoviRighe();
    if (response.length==0) {
    	var row = table.insertRow(0);
    	var noResult = row.insertCell(0);
    	noResult.innerText = "Nessun risultato";
    	noResult.style.colSpan = "3";
    	 noResult.style.textAlign = "center"; // Imposta l'allineamento del testo al centro
    	return;
    }
    for (var i = 0; i < response.length; i++) {
  var row = table.insertRow(i); 
  
  var cellImg = row.insertCell(0);		//si mette anche il link per il dettaglio
  cellImg.innerHTML = '<a href="controlloCatalogo?titolo='+response[i].titolo+'&console='+response[i].console+'&prezzo='+response[i].prezzo+'" '+'target="_blank">' + 
  '<img src = "./getFoto?titolo='+ response[i].titolo + '"alt = "immagine non trovata">'+"</a>";
  
  var cellTitolo = row.insertCell(1);
  cellTitolo.innerHTML = response[i].titolo;

  var cellConsole = row.insertCell(2);
  cellConsole.innerHTML = response[i].console;
}    

  }
};
