package com.gtk.msfinance;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpAction {

    private final static String USER_AGENT = "Mozilla/5.0";
    // HTTP GET request
    public static String sendGet(String url) throws Exception {

        //String url = "http://www.google.com/search?q=mkyong";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();


        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        //System.out.println("\nSending 'GET' request to URL : " + url);
        //System.out.println("Response Code : " + responseCode);

        String headerType = con.getContentType();
        //System.out.println("Header : "+headerType);

        BufferedReader in = null;
        if(headerType.toUpperCase().indexOf("EUC-KR") != -1) {
            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(),"EUC-KR"));
        } else if(headerType.toUpperCase().indexOf("UTF-8") != -1) {
            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(),"UTF-8"));
        } else {
            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
        }
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        con.disconnect();

        //print result
        //System.out.println(response.toString());
        return response.toString();
    }
}
