<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title>Errore</title>
<link rel ="stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/css/stile_errori.css" > 
</head>
<body>
<% String homePath = request.getContextPath()+"/catalogo.jsp";%>
<div class="container">
    <div class="error-content">
    	<div class ="header">
		<h1>Ops! <br> Errore!</h1>
		<img src = "${pageContext.request.contextPath}/foto/bomba-errori.png" alt = "pupazzetto per l'errore" style = "width: 150px; height: 150px;">
    	</div>
        <p>Siamo spiacenti c'&egrave; stato un problema con il raggiungimento del sito. Riprovare pi&ugrave; tardi.</p>
        <a href="<%=homePath%>" class="button">Vai alla Pagina Principale</a>
    </div>
    <div class="error-image">
        <img src="${pageContext.request.contextPath}/foto/kratos-foto-errori.png" alt="kratos incazzato per l'errore">
    </div>
</div>
</body>
</html>