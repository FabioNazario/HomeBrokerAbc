package br.edu.infnet.homebrokerabc.domain;


import br.edu.infnet.homebrokerabc.util.Util;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fabio Nazario
 */

public class CandleStick {
    
    private Date date;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double adjClose;
    private Double volume;

    public CandleStick(Date date, Double open, Double high, Double low, Double close, Double adjClose, Double volume) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.adjClose = adjClose;
        this.volume = volume;
    }

    @Override
    public String toString() {
        
        //return "[" + date + "," + open  + "," +  high  + "," +  low + "," +  close  + "," +  adjClose + "," +  volume + ']';
        return "[" + date.getTime() + "," +  Util.doubleToString(close) + ']';
    
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(Double adjClose) {
        this.adjClose = adjClose;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
    
    

}
