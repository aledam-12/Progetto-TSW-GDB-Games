<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import = "model.beans.ClienteBean,model.AcquistoDAO,model.beans.AcquistoBean,java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>GDB-Games</title>
	<link rel ="stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/css/account.css" >
</head>
<body>
	  <%
	  	ClienteBean cliente = (ClienteBean) request.getSession().getAttribute("cliente"); 
	  		  AcquistoDAO adao = new AcquistoDAO();
	  	  	  ArrayList <AcquistoBean> ordini = (ArrayList <AcquistoBean>)adao.leggiPerEmail(cliente.getEmail());
	  %>  
	<jsp:include page="../header.jsp"/>

    <%
    	Boolean reclamoFlag = (Boolean)(request.getAttribute("recalmoFlag"));
    %>

    <%
    	if (reclamoFlag != null && reclamoFlag.booleanValue()) {
    %>
        <div class="recalmo-inviato">
           <p>reclamo inviato correttamente</p>
        </div>
     <%
     	}
     %>
		
		<h2> Benvenuto <%=cliente.getNome()%>!</h2>
		<button class = "botton"> <a href="${pageContext.request.contextPath}/utenteLoggato/Logout"> Logout </a></button>
		<%
    		if (cliente != null && cliente.getStato().equalsIgnoreCase("admin")) {
		%>
        <button class = "botton"><a href="${pageContext.request.contextPath}\admin\admin.jsp">Funzionalit&agrave;  amministratore</a></button>
		<%
    		}
		%>
		<button class= "botton" onclick = "ModificaUtente (true)" >Modifica i tuoi dati</button>
			<div class="form-container" id ="Modifica" style = "display:none">
				<form action = "./userUpdate" method = "post">
				<div class="error-message">I campi con l'asterisco (*) sono obbligatori.</div>
					<fieldset>
						<legend><b>Indirizzo per la spedizione</b></legend>
							<label for="indirizzo">Indirizzo: <span class="required">*</span></label> <br>
							<input type="text" name="via" value = "<%=cliente.getVia()%>"required> <br>
							<label for="civico">Numero Civico: <span class="required">*</span></label> <br>
							<input type="number" name="civico" value = "<%=cliente.getCivico() %>" max = "999" min="1" required> <br>
							<label for="Città">Citt&agrave;: <span class="required">*</span></label> <br>
							<input type="text" name="citta" value = "<%=cliente.getCitta() %>" required> <br>
							<label for="CAP">CAP: <span class="required">*</span></label> <br>
							<input type="number" name="cap" value = "<%=cliente.getCap() %>" max = "99999" min="0" required> <br>
							<label for="provincia">Provincia: <span class="required">*</span></label><br>
							<input type = "text" name="provincia" value = "<%=cliente.getProvincia() %>"><br>
					</fieldset>
				<input type = "submit" class = "botton" value = "invia">
				<input type = "reset" class = "botton" value = "Cancella">
				<button class = "botton" onclick = "ModificaUtente (false)">Annulla</button>
				</form>
			</div>
			<script src="${pageContext.request.contextPath}/formValidation.js"></script>
			<script src="${pageContext.request.contextPath}/js/user.js"></script>
			<div class="form-not-valid"> </div>
		
		
		<h3>I tuoi ordini: </h3>
			<table>
				<tr>
					<td>N. Fattura</td>
					<td>Data </td>
					<td>Prezzo Totale </td>
					<td>Dettagli</td>
				</tr>
				<%
					if (ordini != null && ordini.size() != 0) {
						for(AcquistoBean a : ordini) {
				%>
				<tr>
					<td><%=a.getnFattura()%></td>
					<td><%=a.getdataAcquito()%></td>
					<td><%=a.getPrezzoTotale()%></td>
					<td><a href="./dettagliOrdine?id=<%=a.getnFattura()%>">ordine </a> </td>
				</tr>	
				<%} }%>
			</table>
	<div class= "f"><%@include file="/footer.jsp" %></div>
</body>
</html>
