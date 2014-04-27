/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preferya.facadesmsgatewaycontrolservlet.controllers;

import com.preferya.facadesmsgatewaycontrolservlet.models.MessageEntity;
import com.preferya.facadesmsgatewaycontrolservlet.services.PhoneValidationService;
import com.preferya.facadesmsgatewaycontrolservlet.services.IPhoneValidationService;
import com.preferya.facadesmsgatewaycontrolservlet.services.PhoneValidationFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergio
 */
@WebServlet(name = "FacadeSmsGateway", urlPatterns = {"/FacadeSmsGateway"})
public class FacadeSmsGateway extends HttpServlet {
    
    /*private String id_metamodel;
    private Object metamodel_link;*/
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        //Firstly, we get the parameters.
        String _action = request.getParameter("action");
        String _args = request.getParameter("args");
        String _iso_lang = request.getParameter("iso_lang");
        String _token = request.getParameter("token");
        
        //Secondly, we check the integrity with token.
        PhoneValidationService _validationService = new PhoneValidationService();
        
        MessageEntity _message = new MessageEntity(_action, _args, _iso_lang);
        if(_validationService.checkIntegrity(_message, _token)){ //If Token matches
            if(_action.equalsIgnoreCase("stop") || _action.equalsIgnoreCase("add_country")){ //If action is not stop
                _validationService.sendQueue(_message);
                out.print("OK! Message sends.");
            }else{ // If action is stop
                _validationService.sendExternalQueue(_message, _iso_lang);
                out.print("OK! Message sends, so the server's stopping soon.");
            }
        }else{ //If token doesn't match
            out.print("FAIL! Token doesn't match.");
        }
        
        out.close();
        
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FacadeSmsGateway.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(FacadeSmsGateway.class.getName()).log(Level.SEVERE, null, ex);
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
