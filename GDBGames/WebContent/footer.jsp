<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <title>Pagina HTML con footer</title>
</head>
<body>
    <footer>
        <div class="contatto">
             <h3><b>Contattaci:</b></h3>
             <ul>
                <li>Indirizzo: Universit&agrave;  degli studi di Salerno, Italia.</li>
                <li>Email:
                    <ul class="email">
                        <li>a.basso23@studenti.unisa.it</li>
                        <li>a.dambrosio101@studenti.unisa.it</li>
                        <li>a.galasso47@studenti.unisa.it</li>
                    </ul>
                </li>
             </ul>
        </div>
        <div class="subscribe">
        	<h3><b>Aiutaci a migliorarci dando la tua opinione sul sito:</b></h3>
        	<a href= "${pageContext.request.contextPath}/utenteLoggato/feedback.jsp" ><i>Feedback</i></a> <br><br><br>
            <h3><b>Ci trovi pure:</b></h3>
            <a href="https://www.instagram.com/gdbgames2024?igsh=ejBjd3llMDE1Nm11" target="_blank">
                <img src="${pageContext.request.contextPath}/foto/instagram.png" alt="Instagram" style="width:60px;height:60px;">
            </a>
            <a href="https://www.facebook.com/share/qd9kWd4fw2LpSRBf/" target="_blank">
                <img src="${pageContext.request.contextPath}/foto/facebook.png" alt="Facebook" style="width:60px;height:60px;">
            </a>
        </div>
        <div class="crediti">
            <h3><b>GDB-Games Autori:</b></h3>
            <p>Alessandro Basso</p>
            <p>Alessandro D'Ambrosio</p>
            <p>Antonio Galasso</p>
        </div>
    </footer>
</body>
</html> 

