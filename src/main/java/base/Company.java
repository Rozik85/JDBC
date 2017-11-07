package base;

import Interface.BaseOperations;
import config.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Company implements BaseOperations {
    private static Scanner scanner;
    private static Connection conn;
    private static Statement stmt;
    private static String sql;

    @Override
    public void choiceOfAction() {
        Company company = new Company ();
        scanner = new Scanner (System.in);
        int choice;
        do {
            System.out.println ("Wybierz operację:\n" +
                    "1: Lista firm\n" +
                    "2: Dodaj nową firmę\n" +
                    "3: Popraw dane istniejącej firmy\n" +
                    "4: Usuń firmę z bazy\n" +
                    "0: powrót do menu");
            choice = scanner.nextInt ();

            switch (choice) {
                case 1:
                    company.list ();
                    break;
                case 2:
                    company.inssert ();
                    break;
                case 3:
                    company.update ();
                    break;
                case 4:
                    company.delete ();
                    break;
                default:
                    System.out.println ("Zły numer operacji");
            }
        }
        while (choice != 0);
    }

    @Override
    public void list() {
        conn = null;
        stmt = null;
        try {
            conn = Database.getConnection ();
            System.out.println ("Creating statemet ...");
            stmt = conn.createStatement ();
            sql = "SELECT * FROM firmy";
            ResultSet rs = stmt.executeQuery (sql);
            while (rs.next ()) {
                int id_firmy = rs.getInt ("id_firmy");
                String nazwa = rs.getString ("nazwa");
                String ulica = rs.getString ("ulica");
                String numer_domu = rs.getString ("numer_domu");
                String numer_mieszkania = rs.getString ("numer_mieszkania");
                String nip = rs.getString ("nip");

                System.out.print ("ID: " + id_firmy +
                        ", nazwa: " + nazwa +
                        ", ulica: " + ulica +
                        ", numer domu: " + numer_domu +
                        ", numer mieszkania: " + numer_mieszkania +
                        ", nip: " + nip + "\n");

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
            System.out.println ("Podaj nazwę firmy");
            String newName = scanner.nextLine ();
            System.out.println ("Podaj ulicę");
            String newStreet = scanner.nextLine ();
            System.out.println ("Podaj numer domu");
            String newHouseNumber = scanner.nextLine ();
            System.out.println ("Podaj numer mieszkania");
            String newApartmetNumber = scanner.nextLine ();
            System.out.println ("Podaj NIP");
            String newNIP = scanner.nextLine ();

            sql = "INSERT INTO firmy VALUES (" + newID + ",'" + newName + "','"
                    + newStreet + "','" + newHouseNumber + "','" + newApartmetNumber +
                    "','" + newNIP + "')";

            int result = stmt.executeUpdate (sql);
            System.out.println ("Result:" + result);

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

            sql = "DELETE FROM firmy WHERE firmy.id_firmy = \"" + noweId + "\"";


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
        try {
            conn = Database.getConnection ();
            System.out.println ("Creating statement...");
            stmt = conn.createStatement ();

            System.out.println ("Podaj id firmy do poprawy");
            int id = scanner.nextInt ();
            scanner.nextLine ();
            System.out.println ("Podaj nazwę firmy");
            String newName = scanner.nextLine ();
            System.out.println ("Podaj ulicę");
            String newStreet = scanner.nextLine ();
            System.out.println ("Podaj numer domu");
            String newHouseNumber = scanner.nextLine ();
            System.out.println ("Podaj numer mieszkania");
            String newApartmetNumber = scanner.nextLine ();
            System.out.println ("Podaj NIP");
            String newNIP = scanner.nextLine ();

            sql = "UPDATE firmy SET nazwa='"+newName+"', ulica= '"+newStreet+
                    "', numer_domu='"+newHouseNumber+"', numer_mieszkania= '"
                    +newApartmetNumber+"', nip='"+newNIP+"' WHERE firmy.id_firmy= "+id;

            int result = stmt.executeUpdate (sql);
            System.out.println ("Result:" + result);

        } catch (SQLException se) {
            se.printStackTrace ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }
}

