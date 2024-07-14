<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.ArrayList, model.beans.ClienteBean" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
	</head>
	<%
		ArrayList <ClienteBean> clienti = (ArrayList <ClienteBean>) request.getAttribute("clienti"); 
		if (clienti == null) {
		response.sendRedirect("../admin/adminCheck");
		return;
		}
	%>
	<body>
		<%
			if (clienti.size() == 0) {
		%>
			<p class = "error-message"> Non ci sono clienti registrati</p>
		<%
			return;}
		%>	
			<div class="scrool">
			<table class = "clienti">
				<tr>
					<th>Email</th>
					<th>Nome </th>
					<th>Cognome </th>
					<th>Indirizzo</th>
					<th>Citt&agrave; </th>
					<th>Ruolo </th>
				</tr>
			<%
				for (ClienteBean cli : clienti) {
			%>
				<tr>
					<td> <%=cli.getEmail()%> </td>
					<td> <%=cli.getNome() %> </td>
					<td> <%=cli.getCognome() %> </td>
					<td> <%=cli.getVia()%>, <%=cli.getCivico()%> </td>
					<td> <%=cli.getCitta()%>    <%=cli.getCap()%>, <%=cli.getProvincia() %></td>
					<td> <%=cli.getStato()%> </td>
				</tr>
				<%} %>
			</table>
			</div>
	</body>
</html>
