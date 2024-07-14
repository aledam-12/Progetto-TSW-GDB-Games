<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<body>
	<div id="signUpForm" style="display:none">
		<form action = "./controlloRegistrazione" method="post">
			<div class="error-message">I campi con l'asterisco (*) sono obbligatori.</div>
			<fieldset>
				<legend><h2><b>Dati anagrafici</b></h2></legend>
					<label for="nome">Nome: <span class="required">*</span></label> <br>
					<div id = "nome-feed" style = "display:none">Nome non valido, solo caratteri alfanumerici</div><br>
					<input type="text" placeholder="Mario" name="nome" id = "reg-nome" required> <br>
					
					<label for="cognome">Cognome: <span class="required">*</span></label> <br>
					<div id = "cognome-feed" style = "display:none">Cogome non valido, solo caratteri alfanumerici</div><br>
					<input type="text" placeholder="Rossi" name="cognome" id = "reg-cognome" required> <br>
					
			</fieldset>
			<fieldset>
				<legend><h2><b>Indirizzo per la spedizione</b></h2></legend>
					<label for="indirizzo">Indirizzo: <span class="required">*</span></label> <br>
					<div id = "via-feed" style = "display:none">Indirizzo non valido, solo caratteri alfanumerici</div>
					<input type="text" name="Via" placeholder="Via Roma" id = "reg-via"required> <br>
					
					<label for="civico">Numero Civico: <span class="required">*</span></label> <br>
					<div id = "civico-feed" style = "display:none">Numero civico non valido</div><br>
					<input type="number" name="NCivico" placeholder="7" max = "999" min="1" id = "reg-civico"required> <br>
					
					<label for="Città ">Citt&agrave;: <span class="required">*</span></label> <br>
					<div id = "citta-feed" style = "display:none">Citt&agrave; non valida</div><br>
					<input type="text" name="città" placeholder="Roma" id = "reg-citta" required> <br>
					
					<label for="CAP">CAP: <span class="required">*</span></label> <br>
					<div id = "cap-feed" style = "display:none">CAP non valido</div><br>
					<input type="number" name="cap" placeholder="11111" max = "99999" min="0" id = "reg-cap"required> <br>
					
					<label for="provincia">Provincia: <span class="required">*</span></label><br>
					<div id = "provincia-feed" style = "display:none">Provincia non valida, inserire solo le prime due lettere in maiuscolo</div><br>
					<input type = "text" name="provincia" placeholder="SA" id = "reg-provincia"><br>
			</fieldset>
			<fieldset>
				<legend><h2><b>Credenziali per l'accesso</b></h2></legend>
					<label for="email">Email: <span class="required">*</span></label>   <div class="feedback" id="EmailRegistrataReg" style="display: none">Email gi&agrave; registrata</div><br>   
					<div id = "email-feed" style = "display:none">Formato email non valido</div><br>
					<input type="email" name="emailCliente" placeholder="example@domain.com" id = "emailReg"required> <br>
					
					<label for="password">Password: <span class="required">*</span></label> <br>
					<div id = "password-feed" style = "display:none">La pw deve contenere da 3 a 16 caratteri alfanumerici </div><br>
					<input type="password" name="pwCliente" placeholder="xxxxxx" id = "reg-password"required> <br>
			</fieldset>
				<input type = "submit" class = "botton" value = "Invia">
				<input type = "reset" class = "botton" value = "Cancella">
			</form>
			</div>
			<script src="${pageContext.request.contextPath}/js/formValidation.js"></script>
	</body>
</html> 