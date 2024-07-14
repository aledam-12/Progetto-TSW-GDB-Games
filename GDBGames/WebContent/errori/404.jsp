<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title>Errore 404 - Pagina Non Trovata</title>
<link rel ="stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/css/stile_errori.css" > 
</head>
<body>
<% String homePath = request.getContextPath()+"/catalogo.jsp";%>
<div class="container">
    <div class="error-content">
    	<div class ="header">
		<h1>Ops! <br> Errore 404</h1>
		<img src = "${pageContext.request.contextPath}/foto/bomba-errori.png" alt = "pupazzetto per l'errore" style = "width: 150px; height: 150px;">
    	</div>
        <p>La pagina che stai cercando non &egrave; stata trovata. Potrebbe essere stata rimossa, avere cambiato nome o essere temporaneamente non disponibile.</p>
        <a href="<%=homePath%>" class="button">Vai alla Pagina Principale</a>
    </div>
    <div class="error-image">
        <img src="${pageContext.request.contextPath}/foto/kratos-foto-errori.png" alt="kratos incazzato per l'errore">
    </div>
</div>
</body>
</html>