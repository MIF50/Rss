package common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mohamed on 12/8/17.
 */

public class HTTPDataHandler {
    static String result =null; // variable that hold data

    public HTTPDataHandler() {
    }
    /*
    * this method to open connection from internet and read data
    * @param url of page that read
    * @return data that read
    * */
    public String getHTTPData(String urlString){
        try{
            // create url
            URL url=new URL(urlString);
            // create connection and open it
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            //responseCode is ok
            if (connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                // create obj of InputStream to read byte set location from connection
                InputStream in =new BufferedInputStream(connection.getInputStream());
                // create obj of BufferedReader that read text from InputStream
                BufferedReader br=new BufferedReader(new InputStreamReader(in));
                StringBuilder sb=new StringBuilder(); // hold data read from br
                String line;
                while ((line=br.readLine())!=null){ // if not arrive to end line

                    sb.append(line);// append line to StringBuilder
                    result =sb.toString(); // add text in sb obj to String result
                    connection.disconnect();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
