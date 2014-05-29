<%-- 
    Document   : ListaUsuarios.jsp
    Created on : 08-oct-2012, 1:14:08
    Author     : Jeovany
--%>

<%@page import="Entidades.Predicador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*,Entidades.*" %>

<%
    
    
//Obtener el arreglo de estudiantes enviado en la solicitud
    ArrayList<Predicador> predicadores = (ArrayList<Predicador>) request.getAttribute("predicadores");
    int contador = 0;
    String mensaje = request.getAttribute("mensaje") == null ? "" : (String) request.getAttribute("mensaje");
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LISTAR PREDICADORES</title>
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
            <caption>Predicadores Registrados</caption>
            <thead>
                <tr>
                     <th scope="col">Cédula</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Género</th>
                    <th scope="col">Estado-Civil</th>
                    <th scope="col">Fecha-Nacimiento</th>
                    <th scope="col">Direccion</th>
                    <th scope="col">Telefono</th>
                    <th scope="col">Celular</th>
                    <th scope="col">Email</th>
                    <th scope="col">Pais</th>
                    <th scope="col">Fecha-Ingreso</th>
                                        
                </tr>
            </thead>
            <%
                if (predicadores != null && predicadores.size() > 0) {
                    for (Iterator iterator = predicadores.iterator(); iterator.hasNext();) {
                        Predicador u = (Predicador) iterator.next();
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
                        <%= u.getCedula()%>
                    </td>
                    <td>
                        <%= u.getNombre()%>
                    </td>
                    <td>
                        <%= u.getGenero()%>
                    </td>
                    <td>
                        <%= u.getEstadocivil()%>
                    </td>
                    <td>
                        <%= u.getFecha_nacimiento()%>
                    </td>
                   
                    <td>
                        <%= u.getDireccion()%>
                    </td>
                    <td>
                        <%= u.getTelefono()%>
                    </td>
                    <td>
                        <%= u.getCelular()%>
                    </td>
                    <td>
                        <%= u.getEmail()%>
                    </td>
                    <td>
                        <%= u.getPais()%>
                    </td>  
                    <td>
                        <%= u.getFecha_ingreso()%>
                    </td>
                   
                   
                    <td>
                        <a href="ControladorPredicadores?accion=eliminar&IDs=<%=u.getCedula()%>">Eliminar</a>
                    </td>
                    <td>
                        <a href="ControladorPredicadores?accion=editar&ID=<%=u.getCedula()%>">Editar</a>
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
            <a href="NewEditPredicador.jsp">Ingresar Seminario Nuevo </a>  
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