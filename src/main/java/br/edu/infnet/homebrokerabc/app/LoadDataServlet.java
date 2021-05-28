/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.infnet.homebrokerabc.app;

import br.edu.infnet.homebrokerabc.domain.CandleStick;
import br.edu.infnet.homebrokerabc.infra.DataHandler;
import br.edu.infnet.homebrokerabc.util.EmaIndicator;
import br.edu.infnet.homebrokerabc.util.Util;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoadDataServlet", urlPatterns = {"/loadData"})
public class LoadDataServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        DataHandler dataHandler = DataHandler.getInstance();
        
        System.out.println("LoadDate -> " + dataHandler.getLoadDate());
        
        request.setAttribute("candlesticks", dataHandler.getCandleSticks().toString());
        request.setAttribute("ema9", dataHandler.getEma9());
        request.setAttribute("ema12", dataHandler.getEma12());
        request.setAttribute("ema26", dataHandler.getEma26());
        request.setAttribute("titleData", dataHandler.getAssembledTitleDataValues());
        
        System.out.println(dataHandler.getLastClose());
        System.out.println(dataHandler.getLastLastClose());
        System.out.println(dataHandler.getDif());
        System.out.println(dataHandler.getPercentVariation());
       
        
        //redirect
        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request, response);
        
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
