/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WarMachine
 */
public class ControladorMateria extends Conexion {

    public int contarMateria() {
        Materia_model mamo = new Materia_model();
        int cantidad = mamo.countMaterias();
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en contarMateria.getCloseConexion: " + e);
        }
        return cantidad;
    }

    public boolean nuevaMateria(Materia materia) {

        Materia_model matMo = new Materia_model();
        boolean bandera = matMo.newMateria(materia);
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en nuevaMateria.getCloseConexion: " + e);
        }
        return bandera;
    }

    public boolean newDimension(Dimension dimension) {

        Dimension_model dimMo = new Dimension_model();
        boolean bandera = dimMo.new_dimension(dimension);
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en newDimension.getCloseConexion: " + e);
        }
        return bandera;
    }

    public boolean updateDimension(Dimension dimension) {

        Dimension_model dimMo = new Dimension_model();
        boolean bandera = dimMo.getUpdateDimension(dimension);
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en updateDimension.getCloseConexion: " + e);
        }
        return bandera;
    }

    public boolean bajaDimension(int idDimension) {

        Dimension_model dimMo = new Dimension_model();
        boolean bandera;
        Materia_model matMo = new Materia_model();
        int idMateria = matMo.getIdMateriaDimension(idDimension);
        int cantidadDimensionesActivas = dimMo.getCantidadDimensionesXMat(idMateria);
        if (cantidadDimensionesActivas == 4) {
            if (dimMo.getEstadoDimension(idDimension) == 1) {
                bandera = dimMo.baja_dimension(idDimension, 0);
                try {
                    getCloseConexion();
                } catch (Exception e) {
                    System.out.println("Error en bajaDimension.getCloseConexion: " + e);
                }
            } else {
                bandera = false;
            }
        } else if (dimMo.getEstadoDimension(idDimension) == 1) {
            bandera = dimMo.baja_dimension(idDimension, 0);
            try {
                getCloseConexion();
            } catch (Exception e) {
                System.out.println("Error en bajaDimension.getCloseConexion: " + e);
            }
        } else {
            bandera = dimMo.baja_dimension(idDimension, 1);
            try {
                getCloseConexion();
            } catch (Exception e) {
                System.out.println("Error en bajaDimension.getCloseConexion: " + e);
            }
        }
        return bandera;
    }

    public String viewDimension(int idDimension) {

        Dimension_model dimMo = new Dimension_model();
        Criterios_model criMo = new Criterios_model();
        String nombreDimension = dimMo.getNombreDimension(idDimension);
        int cantidadCriterios = criMo.getCantidadCriteriosXDimension(idDimension);
        String htmlcode = "<div class=\"container\">\n"
                + "                        <div class=\"col s12 center\">\n"
                + "                            <h5>Dimension</h5>\n"
                + "                            <h3>" + nombreDimension + "</h3>\n"
                + "                        </div>\n"
                + "                        <div class=\"col s12 center\">\n"
                + "                            <h5>Criterios Activos</h5>\n"
                + "                            <h3>" + cantidadCriterios + "</h3>\n"
                + "                        </div>\n"
                + "                    </div>";

        return htmlcode;
    }

    public String editarDimension(int idDimension) {
        String htmlcode = "";
        Dimension_model dimMo = new Dimension_model();
        ResultSet dimension = dimMo.getDimensionActualizar(idDimension);

        try {
            dimension.next();
            htmlcode = "           <div class=\"container\">\n"
                    + "                        <div class=\"row\">\n"
                    + "                            <h1 class=\"center yellow-text\">Actualizar Dimension</h1>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"row\">\n"
                    + "                            <form id=\"updateDimension\" class=\"col s12 yellow-text\">\n"
                    + "                                <div class=\"row\">\n"
                    + "                                    <div class=\"input-field col s6\">\n"
                    + "                                        <i class=\"material-icons prefix yellow-text\">assignment_ind</i>\n"
                    + "                                        <input id=\"nombreDimensionAC\" type=\"text\" value=\"" + dimension.getString(2) + "\" class=\"validate\">\n"
                    + "                                        <label class=\"yellow-text\" for=\"Nombre Materia\">Nombre Dimension</label>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </form>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"col s6\">\n"
                    + "                            <button class=\"btn waves-effect waves-light yellow accent-2 blue-text left\" type=\"button\" id=\"actualizarDimension\" data-id=\"" + dimension.getInt(1) + "\" >\n"
                    + "                                Validar y Guardar<i class=\"material-icons right\">save</i>\n"
                    + "                            </button>\n"
                    + "                            <br><br><br><br>\n"
                    + "                        </div>\n"
                    + "                        <div id=\"notificacionActDimension\">\n"
                    + "                        </div>\n"
                    + "                    </div>";

        } catch (Exception e) {
            System.out.println("Error en  editarDimension: " + e);
        }
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en editarDimension.getCloseConexion: " + e);
        }

        return htmlcode;
    }

    public boolean newCriterio(Criterios criterio) {

        Criterios_model criMo = new Criterios_model();
        boolean bandera = criMo.nuevo_criterio(criterio);
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en newCriterio.getCloseConexion: " + e);
        }
        return bandera;
    }

    public boolean updateCriterio(Criterios criterio) {

        Criterios_model criMo = new Criterios_model();
        boolean bandera = criMo.update_criterio(criterio);
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en updateCriterio.getCloseConexion: " + e);
        }
        return bandera;
    }

    public boolean bajaCriterio(int idCriterio) {

        Criterios_model criMo = new Criterios_model();
        Dimension_model dimMo = new Dimension_model();
        boolean bandera;

        int idDimension = dimMo.getIdDimensionCriterio(idCriterio);

        int cantidadCriteriosActivas = criMo.getCantidadCriteriosXDimension(idDimension);

        int estado = criMo.getEstadoCriterio(idCriterio);
        System.out.println("Estado " + estado + " Criterio " + idCriterio);
        System.out.println("Cantidad de Criterios activos: " + cantidadCriteriosActivas);
        if (cantidadCriteriosActivas == 5) {
            if (estado == 1) {
                bandera = criMo.baja_criterio(idCriterio, 0);
                try {
                    getCloseConexion();
                } catch (Exception e) {
                    System.out.println("Error en bajaCriterio.getCloseConexion: " + e);
                }
            } else {
                bandera = false;
            }
        } else if (estado == 1) {
            bandera = criMo.baja_criterio(idCriterio, 0);
            try {
                getCloseConexion();
            } catch (Exception e) {
                System.out.println("Error en bajaCriterio.getCloseConexion: " + e);
            }
        } else {
            bandera = criMo.baja_criterio(idCriterio, 1);
            try {
                getCloseConexion();
            } catch (Exception e) {
                System.out.println("Error en bajaCriterio.getCloseConexion: " + e);
            }
        }
        return bandera;
    }

    public int getCantidadDimensionesXMateria(int idMateria) {
        Dimension_model dimMo = new Dimension_model();
        int cantidadDimensionesXMateria = dimMo.getCantidadDimensionesXMat(idMateria);
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en getCantidadDimensionesXMateria.getCloseConexion: " + e);
        }
        return cantidadDimensionesXMateria;
    }

    public int getCantidadCriteriosXDimension(int idDimension) {
        Criterios_model criMo = new Criterios_model();
        int cantidadDimensionesXMateria = criMo.getCantidadCriteriosXDimension(idDimension);
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en getCantidadDimensionesXMateria.getCloseConexion: " + e);
        }
        return cantidadDimensionesXMateria;
    }

    public String getViewDimensionesXMat(int idMateria) {

        String htmlcode = "          <div class=\"container\">\n"
                + "                        <div class=\"row\">\n"
                + "                            <div class=\"col s12\">\n"
                + "                                <h1 class=\"center yellow-text\">Dimensiones</h1>\n"
                + "                                <a href=\"#new\" class=\"waves-effect waves-light yellow accent-2 blue-text text-darken-3 btn-large right modal-trigger\"><i class=\"material-icons left\">person_add</i>Nuevo</a>\n"
                + "                            </div>\n"
                + "                        </div>\n"
                + "                    </div>"
                + "                 <div class=\"container\">\n"
                + "                        <table class=\"highlight responsive-table blue darken-3 yellow-text\">\n"
                + "                            <thead>\n"
                + "                                <tr>\n"
                + "                                    <th>#</th>\n"
                + "                                    <th>Nombre</th>\n"
                + "                                    <th>Estado</th>\n"
                + "                                    <th class=\"center-align\">Ver - Editar</th>\n"
                + "                                    <th class=\"center-align\">Dar de Baja</th>\n"
                + "                                </tr>\n"
                + "                            </thead>\n"
                + "                            <tbody>\n";
        ResultSet dimension;
        Dimension_model dimMo = new Dimension_model();
        dimension = dimMo.ver_dimensionesxmateria(idMateria);
        int i = 1;
        if (dimension != null) {
            try {
                while (dimension.next()) {
                    System.out.println(dimension.getString(2));
                    htmlcode += "                <tr>\n"
                            + "                    <td>" + i + "</td>\n"
                            + "                    <td>" + dimension.getString(2) + "</td>\n";

                    if (dimension.getInt(3) == 1) {
                        htmlcode += "                    <td>Activo</td>\n";
                    } else {
                        htmlcode += "                    <td>Inactivo</td>\n";

                    }
                    htmlcode += "    <td><div class=\"center-align\"><a href=\"dimension.jsp?dimension=" + dimension.getInt(1) + "\" data-id=\"" + dimension.getInt(1) + "\" id=\"ver_materia\" class=\"btn-floating btn tooltipped waves-effect waves-light blue yellow-text modal-trigger\" data-position=\"button\" data-tooltip=\"Ver\"><i class=\"material-icons yellow-text\">description</i></a></div></td>\n"
                            + "     <td><div class=\"center-align\"><a id=\"baja_dimension\" data-id=\"" + dimension.getInt(1) + "\" class=\"btn-floating btn tooltipped waves-effect waves-light blue yellow-text\" data-position=\"button\" data-tooltip=\"Dar de Baja\"><i class=\"material-icons yellow-text\">redo</i></a></div></td>\n"
                            + "  </tr>"
                            + "";
                    i++;

                }
            } catch (SQLException ex) {
                System.out.println("Error en getViewDimensionesXMat: " + ex);
            }
        } else {
            htmlcode += "";

        }
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en getViewDimensionesXMat.getCloseConexion: " + e);
        }
        return htmlcode += "                        </tbody>\n"
                + "                        </table>\n"
                + "                    </div>";
    }

    public String verMateriaXCarrera(String abreviatura) {

        String htmlcode = "          <div class=\"container\">\n"
                + "                        <div class=\"row\">\n"
                + "                            <div class=\"col s12\">\n"
                + "                                <h1 class=\"center yellow-text\">Materias</h1>\n"
                + "                                <a href=\"#new\" class=\"waves-effect waves-light yellow accent-2 blue-text text-darken-3 btn-large right modal-trigger\"><i class=\"material-icons left\">person_add</i>Nuevo</a>\n"
                + "                            </div>\n"
                + "                        </div>\n"
                + "                    </div>"
                + "                 <div class=\"container\">\n"
                + "                        <table class=\"highlight responsive-table blue darken-3 yellow-text\">\n"
                + "                            <thead>\n"
                + "                                <tr>\n"
                + "                                    <th>#</th>\n"
                + "                                    <th>Nombre</th>\n"
                + "                                    <th>Descripcion</th>\n"
                + "                                    <th>Semestre</th>\n"
                + "                                    <th>Horas</th>\n"
                + "                                    <th>Estado</th>\n"
                + "                                    <th class=\"center-align\">Ver - Editar</th>\n"
                + "                                    <th class=\"center-align\">Dar de Baja</th>\n"
                + "                                </tr>\n"
                + "                            </thead>\n"
                + "                            <tbody>\n";
        ResultSet rs;
        Materia_model matm = new Materia_model();
        rs = matm.ver_materias(abreviatura);
        int i = 1;
        if (rs != null) {
            try {
                while (rs.next()) {
                    System.out.println(rs.getString(7));
                    htmlcode += "                <tr>\n"
                            + "                    <td>" + i + "</td>\n"
                            + "                    <td>" + rs.getString(2) + "</td>\n";
                    if (rs.getString(3).length() > 20) {
                        htmlcode += "                    <td><p>" + rs.getString(3).substring(0, 20) + "</p></td>\n";
                    } else {
                        htmlcode += "                    <td><p>" + rs.getString(3) + "</p></td>\n";
                    }
                    htmlcode += "                    <td>" + rs.getString(4) + "</td>\n"
                            + "                    <td>" + rs.getString(5) + "</td>\n";
                    if (rs.getInt(6) == 1) {
                        htmlcode += "                    <td>Activo</td>\n";
                    } else {
                        htmlcode += "                    <td>Inactivo</td>\n";

                    }
                    htmlcode += "    <td><div class=\"center-align\"><a href=\"materia.jsp?materia=" + rs.getInt(1) + "\" data-id=\"" + rs.getInt(1) + "\" id=\"ver_materia\" class=\"btn-floating btn tooltipped waves-effect waves-light blue yellow-text modal-trigger\" data-position=\"button\" data-tooltip=\"Ver\"><i class=\"material-icons yellow-text\">description</i></a></div></td>\n"
                            + "     <td><div class=\"center-align\"><a id=\"baja_materia\" data-id=\"" + rs.getInt(1) + "\" class=\"btn-floating btn tooltipped waves-effect waves-light blue yellow-text\" data-position=\"button\" data-tooltip=\"Dar de Baja\"><i class=\"material-icons yellow-text\">redo</i></a></div></td>\n"
                            + "  </tr>"
                            + "";
                    i++;

                }
            } catch (SQLException ex) {
                System.out.println("Error en verMaterias: " + ex);
            }
        } else {
            htmlcode += "";

        }
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en verMaterias.getCloseConexion: " + e);
        }
        return htmlcode += "                        </tbody>\n"
                + "                        </table>\n"
                + "                    </div>";

    }

    public ResultSet getListaIDCriterioXEst(String CI_estudiante) {

        Criterios_model criMo = new Criterios_model();
        return criMo.getListaCriterioXEst(CI_estudiante);
    }

    public String modal_show_materia() {
        System.out.println("ID: ");
        String htmlcode = "";
        Materia_model matMo = new Materia_model();
        ResultSet mat = null;
        Dimension_model dimMo = new Dimension_model();
        mat = matMo.getMateria(1);
        ResultSet dimen = null;
        if (mat != null) {

            try {
                mat.next();
                System.out.println("nombre: " + mat.getString(3));
                htmlcode += "<div class=\"modal-content\">"
                        + "     <h2>" + mat.getString(3) + "</h2>\n"
                        + "       <p>" + mat.getString(4) + "</p>\n"
                        + "     <h4>Las Dimensiones...</h4>"
                        + "       <ul class=\"collapsible popout blue darken-3 yellow-text\" data-collapsible=\"accordion\">";
                dimen = dimMo.getDimensiones(1);
                while (dimen.next()) {
                    System.out.println("id: " + dimen.getInt(1));
                    htmlcode += "       <li>\n"
                            + "             <div class=\"collapsible-header blue darken-3\">\n"
                            + "                 <i class=\"material-icons rigth\">details</i>"
                            + "                 " + dimen.getString(2) + ""
                            + "                 <span class=\"badge yellow-text\">5</span>\n"
                            + "             </div>\n"
                            + "             <div class=\"collapsible-body\">\n";
                    htmlcode += verCriterios(dimen.getInt(1));

                    htmlcode += "         </div>\n"
                            + "       </li>";
                }
                htmlcode += "  </ul>\n";

            } catch (Exception e) {
                System.out.println("Error en modal_show_materia: " + e);
            }

        } else {
            htmlcode += "";
        }
        htmlcode += "    </div>\n"
                + "    <div class=\"modal-footer blue darken-3\">\n"
                + "      <a href=\"#!\" class=\"modal-action modal-close waves-effect waves-yellow yellow accent-2  btn-flat\">OK</a>\n"
                + "    </div>\n"
                + "          ";
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en modal_show_materia.getCloseConexion: " + e);
        }
        return htmlcode;
    }

    private String verCriterios(int dimen) {
        String htmlcode = "";
        ResultSet crit = null;
        Criterios_model criMo = new Criterios_model();
        crit = criMo.getCriterios(dimen);
        System.out.println("id: " + dimen);
        int i = 0;

        htmlcode += "      <table class=\"highlight responsive-table blue darken-3 yellow-text\">\n"
                + "        <thead>\n"
                + "          <tr>\n"
                + "              <th>#</th>\n"
                + "              <th>Nombre</th>\n"
                + "              <th>Ver</th>\n"
                + "              <th>Actualizar</th>\n"
                + "              <th>Dar de Baja</th>\n"
                + "          </tr>\n"
                + "        </thead>\n";

        try {
            while (crit.next()) {
                i++;
                htmlcode += "<tr>\n"
                        + "    <td>" + i + "</td>\n"
                        + "    <td>" + crit.getString(1) + "</td>\n"
                        + "    <td><div class=\"center-align\"><a href=\"#show_criterio\" data-id=\"" + crit.getInt(2) + "\" id=\"ver_materia\" class=\"btn-floating btn tooltipped waves-effect waves-light blue yellow-text modal-trigger\" data-position=\"button\" data-tooltip=\"Ver\"><i class=\"material-icons yellow-text\">description</i></a></div></td>\n"
                        + "     <td><div class=\"center-align\"><a data-id=\"" + crit.getInt(2) + "\" id=\"actualizar_materia\" class=\"btn-floating btn tooltipped waves-effect waves-light blue yellow-text modal-trigger\" data-position=\"button\" data-tooltip=\"Actualizar\"><i class=\"material-icons yellow-text\">border_color</i></a></div></td>\n"
                        + "     <td><div class=\"center-align\"><a id=\"baja_materia\" class=\"btn-floating btn tooltipped waves-effect waves-light blue yellow-text\" data-position=\"button\" data-tooltip=\"Dar de Baja\"><i class=\"material-icons yellow-text\">redo</i></a></div></td>\n"
                        + "</tr>";

                System.out.println("criterio..." + crit.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Error getCriterios: " + e);
        }
        htmlcode += "\n"
                + "        <tbody>\n"
                + "        </tbody>\n"
                + "      </table>\n"
                + "            \n"
                + "";
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en verCriterios.getCloseConexion: " + e);
        }
        return htmlcode;
    }

    public String getEvaluacion(String CI_Estudiante) {
        String htmlcode = "";

        System.out.println("Estudiante _getEvaluacion: " + CI_Estudiante);

        Estudiante_model estMo = new Estudiante_model();
        Notas_model notMo = new Notas_model();
        Materia_model matMo = new Materia_model();
        Dimension_model dimMo = new Dimension_model();
        AsignacionPracticas_model aspMo = new AsignacionPracticas_model();
        Practicas_model praMo = new Practicas_model();
        int idMateria = matMo.getIdMateria(CI_Estudiante);
        ResultSet nombreEstudiante = null;
        ResultSet dimensiones = null;
        nombreEstudiante = estMo.getNombreEstudiante(CI_Estudiante);
        boolean aproboPrimerParcial = notMo.llenoPrimerParcial(CI_Estudiante);
        boolean aproboSegundoParcial = notMo.llenoSegundoParcial(CI_Estudiante);
        boolean evaluacionCompleta = notMo.evaluacionCompletaDocente(CI_Estudiante);
        System.out.println("EvaluacionCompleta: " + evaluacionCompleta);

        boolean primerParcial = praMo.activoParcial(1);
        System.out.println("Primer PArcial activo?: " + primerParcial);
        boolean segundoParcial = praMo.activoParcial(2);
        System.out.println("Segundo PArcial activo?: " + segundoParcial);

        ResultSet criterios;
        Criterios_model criMo = new Criterios_model();
        System.out.println("IDMAteria: " + idMateria);

        int i = 1;
        int c = 1;
        int nota = 0;
        try {
            nombreEstudiante.next();
            if (evaluacionCompleta) {
                nota = notMo.getNotaPrimerParcial(CI_Estudiante);
                htmlcode += "<div class=\"container\">\n"
                        + "     <div class=\"row\">\n"
                        + "       <div class=\"col s6\">\n"
                        + "              <h3 class=\"center\">" + nombreEstudiante.getString(3) + " " + nombreEstudiante.getString(4) + ", " + nombreEstudiante.getString(1) + " " + nombreEstudiante.getString(2) + "</h3>\n"
                        + "              <h4 class=\"center\">Primer Parcial</h4>\n"
                        + "              <h3 class=\"center\">" + nota + "</h3>\n"
                        + "       </div>\n";
                nota = notMo.getNotaSegundoParcial(CI_Estudiante);
                htmlcode += "       <div class=\"col s6\">\n"
                        + "              <h4 class=\"center\">Segundo Parcial</h4>\n"
                        + "              <h3 class=\"center\">" + nota + "</h3>\n"
                        + "       </div>\n"
                        + " </div>\n"
                        + " </div>\n";
            } else if (aproboPrimerParcial && segundoParcial) {
                nota = notMo.getNotaPrimerParcial(CI_Estudiante);
                htmlcode += ""
                        + "<div class=\"row\">\n"
                        + "       <h3 class=\"center\">" + nombreEstudiante.getString(3) + " " + nombreEstudiante.getString(4) + ", " + nombreEstudiante.getString(1) + " " + nombreEstudiante.getString(2) + "</h3>\n"
                        + "       <div class=\"col s6\">\n"
                        + "              <h4 class=\"center\">Primer Parcial</h4>\n"
                        + "              <h3 class=\"center\">" + nota + "</h3>\n"
                        + "       </div>\n";
                if (aproboSegundoParcial) {
                    nota = notMo.getNotaSegundoParcial(CI_Estudiante);
                    htmlcode += "       <div class=\"col s6\">\n"
                            + "              <h4 class=\"center\">Segundo Parcial</h4>\n"
                            + "              <h3 class=\"center\">" + nota + "</h3>\n"
                            + "       </div>\n";
                } else {
                    htmlcode += "      <div class=\"col s6\">\n"
                            + " <div class=\"row\">\n"
                            + "    <form>"
                            + "       \n";
                    htmlcode += "<h5 class=\"center\">Segundo Parcial</h5>\n";
                    dimensiones = dimMo.getDimensiones(idMateria);
                    while (dimensiones.next()) {

                        switch (i) {
                            case 1:
                                htmlcode += "<div class=\"col s6\">\n"
                                        + "    <table class=\"bordered\">\n"
                                        + "         <thead>\n"
                                        + "             <tr>\n"
                                        + "                   <th>A - " + dimensiones.getString(2) + "</th>\n"
                                        + "                   <th>Puntaje</th>\n"
                                        + "             </tr>\n"
                                        + "         </thead>\n"
                                        + "         <tbody>\n";
                                criterios = criMo.getCriterios(dimensiones.getInt(1));
                                while (criterios.next()) {
                                    System.out.println("Criterio: " + criterios.getString(1));
                                    htmlcode += "             <tr>\n"
                                            + "                   <td value=\"" + criterios.getInt(2) + "\" id=\"criterio" + c + "\">" + c + " - " + criterios.getString(1) + "</td>\n"
                                            + "                   <td><input id=\"nota" + c + "\" type=\"number\" min=\"1\" max=\"10\" class=\"validate bold\" style=\"width: 60px; font-size: 30px\"></td>\n"
                                            + "             </tr>\n";
                                    c++;
                                }
                                htmlcode += "         </tbody>\n"
                                        + "    </table>\n";
                                break;
                            case 2:
                                htmlcode += "    <table class=\"bordered\">\n"
                                        + "         <thead>\n"
                                        + "             <tr>\n"
                                        + "                    <th>B - " + dimensiones.getString(2) + "</th>\n"
                                        + "                    <th>Puntaje</th>\n"
                                        + "             </tr>\n"
                                        + "         </thead>\n"
                                        + "         <tbody>\n";
                                criterios = criMo.getCriterios(dimensiones.getInt(1));
                                while (criterios.next()) {
                                    System.out.println("Criterio: " + criterios.getString(1));
                                    htmlcode += "             <tr>\n"
                                            + "                   <td value=\"" + criterios.getInt(2) + "\" id=\"criterio" + c + "\">" + c + " - " + criterios.getString(1) + "</td>\n"
                                            + "                   <td><input id=\"nota" + c + "\" type=\"number\" min=\"1\" max=\"10\" class=\"validate bold\" style=\"width: 60px; font-size: 30px\"></td>\n"
                                            + "             </tr>\n";
                                    c++;
                                }
                                htmlcode += "         </tbody>\n"
                                        + "    </table>\n"
                                        + "</div>";
                                break;
                            case 3:
                                htmlcode += "<div class=\"col s6\">\n"
                                        + "    <table class=\"bordered\">\n"
                                        + "         <thead>\n"
                                        + "             <tr>\n"
                                        + "                   <th>C - " + dimensiones.getString(2) + "</th>\n"
                                        + "                   <th>Puntaje</th>\n"
                                        + "             </tr>\n"
                                        + "         </thead>\n"
                                        + "         <tbody>\n";
                                criterios = criMo.getCriterios(dimensiones.getInt(1));
                                while (criterios.next()) {
                                    System.out.println("Criterio: " + criterios.getString(1));
                                    htmlcode += "             <tr>\n"
                                            + "                   <td value=\"" + criterios.getInt(2) + "\" id=\"criterio" + c + "\">" + c + " - " + criterios.getString(1) + "</td>\n"
                                            + "                   <td><input id=\"nota" + c + "\" type=\"number\" min=\"1\" max=\"10\" class=\"validate bold\" style=\"width: 60px; font-size: 30px\"></td>\n"
                                            + "             </tr>\n";
                                    c++;
                                }
                                htmlcode += "         </tbody>\n"
                                        + "    </table>\n";
                                break;
                            case 4:
                                htmlcode += "    <table class=\"bordered\">\n"
                                        + "         <thead>\n"
                                        + "             <tr>\n"
                                        + "                   <th>D - " + dimensiones.getString(2) + "</th>\n"
                                        + "                   <th>Puntaje</th>\n"
                                        + "             </tr>\n"
                                        + "         </thead>\n"
                                        + "         <tbody>\n";
                                criterios = criMo.getCriterios(dimensiones.getInt(1));
                                while (criterios.next()) {
                                    System.out.println("Criterio: " + criterios.getString(1));
                                    htmlcode += "             <tr>\n"
                                            + "                   <td value=\"" + criterios.getInt(2) + "\" id=\"criterio" + c + "\">" + c + " - " + criterios.getString(1) + "</td>\n"
                                            + "                   <td><input id=\"nota" + c + "\" type=\"number\" min=\"1\" max=\"10\" class=\"validate bold\" style=\"width: 60px; font-size: 30px\"></td>\n"
                                            + "             </tr>\n";
                                    c++;
                                }
                                htmlcode += "         </tbody>\n"
                                        + "    </table>\n"
                                        + "</div>\n";
                                break;
                            default:
                                break;
                        }
                        i++;
                    }
                    htmlcode += "        </form>\n"
                            + "     </div>\n"
                            + "     <div class=\"col s6 center-aling\">\n"
                            + "         <button class=\"btn waves-effect waves-light yellow accent-2 blue-text left tooltipped\" data-position=\"button\" data-tooltip=\"Guardar\" type=\"button\" data-id=\"" + CI_Estudiante + "\" id=\"guardarNuevaNota\">\n"
                            + "             Validar y Guardar<i class=\"material-icons right\">save</i>\n"
                            + "         </button>\n"
                            + "     <br><br><br><br>\n"
                            + "     </div>\n"
                            + "     <div id=\"notaGuardada\" class\"col s6 center-aling\">\n"
                            + "     </div>\n"
                            + "</div>\n"
                            + "</div>\n"
                            + "\n";
                }
            } else if (primerParcial) {
                htmlcode += "      <div class=\"col s6\">\n"
                        + " <div class=\"row\">\n"
                        + "    <form>"
                        + "       \n";
                htmlcode += "<h4 class=\"center\">Primer Parcial</h4>\n";
                dimensiones = dimMo.getDimensiones(idMateria);
                System.out.println("idMateria: " + idMateria);
                while (dimensiones.next()) {
                    switch (i) {
                        case 1:
                            htmlcode += "<div class=\"col s6\">\n"
                                    + "    <table class=\"bordered\">\n"
                                    + "         <thead>\n"
                                    + "             <tr>\n"
                                    + "                   <th>A - " + dimensiones.getString(2) + "</th>\n"
                                    + "                   <th>Puntaje</th>\n"
                                    + "             </tr>\n"
                                    + "         </thead>\n"
                                    + "         <tbody>\n";
                            criterios = criMo.getCriterios(dimensiones.getInt(1));
                            while (criterios.next()) {
                                System.out.println("Criterio: " + criterios.getString(1));
                                htmlcode += "             <tr>\n"
                                        + "                   <td value=\"" + criterios.getInt(2) + "\" id=\"criterio" + c + "\">" + c + " - " + criterios.getString(1) + "</td>\n"
                                        + "                   <td><input id=\"nota" + c + "\" type=\"number\" min=\"1\" max=\"10\" class=\"validate bold\" style=\"width: 60px; font-size: 30px\"></td>\n"
                                        + "             </tr>\n";
                                c++;
                            }
                            htmlcode += "         </tbody>\n"
                                    + "    </table>\n";
                            break;
                        case 2:
                            htmlcode += "    <table class=\"bordered\">\n"
                                    + "         <thead>\n"
                                    + "             <tr>\n"
                                    + "                    <th>B - " + dimensiones.getString(2) + "</th>\n"
                                    + "                    <th>Puntaje</th>\n"
                                    + "             </tr>\n"
                                    + "         </thead>\n"
                                    + "         <tbody>\n";
                            criterios = criMo.getCriterios(dimensiones.getInt(1));
                            while (criterios.next()) {
                                System.out.println("Criterio: " + criterios.getString(1));
                                htmlcode += "             <tr>\n"
                                        + "                   <td value=\"" + criterios.getInt(2) + "\" id=\"criterio" + c + "\">" + c + " - " + criterios.getString(1) + "</td>\n"
                                        + "                   <td><input id=\"nota" + c + "\" type=\"number\" min=\"1\" max=\"10\" class=\"validate bold\" style=\"width: 60px; font-size: 30px\"></td>\n"
                                        + "             </tr>\n";
                                c++;
                            }
                            htmlcode += "         </tbody>\n"
                                    + "    </table>\n"
                                    + "</div>";
                            break;
                        case 3:
                            htmlcode += "<div class=\"col s6\">\n"
                                    + "    <table class=\"bordered\">\n"
                                    + "         <thead>\n"
                                    + "             <tr>\n"
                                    + "                   <th>C - " + dimensiones.getString(2) + "</th>\n"
                                    + "                   <th>Puntaje</th>\n"
                                    + "             </tr>\n"
                                    + "         </thead>\n"
                                    + "         <tbody>\n";
                            criterios = criMo.getCriterios(dimensiones.getInt(1));
                            while (criterios.next()) {
                                System.out.println("Criterio: " + criterios.getString(1));
                                htmlcode += "             <tr>\n"
                                        + "                   <td value=\"" + criterios.getInt(2) + "\" id=\"criterio" + c + "\">" + c + " - " + criterios.getString(1) + "</td>\n"
                                        + "                   <td><input id=\"nota" + c + "\" type=\"number\" min=\"1\" max=\"10\" class=\"validate bold\" style=\"width: 60px; font-size: 30px\"></td>\n"
                                        + "             </tr>\n";
                                c++;
                            }
                            htmlcode += "         </tbody>\n"
                                    + "    </table>\n";
                            break;
                        case 4:
                            htmlcode += "    <table class=\"bordered\">\n"
                                    + "         <thead>\n"
                                    + "             <tr>\n"
                                    + "                   <th>D - " + dimensiones.getString(2) + "</th>\n"
                                    + "                   <th>Puntaje</th>\n"
                                    + "             </tr>\n"
                                    + "         </thead>\n"
                                    + "         <tbody>\n";
                            criterios = criMo.getCriterios(dimensiones.getInt(1));
                            while (criterios.next()) {
                                System.out.println("Criterio: " + criterios.getString(1));
                                htmlcode += "             <tr>\n"
                                        + "                   <td value=\"" + criterios.getInt(2) + "\" id=\"criterio" + c + "\">" + c + " - " + criterios.getString(1) + "</td>\n"
                                        + "                   <td><input id=\"nota" + c + "\" type=\"number\" min=\"1\" max=\"10\" class=\"validate bold\" style=\"width: 60px; font-size: 30px\"></td>\n"
                                        + "             </tr>\n";
                                c++;
                            }
                            htmlcode += "         </tbody>\n"
                                    + "    </table>\n"
                                    + "</div>\n";
                            break;
                        default:
                            break;
                    }
                    i++;
                }
                htmlcode += "        </form>\n"
                        + "     </div>\n"
                        + "     <div class=\"col s6 center-aling\">\n"
                        + "         <button class=\"btn waves-effect waves-light yellow accent-2 blue-text left tooltipped\" data-position=\"button\" data-tooltip=\"Guardar\" type=\"button\" data-id=\"" + CI_Estudiante + "\" id=\"guardarNuevaNota\">\n"
                        + "             Validar y Guardar<i class=\"material-icons right\">save</i>\n"
                        + "         </button>\n"
                        + "     <br><br><br><br>\n"
                        + "     </div>\n"
                        + "     <div id=\"notaGuardada\" class=\"col s6 center-aling\">\n"
                        + "     </div>\n"
                        + "</div>\n"
                        + ""
                        + "         <div class=\"col s6\">\n"
                        + "             <h4 class=\"center\">Segundo Parcial</h4>\n"
                        + "             <h4 class=\"yellow accent-2 red-text center\">Aun sin Asignar Nota</h4>\n"
                        + "         </div>\n"
                        + "     \n"
                        + "\n";
            } else {
                htmlcode += "               <div class=\"container\">\n"
                        + "                        <div class=\"row\">\n"
                        + "                            <div class=\"col s12\">\n"
                        + "                                 <h3 class=\"center\">" + nombreEstudiante.getString(3) + " " + nombreEstudiante.getString(4) + ", " + nombreEstudiante.getString(1) + " " + nombreEstudiante.getString(2) + "</h3>\n"
                        + "                            </div>\n"
                        + "                        </div>\n"
                        + "                        <div class=\"row\">\n"
                        + "                            <div class=\"col s12\">\n"
                        + "                                <h4 class=\"center\">Aun no esta habilitado el sistema para insertar su evaluacion</h4>\n"
                        + "                                <h4 class=\"center\">Disculpe la molestia</h4>\n"
                        + "                            </div>\n"
                        + "                        </div>\n"
                        + "                    </div>";
            }

        } catch (Exception e) {
            System.out.println("Error getEvaluacion: " + e);
        }

        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en getEvaluacion.getCloseConexion: " + e);
        }
        return htmlcode;
    }

    public String getEvaluacionDocente(String CI_Estudiante) {
        String htmlcode = "";

        System.out.println("Estudiante _getEvaluacion: " + CI_Estudiante);

        Estudiante_model estMo = new Estudiante_model();
        Notas_model notMo = new Notas_model();
        Materia_model matMo = new Materia_model();
        Dimension_model dimMo = new Dimension_model();
        int idMateria = matMo.getIdMateria(CI_Estudiante);
        ResultSet nombreEstudiante = null;
        ResultSet dimensiones = null;
        nombreEstudiante = estMo.getNombreEstudiante(CI_Estudiante);
        boolean aproboPrimerParcial = notMo.llenoPrimerParcial(CI_Estudiante);
        boolean aproboSegundoParcial = notMo.llenoSegundoParcial(CI_Estudiante);
        boolean evaluacionCompleta = notMo.evaluacionCompletaDocente(CI_Estudiante);
        System.out.println("EvaluacionCompleta: " + evaluacionCompleta);
        ResultSet criterios;
        Criterios_model criMo = new Criterios_model();
        System.out.println("IDMAteria: " + idMateria);
        AsignacionPracticas_model aspMo = new AsignacionPracticas_model();

        int i = 1;
        int c = 1;
        double nota = 0;
        try {
            nombreEstudiante.next();
            if (aspMo.getRealizaPractica(CI_Estudiante)) {
                if (evaluacionCompleta) {
                    nota = (notMo.getNotaPrimerParcial(CI_Estudiante) / 2) * 0.35;
                    htmlcode += "<div class=\"row\">\n"
                            + "       <h3 class=\"center\">" + nombreEstudiante.getString(3) + " " + nombreEstudiante.getString(4) + ", " + nombreEstudiante.getString(1) + " " + nombreEstudiante.getString(2) + "</h3>\n"
                            + "       <div class=\"col s4\">\n"
                            + "              <h4 class=\"center\">Primer Parcial</h4>\n"
                            + "              <h3 class=\"center\">" + nota + "</h3>\n"
                            + "       </div>\n";
                    nota = (notMo.getNotaSegundoParcial(CI_Estudiante) / 2) * 0.35;
                    htmlcode += "       <div class=\"col s4\">\n"
                            + "              <h4 class=\"center\">Segundo Parcial</h4>\n"
                            + "              <h3 class=\"center\">" + nota + "</h3>\n"
                            + "       </div>\n";
                    nota = (notMo.getNotaExamenFinal(CI_Estudiante) / 2) * 0.30;
                    htmlcode += "       <div class=\"col s4\">\n"
                            + "             <h4 class=\"center\">Examen Final</h4>\n"
                            + "             <h3 class=\"center\">" + nota + "</h3>\n"
                            + "       </div>\n"
                            + "</div>";
                } else if (aproboPrimerParcial) {
                    nota = (notMo.getNotaPrimerParcial(CI_Estudiante) / 2) * 0.35;
                    htmlcode += "<div class=\"row\">\n"
                            + "       <h3 class=\"center\">" + nombreEstudiante.getString(3) + " " + nombreEstudiante.getString(4) + ", " + nombreEstudiante.getString(1) + " " + nombreEstudiante.getString(2) + "</h3>\n"
                            + "       <div class=\"col s3\">\n"
                            + "              <h4 class=\"center\">Primer Parcial</h4>\n"
                            + "              <h3 class=\"center\">" + nota + "</h3>\n"
                            + "       </div>\n";
                    if (aproboSegundoParcial) {
                        nota = (notMo.getNotaSegundoParcial(CI_Estudiante) / 2) * 0.35;
                        htmlcode += "       <div class=\"col s3\">\n"
                                + "              <h4 class=\"center\">Segundo Parcial</h4>\n"
                                + "              <h3 class=\"center\">" + nota + "</h3>\n"
                                + "       </div>\n";
                        if (evaluacionCompleta) {

                        } else {
                            htmlcode += "      <div class=\"col s6\">\n"
                                    + " <div class=\"row\">\n"
                                    + "    <form>"
                                    + "       \n";
                            htmlcode += "<h5 class=\"center\">Evaluacion Examen Final</h5>\n";
                            dimensiones = dimMo.getDimensiones(idMateria);
                            while (dimensiones.next()) {

                                switch (i) {
                                    case 1:
                                        htmlcode += "<div class=\"col s6\">\n"
                                                + "    <table class=\"bordered\">\n"
                                                + "         <thead>\n"
                                                + "             <tr>\n"
                                                + "                   <th>A - " + dimensiones.getString(2) + "</th>\n"
                                                + "                   <th>Puntaje</th>\n"
                                                + "             </tr>\n"
                                                + "         </thead>\n"
                                                + "         <tbody>\n";
                                        criterios = criMo.getCriterios(dimensiones.getInt(1));
                                        while (criterios.next()) {
                                            System.out.println("Criterio: " + criterios.getString(1));
                                            htmlcode += "             <tr>\n"
                                                    + "                   <td value=\"" + criterios.getInt(2) + "\" id=\"criterio" + c + "\">" + c + " - " + criterios.getString(1) + "</td>\n"
                                                    + "                   <td><input id=\"nota" + c + "\" type=\"number\" min=\"1\" max=\"10\" class=\"validate bold\" style=\"width: 60px; font-size: 30px\"></td>\n"
                                                    + "             </tr>\n";
                                            c++;
                                        }
                                        htmlcode += "         </tbody>\n"
                                                + "    </table>\n";
                                        break;
                                    case 2:
                                        htmlcode += "    <table class=\"bordered\">\n"
                                                + "         <thead>\n"
                                                + "             <tr>\n"
                                                + "                    <th>B - " + dimensiones.getString(2) + "</th>\n"
                                                + "                    <th>Puntaje</th>\n"
                                                + "             </tr>\n"
                                                + "         </thead>\n"
                                                + "         <tbody>\n";
                                        criterios = criMo.getCriterios(dimensiones.getInt(1));
                                        while (criterios.next()) {
                                            System.out.println("Criterio: " + criterios.getString(1));
                                            htmlcode += "             <tr>\n"
                                                    + "                   <td value=\"" + criterios.getInt(2) + "\" id=\"criterio" + c + "\">" + c + " - " + criterios.getString(1) + "</td>\n"
                                                    + "                   <td><input id=\"nota" + c + "\" type=\"number\" min=\"1\" max=\"10\" class=\"validate bold\" style=\"width: 60px; font-size: 30px\"></td>\n"
                                                    + "             </tr>\n";
                                            c++;
                                        }
                                        htmlcode += "         </tbody>\n"
                                                + "    </table>\n"
                                                + "</div>";
                                        break;
                                    case 3:
                                        htmlcode += "<div class=\"col s6\">\n"
                                                + "    <table class=\"bordered\">\n"
                                                + "         <thead>\n"
                                                + "             <tr>\n"
                                                + "                   <th>C - " + dimensiones.getString(2) + "</th>\n"
                                                + "                   <th>Puntaje</th>\n"
                                                + "             </tr>\n"
                                                + "         </thead>\n"
                                                + "         <tbody>\n";
                                        criterios = criMo.getCriterios(dimensiones.getInt(1));
                                        while (criterios.next()) {
                                            System.out.println("Criterio: " + criterios.getString(1));
                                            htmlcode += "             <tr>\n"
                                                    + "                   <td value=\"" + criterios.getInt(2) + "\" id=\"criterio" + c + "\">" + c + " - " + criterios.getString(1) + "</td>\n"
                                                    + "                   <td><input id=\"nota" + c + "\" type=\"number\" min=\"1\" max=\"10\" class=\"validate bold\" style=\"width: 60px; font-size: 30px\"></td>\n"
                                                    + "             </tr>\n";
                                            c++;
                                        }
                                        htmlcode += "         </tbody>\n"
                                                + "    </table>\n";
                                        break;
                                    case 4:
                                        htmlcode += "    <table class=\"bordered\">\n"
                                                + "         <thead>\n"
                                                + "             <tr>\n"
                                                + "                   <th>D - " + dimensiones.getString(2) + "</th>\n"
                                                + "                   <th>Puntaje</th>\n"
                                                + "             </tr>\n"
                                                + "         </thead>\n"
                                                + "         <tbody>\n";
                                        criterios = criMo.getCriterios(dimensiones.getInt(1));
                                        while (criterios.next()) {
                                            System.out.println("Criterio: " + criterios.getString(1));
                                            htmlcode += "             <tr>\n"
                                                    + "                   <td value=\"" + criterios.getInt(2) + "\" id=\"criterio" + c + "\">" + c + " - " + criterios.getString(1) + "</td>\n"
                                                    + "                   <td><input id=\"nota" + c + "\" type=\"number\" min=\"1\" max=\"10\" class=\"validate bold\" style=\"width: 60px; font-size: 30px\"></td>\n"
                                                    + "             </tr>\n";
                                            c++;
                                        }
                                        htmlcode += "         </tbody>\n"
                                                + "    </table>\n"
                                                + "</div>\n";
                                        break;
                                    default:
                                        break;
                                }
                                i++;
                            }
                            htmlcode += "        </form>\n"
                                    + "     </div>\n"
                                    + "     <div class=\"col s6 center-aling\">\n"
                                    + "         <button class=\"btn waves-effect waves-light yellow accent-2 blue-text left tooltipped\" data-position=\"button\" data-tooltip=\"Guardar\" type=\"button\" data-id=\"" + CI_Estudiante + "\" id=\"guardarNuevaNota\">\n"
                                    + "             Validar y Guardar<i class=\"material-icons right\">save</i>\n"
                                    + "         </button>\n"
                                    + "     <br><br><br><br>\n"
                                    + "     </div>\n"
                                    + "     <div id=\"notaGuardada\" class=\"col s6 center-aling\">\n"
                                    + "     </div>\n"
                                    + "</div>\n"
                                    + "</div>\n";

                        }
                    } else {
                        htmlcode += "       <div class=\"col s4\">\n"
                                + "              <h4 class=\"center\">Segundo Parcial</h4>\n"
                                + "              <h4 class=\"yellow accent-2 red-text center\">Aun sin Asignar Nota</h4>\n"
                                + "         </div>\n"
                                + "         <div class=\"col s4\">\n"
                                + "              <h4 class=\"center\">Examen Final</h4>\n"
                                + "              <h4 class=\"yellow accent-2 red-text center\">Asignacion no Disponible Aun</h4>\n"
                                + "          </div>\n"
                                + "</div>";
                    }
                } else {
                    htmlcode += "<div class=\"container\">\n"
                            + "     <div class=\"row\">\n"
                            + "             <h3 class=\"center\">" + nombreEstudiante.getString(3) + " " + nombreEstudiante.getString(4) + ", " + nombreEstudiante.getString(1) + " " + nombreEstudiante.getString(2) + "</h3>\n"
                            + "         <div class=\"col s4\">\n"
                            + "             <h4 class=\"center\">Primer Parcial</h4>\n"
                            + "             <h4 class=\"yellow accent-2 red-text center\">Aun sin Asignar Nota</h4>\n"
                            + "         </div>\n"
                            + "         <div class=\"col s4\">\n"
                            + "             <h4 class=\"center\">Segundo Parcial</h4>\n"
                            + "             <h4 class=\"yellow accent-2 red-text center\">Aun sin Asignar Nota</h4>\n"
                            + "         </div>\n"
                            + "         <div class=\"col s4\">\n"
                            + "             <h4 class=\"center\">Examen Final</h4>\n"
                            + "             <h4 class=\"yellow accent-2 red-text center\">Asignacion no Disponible Aun</h4>\n"
                            + "         </div>\n"
                            + "     </div>\n"
                            + "</div>\n";
                }

            } else {
                htmlcode += "\n"
                        + "    <div class=\"row\">\n"
                        + "        <h3 class=\"center\">" + nombreEstudiante.getString(3) + " " + nombreEstudiante.getString(4) + ", " + nombreEstudiante.getString(1) + " " + nombreEstudiante.getString(2) + "</h3>\n"
                        + "    </div>\n"
                        + "    <div class=\"col s12 center-align\">\n"
                        + "        <a href=\"asignar_practica.jsp?ci=" + nombreEstudiante.getString(5) + "\" id=\"asignar_practica\" class=\"waves-effect waves-light waves-teal yellow accent-2 blue-text btn tooltipped\" data-position=\"button\" data-tooltip=\"Asignar Practica\"><i class=\"material-icons left\">clear_all</i>Asignar Practica</a>\n"
                        + "         <br>\n"
                        + "         <br>\n"
                        + "         <br>\n"
                        + "         <br>\n"
                        + "    </div>\n"
                        + "";
            }
        } catch (Exception e) {
            System.out.println("Error getEvaluacion: " + e);
        }

        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en getEvaluacion.getCloseConexion: " + e);
        }
        return htmlcode;
    }

    public boolean bajaMateria(int idMateria) {

        Materia_model matMo = new Materia_model();
        boolean bandera;
        if (matMo.getEstadoMateria(idMateria) == 1) {
            bandera = matMo.baja_materia(idMateria, 0);
            try {
                getCloseConexion();
            } catch (Exception e) {
                System.out.println("Error en bajaMateria.getCloseConexion: " + e);
            }
        } else {
            bandera = matMo.baja_materia(idMateria, 1);
            try {
                getCloseConexion();
            } catch (Exception e) {
                System.out.println("Error en bajaMateria.getCloseConexion: " + e);
            }
        }
        return bandera;
    }

    public String getResumenMaterias(int idMateria) {

        Materia_model matMo = new Materia_model();
        String nombreMateria = matMo.getNombreMateria(idMateria);
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en getResumenMaterias.getCloseConexion: " + e);
        }
        Dimension_model dimMo = new Dimension_model();
        int cantidadDimensionesXMateria = dimMo.getCantidadDimensionesXMat(idMateria);
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en getResumenMaterias.getCloseConexion: " + e);
        }
        String htmlcode = "                    <div class=\"container center\">\n"
                + "                        <div class=\"row\">\n"
                + "                            <div class=\"col s12\">\n"
                + "                                <h5>Materia</h5>\n"
                + "                                <h3>" + nombreMateria + "</h3>\n"
                + "                            </div>\n"
                + "                        </div>\n"
                + "                        <div class=\"row\">\n"
                + "                            <div class=\"col s12\">\n"
                + "                                <h5>Dimensiones</h5>\n"
                + "                                <h3>" + cantidadDimensionesXMateria + "</h3>\n"
                + "                            </div>\n"
                + "                        </div>\n"
                + "                    </div>\n";

        return htmlcode;
    }

    public String getEditarMateria(int idMateria) {

        String htmlcode = "";
        Materia_model matMo = new Materia_model();
        ResultSet materia = matMo.getMateria(idMateria);

        try {
            materia.next();
            htmlcode = "           <div class=\"container\">\n"
                    + "                        <div class=\"row\">\n"
                    + "                            <h1 class=\"center yellow-text\">Actualizar Materia</h1>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"row\">\n"
                    + "                            <form id=\"updateMateria\" class=\"col s12 yellow-text\">\n"
                    + "                                <div class=\"row\">\n"
                    + "                                    <div class=\"input-field col s6\">\n"
                    + "                                        <i class=\"material-icons prefix yellow-text\">assignment_ind</i>\n"
                    + "                                        <input id=\"nombreMatAC\" type=\"text\" value=\"" + materia.getString(3) + "\" class=\"validate\">\n"
                    + "                                        <label class=\"yellow-text\" for=\"Nombre Materia\">Nombre Materia</label>\n"
                    + "                                    </div>\n"
                    + "                                    <div class=\"input-field col s6\">\n"
                    + "                                        <i class=\"material-icons prefix yellow-text\">assignment_ind</i>\n"
                    + "                                        <input id=\"semestreMatAC\" type=\"text\" value=\"" + materia.getString(5) + "\" >\n"
                    + "                                        <label class=\"yellow-text\" for=\"Semestre\">Semestre</label>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                                <div class=\"row\">\n"
                    + "                                    <div class=\"input-field col s6\">\n"
                    + "                                        <i class=\"material-icons prefix yellow-text\">wc</i>\n"
                    + "                                        <input id=\"horasPracticasAC\" type=\"number\" value=\"" + materia.getInt(6) + "\" class=\"validate\">\n"
                    + "                                        <label class=\"yellow-text\" for=\"Horas Practicas\">Horas Practicas</label>\n"
                    + "                                    </div>\n"
                    + "                                    <div class=\"input-field col s6\">\n"
                    + "                                        <i class=\"material-icons prefix yellow-text\">wc</i>\n"
                    + "                                        <input id=\"descripcionMateriaAC\" type=\"text\" value=\"" + materia.getString(4) + "\" class=\"validate\">\n"
                    + "                                        <label class=\"yellow-text\" for=\"Descripcion Materia\">Descripcion Materia</label>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </form>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"col s6\">\n"
                    + "                            <button class=\"btn waves-effect waves-light yellow accent-2 blue-text left\" type=\"button\" id=\"actualizarMateria\" data-id=\"" + materia.getInt(2) + "\" >\n"
                    + "                                Validar y Guardar<i class=\"material-icons right\">save</i>\n"
                    + "                            </button>\n"
                    + "                            <br><br><br><br>\n"
                    + "                        </div>\n"
                    + "                        <div id=\"notificacionACMateria\">\n"
                    + "                        </div>\n"
                    + "                    </div>";

        } catch (Exception e) {
            System.out.println("Error en  getEditarMateria: " + e);
        }
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en getEditarMateria.getCloseConexion: " + e);
        }

        return htmlcode;
    }

    public boolean updateMateria(Materia materia) {

        Materia_model matMo = new Materia_model();
        boolean bandera = matMo.actualizarMateria(materia);
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en updateMateria.getCloseConexion: " + e);
        }
        return bandera;
    }

    public String getViewCriteriosXDimension(int idDimension) {

        String htmlcode = "          <div class=\"container\">\n"
                + "                        <div class=\"row\">\n"
                + "                            <div class=\"col s12\">\n"
                + "                                <h1 class=\"center yellow-text\">Criterios</h1>\n"
                + "                                <a href=\"#new\" class=\"waves-effect waves-light yellow accent-2 blue-text text-darken-3 btn-large right modal-trigger\"><i class=\"material-icons left\">person_add</i>Nuevo</a>\n"
                + "                            </div>\n"
                + "                        </div>\n"
                + "                    </div>"
                + "                 <div class=\"container\">\n"
                + "                        <table class=\"highlight responsive-table blue darken-3 yellow-text\">\n"
                + "                            <thead>\n"
                + "                                <tr>\n"
                + "                                    <th>#</th>\n"
                + "                                    <th>Nombre</th>\n"
                + "                                    <th>Estado</th>\n"
                + "                                    <th class=\"center-align\">Ver - Editar</th>\n"
                + "                                    <th class=\"center-align\">Dar de Baja</th>\n"
                + "                                </tr>\n"
                + "                            </thead>\n"
                + "                            <tbody>\n";
        ResultSet criterio;
        Criterios_model criMo = new Criterios_model();
        criterio = criMo.getListaCriterioXDimnension(idDimension);
        int i = 1;
        if (criterio != null) {
            try {
                while (criterio.next()) {
                    System.out.println(criterio.getString(2));
                    htmlcode += "                <tr>\n"
                            + "                    <td>" + i + "</td>\n"
                            + "                    <td>" + criterio.getString(2) + "</td>\n";

                    if (criterio.getInt(3) == 1) {
                        htmlcode += "                    <td>Activo</td>\n";
                    } else {
                        htmlcode += "                    <td>Inactivo</td>\n";

                    }
                    htmlcode += "    <td><div class=\"center-align\"><a href=\"criterio.jsp?criterio=" + criterio.getInt(1) + "\" id=\"ver_criterio\" class=\"btn-floating btn tooltipped waves-effect waves-light blue yellow-text modal-trigger\" data-position=\"button\" data-tooltip=\"Ver\"><i class=\"material-icons yellow-text\">description</i></a></div></td>\n"
                            + "     <td><div class=\"center-align\"><a id=\"baja_criterio\" data-id=\"" + criterio.getInt(1) + "\" class=\"btn-floating btn tooltipped waves-effect waves-light blue yellow-text\" data-position=\"button\" data-tooltip=\"Dar de Baja\"><i class=\"material-icons yellow-text\">redo</i></a></div></td>\n"
                            + "  </tr>"
                            + "";
                    i++;

                }
            } catch (SQLException ex) {
                System.out.println("Error en getViewCriteriosXDimension: " + ex);
            }
        } else {
            htmlcode += "";

        }
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en getViewCriteriosXDimension.getCloseConexion: " + e);
        }
        return htmlcode += "                        </tbody>\n"
                + "                        </table>\n"
                + "                    </div>";
    }
}
