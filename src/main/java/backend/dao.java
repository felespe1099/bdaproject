package backend;
import backend.Classes.*;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;

import org.ektorp.*;
import backend.Connection.connection;
import org.ektorp.support.View;

import javax.swing.*;

public class dao {
    CouchDbConnector conn = connection.connection();

    public dao() throws MalformedURLException {
    }

    //Login

    public boolean Login(String pUsername, String pPassword, String pRole){
        users Users= conn.get(users.class,pUsername);
        return Objects.equals(Users.password, pPassword) && Objects.equals(Users.role, pRole);
    }

    //Registro de una nuevo usuario

    public boolean RegisterNewUser(String nombre, String group, String username, String password, String role) {
        try {
            users Users = new users(nombre, group, username, password, role);
            conn.create(Users.username,Users);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    //Almacena la relacion entre una clase y el estudiante

    public boolean StudentClassRelationship(String student, String course) {
        try {
            StudentClass studentClass = new StudentClass(student, course);
            conn.create(studentClass);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    //Registrar una clase

    public boolean RegistrerClass(String GroupName, String GroupCategory, int GroupSelected) {
        try {
            groups Groups = new groups(GroupName, GroupCategory, GroupSelected);
            if (AlreadyCreated(GroupName)) {
                Groups.setGroupSelected(Groups.GroupSelected + 1);
                conn.update(Groups);
            } else {
                conn.create(Groups.GroupName, Groups);
            }
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    //Revisa si una clase ya fue creada

    public boolean AlreadyCreated(String GroupName) {
        return conn.contains(GroupName);
    }

    //Todos los grupos creados

    public static void AllGroups() throws MalformedURLException {
        CouchDbConnector conn = connection.connection();
        ViewQuery query = new ViewQuery().designDocId("_design/AllViews").viewName("all-groups");
        List<groups> Groups = conn.queryView(query, groups.class);
        for (groups test : Groups) {
            if (test.GroupName != null) {
                System.out.println(test.GroupName + " " + test.GroupCategory + " ");
            }
        }
    }

    //PRUEBA- ELIMINAR
    public static void StudentNames() throws MalformedURLException {
        CouchDbConnector conn = connection.connection();
        ViewQuery query = new ViewQuery().designDocId("_design/AllViews").viewName("student-names");
        List<users> Groups = conn.queryView(query, users.class);
        List<String> Test = new ArrayList<>();
        for (users test : Groups) {
            if (test.name != null) {
                Test.add(test.name);
            }
        }
        List<String> Result = Test.stream().sorted().toList();
        Result.forEach(System.out::println);
    }

    //PRUEBA - SE DEBE ELIMINAR
    public static void TotalSelections() throws MalformedURLException {
        CouchDbConnector conn = connection.connection();
        ViewQuery query = new ViewQuery().designDocId("_design/AllViews").viewName("total-selections");
        List<groups> Groups = conn.queryView(query, groups.class);
        List<String> Test = new ArrayList<>();
        for (groups result : Groups) {
            if (result.GroupSelected != 0) {
                Test.add(String.valueOf(result.GroupSelected));
            }
        }
        List<String> Resultado = Test.stream().sorted(Comparator.reverseOrder()).toList();
        Resultado.forEach(System.out::println);
    }

    //CONSULTA 1
    public static void ClassPerCategory() throws MalformedURLException {
        CouchDbConnector conn = connection.connection();
        ViewQuery query = new ViewQuery().designDocId("_design/AllViews").viewName("class-per-category");
        List<groups> Groups = conn.queryView(query, groups.class);
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        List<String> Test = new ArrayList<>();
        for (groups result : Groups) {
            if (result.GroupCategory != null) {
                Test.add(result.GroupCategory);
            }
        }
        for (String i: Test) {
            Integer retrievedValue = map.get(i);
            if (null == retrievedValue) {
                map.put(String.valueOf(i), 1);
            }
            else {
                map.put(String.valueOf(i), retrievedValue + 1);
            }
        }

        System.out.println("Categorias: " + Test);
        System.out.println(map);
    }

    //CONSULTA 2 - INCOMPLETA

    public static void TopStudents() throws MalformedURLException {
        CouchDbConnector conn = connection.connection();
        ViewQuery query = new ViewQuery().designDocId("_design/AllViews").viewName("students-class");
        List<StudentClass> TopStudents = conn.queryView(query, StudentClass.class);
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        List<String> Test = new ArrayList<>();
        for (StudentClass result : TopStudents) {
            if (result.student != null) {
                Test.add(String.valueOf(result.student));
            }
        }
        for (String i: Test) {
            Integer retrievedValue = map.get(i);
            if (null == retrievedValue) {
                map.put(String.valueOf(i), 1);
            }
            else {
                map.put(String.valueOf(i), retrievedValue + 1);
            }
        }

        System.out.println("Usuarios: " + Test);
        List<Integer> result = new ArrayList<Integer>(map.values());
        result = result.subList(0,map.size());
        System.out.println(result);
    }

    public static void main(String[] args) throws MalformedURLException {
        //StudentNames();
        //TotalSelections();
        //ClassPerCategory();
        //TopClubs();
        //BottomClubs();
        //AllGroups();
        TopStudents();
    }

    //CONSULTA 3 - INCOMPLETA
    /*public static void TopClubs() throws MalformedURLException{
        CouchDbConnector conn = connection.connection();
        ViewQuery query = new ViewQuery().designDocId("_design/AllViews").viewName("top-clubs");
        List<groups> Groups = conn.queryView(query, groups.class);
        List<String> Test = new ArrayList<>();
        for (groups result : Groups) {
            if (result.GroupName != null) {
                Test.add(Arrays.toString(new String[]{result.GroupName, result.GroupCategory, String.valueOf(result.GroupSelected)}));
            }
        }
        Collections.sort(Test, new Comparator<groups>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
            public int compare(groups o1, groups o2) {
                return Integer.compare(o2.GroupSelected, o1.GroupSelected);
            }
        });
        for(int iCount = 0; iCount<Test.size(); iCount++){
            System.out.println(Test.get(iCount).GroupName+" "+ Test.get(iCount).GroupCategory+ " "+ Test.get(iCount).GroupSelection);
        }
        List<String>Resultado = Test.stream().sorted(Comparator.reverseOrder()).toList();

        System.out.println("Clubes: " + Test);
    }*/

    //CONSULTA 4 - INCOMPLETA
    public static void BottomClubs() throws MalformedURLException{
        CouchDbConnector conn = connection.connection();
        ViewQuery query = new ViewQuery().designDocId("_design/AllViews").viewName("top-clubs");
        List<groups> Groups = conn.queryView(query, groups.class);
        for(groups result: Groups){
            if(result.GroupName!=null){
                System.out.println(result.GroupName+" "+result.GroupCategory+" "+result.GroupSelected);
            }
        }
    }
}
