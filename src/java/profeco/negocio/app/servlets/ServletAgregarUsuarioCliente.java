/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profeco.negocio.app.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import profeco.negocio.app.services.ClienteServices;
import profeco.negocio.app.services.UsuarioServices;
import profeco.negocio.app.dto.Cliente;
import profeco.negocio.app.dto.Usuario;

/**
 *
 * @author Luis Barroso
 */
@WebServlet(name = "ServletAgregarUsuarioCliente", urlPatterns = {"/ServletAgregarUsuarioCliente"})
@MultipartConfig
public class ServletAgregarUsuarioCliente extends HttpServlet {

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
        String estado = "";
        try {
            ClienteServices services = new ClienteServices();

            String idUsuario = request.getParameter("idUsuario");
            String Nombre = request.getParameter("Nombre");
            boolean Sexo = Boolean.parseBoolean(request.getParameter("Select"));
            String Correo = request.getParameter("Correo");
            String Contrasena = request.getParameter("Password");
            byte Edad = Byte.parseByte(request.getParameter("Edad"));

            Cliente str = new Cliente();

            str.setIdCliente(idUsuario);
            str.setSexo(Sexo);
            str.setNombreCliente(Nombre);
            str.setCorreo(Correo);
            str.setEdad(Edad);

            services.Agregar(str);

            Usuario obj = new Usuario();

            obj.setIdUsuario(idUsuario);
            obj.setIdTipoUsuario(2);
            obj.setNombre(Nombre);
            obj.setIdCOS(2);
            obj.setContrasena(Contrasena);
            obj.setCorreo(Correo);

            new UsuarioServices().Agregar(obj);
            
            estado = "correcto";
        } catch (Exception e) {
            estado = "no correcto";
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            out.println(estado);

        }
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
