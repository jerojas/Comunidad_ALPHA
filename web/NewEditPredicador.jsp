<%-- 
    Document   : NewEditUsuario
    Created on : 11-oct-2012, 23:08:39
    Author     : Jeovany
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entidades.*"%>
<%
//Parámetros de entrada

    String mensaje2 = "";
    Integer guardo = (Integer) request.getAttribute("guardoOK");

    if (guardo != null) {
        mensaje2 = "Registro ingresado exitosamente";
    }

    Predicador e = (Predicador) request.getAttribute("predicador");

    String cedula = "";
    String nombre = "";
    String genero = "";
    String ecivil = "";
    String fnacimiento = "";
    String dir = "";
    String tel = "";
    String cel = "";
    String email = "";
    String pais = "";
    String fecha_ingreso = "";

    String accion = "insertar";//por defecto es un nuevo registro
    if (e != null) //si el usuario no es nulo significa que es modificación
    {
        cedula = Integer.toString(e.getCedula());
        nombre = e.getNombre();
        genero = e.getGenero();
        ecivil = e.getEstadocivil();
        fnacimiento = e.getFecha_nacimiento();
        dir = e.getDireccion();
        tel = e.getTelefono();
        cel = e.getCelular();
        email = e.getEmail();
        pais = e.getPais();
        fecha_ingreso = e.getFecha_ingreso();

        accion = "modificar";

    }


%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PREDICADORES</title>
        <link rel="stylesheet" href="css/estilotarea3.css" type="text/css" >        
        <link rel="stylesheet" href="css/bluedream.css" type="text/css" >        
        <link rel="stylesheet" type="text/css" href="css/jMenu.jquery.css" media="screen" /> 
        <link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.23.custom.css"/> 
        <script src = "js/jquery-1.8.0.min.js"></script>
        <script src = "js/jquery-ui-1.8.23.custom.min.js"></script>
        <script src="js/jMenu.jquery.js"></script>   
        <link href="imagenes/favicon.png" type="image/x-icon" rel="shortcut icon" />   
        <script type="text/javascript">
            $(document).ready(function() {
                // simple jMenu plugin called 
                $("#jMenu").jMenu();
                // more complex jMenu plugin called 
                $("#jMenu").jMenu({
                    ulWidth: 'auto',
                    effects: {
                        effectSpeedOpen: 300,
                        effectTypeClose: 'hide'
                    },
                    animatedText: false
                });

                $("#menuAdminOP1").click(function() {
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

                <h2>Información del Predicador</h2>

                <form name="reg"  id="reg" method="POST" action="ControladorPredicadores"> 
                    <fieldset>
                        <legend><strong>Predicadores</strong></legend>
                        <div class="formgeneral">

                            <div class="campos">
                                <label class="etiqueta"><strong>Cedula:</strong></label>
                                <input  type="text" size="30"  id="ced" name="ced" class ="texto" value="<%=cedula%>" required autofocus=""  /> 
                            </div>
                            <div class="campos">
                                <label class="etiqueta"><strong>Nombre:</strong></label>
                                <input  type="text" size="30"  id="nombre" name="nombre" class ="texto" value="<%=nombre%>" required  /> 
                            </div>
                            <div class="campos">
                                <label class="etiqueta"><strong>Género:</strong></label>
                                <input  type="text" size="30"  id="gen" name="gen" class ="texto" value="<%=genero%>"  a /> 
                            </div>
                            <div class="campos">
                                <label class="etiqueta"><strong>Estado Civil:</strong></label>
                                <input  type="text" size="30"  id="ecivil" name="ecivil" class ="texto" value="<%=ecivil%>"    /> 
                            </div>
                            <div class="campos2">
                                <label class="etiqueta"><strong>Fecha Nacimiento:</strong></label>
                                <input  type="date" size="30"  id="fnac" name="fnac" class ="texto" value="<%=fnacimiento%>" required  /> 
                            </div>
                            <div class="campos">
                                <label class="etiqueta"><strong>Direccion:</strong></label>
                                <input  type="text" size="30"  id="dir" name="dir" class ="texto" value="<%=dir%>" required   /> 
                            </div>
                            <div class="campos">
                                <label class="etiqueta"><strong>Teléfono</strong></label>
                                <input  type="text" size="30"  id="tel" name="tel" class ="texto" value="<%=tel%>"    /> 
                            </div>

                            <div class="campos">
                                <label class="etiqueta"><strong>Celular:</strong> </label>
                                <input  type="tel" id="cel" name="cel" size="30" l maxlength="25" class ="texto"value="<%=cel%>" required/> 
                            </div>
                            <div class="campos">
                                <label class="etiqueta"><strong>Email:</strong> </label>
                                <input  type="email" id="email" name="email" size="30" l maxlength="25" class ="texto"value="<%=email%>" required/> 
                            </div>
                            <div class="campos">
                                <label class="etiqueta"><strong>País:</strong> </label>
                                <input  type="text" id="pais" name="pais" size="30" l maxlength="25" class ="texto"value="<%=pais%>" required/> 
                            </div>
                            




                            <div style="left: 40%;position: relative">
                                <br>
                                <input type="submit" value="Guardar" name="btnModificar" id="btnModificar" class="boton"/>
                                <br>
                                <br>                                            
                            </div>
                            <input type="hidden" name="accion" value="<%=accion%>" />
                            <input type="hidden" name="id" value="<%=cedula%>" />

                        </div>
                        <p style="text-align:center;color: red">
                            <%=mensaje2%>
                        </p>
                    </fieldset>
                </form>

            </div>



        </div>
        <hr>
    </body>
</html>
