<%-- 
    Document   : index
    Created on : 05-sep-2012, 21:40:46
    Author     : JEOVANY 
--%>

<%

    String correo = request.getParameter("correo");
    String email = (String)request.getAttribute("login");
    Integer guardo = (Integer) request.getAttribute("guardoOK");
    String  estado = (String) request.getAttribute("estado");
    String mensaje = "";
    String mensaje2 = "";
    String mensaje3 = "";
     String mensaje4 = "";
     String accion = "insertar";//la acción será un nuevo registro 
     String accion2 = "validarusuario";
    
    if (estado != null){
         mensaje4 = "El usuario esta deshabilitado "; 
     }else if (correo != null && guardo == null) {
        mensaje = "Usuario y/o Contraseña incorrecta";
    }
    
    if (guardo != null) {
        mensaje2 = "Registro ingresado exitosamente";
    }
    
    if (email != null ) {
        mensaje3 = "El usuario ya existe \n Por favor digite otro correo ";
    }
     
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comunidad ALPHA</title>
        <link type="text/css" href="css/estilotarea3.css" rel="stylesheet" />
        <script src = "js/jquery-1.8.0.min.js"></script>
        <script src = "js/scripts.js"></script>
        <script src = "js/jquery-ui-1.8.23.custom.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.23.custom.css"/> 
         
        <link href="imagenes/favicon.png" type="image/x-icon" rel="shortcut icon" />
     
    </head>
    <body>
        <div class="header"id ="header"> 

            <div id="logo">
                <img src="imagenes/logo_unac.jpg" width="282px" height="91px" alt="logo_unac"/>
            </div> 

            <div id="ingresar">	
                <form id="for" name="form" method="POST" action="Validar" >
                    <label for="correo">Usuario:</label> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  &nbsp  &nbsp  &nbsp  &nbsp  &nbsp  &nbsp   &nbsp  &nbsp  &nbsp   
                    <label for="password"> Contraseña: </label> <br><br>
                    <input type= "text" id="user" name ="user"> &nbsp &nbsp
                    <input type="password" id="password" name="password" > &nbsp &nbsp &nbsp &nbsp 
                    <input type="button" id ="boton" value= "ENTRAR"  > &nbsp &nbsp &nbsp&nbsp &nbsp &nbsp &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp 
                    
                   

                </form>	
                
            </div>
            <div id="mensajerror">
                 <br>
                        <b><font color="RED"><%=mensaje%></font></b>
                        <b><font color="RED"><%=mensaje4%></font></b>
                       
                        
                </div>
        </div>	
       
                       
                        <div class="cuerpo" id="content" style="text-align: center"> 
          <H2>LA COMUNIDAD ALPHA TE DA LA BIENVENIDA</h2>
          
             <img src="imagenes/iglesia.jpg"  width="960px" height="456px" alt="iglesia"/>
           
             
             
            
    </div>
    <div id="dialog">
        <p id="ok"></p>
    </div>
</body>
</html>
