<%-- 
    Document   : ListaUsuarios.jsp
    Created on : 08-oct-2012, 1:14:08
    Author     : Jeovany
--%>

<%@page import="Entidades.Seminario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*,Entidades.*" %>

<%
    
    
//Obtener el arreglo de estudiantes enviado en la solicitud
    ArrayList<Seminario> seminarios = (ArrayList<Seminario>) request.getAttribute("seminarios");
    int contador = 0;
    String mensaje = request.getAttribute("mensaje") == null ? "" : (String) request.getAttribute("mensaje");
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LISTAR SEMINARIOS</title>
        <link rel="stylesheet" href="css/estilotarea3.css" type="text/css" >        
        <link rel="stylesheet" href="css/bluedream.css" type="text/css" >        
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
            <div id='login'>                
            </div>
            <div id='banner'>
                <img src='imagenes/logo_unac.jpg' width='282' height='91' alt='logo_unac'/>
            </div>
        </div>
         <div class="cuerpo" id="content">
        <jsp:include page="menuAdmin.jsp"/>        
        <br>
        <hr>
        <table align="center">            
            <caption>Seminarios Registrados</caption>
            <thead>
                <tr>
                    <th scope="col">Nombre</th>
                    <th scope="col">Fecha</th>
                    <th scope="col">Hora-Inicio</th>
                    <th scope="col">Hora-Fin</th>
                    <th scope="col">Observaciones</th>
                    <th scope="col">Predicador</th>
                    <th scope="col">Fecha-Creación</th>
                    
                </tr>
            </thead>
            <%
                if (seminarios != null && seminarios.size() > 0) {
                    for (Iterator iterator = seminarios.iterator(); iterator.hasNext();) {
                        Seminario u = (Seminario) iterator.next();
                        contador++;
            %>
            <tbody>
                <%
                    if (contador % 2 == 0) {
                %>
                <tr>
                    <%} else {%>
                <tr class="odd">                               
                    <%}%>
                    <td>
                        <%= u.getNombre()%>
                    </td>
                    <td>
                        <%= u.getFecha()%>
                    </td>
                    <td>
                        <%= u.getHora_inicio()%>
                    </td>
                    <td>
                        <%= u.getHora_fin()%>
                    </td>
                    <td>
                        <%= u.getObservaciones()%>
                    </td>
                    <td>
                        <%= u.getDescripcionPredicador()%>
                    </td>
                    <td>
                        <%= u.getFecha_creacion()%>
                    </td>
                                      
                   
                   
                    <td>
                        <a href="ControladorSeminarios?accion=eliminar&IDs=<%=u.getIdseminario()%>">Eliminar</a>
                    </td>
                    <td>
                        <a href="ControladorSeminarios?accion=editar&ID=<%=u.getIdseminario()%>">Editar</a>
                    </td>
                </tr>
            </tbody>
            <%
                    }
                }
            %>

        </table>
        <br>
        <p style="text-align:center">
            <a href="ControladorSeminarios?accion=nuevo">Ingresar Seminario Nuevo </a>  
        </p>
        <p style="text-align:center;color: red">
            <%=mensaje%>
        </p>
        
        <hr>
    <center>
     
       
    </center>
         </div>
</body>
</html>