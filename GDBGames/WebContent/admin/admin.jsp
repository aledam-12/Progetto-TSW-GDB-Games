<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel ="stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/css/funAdmin.css" >
		<title>GDB-Games</title>
<script>
  function sanitizeInput(input) {
    return input.replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#x27;").replace(/\//g, "&#x2F;");
  }

  function sanitizeForm(event) {
    // Sanifica il campo email-cliente
    const emailClienteInput = document.querySelector('input[name="email-cliente"]');
    emailClienteInput.value = sanitizeInput(emailClienteInput.value);

    // Sanifica il campo inizio
    const inizioInput = document.getElementById('dataInzio');
    inizioInput.value = sanitizeInput(inizioInput.value);

    // Sanifica il campo fine
    const fineInput = document.getElementById('dataFine'); 
    fineInput.value = sanitizeInput(fineInput.value);
  }

  document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form[action="../admin/adminCheck"]');
    form.addEventListener('submit', sanitizeForm);
  });
</script>
	</head>
	<body>
		<jsp:include page="../header.jsp" />
		<div class= "titoli">
			<h2>inserisci nuovi prodotti:</h2>
			<h2>inserisci copia del prodotto:</h2>
		</div> 
		<div class="inserisci">
			<%@ include file = "inserire_videogiochi.jsp"%><br>
			<%@ include file = "inserire_copia.jsp"%> <br>
		</div>
				<h2 class = "h">Ricerca ordini:</h2>
				<div class = "ordini">
				<form method="GET" action = "../admin/adminCheck">
						<div class="error-message">I campi con l'asterisco (*) sono obbligatori.</div>
						<label for="email"> Email: <span class="required">*</span></label>
						<input type="email" name="email-cliente" placeholder="example@mail.com">
						<label for="data inizio"> Data Inizio: <span class="required">*</span></label>
						<input type="date" name="inizio" id="dataInzio" required>
						<label for="data fine"> Data fine: <span class="required">*</span></label>
						<input type="date" name="fine" id="dataFine" required>
						<input type="submit" value="Cerca">
						<input type="reset" value="Cancella">
						<button onclick='window.location.href="../admin/adminCheck"'>Tutti gli ordini</button>
				</form>
			</div>
		<h2>Visualizzazione ordini:</h2>
		<%@ include file = "visualizza ordini.jsp"%>
		<h2>Modifiche del catalogo:</h2>
		<%@ include file="visualizza prodotti.jsp" %>
			<h2 class = "h"> Ricerca clienti: </h2>
			<div class = "cl">
				<form method="GET" action = "../admin/adminCheck">
						<div class="error-message">I campi con l'asterisco (*) sono obbligatori.</div>
						<label for="email"> Email: <span class="required">*</span></label>
						<input type="email" name="email-utente" placeholder="example@mail.com"> <br><br>
						<input type="submit" value = "Cerca">
				</form>
				</div>
				<h2>Visualizzazione clienti:</h2>
		<%@ include file = "visualizza clienti.jsp" %>
		<%@ include file = "visualizza_reclami.jsp" %>
	</body>	
</html>
