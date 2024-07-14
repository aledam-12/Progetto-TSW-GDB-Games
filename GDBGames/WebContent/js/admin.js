function MostraConferma (id) {
	let temp = document.getElementById("Conferma "+id);
		temp.style.display = "block";
		console.log("Elemento da mostrare: ", temp);

}
function EliminaProdotti (a, id) {
	let temp = document.getElementById("Elimina "+id); 
	if (a) {
		temp.style.display = "block";
		let tempConferma = document.getElementById("BottoneElimina "+id);
		tempConferma.addEventListener("click", function () {
			event.preventDefault();
			MostraConferma(id);
		});
	}
	else {
		temp.style.display = "none";
	}
}

function ModificaProdotti (a, id) {
	let temp = document.getElementById ("Modifica "+id);
	if (a) {
		temp.style.display = "block";
	}
	else  {
		temp.style.display = "none";
	}
	
}
function ModificaVideogioco (a, id) {
	let temp = document.getElementById ("ModificaVideogioco "+id);
	if (a) {
		temp.style.display = "block";
	}
	else {
		temp.style.display = "none";
	}
		
}