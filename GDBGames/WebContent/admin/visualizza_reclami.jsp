<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import = "java.util.ArrayList, model.beans.ReclamoBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type = "text/css" href="${pageContext.request.contextPath}/css/visualReclamo.css">
</head>
<body>
	<h2> Visualizza reclami: </h2>
	<% ArrayList<ReclamoBean> reclami = (ArrayList <ReclamoBean>)request.getAttribute("reclami");	
	if (reclami == null) {
		response.sendRedirect("./adminCheck");
		return;
	} 
	%>
	<%if (reclami.size()==0 && reclami != null) {%>
		<p>Non &egrave; presente nessun reclamo!</p>
	<%return;} %>
	<div class="container-reclamo">
	<% for (ReclamoBean reclamo : reclami) {%>
	<div class="reclamo">	
	<h3>-Mittente:</h3> <p><br> <%=reclamo.getEmailCliente() %> </p> <br>
	<h3>-Tipologia di problema: </h3>
	<p><%=reclamo.getTitolo() %></p>
	<h3>-Descrizione del problema:</h3><p> <br> <%=reclamo.getContenuto() %></p>
	</div>
	<% } %>
	</div>
</body>
</html>
