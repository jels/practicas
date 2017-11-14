<%-- 
    Document   : estudiante
    Created on : 06/09/2017, 06:36:45 PM
    Author     : WarMachine
--%>

<%@page import="Controller.ControladorVarios"%>
<%@page import="Controller.ControladorEstudiante"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%
    HttpSession objsession = request.getSession(false);
    String usuario = (String) objsession.getAttribute("usuario");
    String rol = (String) objsession.getAttribute("rol");
    if (objsession.equals(false) || usuario == null) {
        //esta linea es la que ayuda a que no salga el error de null...
        session.invalidate();
        response.sendRedirect("../../index.jsp");

    } else if (rol.equals("Docente")) {
        response.sendRedirect("../docente/menu.jsp");
    } else if (rol.equals("Tutor")) {

    } else {
        response.sendRedirect("../../index.jsp");
    }
%>
<!DOCTYPE html>
<html lang="en">


    <%@include file="head.jsp" %>

    <body class="yellow accent-2">
        <% ControladorEstudiante conEst = new ControladorEstudiante();%>
        <% ControladorVarios conVar = new ControladorVarios();%>

        <header>
            <!-- Inicio HEADER -->
            <div class="fixed-action-btn vertical click-to-toggle">
                <a href="#" class="btn tooltipped button-collapse btn-floating btn-large blue darken-4 pulse" data-activates="slide-out" data-position="left" data-tooltip="Menu">
                    <i class="material-icons yellow-text">apps</i>
                </a>
            </div>
            <div class="navbar-fixed">
                <nav class="blue darken-3">
                    <div class="nav-wrapper">
                        <a href="#" class="brand-logo center yellow-text hide-on-med-and-down">Sistema Gestor de Practicas</a>
                        <ul id="nav-mobile" class="right">
                            <li><a href="#" class="tooltipped" data-position="button" data-tooltip="Ayuda"><i class="material-icons yellow-text">help</i></a></li>
                            <li><a href="#" class="tooltipped" data-position="button" data-tooltip="Foro"><i class="material-icons yellow-text">forum</i></a></li>
                            <li><a href="#" class="tooltipped" data-position="button" data-tooltip="Mi Cuenta"><i class="material-icons yellow-text">account_circle</i></a></li>
                            <li><a href="../../web-fcea/index.jsp" class="tooltipped" data-position="button" data-tooltip="Salir"><i class="material-icons yellow-text">directions_run</i></a></li>
                        </ul>
                    </div>
                    <div class="container">
                        <div class="nav-content">
                            <div class="col s12">
                                <ul class="tabs  blue darken-3 tabs-fixed-width">
                                    <li class="tab col s3"><a class="yellow-text" href="#cantidad">Resumen</a></li>
                                    <li class="tab col s3"><a class="yellow-text" href="#show">Mostrar</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </nav>
            </div>

        </header>
        <!-- Final HEADER -->
        <main>
            <!-- Inicio NAV -->
            <div class="container">
                <div class="row">
                    <div class="col s12 m9 l10">
                        <div id="structure" class="section scrollspy">
                            <ul id="slide-out" class="side-nav blue darken-3" style="width: 240px">
                                <li>
                                    <div class="user-view">
                                        <%=conVar.getUserViewTutor(usuario)%>
                                    </div>
                                </li>
                                <li>
                                    <a href="../../index.jsp" class="waves-effect yellow-text">
                                        <i class="material-icons yellow-text">home</i>Sitio Web
                                    </a>
                                </li>
                                <li>
                                    <a href="menu.jsp" class="waves-effect yellow-text">
                                        <i class="material-icons yellow-text">dashboard</i>Inicio
                                    </a>
                                </li>
                                <li>
                                    <a href="estudiante.jsp" class="waves-effect yellow-text">
                                        <i class="material-icons yellow-text">face</i>Estudiante
                                    </a>
                                </li>
                                <li>
                                    <a href="ayuda.jsp" class="waves-effect yellow-text">
                                        <i class="material-icons yellow-text">help</i>Ayuda
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Fin NAV -->

            <div class="row blue darken-3">
                <div id="cantidad" class="col s12">                    
                    <div class="container">
                        <h1 class="center yellow-text">Estudiantes</h1>
                        <div class="row">
                            <div class="col s6">
                                <h2 class="center yellow-text"> <%=conEst.cantidadEstudiantes_tutor(usuario)%></h2>
                                <h3 class="center yellow-text">Registrados en La Empresa <%=conVar.getNombreEmpresa(usuario)%></h3>
                            </div>
                            <div class="col s6">
                                <h2 class="center yellow-text">2</h2>
                                <h3 class="center yellow-text">Los estudiantes que estan Evaluados</h3>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="show">
                    <%=conEst.verEstudiantesXTutor(usuario)%>

                    <!-- Inicio del Modal search -->
                    <div id="search" class="modal modal-fixed-footer blue darken-3 yellow-text">
                        <div class="modal-content blue darken-3">
                            <div class="row">
                                <h1 class="center yellow-text">Buscar Estudiante</h1>
                                <form method="post" class="col s12 yellow-text" >
                                    <div class="row">
                                        <div class="input-field col s4">
                                            <i class="material-icons prefix">account_circle</i>
                                            <input id="apellido_estudiante" type="text">
                                            <label for="apellido_estudiante">Apellido</label>
                                        </div>
                                        <div class="input-field col s4">
                                            <i class="material-icons prefix">contact_mail</i>
                                            <input id="ci_estudiante" type="text">
                                            <label for="ci_estudiante"># de Carnet</label>
                                        </div>
                                        <div class="input-field col s4">
                                            <a id="buscar_estudiante" data-id="<%=usuario%>" class="waves-effect waves-light waves-teal yellow accent-2 blue-text text-darken-3 btn-large"> 
                                                <i class="material-icons right">search</i>Buscar
                                            </a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="row">
                                <div id="ver_estudiante_buscado"></div>

                            </div>

                        </div>

                        <div class="modal-footer blue darken-3 yellow-text">
                            <button class="modal-action modal-close waves-effect waves-teal yellow accent-2 blue-text text-darken-3 waves-yellow btn-flat">
                                Cerrar<i class="material-icons right">clear_all</i>
                            </button>
                        </div>
                    </div>
                    <!-- Final del Modal --> 

                    <!-- Inicio del Modal Asignar Notas -->
                    <div id="asignarNotas" class="modal modal-fixed-footer blue darken-3 yellow-text">

                        <div id="cargarDatos_de_la_nota"></div> 


                        <!--
                        <div class="modal-footer blue darken-3">
                            <div class="col s4 center-align">
                                <a id="guardarNotas" class="waves-effect waves-light waves-teal green yellow-text btn tooltipped" data-position="button" data-tooltip="Aceptar y Guardar"><i class="material-icons right">save</i>Aceptar</a>
                            </div>
                            <div class="col s4 center-align" >
                                <div id="notaGuardada">

                                </div>
                            </div>
                            <div class="col s4 center-align">
                                <a class="modal-action modal-close waves-effect waves-light waves-teal red yellow-text btn tooltipped" data-position="button" data-tooltip="Cancelar"><i class="material-icons left">clear_all</i>Cerrar</a>
                            </div>
                        </div>-->

                    </div>
                    <!-- Final del Modal --> 

                    <!-- Inicio del Modal Asignar Notas -->
                    <div id="verNotaAsignada" class="modal modal-fixed-footer blue darken-3 yellow-text">
                        <div class="modal-content blue darken-3">
                            <div class="row">
                                <h3 class="yellow-text accent-2 center">Leonardi Sauer, Jonathan Elias</h3>
                            </div>
                            <div class="row">
                                <div class="col s8">
                                    <table class="bordered">
                                        <thead>
                                            <tr>
                                                <th>Evaluacion</th>
                                                <th>Nota</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            <tr>
                                                <td>Primer Parcial</td>
                                                <td>90</td>
                                            </tr>
                                            <tr>
                                                <td>Segundo Parcial</td>
                                                <td>88</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer blue darken-3">
                            <div class="col s12 right-align">
                                <a class="modal-action modal-close waves-effect waves-light waves-teal red yellow-text btn tooltipped" data-position="button" data-tooltip="Cerrar"><i class="material-icons left">clear_all</i>Cerrar</a>
                            </div>
                        </div>
                        <div id="ver_Nota_Asignada_estudiante"></div> 

                    </div>
                    <!-- Final del Modal --> 

                </div>

            </div>

        </main>



        <!-- //////////////////////////////////////////////////////////////////////////// -->
        <!-- START FOOTER -->
        <footer class="page-footer blue darken-3">
            <div class="footer-copyright">
                <div class="container orange-text text-lighten-4">
                    � 2017 Copyright
                    <a class="orange-text text-lighten-4 right" href="#">LynxLion.net</a>
                </div>
            </div>
        </footer>
        <!-- END FOOTER -->


        <!-- ================================================
        Scripts
        ================================================ -->
        <script type="text/javascript" src="../../js/jquery-3.2.1.min.js"></script>
        <script src="../../js/materialize.min.js"></script>
        <script src="../../js/tutor.js"></script>
        <script src="../../../js/tutor.js"></script>

        <script type="text/javascript">
            $(document).ready(function () {
                $('.slider').slider();
                $('.button-collapse').sideNav();
                $('.materialboxed').materialbox();
                $('.modal').modal();
                $('.tooltipped').tooltip({delay: 50});
                $('.collapsible').collapsible();
            });
        </script>
    </body>
</html>