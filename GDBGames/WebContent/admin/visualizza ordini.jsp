<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.ArrayList,model.beans.AcquistoBean" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/visualizza_ordini.css">
		<meta charset="ISO-8859-1">
	</head>
	<%
		ArrayList <AcquistoBean> ordini = (ArrayList <AcquistoBean>) request.getAttribute("ordini"); 
		if (ordini == null) {
		response.sendRedirect("../admin/adminCheck");
		return;
		}
	%>
	<body>
		<%
			if (ordini.size() == 0) {
		%>
			<p class = "error-message"> Non ci sono ordini effettuati</p>
		<%
			return;}
		%>
		<div class="scrool">
		<table class = "acquisti">
			<tr>
				<th>N. Fattura</th>
				<th>Data </th>
				<th>Importo </th>
				<th>Cliente </th>
				<th>Indirizzo Spedizione </th>
				<th>Dettagli </th>
			</tr>
			<%
				for (AcquistoBean ord : ordini) {
			%>
			<tr>
				<td> <%=ord.getnFattura() %> </td>
				<td> <%=ord.getdataAcquito() %> </td>
				<td> <%=ord.getPrezzoTotale() %> </td>
				<td> <%=ord.getemailcliente() %></td>
				<td> <%=ord.getVia()%> <%=ord.getCap()%>, <%=ord.getCitta()%></td>
				<td> <a href = "./dettagliOrdine?id=<%=ord.getnFattura() %>">Dettagli</a>
			</tr>
			<%} %>
		</table>
		</div>
	</body>
</html>