/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profeco.negocio.app.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperRunManager;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import net.sf.jasperreports.engine.JRException;
import profeco.negocio.app.ds.Conexion;

/**
 *
 * @author carlos
 */
@WebServlet(name = "ServletReporteCalificaciones", urlPatterns = {"/ServletReporteCalificaciones"})
public class ServletReporteCalificaciones extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException,JRException{
             Connection con;
 Conexion conn=Conexion.getConexionInstance();
 con=DriverManager.getConnection("jdbc:sqlserver://sql5104.site4now.net; database=db_a747f3_profecoapp; user=db_a747f3_profecoapp_admin ; password=adodesconectado1;");
                
 File reportFile=new File(request.getServletContext().getRealPath("Calificaciones/ReporteCalificaciones.jasper"));
                        Map<String,Object> parametros=new HashMap<String,Object>();
                        byte [] bytes= JasperRunManager.runReportToPdf(reportFile.getPath(), parametros,con);
                        response.setContentType("application/pdf");
                        response.setContentLength(bytes.length);

                        ServletOutputStream output=response.getOutputStream();
                        response.getOutputStream();
                        output.write(bytes,0,bytes.length);
                        output.flush();
                        output.close();
 

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletReporteInconsistencias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ServletReporteInconsistencias.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletReporteInconsistencias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ServletReporteInconsistencias.class.getName()).log(Level.SEVERE, null, ex);
        }
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
