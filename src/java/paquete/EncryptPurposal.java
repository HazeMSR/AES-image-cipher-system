/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Master
 */
public class EncryptPurposal extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String mensaje = request.getParameter("texto");
        String u2 =request.getParameter("u2");
        String text = "0";
        String receiver = request.getParameter("r2");
        
        System.out.println("receiver: "+receiver);
        System.out.println("Texto: "+mensaje);
        System.out.println("user_id: "+u2);
        int user_id = Integer.valueOf(u2);

        try {
       
        String selectSQL = "SELECT * FROM user WHERE user=?";
        String rutaLlavePub="";
        String rutaLlavePriv="";
        ResultSet rs = null, rs2 = null;
        
        Connection conn = Conexion.getConexion();
        int id = -1;
    Random randomGenerator = new Random();
   
      int randomInt = randomGenerator.nextInt(10000);
        try (
                PreparedStatement pstmt = conn.prepareStatement(selectSQL);) {
            // set parameter;
            pstmt.setString(1, receiver);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
            System.out.println("Se pudo consultar del usuario");

        } catch (SQLException e) {
            text = "2";
            System.out.println("Hubo un error al consultar del usuario: ");
            System.out.println(e.getMessage());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rutaLlavePub="C:/Users/Master/Documents/NetBeansProjects/crypto2/Llaves/temppubk"+id+receiver+".txt";
                    rutaLlavePriv="C:/Users/Master/Documents/NetBeansProjects/crypto2/Llaves/tempprivk"+id+receiver+".txt";
                    
                    System.out.println("Ruta Llave Pub: "+rutaLlavePub);
                    System.out.println("Ruta Llave Priv: "+rutaLlavePriv);
                    String res = crypto.readBlob2(id,rutaLlavePub,rutaLlavePriv);

                } else {
                    System.out.println("No se encontro algun usuario relacionado en la base");
                }
            } catch (SQLException e) {
                text = "5";
                System.out.println("Error al intentar grabar en la tabla image");
                System.out.println(e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(UploadImage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            new Message(mensaje, "C:/Users/Master/Documents/NetBeansProjects/crypto2/Llaves/userprivk1985.txt").writeToFile("C:/Users/Master/Documents/NetBeansProjects/crypto2/Mensaje/tempMess"+id+receiver+randomInt+".txt");
            //new Message(mensaje, "C:/Users/Master/Documents/NetBeansProjects/crypto2/Llaves/tempprivk0artist.txt").writeToFile("C:/Users/Master/Documents/NetBeansProjects/crypto2/Mensaje/tempMess.txt");
            File llaveAES = new File("C:/Users/Master/Documents/NetBeansProjects/crypto2/Llaves/userpubk1985.txt");
            File enc_img = new File("C:/Users/Master/Documents/NetBeansProjects/crypto2/Mensaje/tempMess"+id+receiver+randomInt+".txt");
            int ret = crypto.writeBlob4(user_id, "tempMess"+id+receiver+randomInt+".txt", enc_img, llaveAES, id);

                    
                    if (ret == 0) {
                        text="The purposal could not be saved, with another.";
                        System.out.println("No grabo en la BD");
                    } else if (ret == 1) {
                        text="The purposal was successfully saved.";
                        System.out.println("Grabo en la BD");
                    } else {
                        text="The purposal could not be saved, with another.";
                        System.out.println("Hubo un error al grabar en la BD");
                    }
        } catch (Exception ex) {
            Logger.getLogger(EncryptPurposal.class.getName()).log(Level.SEVERE, null, ex);
        }
                response.setContentType("text/html;charset=UTF-8");
        
        response.getWriter().write("<script>"+
                "alert('"+text+"');"+
                "location='client.jsp';"+
                "</script>");
        //response.sendRedirect(page);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
