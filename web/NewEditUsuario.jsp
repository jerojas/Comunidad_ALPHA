<%-- 
    Document   : NewEditUsuario
    Created on : 11-oct-2012, 23:08:39
    Author     : Guillermo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entidades.*"%>
<%
//Parámetros de entrada
    Usuario e = (Usuario) request.getAttribute("usuario");

    String nombre = "";
    String apellidos = "";
    String correo = "";
    String clave = "";
    String telefono = "";
    String genero = "";
    String perfil = "";
    String estado = "";

    String accion = "insertar";//por defecto es un nuevo registro
    if (e != null) //si el usuario no es nulo significa que es modificación
    {
        nombre = e.getNombre();
        apellidos =e.getApellido();
        correo = e.getEmail();
        clave = e.getClave();
        telefono = e.getTelefono();
        genero = e.getGenero();
        perfil = Integer.toString(e.getIdPerfil());
        accion = "modificar";        
        estado=Integer.toString(e.getEstado());
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EDITAR USUARIO</title>
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
        <div id='banner'>
                <img src='imagenes/logo_unac.jpg' width='282' height='91' alt='logo_unac'/>
            </div>
        </div>
         <div class="cuerpo" id="content">
        <jsp:include page="menuAdmin.jsp"/>        
        <br>
        <hr>        
        <div class="formulario2" id="formulario2"> 

                <h2>Información del usuario</h2>

                <form name="reg"  id="reg" method="POST" action="ControladorUsuarios"> 
                    <fieldset>
                        <legend><strong>Editar Usuario</strong></legend>
                        <div class="formgeneral">
                            <div class="campos">
                                <label class="etiqueta"><strong>Nombre:</strong></label>
                                <input  type="text" size="30"  id="nombre" name="nombre" class ="texto" value="<%=nombre%>" /> 
                            </div>
                            <div class="campos">
                                <label class="etiqueta"><strong>Apellidos:</strong></label>
                                <input type="text" id="ape" name="ape" size="30"class ="texto" value="<%=apellidos%>"/> 
                            </div>
                            <div class="campos2">
                                <label class="etiqueta"><strong>Correo electrónico:</strong> </label>
                                <input  type="text" id="correoo" name="correoo" size="30" l maxlength="25" class ="texto"value="<%=correo%>" disabled=""/> 
                            </div>
                            <div class="campos">
                                <label class="etiqueta"><strong>Contraseña:</strong> </label>
                                <input type="password" id="passwor" name= "passwor" size="30" class ="texto" value="<%=clave%>"/> 
                            </div>
                            <div class="campos">
                                <label class="etiqueta"> <strong>Teléfono:</strong>  </label>
                                <input class = "campo"type="text" id="tel" name="tel"size="30" class ="texto" value="<%=telefono%>"/> 
                            </div>
                            <div class="campos">
                                <label class="etiqueta">Género:</label>                    
                    <select name="genero" class="campos" id="genero">
                        <option value="2">Seleccione el Género</option>
                        <%if (genero.equals("0")) {%>
                        <option value="0" selected>Femenino</option>
                        <option value="1">Masculino</option>
                        <%} else {
                        %>
                        <option value="0" >Femenino</option>
                        <option value="1" selected>Masculino</option>
                        <%}%>
                    </select>   
                            </div>
                    
                    <div class="campos">
                    <label class="etiqueta">Perfil:</label>
                    <input type="text" class="campo" id="perfil" name="perfil" value="<%=perfil%>"/>
                </div>                        
                <div class="campos">
                    <label class="etiqueta">Estado:</label>
                    <input type="text" class="campo" id="estado" name="estado" value="<%=estado%>"/>
                </div>                        
                <div style="left: 40%;position: relative">
                    <br>
                    <input type="submit" value="Modificar" name="btnModificar" id="btnModificar" class="boton"/>
                    <br>
                    <br>                                            
                </div>
                <input type="hidden" name="accion" value="<%=accion%>" />
                <input type="hidden" name="id" value="<%=correo%>" />
                      
            </div>
                </fieldset>
                </form>
                            
        </div>
         </div>
        <hr>
    </body>
</html>
