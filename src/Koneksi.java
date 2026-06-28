/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {
    private static Connection koneksi;

    public static Connection getKoneksi() {
        if (koneksi == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/db_kafe";
                String user = "root";
                String pass = "";
                
                koneksi = DriverManager.getConnection(url, user, pass);
            } catch (Exception e) {
                System.out.println("❌ Koneksi ke Database Gagal: " + e.getMessage());
            }
        }
        return koneksi;
    }
}
