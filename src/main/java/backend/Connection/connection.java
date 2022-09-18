package backend.Connection;

import java.net.MalformedURLException;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

public class connection {
    public static StdCouchDbConnector connection() throws MalformedURLException {
        HttpClient httpClient = new StdHttpClient.Builder()
                .url("http://localhost:5984").username("admin").password("admin").build();
        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        return new StdCouchDbConnector("bdaproject",dbInstance);
    }

    public static void main(String[] args) throws MalformedURLException {
        try{
            HttpClient httpClient = new StdHttpClient.Builder()
                    .url("http://localhost:5984").username("admin").password("admin").build();
            CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
            CouchDbConnector db = new StdCouchDbConnector("bdaproject",dbInstance);
            System.out.println("Conexion exitosa");
        }
        catch(Exception err){
            System.out.println(err);
        }
    }
}
