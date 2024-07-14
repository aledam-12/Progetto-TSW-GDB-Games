<%@  page import = "model.OrdineCopia,model.beans.VideogiocoBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>GDB Games</title>
<link rel="stylesheet" href="css/Dettaglio_Videogioco.css">
</head>
<body>
	<div class = "head">
		<%@include file="header.jsp" %>
    </div>
    <%
    	OrdineCopia copia = (OrdineCopia) request.getAttribute("copia");
    %>
    <%
    	VideogiocoBean videogioco = (VideogiocoBean) request.getAttribute("videogioco");
    %>
    <% if (videogioco != null && copia != null) { %>
        <div class="container">
            <div class="gioco">
                <img src="./getFoto?titolo=<%=copia.getTitoloVideogioco()%>" alt="immagine del videogioco non trovata">
                <h2><%=videogioco.getTitolo()%></h2>
            </div>
            <div class="details">
                <div class= "pegi">
                	<h1><%=videogioco.getPegi() %></h1>
                	<a href = "https://pegi.info/it">www.pegi.info</a>
                </div>
                <div class="desc">
                	<h2>Cosa giocherai:</h2>
                	<p><%=videogioco.getDescrizione() %></p>
                </div>
                <div class = "mprod">
                <p> <%=copia.getPrezzo()%> &euro;</p>
                <button class="button"><a href="controlloCarrello?action=add&titolo=<%=copia.getTitoloVideogioco()%>&prezzo=<%=copia.getPrezzo()%>&console=<%=copia.getNomeConsole()%>"> Aggiungi al carrello</a></button>
            	</div>
            </div>
        </div>
    <% } %>
    <div class = "f">
    	<%@include file="footer.jsp" %>
	</div>
</body>
</html>
