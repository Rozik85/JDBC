package base;

import Interface.BaseOperations;
import config.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Positions implements BaseOperations {

    private static Scanner scanner;
    private static Connection conn;
    private static Statement stmt;
    private static String sql;

    @Override
    public void choiceOfAction() {
        Positions positions = new Positions ();
        scanner = new Scanner (System.in);
        int choise;
        do {
            System.out.println ("Wybierz operacje: \n" +
                    "1: Lista stanowisk \n" +
                    "2: Dodaj stanowisko\n" +
                    "3: Popraw stanowisko\n" +
                    "4: Usuń stanowisko\n" +
                    "0: powrót do menu");
            choise = scanner.nextInt ();

            switch (choise) {
                case 1:
                    positions.list ();
                    break;
                case 2:
                    positions.inssert ();
                    break;
                case 3:
                    positions.update ();
                    break;
                case 4:
                    positions.delete ();
                    break;
                default:
                    System.out.println ("Podaj poprawną wartość");
            }
        }
        while (choise != 0);
    }

    @Override
    public void list() {
        conn = null;
        stmt = null;
        try {
            conn = Database.getConnection ();
            System.out.println ("Creating statement...");
            stmt = conn.createStatement ();

            sql = "SELECT * FROM stanowiska";
            ResultSet rs = stmt.executeQuery (sql);

            while (rs.next ()) {
                int id_stanowiska = rs.getInt ("id_stanowiska");
                String nazwa = rs.getString ("nazwa");



                System.out.print ("ID: " + id_stanowiska);
                System.out.print (", nazwa: " + nazwa+"\n");

            }
        } catch (SQLException se) {
            se.printStackTrace ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }


    @Override
    public void inssert() {
        try {
            conn = Database.getConnection ();
            System.out.println ("Creating statement...");
            stmt = conn.createStatement ();
            System.out.println ("Podaj ID");
            int newID = scanner.nextInt ();
            scanner.nextLine ();
            System.out.println ("Podaj nazwę stanowiska: ");
            String newNamePosition = scanner.nextLine ();


            sql = "INSERT INTO stanowiska " +
                    "VALUES (" + newID + ",'" + newNamePosition +"')";
            int result = stmt.executeUpdate (sql);
            System.out.println ("Result: " + result);
        } catch (SQLException se) {
            se.printStackTrace ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    @Override
    public void delete() {
        try {
            conn = Database.getConnection ();
            System.out.println ("Creating statement...");
            stmt = conn.createStatement ();
            System.out.println ("Podaj ID");
            int noweId = scanner.nextInt ();

            sql = "DELETE FROM stanowiska WHERE stanowiska.id_stanowiska = \"" + noweId + "\"";


            int result = stmt.executeUpdate (sql);
            System.out.println ("Result: " + result);
        } catch (SQLException se) {
            se.printStackTrace ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    @Override
    public void update() {
try{
    conn = Database.getConnection ();
    System.out.println ("Creating statement...");
    stmt = conn.createStatement ();

    System.out.println ("Podaj Id stanowiska do zmodyfikowania");
    int id = scanner.nextInt ();
    scanner.nextLine ();
    System.out.println ("Podaj nazwę stanowiska");
    String newPositions = scanner.nextLine ();

    sql =" UPDATE stanowiska SET nazwa='"+newPositions+"' WHERE id_stanowiska="+id;

    int result = stmt.executeUpdate (sql);
    System.out.println ("Result: "+result);

} catch (SQLException se) {
    se.printStackTrace ();
} catch (Exception e) {
    e.printStackTrace ();
}
    }
}
