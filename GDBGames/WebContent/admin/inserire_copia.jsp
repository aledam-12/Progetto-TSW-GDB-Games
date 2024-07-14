<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<link rel ="stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/css/stile_inserire_copia.css" > 
<script>
  function sanitizeInput(input) {
    return input.replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#x27;").replace(/\//g, "&#x2F;");
  }

  function sanitizeForm1(event) {
    // Sanifica il campo titolo
    const titoloInput = document.getElementById('titolo');
    titoloInput.value = sanitizeInput(titoloInput.value);

    // Sanifica il campo console
    const consoleInput = document.getElementById('console');
    consoleInput.value = sanitizeInput(consoleInput.value);

    // Sanifica il campo iva
    const ivaInput = document.getElementById('iva');
    ivaInput.value = sanitizeInput(ivaInput.value);

    // Sanifica il campo prezzo
    const prezzoInput = document.getElementById('prezzo');
    prezzoInput.value = sanitizeInput(prezzoInput.value);

    // Sanifica il campo quantita
    const quantitaInput = document.getElementById('quantita');
    quantitaInput.value = sanitizeInput(quantitaInput.value);
  }

  function sanitizeForm2(event) {
    // Sanifica il campo percIva
    const percIvaInput = document.getElementById('percIva');
    percIvaInput.value = sanitizeInput(percIvaInput.value);
  }

  document.addEventListener('DOMContentLoaded', function() {
    const form1 = document.querySelector('form[action="./insert?type=copia"]');
    form1.addEventListener('submit', sanitizeForm1);

    const form2 = document.querySelector('form[action="./insert?type=changeIVA"]');
    form2.addEventListener('submit', sanitizeForm2);
  });
</script>
</head>
<body>
<div class = "t">
<div class="copia">
<form action="./insert?type=copia" method="post">
  <div class="error-message">I campi con l'asterisco (*) sono obbligatori.</div>
  
  			 <label for="titolo">Titolo: <span class="required">*</span></label>
 			 <input type="text" placeholder="God of War Ragnarock" id="titolo" name="titolo" required>
  
 			 <label for="console">Console: <span class="required">*</span></label>
 			 <input type="text" placeholder="PC" id="console" name="console" required>
  
  			<label for="iva">Percentuale IVA: <span class="required">*</span></label>
  			<input type="number" placeholder="22" id="iva" name="iva" min="0" max="100" step="1.0" required>
  
  			<label for="prezzo">Prezzo: <span class="required">*</span></label>
  			<input type="number" placeholder="59.99" id="prezzo" name="prezzo" step="1.0" required>
  
  			<label for="quantità">Quantit&agrave;: <span class="required">*</span></label>
  			<input type="number" placeholder="3" id="quantita" name = "quantita" step="1" required>
  			<input type="submit" value="Invia">
  			<input type="reset" value="Cancella">
		</form>
	</div>
	<div class="copia" id="form-IVA">
		<form action="./insert?type=changeIVA" method="post">
			<label for="iva"> Modifica IVA: <span class="required">*</span></label>
			<input type="number" id="percIva" name="percIva" min="1" max="100" step="1" value="22" required>
			<input type="submit" value="Invia">
			<input type="reset" value="Cancella"> 
		</form>
	</div>
</div>
</body>
</html>
