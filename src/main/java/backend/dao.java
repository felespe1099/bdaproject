package backend;
import backend.Classes.*;

import java.net.MalformedURLException;
import java.util.*;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.ektorp.*;
import backend.Connection.connection;

public class dao {
    static CouchDbConnector conn;

    static {
        try {
            conn = connection.connection();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public dao() throws MalformedURLException {
    }

    //Login

    public boolean Login(String pUsername, String pPassword, String pRole){
        users Users= conn.get(users.class,pUsername);
        return Objects.equals(Users.password, pPassword) && Objects.equals(Users.role, pRole);
    }

    //Registro de una nuevo usuario

    public static boolean RegisterNewUser(String nombre, String group, String username, String password, String role) {
        try {
            users Users = new users(nombre, group, username, password, role);
            conn.create(Users.username,Users);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    public static void main(String[] args) throws MalformedURLException {
        RegisterNewUser("Gabriel","11-A","gabriel0901","contrasena","Student");
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

    //CONSULTA 2

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
        for (String i : Test) {
            Integer retrievedValue = map.get(i);
            if (null == retrievedValue) {
                map.put(String.valueOf(i), 1);
            } else {
                map.put(String.valueOf(i), retrievedValue + 1);
            }
        }
        List<String> result = map.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(3).map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println("Usuarios: " + Test);
        System.out.println(result);
    }

    //CONSULTA 3
    public static void TopClubs() throws MalformedURLException {
        CouchDbConnector conn = connection.connection();
        ViewQuery query = new ViewQuery().designDocId("_design/AllViews").viewName("top-clubs");
        List<groups> Groups = conn.queryView(query, groups.class);
        ArrayList<groups> Test = new ArrayList<>();
        for (groups result : Groups) {
            if (result.GroupName != null) {
                Test.add(new groups(result.GroupName, result.GroupCategory, result.GroupSelected));
            }
        }
        Collections.sort(Test, new Comparator<groups>()
        {

            @Override
            public int compare(groups o1, groups o2) {
                return Integer.valueOf(o1.GroupSelected).compareTo(o2.GroupSelected);
            }
        });
        List<String> PreResult = new ArrayList<>();
        for(int iCount = 0; iCount<Test.size(); iCount++){
            PreResult.add(Test.get(iCount).GroupName+ " "+ Test.get(iCount).GroupCategory+" "+Test.get(iCount).GroupSelected);
            //System.out.println(Test.get(iCount).GroupName+ " "+ Test.get(iCount).GroupCategory+" "+Test.get(iCount).GroupSelected);
        }
        List<String> Top5 = new ArrayList<>(PreResult.subList(PreResult.size()-5,PreResult.size()));
        System.out.println(Top5);
    }

    //CONSULTA 4
    public static void BottomClubs() throws MalformedURLException{
        CouchDbConnector conn = connection.connection();
        ViewQuery query = new ViewQuery().designDocId("_design/AllViews").viewName("top-clubs");
        List<groups> Groups = conn.queryView(query, groups.class);
        ArrayList<groups> Test = new ArrayList<>();
        for(groups result: Groups){
            if(result.GroupName!=null){
                Test.add(new groups(result.GroupName, result.GroupCategory, result.GroupSelected));
            }
        }
        Collections.sort(Test, new Comparator<groups>()
        {

            @Override
            public int compare(groups o1, groups o2) {
                return Integer.valueOf(o2.GroupSelected).compareTo(o1.GroupSelected);
            }
        });
        List<String> PreResult = new ArrayList<>();
        for(int iCount = 0; iCount<Test.size(); iCount++){
            PreResult.add(Test.get(iCount).GroupName+ " "+ Test.get(iCount).GroupCategory+" "+Test.get(iCount).GroupSelected);
            //System.out.println(Test.get(iCount).GroupName+ " "+ Test.get(iCount).GroupCategory+" "+Test.get(iCount).GroupSelected);
        }
        List<String> Bottom3 = new ArrayList<>(PreResult.subList(PreResult.size()-3,PreResult.size()));
        System.out.println(Bottom3);
    }
}
