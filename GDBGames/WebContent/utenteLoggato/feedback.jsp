<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Inserimento feedback</title>
<link rel ="stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/css/feedback.css" > 
<script>
  function sanitizeInput(input) {
    return input.replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#x27;").replace(/\//g, "&#x2F;");
  }

  function sanitizeForm(event) {
    const inputs = document.querySelectorAll('input[type="text"], textarea');
    inputs.forEach(input => {
      input.value = sanitizeInput(input.value);
    });
  }

  document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form');
    form.addEventListener('submit', sanitizeForm);
  });
</script>
</head>
<body>
<%@include file="../header.jsp" %>

<div class= "reclamo">
<form action="./reclamo" method="post">
    <div class="error-message">
    	<p><h3><b>I campi con l'asterisco (*) sono obbligatori.</b></h3></p>
    </div>
  
  <fieldset>
    <legend><h3><b>Feedback</b></h3></legend>
    
    <label for="titolo">Titolo: <span class="required">*</span></label> <br>
    <input type="text" placeholder="God of War Ragnarock" id="titolo" name="titolo" required><br>
    
    <label for="messaggio">Messaggio: <span class="required">*</span></label> <br>
    <textarea id="messaggio" name="descrizione" placeholder="breve descrizione del problema riscontrato" required></textarea> <br>
  </fieldset>
  
  <input type="submit" value="Invia feedback">
</form>
</div>
<div class="f"><%@include file="../footer.jsp" %></div>
</body>
</html>
