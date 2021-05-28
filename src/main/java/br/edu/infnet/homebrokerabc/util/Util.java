/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.infnet.homebrokerabc.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabio Nazario
 */
public class Util {
      
    public static String dateToMilis(String date){
         
        try {
            
            Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            Long l = d.getTime();
            return l.toString();
        
        } catch (ParseException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    
    }
    
    
    public static String doubleToString(Double value){
        
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
        otherSymbols.setDecimalSeparator('.'); 
        DecimalFormat df = new DecimalFormat("0.00",otherSymbols);
        return df.format(value);
        
    }
    
    public static Double round(Double value, int scale){
        
        BigDecimal bd = new BigDecimal(value).setScale(scale, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
        
    }
        
    

}