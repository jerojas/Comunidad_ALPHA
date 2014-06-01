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

    Seminario e = (Seminario) request.getAttribute("seminario");

    String idsemin ="";
    String nombre = "";
    String fecha = "";
    String horainicio = "";
    String horafin = "";
    String observaciones = "";
    String predicador = "";
    String fecha_creacion = "";

    String accion = "insertar";//por defecto es un nuevo registro
    if (e != null) //si el usuario no es nulo significa que es modificación
    {
        idsemin = Integer.toString(e.getIdseminario());
        nombre = e.getNombre();
        fecha = e.getFecha();
        horainicio = e.getHora_inicio();
        horafin = e.getHora_fin();
        observaciones = e.getObservaciones();
        predicador = Integer.toString(e.getPredicador());
        fecha_creacion = e.getFecha_creacion();

        accion = "modificar";

    }


%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SEMINARIOS</title>
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

                <h2>Información del Seminario</h2>

                <form name="reg"  id="reg" method="POST" action="ControladorSeminarios"> 
                    <fieldset>
                        <legend><strong>Seminarios</strong></legend>
                        <div class="formgeneral">
                            <div class="campos">
                                <label class="etiqueta"><strong>Nombre:</strong></label>
                                <input  type="text" size="30"  id="nombre" name="nombre" class ="texto" value="<%=nombre%>" required autofocus=""  /> 
                            </div>
                            <div class="campos">
                                <label class="etiqueta"><strong>Fecha:</strong></label>
                                <input  type="date" size="30"  id="fecha" name="fecha" class ="texto" value="<%=fecha%>" required  /> 
                            </div>
                            <div class="campos">
                                <label class="etiqueta"><strong>Hora Inicio:</strong> </label>
                                <input  type="time" id="horainicio" name="horainicio" size="30" l maxlength="25" class ="texto"value="<%=horainicio%>" required/> 
                            </div>
                            <div class="campos">
                                <label class="etiqueta"><strong>Hora Fin:</strong> </label>
                                <input  type="time" id="horafin" name="horafin" size="50" l maxlength="25" class ="texto"value="<%=horafin%>" required/> 
                            </div>
                            <div class="campos">
                                <label class="etiqueta"><strong>Observaciones</strong></label>
                                <textarea id="observ" name="observ"  rows="10" cols="24"  >  <%=observaciones%> </textarea> 
                                
                            </div>

                            <div class="campos">
                                <label class="etiqueta"> <strong>Predicador:</strong>  </label>
                                <input class = "texto" type="text" id="predicador" name="predicador"size="30" class ="texto" value="<%=predicador%>"  required/> 
                            </div>
                             



                            <div style="left: 40%;position: relative">
                                <br>
                                <input type="submit" value="Guardar" name="btnModificar" id="btnModificar" class="boton"/>
                                <br>
                                <br>                                            
                            </div>
                            <input type="hidden" name="accion" value="<%=accion%>" />
                            <input type="hidden" name="id" value="<%=idsemin%>" />

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
