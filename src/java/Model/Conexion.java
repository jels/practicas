/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author WarMachine
 */
public class Conexion {

    private String USERNAME = "root";
    private String PASSWORD = "";
    private String HOST = "localhost";
    private String PORT = "3306";
    private String DATABASE = "sistemapracticas";
    private String CLASSNAME = "com.mysql.jdbc.Driver";
    private String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
    private Connection con;

    public Conexion() {

        try {
            Class.forName(CLASSNAME);
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: " + e);

        } catch (SQLException ex) {
            System.err.println("Error sql: " + ex);
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void getCloseConexion() throws SQLException {
        this.con.close();
        System.out.println("Conexion cerrada..!!");
    }

//    public Connection getClose() {
//        this.con.close();
//    }
//    public static void main(String[] args) {
//        Conexion con=new Conexion();
//    }
    //        String saludo = "Hola Mundo";
//        String cortado = saludo.substring(0, 6);
//        System.out.println("saludo cortado: " + cortado);
}
