
package paquete;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Master
 */
public class DownloadSPurposal extends HttpServlet {

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

        // reads input file from an absolute path
        String filePath=request.getParameter("name");
        String dp=request.getParameter("dp");
        int id = Integer.valueOf(request.getParameter("id"));
        System.out.println("NAME: "+filePath);

            String v1="C:/Users/Master/Documents/NetBeansProjects/crypto2/Mensaje/"+filePath;
            String v2="C:/Users/Master/Documents/NetBeansProjects/crypto2/Llaves/userpubk1985.txt";
            String v3="C:/Users/Master/Documents/NetBeansProjects/crypto2/Mensaje/Dec"+filePath;
            System.out.println("v1: "+v1);
            System.out.println("v2: "+v2);
            System.out.println("v3: "+v3); 
            
            try {

            //        new VerifyMessage("C:/Users/Master/Documents/NetBeansProjects/crypto2/Mensaje/prueba.txt", "C:/Users/Master/Documents/NetBeansProjects/crypto2/Llaves/userpubk1985.txt","C:/Users/Master/Documents/NetBeansProjects/crypto2/Mensaje/Dec2prueba.txt");
            if(dp.equals("1"))
                new VerifyMessage(v1,v2,v3);
            

        } catch (Exception ex) {
            Logger.getLogger(DownloadSPurposal.class.getName()).log(Level.SEVERE, null, ex);
        }
            File downloadFile=null;
        if(dp.equals("1"))
            downloadFile = new File(v3);
        else
            downloadFile = new File(v1);
        FileInputStream inStream = new FileInputStream(downloadFile);
         
        // if you want to use a relative path to context root:
        String relativePath = getServletContext().getRealPath("");
        System.out.println("relativePath = " + relativePath);
         
        // obtains ServletContext
        ServletContext context = getServletContext();
         
        // gets MIME type of the file
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {        
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
         
        // modifies response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
         
        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
         
        // obtains response's output stream
        OutputStream outStream = response.getOutputStream();
         
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
         
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
         
        inStream.close();
        outStream.close(); 
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
