<%-- 
    Document   : tutor
    Created on : 06/09/2017, 06:37:11 PM
    Author     : WarMachine
--%>

<%@page import="Controller.ControladorVarios"%>
<%@page import="Controller.ControladorTutor"%>
<%@page import="javafx.scene.control.Alert"%>
<%@page import="Controller.ControladorEstudiante"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession objsession = request.getSession(false);
    String usuario = (String) objsession.getAttribute("usuario");
    String rol = (String) objsession.getAttribute("rol");
    if (objsession.equals(false) || usuario == null) {
        //esta linea es la que ayuda a que no salga el error de null...
        session.invalidate();
        response.sendRedirect("../../index.jsp");

    } else if (rol.equals("Tutor")) {
        response.sendRedirect("../tutor/menu.jsp");
    } else if (rol.equals("Docente")) {

    } else {
        response.sendRedirect("../../index.jsp");
    }
%>

<!DOCTYPE html>
<html lang="en">
    <% ControladorVarios conVar = new ControladorVarios();%>
    <% ControladorTutor conTut = new ControladorTutor();%>

    <%@include file="head.jsp" %>

    <body class="yellow accent-2">

        <!-- Inicio HEADER -->
        <header>
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
                            <li><a href="#" class="tooltipped" data-position="button" data-tooltip="Salir"><i class="material-icons yellow-text">directions_run</i></a></li>
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

            <!-- Inicio del NAV-->
            <div class="container">
                <div class="row">
                    <div class="col s12 m9 l10">
                        <div id="structure" class="section scrollspy">
                            <ul id="slide-out" class="side-nav blue darken-3" style="width: 240px">
                                <li>
                                    <div class="user-view">
                                        <%=conVar.getUserViewDocente(usuario)%>
                                    </div>
                                </li>
                                <li>
                                    <a href="../../index.jsps" class="waves-effect yellow-text">
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
                                    <a href="docente.jsp" class="waves-effect yellow-text">
                                        <i class="material-icons yellow-text">card_travel</i>Docente
                                    </a>
                                </li>
                                <li>
                                    <a href="tutor.jsp" class="waves-effect yellow-text">
                                        <i class="material-icons yellow-text">contacts</i>Tutor
                                    </a>
                                </li>
                                <li>
                                    <a href="materia.jsp" class="waves-effect yellow-text">
                                        <i class="material-icons yellow-text">storage</i>Materias
                                    </a>
                                </li>
                                <li>
                                    <a href="carrera.jsp" class="waves-effect yellow-text">
                                        <i class="material-icons yellow-text">format_list_bulleted</i>Carreras
                                    </a>
                                </li>
                                <li>
                                    <a href="empresa.jsp" class="waves-effect yellow-text">
                                        <i class="material-icons yellow-text">store</i>Empresas
                                    </a>
                                </li>
                                <li>
                                    <a href="usuario.jsp" class="waves-effect yellow-text">
                                        <i class="material-icons yellow-text">people</i>Usuarios
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
            <!-- Final del NAV-->

            <!-- Inicio del MENU -->
            <div class="row blue darken-3">

                <div id="cantidad" class="col s12">                    
                    <div class="container">
                        <h1 class="center yellow-text">Tutores</h1>
                        <h2 class="center yellow-text"><%=conTut.contarTutores()%></h2>
                        <h3 class="center yellow-text">Matriculados durante esta Gestion Academica en las Materias de Practicas Empresariales</h3>
                    </div>


                </div>
                <div id="show">
                    <%=conTut.verTutores()%>
                </div>
                <!-- Modal Structure -->
                <div id="new" class="modal modal-fixed-footer blue darken-3 yellow-text">
                    <%=conTut.modal_newTutor()%>

                </div>
                <div id="update" class="modal modal-fixed-footer blue darken-3 yellow-text ">
                    <% String ci = request.getParameter("CI_tutor");%>
                    <%=conTut.updateTutor()%>
                </div>
                <div id="search" class="modal modal-fixed-footer blue darken-3 yellow-text">


                </div>
            </div>
            <!-- Final del MENU -->

        </main>

        <%@include file="foother.jsp" %>
    </body>
</html>