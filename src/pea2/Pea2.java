
package pea2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

 
public class Pea2 {

    
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/apple";
            String user = "root";
            String pwd = "mysqladmin";
            try (Connection conect = DriverManager.getConnection(url, user, pwd); Statement statement = conect.createStatement()) {
                String sql = "select * from dispositivo";
                try (ResultSet resultset = statement.executeQuery(sql)) {
                    while (resultset.next()) {
                        int id = resultset.getInt("id");
                        String nombre = resultset.getString("nombre");                  
                        int precio = resultset.getInt("precio");
                        String tipo = resultset.getString("tipo");
                        System.out.println("id " + id);
                        System.out.println("nombre" + nombre);
                        System.out.println("precio" + precio);
                        System.out.println("tipo" + tipo);
                        System.out.println("==============================");
                    }
                    Scanner scan = new Scanner(System.in);
                    System.out.println("¿que desas hacer : insertar / actualizar /eliminar");
                    String accion = scan.nextLine();
                    if (accion.equals("insertar")) {
                        scan = new Scanner(System.in);
                        System.out.println("ingresa el id");
                        String idprod = scan.nextLine();

                        scan = new Scanner(System.in);
                        System.out.println("ingresa el nombre");
                        String nombre = scan.nextLine();

                        scan = new Scanner(System.in);
                        System.out.println("ingresa el precio");
                        String precio = scan.nextLine();
                        
                        scan = new Scanner(System.in);
                        System.out.println("ingresa el tipo");
                        String tipo = scan.nextLine();
                      
                       
                        String query = "insert into dispositivo values(?,?,?,?)";
                        PreparedStatement ps = conect.prepareStatement(query);
                        ps.setInt(1, Integer.parseInt(idprod));
                        ps.setString(2, nombre);
                        ps.setInt(3, Integer.parseInt(precio));
                        ps.setString(4, tipo);
                        
                        ps.executeUpdate();

                    }
                    if (accion.equals("eliminar")) {
                        scan = new Scanner(System.in);
                        System.out.println("ingresa el id");
                        String id = scan.nextLine();
                        String query = "delete from dispositivo where id=?";
                        PreparedStatement ps = conect.prepareStatement(query);
                        ps.setInt(1, Integer.parseInt(id));
                        ps.executeUpdate();
                    }
                    if (accion.equals("actualizar")) {
                        scan = new Scanner(System.in);
                        System.out.println("ingresa el dispositivo");
                        String id = scan.nextLine();

                        scan = new Scanner(System.in);
                        System.out.println("ingresa el nombre");
                        String nombre = scan.nextLine();

                        scan = new Scanner(System.in);
                        System.out.println("ingresa el precio");
                        String precio = scan.nextLine();
                        
                        scan = new Scanner(System.in);
                        System.out.println("ingresa el tipo");
                        String tipo = scan.nextLine();
                        //
                        String query = "update dispositivo set nombre=?, precio=?,tipo=? where id=?";
                        PreparedStatement ps = conect.prepareStatement(query);
                        ps.setString(1, nombre);
                        ps.setInt(2, Integer.parseInt(precio));
                        ps.setInt(3, Integer.parseInt(id));
                        ps.setString(4, tipo);
                        
                        ps.executeUpdate();
                    }
                    if (accion.equals("procedure")) {
                     
                        scan = new Scanner(System.in);
                        System.out.println("ingresa el nombre");
                        String nombre = scan.nextLine();

                        scan = new Scanner(System.in);
                        System.out.println("ingresa el precio");
                        String precio = scan.nextLine();
                        
                           scan = new Scanner(System.in);
                        System.out.println("ingresa el tipo");
                        String tipo = scan.nextLine();
                        //
                        String query = "call insertar_dispositivo(?,?,?)";
                        PreparedStatement ps = conect.prepareStatement(query);
                        ps.setString(1, nombre);
                        ps.setInt(2, Integer.parseInt(precio));
                        ps.setString(3, tipo);
                      
                        
                ;
                        
                        ps.executeUpdate();}
                   
                      
                        
                     
                    resultset.close();
                    statement.close();
                    conect.close();
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    
    }
    
}
