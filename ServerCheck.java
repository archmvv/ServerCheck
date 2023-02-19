import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class ServerCheck {
    public ServerCheck(){
        String[] sites = {
                "https://www.apple.com",
                "https://www.microsoft.com",
                "http://www.hp.com",
                "https://www.oracle.com"

        };

        try{
            load(sites);
        } catch (URISyntaxException oops){
            System.out.println("Bad URI" + oops.getMessage());
        }catch(IOException oops){
            System.err.println("Error" + oops.toString());
        }catch(InterruptedException oops){
            System.err.println("Error: " + oops.getMessage());
        }
    }

    public void load(String[] sites) throws URISyntaxException, IOException, InterruptedException {
        for(String site : sites){
            System.err.println("\nSite: " + site);
            //create a new web client - syzdavane na nov web klient
            HttpClient browser = HttpClient.newHttpClient();
            // web site request -  zaqvka za web site
            URI uri = new URI(site);
            HttpRequest.Builder bob = HttpRequest.newBuilder(uri);
            HttpRequest request = bob.build();

            // implementation - izpylnenie
            HttpResponse<String> response = browser.send(request, HttpResponse.BodyHandlers.ofString());
            //tyrsene na zaglavie
            HttpHeaders headers = response.headers();
            Optional<String> server = headers.firstValue("Server");
            if(server.isPresent()){
                System.err.println("Server: " + server.get());
            } else {
                System.err.println("Server unidentified");
            }
        }
    }

    public static void main(String[] args) {
        new ServerCheck();
    }
}
