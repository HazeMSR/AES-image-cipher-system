
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mysql.jdbc.Connection.*;
import com.mysql.jdbc.util.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
/**
 *
 * @author mysqltutorial.org
 */
public class crypto {

    

    public static void writeBlob(int candidateId, String filename) throws SQLException {
        // update sql
        Connection conn=Conexion.getConexion();
 
        String updateSQL = "UPDATE image "
                + "SET image = ? "
                + "WHERE id=?";
        


        try (
                PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
 
            // read the file
            File file = new File(filename);
            FileInputStream input = new FileInputStream(file);
 
            // set parameters
            pstmt.setBinaryStream(1, input);
            pstmt.setInt(2, candidateId);
 
            // store the resume file in database
            System.out.println("Reading file " + file.getAbsolutePath());
            System.out.println("Store file in the database.");
            pstmt.executeUpdate();
 
        } catch (SQLException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
   public static void readBlob(int candidateId, String filename) throws IOException {
        // update sql
        
 
        String selectSQL = "SELECT image FROM image WHERE id=?";
        ResultSet rs = null;
 
        try (Connection conn=Conexion.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(selectSQL);) {
            // set parameter;
            pstmt.setInt(1, candidateId);
            rs = pstmt.executeQuery();
 
            // write binary stream into file
            File file = new File(filename);
            FileOutputStream output = new FileOutputStream(file);
 
            System.out.println("Writing to file " + file.getAbsolutePath());
            while (rs.next()) {
                InputStream input = rs.getBinaryStream("image");
                byte[] buffer = new byte[1024];
                while (input.read(buffer) > 0) {
                    output.write(buffer);
                }
            }
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
 
    }
 



}