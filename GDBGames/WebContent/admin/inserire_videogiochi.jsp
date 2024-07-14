<!DOCTYPE html>
<html lang="it">
	<head>
		<meta charset="UTF-8">
		<link rel ="stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/css/stile_inserire_copia.css" >
		<title>Inserimento videogioco</title>
	<script>
  function sanitizeInput(input) {
    return input.replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#x27;").replace(/\//g, "&#x2F;");
  }

  function sanitizeForm(event) {
    // Sanifica il campo titolo
    const titoloInput = document.getElementById('titolo');
    titoloInput.value = sanitizeInput(titoloInput.value);

    // Sanifica il campo descrizione
    const descrizioneInput = document.getElementsByName('descrizione')[0]; // Seleziona il primo elemento con name='descrizione'
    descrizioneInput.value = sanitizeInput(descrizioneInput.value);

    // Sanifica il campo pegi
    const pegiInput = document.getElementById('pegi');
    pegiInput.value = sanitizeInput(pegiInput.value);
  }

  document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form');
    form.addEventListener('submit', sanitizeForm);
  });
</script>
	</head>
	<body>
	<div class = "videogioco">
		<form action="./insert?type=videogioco" method="post" enctype="multipart/form-data">
  			
  			<div class="error-message">I campi con l'asterisco (*) sono obbligatori.</div>
  
  			<label for="titolo">Titolo: <span class="required">*</span></label>
  			<input type="text" id="titolo" name="titolo" placeholder="God of War Ragnarock" required>
  			<label for="descrizione">Descrizione: <span class="required">*</span></label>
  			<textarea name="descrizione" placeholder="breve descrizione del prodotto" required></textarea>
 	 		<label for="pegi">Pegi (3-18): <span class="required">*</span></label>
 	 		<input type = "number" name = "pegi" placeholder="10" id="pegi" max="18" min ="3">
	    	<input type = "file" class= "file" name = "fotoVideogioco" accept="image/*"> <br><br>
	    	<input type = "submit" value="Invia">
	    	<input type = "reset" value="Cancella">
	    </form>
	    </div>
	</body>
</html>
