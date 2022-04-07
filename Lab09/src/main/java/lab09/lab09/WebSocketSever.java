package lab09.lab09;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WebSocketSever {
    private String sockName;
    private ArrayList<String> header = null;
    private ArrayList<ArrayList<String>> record = new ArrayList<>();

    private ArrayList<Double> prices = new ArrayList<>();

    public WebSocketSever(String sockName){
        this.sockName = sockName;
    }

    public void downloadStockPrices() throws Exception {
        String urlPrefix = "https://query1.finance.yahoo.com/v7/finance/download/";
        String urlSuffix = "?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true";

        URL file = new URL(urlPrefix + sockName + urlSuffix);
        URLConnection urlConnection = file.openConnection();
        Scanner sc = new Scanner(urlConnection.getInputStream());
        header = new ArrayList<String>(List.of(sc.nextLine().split(",")));
        while (sc.hasNext()){
            ArrayList<String> rec = new ArrayList<String>(List.of(sc.nextLine().split(",")));
            record.add(rec);
            prices.add(Double.parseDouble(rec.get(header.indexOf("Close"))));
        }

    }

    public ArrayList<String> getHeader() {
        return header;
    }

    public ArrayList<ArrayList<String>> getRecord() {
        return record;
    }

    public ArrayList<Double> getPrices() {
        return prices;
    }
}
