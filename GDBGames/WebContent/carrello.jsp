<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,model.OrdineCopia, model.ProdottiDAO" %>
<%@ page import="java.util.Locale"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>GDB Games</title>
<link rel ="stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/css/carello.css" >
</head>
<body>
<%	ProdottiDAO pdao = new ProdottiDAO();
    ArrayList<OrdineCopia> prodotti = (ArrayList<OrdineCopia>)session.getAttribute("prodottiCarrello");
	if (prodotti == null) {
        response.sendRedirect("./controlloCarrello?action=view");    
        return;
    }    
    if (prodotti.size() == 0) {
%>    
    <div class="scritta">
    	<img src="foto\icon-cart.svg" alt ="carello">
        <h2>Carrello vuoto!!</h2>
        <button><a href="catalogo.jsp">Continua ad acquistare!!</a></button>
    </div>
<%
    } else {
    	double totale = prodotti.stream().mapToDouble(OrdineCopia::getPrezzoTotale).sum();
    	String totaleFormattato = String.format(Locale.US, "%.2f", totale);
%>

<div class="totale-container">
    <h2>Totale da pagare:</h2>
    <p id = "quanTotale">
        Totale articoli: <%=prodotti.stream().mapToInt(OrdineCopia::getQuantità).sum()%><br> 
    </p>
    <div>
    <% for (OrdineCopia copia : prodotti) { %>
        <div>
            <ul>
            <li><span><%= copia.getTitoloVideogioco() %></span> - 
            <span><%= copia.getPrezzo() %> &euro;</span></li> 
            </ul>
        </div>
    <% } %>
    <br>
    <h3 id = "prezzoTotale"> Totale: <%= totaleFormattato %> &euro;</h3>
    </div>
    <br>
    <button><a href="utenteLoggato/FinalizzaAcquisto.jsp">Acquista</a></button>
</div>
<br>
<div class="prodotti-container">
    <%
        int i = 1;
        
        for (OrdineCopia copia : prodotti) {
            

    %>
    <div class="prodotto">
        <a href="./controlloCatalogo?action=read&id=<%= copia.getCodiceCopia() %>&titolo=<%=copia.getTitoloVideogioco()%>" target="_blank">
            <img src="./getFoto?titolo=<%=copia.getTitoloVideogioco()%>" id="immagine<%=i%>" alt="Immagine del videogioco non trovata">
        </a>
        <div class="el" id="el<%=i%>">
            <h2><%=copia.getTitoloVideogioco() %></h2>
            <p>Console: <b><%=copia.getNomeConsole() %></b></p>
            <p class="prezzo"> <%=copia.getPrezzo() %>&euro;</p>
        </div>
        <div class="quantita">


            <h4>Quantit&agrave;: </h4>
            <button onclick="updateQuantity(<%=i%>, -1, '<%=copia.getTitoloVideogioco()%>','<%=copia.getPrezzo()%>','<%=copia.getNomeConsole()%>')">-</button>
    		<span id="quantita<%=i%>">1</span>
    		<button onclick="updateQuantity(<%=i%>, 1, '<%=copia.getTitoloVideogioco()%>','<%=copia.getPrezzo()%>','<%=copia.getNomeConsole()%>')">+</button>
		</div>
        <a class="rimuovi" href="controlloCarrello?action=remove&titolo=<%=copia.getTitoloVideogioco()%>&prezzo=<%=copia.getPrezzo()%>&console=<%=copia.getNomeConsole()%>"><img src="foto/x.png" alt="Rimuovi"></a>
    </div>
	
    <script src="${pageContext.request.contextPath}/js/carrello.js" type = "text/javascript"></script>
    <%i++;}%>
        <% Boolean status = (Boolean) request.getSession().getAttribute("Status");
        if (status != null){if (status.booleanValue()){ %><div class = "feedback" id="feedbackCarrello" style="display:none">Hai raggiunto il numero massimo di prodotti diversi da inserire nel carrello!</div><%}} %>
</div>
<% }%>
<div class ="end"><%@include file="footer.jsp" %></div>
</body>
</html>