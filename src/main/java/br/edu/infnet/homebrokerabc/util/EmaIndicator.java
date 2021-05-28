package br.edu.infnet.homebrokerabc.util;


import br.edu.infnet.homebrokerabc.domain.CandleStick;
import java.util.List;

public class EmaIndicator {

    public static Double[] calculateEmaValues(List<CandleStick> candlesticks, Double n){

        Double[] results = new Double[candlesticks.size()];

        calculateEmasHelper(candlesticks, n, candlesticks.size()-1, results);
        return results;
    }
    
    public static Double calculateEmasHelper(List<CandleStick> candlesticks, Double n, int i, Double[] results){
        if(i == 0){
            results[0] = candlesticks.get(0).getClose();
            return results[0];
        }else {
            Double close = candlesticks.get(i).getClose();
            Double factor = ( 2.0 / (n + 1) );
            Double ema =  close * factor + (1 - factor) * calculateEmasHelper(candlesticks, n, i-1, results) ;
            results[i] = ema;
            return ema;
        }
    }
    
    public static String calculateEmaValuesToString(List<CandleStick> candleSticks, Double n){
        Double[] emas = EmaIndicator.calculateEmaValues(candleSticks, n);

                String stringEma = "[";
                int i = 0;
                
                for (Double ema : emas){
                   stringEma = stringEma + "[" + candleSticks.get(i).getDate().getTime()+ ", " + Util.doubleToString(ema) +"], ";
                   i++;
                }
                
                stringEma = stringEma + "]";
                stringEma = stringEma.replace(", ]","]" );
                
        return stringEma;
    }
    
}