<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/stile_login.css">
</head>
<body> 
	
	<%
		Boolean feedbackEmail = (Boolean) request.getAttribute("feedbackEmail");
		Boolean feedbackPw = (Boolean) request.getAttribute("feedbackPw");
		Boolean cambioForm = (Boolean) request.getAttribute("cambioForm");
	%>
	<jsp:include page="header.jsp" />



	<div class="logSig">
		<div class="form-container">
			<div id="loginForm" style="display: block">
				<form action="./Login" method="post">
					<fieldset name="group-user">
						<legend>
							<h2>
								<b>Accesso</b>
							</h2>
						</legend>
						<div class="error-message">I campi con l'asterisco (*) sono
							obbligatori.</div>
						<label for="username">Username: <span class="required">*</span></label><br>
						<input type="text" name="username" placeholder="username@domain.com" id = "username" required>
						<div class="feedback" id="EmailRegistrata" style="display: none">Email registrata</div>
						<div class="feedback" id="EmailNonRegistrata" style="display: none">Utente non registrato</div>
						<br> <label for="password">Password: <span class="required">*</span></label><br> 
							<%if (feedbackPw != null) {		//per la pw errata
			if (!feedbackPw.booleanValue()) {%><div class = "feedback" id="PwErrata">password errata, riprova</div><%}}%>
						<input type="password" placeholder ="xxxxxxxx" name="password" required><br>
					</fieldset>		
					
												<%								//dà feedback sulla email, sulla registrazione e sull'esito del login
		Boolean feedbackReg = (Boolean) session.getAttribute("feedbackReg");
		if (feedbackReg != null) {
			if (feedbackReg.booleanValue()) {
	%>
	<div class = "feedback" id="RegistrazioneCorretta">Registrazione correttamente effettuata!</div>
	<%
		} else {
	%>
	<div class="feedback" id="EmailRegistrata"> Utente gi&agrave; registrato, accedi</div>
	<%}}%>
	<%
		if (feedbackEmail != null) {
			if (feedbackEmail.booleanValue()) {
	%>
	<div class = "feedback" id="UtenteNonRegistrato">Utente non registrato</div>
	<%
		} else {
	%>
	<div class="feedback" id="EmailRegistrata">utente gi&agrave; registrato, accedi</div>
	<% }}%>
					
					
					
					<input type="submit" class="botton" value="Accesso">
					<button type = "button" class="botton" onclick="changeForm('Sign Up')">Non hai un account?</button>
				</form>
			</div>
				<%@ include file="Register.jsp"%>
		</div>
	</div>

	<div class="f"><%@ include file="footer.jsp"%></div>
	<script type="text/javascript" src='${pageContext.request.contextPath}/js/Login.js'></script>
</body>
</html>
