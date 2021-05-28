package br.edu.infnet.homebrokerabc.infra;

import br.edu.infnet.homebrokerabc.domain.CandleStick;
import br.edu.infnet.homebrokerabc.util.EmaIndicator;
import br.edu.infnet.homebrokerabc.util.Util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataHandler {
    
    
    final String URL_CSV = "https://query1.finance.yahoo.com/v7/finance/"
            + "download/MGLU3.SA?period1=1304294400"
            + "&period2=99999999999&interval=1d"
            + "&events=history"
            + "&includeAdjustedClose=true";
    
    private static DataHandler dataHandler;
    
    private List<CandleStick> candleSticks;
    private String ema9;
    private String ema12;
    private String ema26;
    private Double lastClose;
    private Double lastLastClose;
    private Double dif;
    private Double percentVariation;
    private Date loadDate;

    public static synchronized DataHandler getInstance() {
        if (dataHandler == null){
            dataHandler = new DataHandler();
        }
        
        return dataHandler;
    }
    
    private DataHandler() {
        this.candleSticks = this.loadCandleSticksFromCsvFile();
        this.ema9 = EmaIndicator.calculateEmaValuesToString(candleSticks, 9.0);
        this.ema12 = EmaIndicator.calculateEmaValuesToString(candleSticks, 12.0);
        this.ema26 = EmaIndicator.calculateEmaValuesToString(candleSticks, 26.0);
        this.calculateTitleDataValues();
        loadDate = new Date();
    }
    
    private List<CandleStick> loadCandleSticksFromCsvFile() {
        
        try 
        {
            URL urlCSV = new URL(URL_CSV);
           
            URLConnection urlConn = urlCSV.openConnection();
            
            InputStreamReader inputCSV = new InputStreamReader(
                ((URLConnection) urlConn).getInputStream());
        
            BufferedReader br = new BufferedReader(inputCSV);
            
            String line;
            
            List<CandleStick> candleStickList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                
                String[] lineInArray = line.split(",");
                if (!"null".equals(lineInArray[1]) 
                        && !"Date".equals(lineInArray[0])
                        && Double.parseDouble(lineInArray[4])!= 0){
 
                    candleStickList.add(new CandleStick(
                            new SimpleDateFormat("yyyy-MM-dd").parse(lineInArray[0]),
                            Util.round(Double.parseDouble(lineInArray[1]),2),
                            Util.round(Double.parseDouble(lineInArray[2]),2),
                            Util.round(Double.parseDouble(lineInArray[3]),2),
                            Util.round(Double.parseDouble(lineInArray[4]),2),
                            Util.round(Double.parseDouble(lineInArray[5]),2),
                            Util.round(Double.parseDouble(lineInArray[6]),2)
                    ));
                    
                }
            }
            
            return candleStickList;
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        return null;
        
    }
    
    private void calculateTitleDataValues(){
        
        this.lastClose = this.candleSticks.get(this.candleSticks.size()-1).getClose();
        this.lastLastClose =  this.candleSticks.get(this.candleSticks.size()-2).getClose();
        this.dif = Util.round(lastClose - lastLastClose, 2);
        this.percentVariation = Util.round((this.dif*100)/lastLastClose, 2);
        
    }
    
    public String getAssembledTitleDataValues(){
        
        String color = "";
        String arrow = "";

        if (this.dif >= 0){
            color = "#34a853";
            arrow = "⮝";
        }else{
            color = "#d93025";
            arrow = "⮟";
        }
        
        return "<span style='font-size:28px; color:black;'>" + lastClose 
                + "</span>" 
                + "<span style='color:"+color+"; font-size:18px'> " + dif 
                + " (" + percentVariation + "%" + ") " + arrow
                + "</span>";       
    
    }

    public List<CandleStick> getCandleSticks() {
        return candleSticks;
    }

    public void setCandleSticks(List<CandleStick> candleSticks) {
        this.candleSticks = candleSticks;
    }

    public String getEma9() {
        return ema9;
    }

    public void setEma9(String ema9) {
        this.ema9 = ema9;
    }

    public String getEma12() {
        return ema12;
    }

    public void setEma12(String ema12) {
        this.ema12 = ema12;
    }

    public String getEma26() {
        return ema26;
    }

    public void setEma26(String ema26) {
        this.ema26 = ema26;
    }

    public Double getLastClose() {
        return lastClose;
    }

    public void setLastClose(Double lastClose) {
        this.lastClose = lastClose;
    }

    public Double getLastLastClose() {
        return lastLastClose;
    }

    public void setLastLastClose(Double lastLastClose) {
        this.lastLastClose = lastLastClose;
    }

    public Double getDif() {
        return dif;
    }

    public void setDif(Double dif) {
        this.dif = dif;
    }

    public Double getPercentVariation() {
        return percentVariation;
    }

    public void setPercentVariation(Double percentVariation) {
        this.percentVariation = percentVariation;
    }

    public Date getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(Date loadDate) {
        this.loadDate = loadDate;
    }
    
    
    

}
