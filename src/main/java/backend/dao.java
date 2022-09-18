package backend;
import java.net.MalformedURLException;

import backend.Classes.*;
import org.ektorp.*;
import backend.Connection.connection;

public class dao {
    /*public static void main(String[] args) throws MalformedURLException {
        users Users = new users("Luis","3A","Paquito123","password","Student");
        CouchDbConnector conn = connection.connection();
        conn.create(Users.name,Users);
    }*/

    /*public static void main(String[] args) throws MalformedURLException{
        groups Groups = new groups("Basketball","Deportes",1);
        CouchDbConnector conn = connection.connection();
        conn.create(Groups.GroupName,Groups);
    }*/

    public static void main(String[] args) throws MalformedURLException, DocumentNotFoundException {
        String id ="Luis";
        CouchDbConnector conn = connection.connection();
        users Users = conn.get(users.class, "Luis");
        System.out.println(Users);
    }



}
