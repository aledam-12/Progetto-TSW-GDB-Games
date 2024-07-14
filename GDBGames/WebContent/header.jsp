<!DOCTYPE html>
<html lang="it">
<head> 
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <%@ page import = "model.beans.ClienteBean, model.beans.ConsoleBean, model.ConsoleDAO, java.util.ArrayList"%>
    
</head>
<body>
	<% String nomeHeader;
	String titoloVideogioco = request.getParameter("search");
	String consoleVideogioco = request.getParameter("console");
	if (consoleVideogioco == null) consoleVideogioco = "";
	ConsoleDAO cdao = new ConsoleDAO();
	ArrayList <ConsoleBean> piattaforme = cdao.leggiConsole();
	if (session.getAttribute("nomeCliente") != null) {
	nomeHeader = (String) session.getAttribute("nomeCliente"); }
	else nomeHeader = "Accedi";
	%>
	<div class="header">
    <div class="sinistra">
    	
        <img src="${pageContext.request.contextPath}/foto/logo5.png" alt="logo" class="logo">
        <a href="${pageContext.request.contextPath}/catalogo.jsp">
            <img src="${pageContext.request.contextPath}/foto/home.png" alt="Home" class="home">
        </a>
    </div>
    <form action="./searchbar" method="post" class= "destra">
    	   <select class="console" name="console" id = "Searchbar Console">
       			 <option value="">Tutte</option>
       			 <%for (ConsoleBean console : piattaforme){ %>
       			 <option value="<%=console.getNome()%>" <%if (consoleVideogioco.equals(console.getNome())){%> selected <%} %>><%=console.getNome()%></option>
        		 <%} %>
    	   </select>
    	<input type="text" class="search-bar" placeholder="Cerca il tuo videogioco" name="search" id = "Searchbar Titolo" <%if (titoloVideogioco!=null){ %>
    	value = '<%=titoloVideogioco %>'<%} %>>
    	<button type="submit" class="ricerca">
    		<img src="${pageContext.request.contextPath}/foto/ricerca.png" alt="ricerca" class="fotoRic">
		</button>
    	</form>
     <div class="account">
     		<a href="${pageContext.request.contextPath}/carrello.jsp">
            	<img src="${pageContext.request.contextPath}/foto/carrello.png" alt="carello" class="carrello">
    		<a href="${pageContext.request.contextPath}/utenteLoggato/account.jsp">
            	<img src="${pageContext.request.contextPath}/foto/omino.png" alt="omino" class="omino" >
        		<div class="name">
        			<span><%=nomeHeader%></span>
        		</div>
        	</a>
        
    </div>
    </div>	
    <script src="${pageContext.request.contextPath}/js/Searchbar.js" type="text/javascript"></script>
    <table id = "SearchResult"></table>
</body>
</html>
