<%-- 
    Document   : index
    Created on : 05-sep-2012, 21:40:46
    Author     : JEOVANY
--%>

<%
    String nombre = request.getParameter("correo");
    String email = (String)request.getAttribute("login");
    Integer guardo = (Integer) request.getAttribute("guardoOK");
    String mensaje = "";
    String mensaje2 = "";
    String mensaje3 = "";
    
    
    if (nombre != null && guardo == null) {
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
        <title>Portal UNAC</title>
        <link type="text/css" href="css/estilotarea3.css" rel="stylesheet" />
        <script src = "js/jquery-1.8.0.min.js"></script>
        <script src = "js/scripts.js"></script>
        <script src = "js/jquery-ui-1.8.23.custom.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.23.custom.css"/> 

       
    </head>
    <body>
        <div class="header"id ="header"> 

            <div id="logo">
                <img src="imagenes/logo_unac.jpg" width="282px" height="91px" alt="logo_unac"/>
            </div> 

            <div id="ingresar">	
                <form id="for" name="form" method="POST" action="Validar" >
                    <label for="correo">Correo electrónico:</label> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
                    <label for="password">Contraseña: </label> <br><br>
                    <input type= "text" id="correo" name ="correo"> &nbsp &nbsp
                    <input type="password" id="password" name="password" > &nbsp &nbsp &nbsp &nbsp 
                    <input type="button" id ="boton" value= "ENTRAR"  > &nbsp &nbsp &nbsp&nbsp &nbsp &nbsp &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp 
                    <a href="Validar?accion=0">Ir a la UNAC</a>&nbsp &nbsp &nbsp&nbsp &nbsp &nbsp &nbsp &nbsp&nbsp &nbsp 
                    <a href="#" id="olvipass">¿Has olvidado tu contraseña?</a> 

                </form>	
                
            </div>
            <div id="mensajerror">
                 <br>
                        <b><font color="RED"><%=mensaje%></font></b>
                       
                        
                </div>
        </div>	
       
        <div class="cuerpo" id="content"> 
            <div class="formulario"> 

                <h2>Registrese en nuestro Portal</h2>

                <form name="reg"  id="reg" method="POST" action="ControladorUsuarios"> 
                    <fieldset>
                        <legend><strong>Registrese</strong></legend>
                        <div class="formgeneral">
                            <div class="campos">
                                <label class="etiqueta"><strong>Nombre:</strong></label>
                                <input  type="text" size="30"  id="nombre" name="nombre" class ="texto" /> 
                            </div>
                            <div class="campos">
                                <label class="etiqueta"><strong>Apellidos:</strong></label>
                                <input type="text" id="ape" name="ape" size="30"class ="texto"/> 
                            </div>
                            <div class="campos2">
                                <label class="etiqueta"><strong>Correo electrónico:</strong> </label>
                                <input  type="text" id="correoo" name="correoo" size="30" l maxlength="25" class ="texto"/> 
                            </div>
                            <div class="campos">
                                <label class="etiqueta"><strong>Contraseña:</strong> </label>
                                <input type="password" id="passwor" name= "passwor" size="30" class ="texto" value=""/> 
                            </div>
                            <div class="campos2">
                                <label class="etiqueta"> <strong>Repita la Contraseña:</strong>  </label>
                                <input type="password" id="repitapassword" name="repitapassword" size="30"class ="texto" value=""/>  
                            </div>
                            <div class="campos">
                                <label class="etiqueta"> <strong>Teléfono:</strong>  </label>
                                <input class = "campo"type="text" id="tel" name="tel"size="30" class ="texto"/> 
                            </div>
                            <div class="campos">
                                <label class="etiqueta"> <strong>Género:</strong> </label>
                                <select  name="genero" size = 1 id = "genero" class="campos" >
                                    <option value="2"> Seleccione el género</option>
                                    <option value="1">Masculino</option>
                                    <option value="0">Femenino</option>
                                </select>   
                            </div>
                        </div>
                        <div class="btn">
                            <input type="button" id = "validar" name="validar" value="REGISTRARSE"/>
                            <br>
                        <br>                        
                        <b><font color="RED"><%=mensaje2%></font></b>
                         <b><font color="RED"><%=mensaje3%></font></b>
                          
                        
                        </div>
                    </fieldset>

                </form>

                <div class = "ale" id = "capaerrores"  style="display: none;" >
                    Información mal ingresada por favor corrija lo siguiente:
                    <br> 
                    <div class= "alerta" id="errornombre" >
                    </div>
                    <div class= "alerta" id="errorape">
                    </div>
                    <div class= "alerta"  id="errorcorreoo">
                    </div>
                    <div class= "alerta" id="errorvalcorreoo">
                    </div>
                    <div class= "alerta" id="errorpasswor">
                    </div>
                    <div class= "alerta" id="errorrepitapassword">
                    </div>
                    <div class= "alerta" id="errortel">
                    </div>
                    <div class= "alerta" id="errorvalpassword">
                    </div>
                    <div class= "alerta" id="errorgenero">
                    </div>

                </div>
                <br>
                <br>

         
            </div>
            <div id="tabs">

                <ul>
                    <li><a href="#tabs-1">Misión</a></li>
                    <li><a href="#tabs-2">Visión</a></li>
                    <li><a href="#tabs-3">Perfiles Institucionales</a></li>
                </ul>
                <div id="tabs-1">
                    <p>La Corporación Universitaria Adventista reconoce a Dios como Creador, Redentor y Sustentador del hombre y del universo; y en armonía con los principios filosóficos y educativos inspirados por el Espíritu Santo, evidenciados en la naturaleza, ejemplificados por Jesucristo, expuestos en las Sagradas Escrituras y tal como los profesa la iglesia Adventista del Séptimo Día, nuestra Institución declara como su misión propiciar y fomentar una significativa relación del hombre con Dios por medio del trabajo en las diferentes disciplinas del conocimiento. En consecuencia, la Corporación se define como una institución universitaria sin ánimo de lucro que desarrolla su labor educativa enmarcada en el servicio a Dios, la comunidad adventista y la sociedad en general.<br><br>
                        El trabajo del conocimiento se fundamenta en tres pilares: la formación integral, la cultura investigativa y la excelencia en el servicio,en el que el hombre es el agente principal del proceso educativo que persigue el desarrollo armónico de los aspectos físicos, mentales sociales y espirituales. La misión se desarrollará en procura de los altos niveles de calidad educativa, a través de un personal calificado con un profundo sentido de compromiso, apoyado en el uso óptimo de los recursos físicos, financieros y tecnológicos.
                    </p>
                </div>
                <div id="tabs-2">
                    <p>
                        La Corporación Universitaria Adventista será una universidad completamente accesible a la Iglesia Adventista del Séptimo Día en particular, y a la comunidad en general, con el propósito de preparar ciudadanos para este mundo y para la eternidad.
                    </p>
                </div>
                <div id="tabs-3">
                    <p>
                    <ol type="1">
                        <li><em>Una sólida formación cristiana.</em> El egresado es una persona íntegra, con altos ideales fundamentados en valores, con capacidad para servir a la sociedad utilizando los conocimientos adquiridos y aplicando los principios recibidos en su Alma Máter</li><br>
                        <li><em>Competencia profesional y laboral.</em> El egresado es un profesional de excelencia. Con competencias cognitivas y laborales que le permiten desempeñarse profesionalmente en las áreas del conocimiento para las cuales fue formado. Con capacidad para crear nuevas propuestas de mejoramiento y alternativas de solución a los problemas que se presenten.</li><br>
                        <li><em>Un compromiso con el servicio.</em> El egresado es un líder servidor. Reconoce en cada ser humano una criatura formada a imagen de Dios y alguien por quien Cristo murió. Por lo tanto, desarrolla una labor social enfocada en el servicio a los demás. </li><br>
                        <li><em>Cuidado personal y de la salud</em>. El egresado reconoce la importancia de mantener su salud física y mental, por ende practica un estilo de vida que incluye la sana alimentación, hábitos de higiene, el ejercicio físico, la recreación y el descanso.</li><br>
                    </ol>
                    </p>
                </div>


            </div>
        </div>
    </div>
    <div id="dialog">
        <p id="ok"></p>
    </div>
</body>
</html>
