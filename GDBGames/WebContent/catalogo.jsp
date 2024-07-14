<%@include file="header.jsp" %>
<%@ page import="java.util.ArrayList,model.OrdineCopia" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GDB Games</title>
     <link rel="stylesheet" href="css/stile_catalogo.css">
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var currentIndex = 0;
            var images = document.querySelectorAll('.carousel img');
            var totalImages = images.length;

            function showImage(index) {
                images.forEach((img, i) => {
                    img.style.display = (i === index) ? 'block' : 'none';
                });
            }

            function nextImage() {
                currentIndex = (currentIndex + 1) % totalImages;
                showImage(currentIndex);
            }

            setInterval(nextImage, 3000); // Cambia immagine ogni 3 secondi

            showImage(currentIndex);
        });
    </script>
</head>

<body>
	<% ArrayList <OrdineCopia> copie = (ArrayList<OrdineCopia>)session.getAttribute("prodotti"); 
		if(copie == null) {
    		response.sendRedirect("./searchbar?console=PC");    
    		return;} 
    %>


    <%if (copie.size() == 0) { %>
    	<p>Nessun risultato trovato!</p>
    <%return;} %>
    <div class="novita">
        <div class="carousel">
            <img src="foto\ACS.jpg" alt="Collector edition assassin creed shadow">
            <img src="foto\GTA 6.jpg" alt="GTA 6">
            <img src="foto\NSwitch.jpg" alt="Novità  Switch">
            <img src="foto\PrinceOfPersiaTheLostCrown.jpg" alt="Prince Of Persia The Lost Crown">
        	<img src="foto\Sea of Thieves.png" alt="Sea of Thieves">
        </div>
    </div> 
	<% String platform = request.getParameter("console");
		if (platform == null || platform.isBlank()) platform = "tutte le console";
	%>
    <h2>Selezione di prodotti per <%=platform %></h2>
    <div class="griglia">
        <%
        	if(copie != null && copie.size() != 0) {
                for (OrdineCopia copia : copie) {{
        %>
        <div class="prodotto">
            <a href="controlloCatalogo?titolo=<%=copia.getTitoloVideogioco()%>&console=<%=copia.getNomeConsole()%>&prezzo=<%=copia.getPrezzo()%>" target="_blank">
                <img src="./getFoto?titolo=<%=copia.getTitoloVideogioco()%>" alt="Immagine del videogioco non trovata">
            </a>
            <h2><%= copia.getTitoloVideogioco() %></h2>
            <h4> <b><%=copia.getPrezzo()%> &euro;</b></h4>
            <p>Console: <b><%=copia.getNomeConsole() %></b></p> <br>
            <p>Copie disponibili: <b><%=copia.getQuantità()%></b> </p> 
        </div>
        <% } } }%>
    </div>
<div class = "fine">
<%@include file="footer.jsp" %>
</div>
</body>
</html>
