package traceroute;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.*;
import java.lang.*;
import java.net.*;
import java.util.ArrayList;

public class TraceRoute {
    private ArrayList<String> output = null;

    private SimpleIntegerProperty hop;
    private SimpleStringProperty ip;
    private SimpleStringProperty hostname;

    public TraceRoute(){}

    public TraceRoute(int hop, String ip, String hostname){
        this.hop = new SimpleIntegerProperty(hop);
        this.ip = new SimpleStringProperty(ip);
        this.hostname = new SimpleStringProperty(hostname);
    }

    public void setHop(int hop){ this.hop.set(hop); }

    public void setIp(String ip){ this.ip.set(ip); }

    public void setHostname(String hostname){ this.hostname.set(hostname); }

    public int getHop(){ return this.hop.get(); }

    public String getIp(){ return this.ip.get(); }

    public String getHostname(){ return this.hostname.get(); }

    public ArrayList<String> start(String url){
        output = new ArrayList<String>();
        BufferedReader in = null;

        try{
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("traceroute " + url);

            String line;
            in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            if(in == null) System.out.println("Could not connect");

            while((line=in.readLine()) != null){
                output.add(line);
            }
            in.close();
        }catch (IOException e){

        }
        return  output;
    }

}