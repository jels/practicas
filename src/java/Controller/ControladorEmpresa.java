/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author WarMachine
 */
public class ControladorEmpresa extends Conexion {

    Empresa_model empMo = new Empresa_model();
    Representante_model repMo = new Representante_model();
    Tutor_model tutMo = new Tutor_model();
    int cantidad;
    String htmlcode = " ";
    boolean bandera;

    public int countEmpresas() {
        cantidad = empMo.getCantidadEmpresas();
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en countEmpresas.getCloseConexion: " + e);
        }
        return cantidad;
    }

    public int countEmpresasActivas() {
        cantidad = empMo.getCantidadEmpresasEstado(1);
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en countEmpresasActivas.getCloseConexion: " + e);
        }
        return cantidad;
    }

    public int countEmpresasInactivas() {
        cantidad = empMo.getCantidadEmpresasEstado(0);
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en countEmpresasInactivas.getCloseConexion: " + e);
        }
        return cantidad;
    }

    public String verEmpresas() {
        ResultSet empresa = empMo.getEmpresas();
        int i = 1;
        htmlcode = "";
        htmlcode = "          <div class=\"container\">\n"
                + "                        <div class=\"row\">\n"
                + "                            <div class=\"col s12\">\n"
                + "                                <h1 class=\"center yellow-text\">Empresas</h1>\n"
                + "                                <a href=\"#search\" class=\"waves-effect waves-light waves-teal yellow accent-2 blue-text text-darken-3 btn-large modal-trigger\"><i class=\"material-icons right\">search</i>Buscar</a>\n"
                + "                                <a href=\"#new\" class=\"waves-effect waves-light waves-teal yellow accent-2 blue-text text-darken-3 btn-large right modal-trigger\"><i class=\"material-icons left\">add_to_photos</i>Nueva</a>\n"
                + "                            </div>\n"
                + "                        </div>\n"
                + "                    </div>"
                + "                 <div class=\"container\">\n"
                + "                        <table class=\"highlight responsive-table blue darken-3 yellow-text\">\n"
                + "                            <thead>\n"
                + "                                <tr>\n"
                + "                                    <th>#</th>\n"
                + "                                    <th>Nombre</th>\n"
                + "                                    <th>Dirección</th>\n"
                + "                                    <th>Teléfono</th>\n"
                + "                                    <th>Rubro</th>\n"
                + "                                    <th>Estado</th>\n"
                + "                                    <th class=\"center-align\">Ver - Editar</th>\n"
                + "                                    <th class=\"center-align\">Dar de Baja</th>\n"
                + "                                </tr>\n"
                + "                            </thead>\n"
                + "                            <tbody>\n";
        try {
            if (empresa == null) {

            } else {

                while (empresa.next()) {
                    System.out.println(empresa.getString(1));
                    htmlcode += "                <tr>\n"
                            + "                    <td>" + i + "</td>\n"
                            + "                    <td>" + empresa.getString(2) + "</td>\n"
                            + "                    <td>" + empresa.getString(3) + "</td>\n"
                            + "                    <td>" + empresa.getString(4) + "</td>\n"
                            + "                    <td>" + empresa.getString(5) + "</td>\n";
                    if (empresa.getInt(6) == 1) {
                        htmlcode += "                    <td>Activo</td>\n";
                    } else {
                        htmlcode += "                    <td>Inactivo</td>\n";
                    }

                    htmlcode += "                    <td><div class=\"center-align\">  <a id=\"ver_empresa\" href=\"empresa_ver.jsp?empresa=" + empresa.getString(1) + "\" class=\"btn-floating btn tooltipped waves-effect waves-light blue yellow-text\" data-position=\"button\" data-tooltip=\"Ver - Editar\"><i class=\"material-icons yellow-text\">store</i></a></div></td>\n"
                            + "                    <td><div class=\"center-align\">  <a id=\"baja_empresa\" data-id=\"" + empresa.getString(1) + "\" class=\"btn-floating btn tooltipped waves-effect waves-light blue yellow-text\" data-position=\"button\" data-tooltip=\"Dar de Baja\"><i class=\"material-icons yellow-text\">redo</i></a></div></td>\n";

                    i++;

                }

            }
            getCloseConexion();
        } catch (SQLException ex) {
            System.out.println("Error en verEmpresas: " + ex);
        }
        htmlcode += "                        </tbody>\n"
                + "                        </table>\n"
                + "                    </div>";
        return htmlcode;
    }

    public boolean newEmpresa(Empresa emp) {
        bandera = empMo.nueva_empresa(emp);
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en newEmpresa.getCloseConexion: " + e);
        }
        return bandera;
    }

    public boolean bajaEmpresa(int idEmpresa) {
        bandera = empMo.darBajaEmpresa(idEmpresa);
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en bajaEmpresa.getCloseConexion: " + e);
        }
        return bandera;
    }

    public int contarTutoresXempresaEstado(int idEmpresa, int estado) {
        cantidad = tutMo.contar_tutorEstado(idEmpresa, estado);
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en contarTutoresXempresaEstado.getCloseConexion: " + e);
        }
        return cantidad;
    }

    public int contarTutoresEmpresa(int idEmpresa) {
        cantidad = tutMo.contar_tutorEmpresa(idEmpresa);
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en contarTutoresEmpresa.getCloseConexion: " + e);
        }
        return cantidad;
    }

    public String verResumenXempresa(int idEmpresa) {
        htmlcode = "";
        try {
            String nombreEmpresa = empMo.getNombreEmpresa(idEmpresa);
            getCloseConexion();
            String representante = repMo.getNombreRepresentanteEmpresa(idEmpresa);
            getCloseConexion();
            htmlcode = "                <div class=\"container\">\n"
                    + "                        <h1 class=\"center\">Empresa</h1>\n"
                    + "                        <h3 class=\"center\">" + nombreEmpresa + "</h3>\n"
                    + "                        <h2 class=\"center\">Representante</h2>\n"
                    + "                        <h4 class=\"center\">" + representante + "</h4>\n"
                    + "                        <div class=\"row\">\n"
                    + "                            <div class=\"col s12\">\n"
                    + "                                <h2 class=\"center\">" + contarTutoresEmpresa(idEmpresa) + "</h2>\n"
                    + "                                <h3 class=\"center\">Total de Tutores Almacenados</h3>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"row\">\n"
                    + "                            <div class=\"col s6\">\n"
                    + "                                <h2 class=\"center\">" + contarTutoresXempresaEstado(idEmpresa, 1) + "</h2>\n"
                    + "                                <h5 class=\"center\">Tutores Activos</h5>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"col s6\">\n"
                    + "                                <h2 class=\"center\">" + contarTutoresXempresaEstado(idEmpresa, 0) + "</h2>\n"
                    + "                                <h5 class=\"center\">Tutores Inactivos</h5>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                    </div>\n";
        } catch (Exception e) {
            System.out.println("Error en verResumenXempresa.getCloseConexion: " + e);
        }
        return htmlcode;
    }

    public String verTutoresXempresa(int idEmpresa) {
        htmlcode = "";
        htmlcode = "                <div class=\"container\">\n"
                + "                        <div class=\"row\">\n"
                + "                             <div class=\"col s12\">\n"
                + "                                 <h1 class=\"center yellow-text\">Tutores</h1>\n"
                + "                                 <a href=\"#new\" class=\"waves-effect waves-light yellow accent-2 blue-text text-darken-3 btn-large right modal-trigger\"><i class=\"material-icons left\">person_add</i>Nuevo</a>\n"
                + "                               </div>\n"
                + "                        </div>\n"
                + "                        <div class=\"row\">\n"
                + "                            <table class=\"bordered\">\n"
                + "                                <thead>\n"
                + "                                    <tr>\n"
                + "                                        <th>#</th>\n"
                + "                                        <th>Foto</th>\n"
                + "                                        <th>Nombre</th>\n"
                + "                                        <th>N° de Carnet</th>\n"
                + "                                        <th>Teléfono</th>\n"
                + "                                        <th>Cargo</th>\n"
                + "                                        <th>Estado</th>\n"
                + "                                        <th class=\"center\">Ver - Editar</th>\n"
                + "                                        <th class=\"center\">Dar de Baja</th>\n"
                + "                                    </tr>\n"
                + "                                </thead>\n"
                + "\n"
                + "                                <tbody>\n";

        ResultSet tutores = tutMo.getTutoresXempresa(idEmpresa);
        int c = 0;
        try {
            while (tutores.next()) {
                c++;
                htmlcode += "                                    <tr>\n"
                        + "                                        <td>" + c + "</td>\n"
                        + "                                        <td><img class=\"responsive-img materialboxed\" src=\"../../img/fcea/tutores/" + tutores.getString(10) + "\" alt=\"" + tutores.getString(2) + "\" width=\"50\"></td>\n"
                        + "                                        <td>" + tutores.getString(4) + " " + tutores.getString(5) + ", " + tutores.getString(2) + " " + tutores.getString(3) + "</td>\n"
                        + "                                        <td>" + tutores.getString(6) + "</td>\n"
                        + "                                        <td>" + tutores.getString(7) + "</td>\n"
                        + "                                        <td>" + tutores.getString(8) + "</td>\n";
                if (tutores.getInt(9) == 1) {
                    htmlcode += "<td>Activo</td>\n";

                } else {
                    htmlcode += "<td>Inactivo</td>\n";
                }
                htmlcode += "                 <td><div class=\"center-align\">  <a href=\"tutor.jsp?tutor=" + tutores.getInt(1) + "&empresa=" + idEmpresa + "\" class=\"btn-floating btn tooltipped waves-effect waves-light blue yellow-text\" data-position=\"button\" data-tooltip=\"Ver - Actualizar\"><i class=\"material-icons yellow-text\">visibility</i></a></div></td>\n"
                        + "                   <td><div class=\"center-align\">  <a id=\"baja_tutor\" data-id=\"" + tutores.getInt(1) + "\" class=\"btn-floating btn tooltipped waves-effect waves-light blue yellow-text\" data-position=\"button\" data-tooltip=\"Dar de Baja\"><i class=\"material-icons yellow-text\">redo</i></a></div></td>\n"
                        + "                </tr>\n";
            }
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en verTutoresXempresa: " + e);
        }
        htmlcode += "                                </tbody>\n"
                + "                            </table>\n"
                + "                        </div>\n"
                + "                    </div>";
        return htmlcode;
    }

    public String editarEmpresa(int idEmpresa) {

        htmlcode = "";
        ResultSet empresa = empMo.getEmpresaXID(idEmpresa);
        try {
            empresa.next();
            htmlcode = "                 <div class=\"container\">\n"
                    + "                        <div class=\"row\">\n"
                    + "                            <h3 class=\"center yellow-text\">Actualizar Datos de la Empresa</h3>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"row\">\n"
                    + "                            <form id=\"updateEmpresa\" class=\"col s12 yellow-text\">\n"
                    + "                                <div class=\"row\">\n"
                    + "                                    <div class=\"input-field col s6\">\n"
                    + "                                        <i class=\"material-icons prefix yellow-text\">store</i>\n"
                    + "                                        <input id=\"nombreEmpresaAC\" type=\"text\" value=\"" + empresa.getString(2) + "\" class=\"validate\">\n"
                    + "                                        <label class=\"yellow-text\" for=\"Nombre\">Nombre</label>\n"
                    + "                                    </div>\n"
                    + "                                    <div class=\"input-field col s6\">\n"
                    + "                                        <i class=\"material-icons prefix yellow-text\">directions</i>\n"
                    + "                                        <input id=\"direccionEmpresaAC\" type=\"text\" value=\"" + empresa.getString(3) + "\" class=\"validate\">\n"
                    + "                                        <label class=\"yellow-text\" for=\"Direccion\">Dirección</label>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                                <div class=\"row\">\n"
                    + "                                    <div class=\"input-field col s6\">\n"
                    + "                                        <i class=\"material-icons prefix yellow-text\">contacts</i>\n"
                    + "                                        <input id=\"telefonoEmpresaAC\" type=\"text\" value=\"" + empresa.getString(4) + "\" class=\"validate\">\n"
                    + "                                        <label class=\"yellow-text\" for=\"Telefono\">Teléfono</label>\n"
                    + "                                    </div>\n"
                    + "                                    <div class=\"input-field col s6\">\n"
                    + "                                        <i class=\"material-icons prefix yellow-text\">description</i>\n"
                    + "                                        <input id=\"rubroEmpresaAC\" type=\"text\" value=\"" + empresa.getString(5) + "\" class=\"validate\">\n"
                    + "                                        <label class=\"yellow-text\" for=\"Rubro\">Rubro</label>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </form>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"col s6\">\n"
                    + "                            <button class=\"btn waves-effect waves-light yellow accent-2 blue-text left\" type=\"button\" id=\"actualizarEmpresa\" data-id=\"" + idEmpresa + "\">\n"
                    + "                                Validar y Guardar<i class=\"material-icons right\">save</i>\n"
                    + "                            </button>\n"
                    + "                            <br><br><br><br>\n"
                    + "                        </div>\n"
                    + "                        <div id=\"notificacionUPEmpresa\">\n"
                    + "                        </div>\n"
                    + "                    </div>";
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en editarEmpresa: " + e);
        }
        return htmlcode;
    }

    public boolean updateEmpresa(Empresa emp) {
        bandera = empMo.actualizarEmpresa(emp);
        try {
            getCloseConexion();
        } catch (Exception e) {
            System.out.println("Error en updateEmpresa: " + e);
        }
        return bandera;
    }

}
