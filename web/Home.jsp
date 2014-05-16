<%-- 
    Document   : Home
    Created on : 07-sep-2012, 2:09:12
    Author     : Jeovany
--%>


<%
    String nombre = (String)request.getAttribute("usuario");    
    String perfil = (String)request.getAttribute("perfil");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/estilotarea3.css" type="text/css" >
        <title>Inicio</title>        
        <link rel="stylesheet" type="text/css" href="css/jMenu.jquery.css" media="screen" /> 
        <link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.23.custom.css"/> 
        <script src = "js/jquery-1.8.0.min.js"></script>
        <script src = "js/jquery-ui-1.8.23.custom.min.js"></script>
        <script src="js/jMenu.jquery.js"></script> 
         <link href="imagenes/favicon.png" type="image/x-icon" rel="shortcut icon" />
        <script type="text/javascript"> 
            $(document).ready(function(){ 
                // simple jMenu plugin called 
                $("#jMenu").jMenu(); 
                // more complex jMenu plugin called 
                $("#jMenu").jMenu({ 
                    ulWidth : 'auto', 
                    effects : { 
                        effectSpeedOpen : 300, 
                        effectTypeClose : 'hide' 
                    }, 
                    animatedText : false 
                }); 
                
                $("#menuAdminOP1").click(function(){
                    alert($("#menuAdminOP1").html())
                });
            }); 
        </script>             
    </head>
    <body>
        <div id='header' class="header">
         <div id='logo'>
                <img src='imagenes/logo_unac.jpg' width='282' height='91' alt='logo_unac'/>
            </div>
        </div>
        <div class="cuerpo" id="content">
        <%
            if (perfil.equals("1")) {
        %>
        <jsp:include page="menuAdmin.jsp"/>
        <%} else if (perfil.equals("2")) {
        %>
        <jsp:include page="menuEstudiante.jsp"/>
        <%   }else if (perfil.equals("3")) {
        %>
        <jsp:include page="menuProfesor.jsp"/>
        <%   }else if (perfil.equals("4")) {
        %>
        <jsp:include page="menuVisitante.jsp"/>
        <%   }
        %>
        
        <div id='content' style='text-align:center'>
            <h1>Validación del Login </h1>
              <%
            if (perfil.equals("1")) {
        %>
       <h2>Bienvenido Señor Administrador </h2>
        <%} else if (perfil.equals("2")) {
        %>
       <h1>Bienvenido Señor Estudiante </h1>
        <%   }else if (perfil.equals("3")) {
        %>
       <h1>Bienvenido Señor Profesor </h1>
        <%   }else if (perfil.equals("4")) {
        %>
        <h1>Bienvenido Señor Visitante </h1>
        <%   }
        %>
        
            <p class='mensajeT2'>Hola <%=nombre%>, has ingresado exitosamente !</p><br>
            <a href='/PortalUNAC'>Volver</a>
        </div>
            </div>
           
    </body>
</html>
