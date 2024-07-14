<%@page import="model.ProdottiDAO"%>
<%@page import="model.AcquistoDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "model.beans.AcquistoBean,java.util.ArrayList,model.OrdineCopia,model.beans.ClienteBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/fattura.css">
<title>GDBGames</title>
</head>
<%
    ClienteBean cliente = (ClienteBean) request.getAttribute("Cliente");
    AcquistoBean ordine = (AcquistoBean) request.getAttribute("Ordine"); 
    ArrayList <OrdineCopia> dettagli = (ArrayList <OrdineCopia>) request.getAttribute("DettagliOrdine");
    if (ordine == null || dettagli == null) {
        response.sendRedirect(request.getServletContext()+"./dettagliOrdine");
    }
    Boolean feedback = (Boolean) request.getAttribute("feedback");
%>
<body>    

    <p>
        <div class="titolo">
            <div class="numero">Fattura Numero: <%=ordine.getnFattura() %></div>
            <div class="data">Data: <%=ordine.getdataAcquito()%></div>
        </div>
        
        <div class="destinatario">
            <h3> Gentile <%=cliente.getNome()%>, </h3>
            <p>Grazie per il suo ordine. Le verr&agrave;  consegnato a <%=ordine.getVia() %>, <%=ordine.getCitta() %>. <br>Cordiali saluti, il team di GDB-Games.</p>
        </div>
        
        <div class="intestazione">
            <b>SPESA TOTALE:</b> <%=ordine.getPrezzoTotale()%>&euro; <br>
            <b>TOTALE ARTICOLI:</b> <%= dettagli.stream().mapToInt(OrdineCopia::getQuantità).sum()%>
        </div>
        <div class="corpo">
            <table class="dettagli-prodotti">
                <tr class="titoli-tabella">
                    <th>Immagine</th>
                    <th>Titolo</th>
                    <th>Console</th>
                    <th>Quantit&agrave; </th>
                    <th>Prezzo C.I.</th>
                </tr>
                <% for(OrdineCopia o : dettagli) { %>
                <tr class="corpo-tabella">
                    <td id="riga-img"><img src="../getFoto?titolo=<%=o.getTitoloVideogioco()%>" alt="immagine del videogioco non trovata" class="immagine"></td>
                    <td><%=o.getTitoloVideogioco() %></td>
                    <td><%=o.getNomeConsole() %></td>
                    <td><%=o.getQuantità() %></td>
                    <td><%=o.getPrezzoTotale()%>&euro;</td>
                </tr>
                <% } %>
            </table>
            <%if (feedback != null){
            	if (feedback.booleanValue()){
            	%>
            	<div>Fattura creata con successo</div>
            <%}} %>
            <button class = "botton"><a href = "${pageContext.request.contextPath}/creaFattura?id=<%=ordine.getnFattura() %>">Scarica la fattura</a></button>
        <div class= "f"> <%@include file="../footer.jsp" %> </div>
        </div>
</body>
</html>