package backend;
import java.net.MalformedURLException;

import backend.Classes.*;
import org.ektorp.*;
import backend.Connection.connection;
import org.ektorp.support.DesignDocument;

public class dao {
    /*public static void main(String[] args) throws MalformedURLException {
        users Users = new users("Sebastian","3A","Sebastian123","estaesunaprueba","Student");
        CouchDbConnector conn = connection.connection();

        //conn.update(Users,Users.name);
        //conn.create(Users.name,Users);

        /*String prueba = String.valueOf(conn.contains(" "));
        System.out.println(prueba);
    }*/

    /*public static void main(String[] args) throws MalformedURLException{
        groups Groups = new groups("Basketball","Deportes",1);
        CouchDbConnector conn = connection.connection();
        conn.create(Groups.GroupName,Groups);
    }*/

    public static void main(String[] args) throws MalformedURLException, DocumentNotFoundException {
        String id ="Luis";
        CouchDbConnector conn = connection.connection();
        groups Groups = conn.get(groups.class, "Atletismo");
        String prueba = String.valueOf(Groups);
        System.out.println(Groups.GroupCategory+Groups.GroupName+Groups.GroupSelected);
    }

//POR PROBAR
    //UPDATE
    /*Sofa sofa = db.get(Sofa.class, "ektorp");
    sofa.setColor("blue");
    db.update(sofa);*/



}
