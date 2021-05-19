/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profeco.negocio.app.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import profeco.negocio.app.services.UsuarioServices;
import profeco.negocio.app.dto.Usuario;

/**
 *
 * @author Luis Barroso
 */
@WebServlet(name = "ServletValidacionLogin", urlPatterns = {"/ServletValidacionLogin"})
public class ServletValidacionLogin extends HttpServlet {

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
        String email = request.getParameter("Email");
        String password = request.getParameter("Password");
        PrintWriter Out = response.getWriter();
        

        if (ValidarUsuario(email, password)) {

            Cookie info = new Cookie("varRecordar", email);
            Cookie infopass = new Cookie("varPass", password);
            info.setMaxAge(5000);
            infopass.setMaxAge(5000);

            response.addCookie(info);
            response.addCookie(infopass);
            
            Usuario usuario = BuscarUsuario(email);
            HttpSession misession = request.getSession(true);

            misession.setAttribute("usuario", usuario);

            Out.print("esta");

            //response.sendRedirect("principal.jsp");
        } else {
            Out.print("no esta");

        }

    }
    
    boolean ValidarUsuario(String email, String password){
        Usuario[] arr = new UsuarioServices().Listar("");
        boolean UsuarioCorrecto = false;
        
        for (int i = 0; i < arr.length; i++) {
            if (email.toLowerCase().equals(arr[i].getCorreo().toLowerCase()) && password.equals(arr[i].getContrasena())) {
                UsuarioCorrecto = true;
                break;
            }
        }
        
        
        return UsuarioCorrecto;
    }
    
    Usuario BuscarUsuario(String email){
         Usuario str = new Usuario();
         
         Usuario [] arr = new UsuarioServices().Listar("");
         
         for (int i = 0; i < arr.length; i++) {
             if(arr[i].getCorreo().toLowerCase().equals(email.toLowerCase())){
                 str = arr[i];
                 return str;
             }
         }
         return null;
        
        
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
