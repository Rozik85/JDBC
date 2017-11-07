package base;

import Interface.BaseOperations;
import config.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Employees implements BaseOperations {
    public static Scanner scanner;
    private static Connection conn;
    private static String sql;
    private static Statement stmt;

    @Override
    public void choiceOfAction() {
        scanner = new Scanner (System.in);
        int choise;
        Employees employees = new Employees ();
        do {
            System.out.println ("Wybierz operacje: \n" +
                    "1: Lista pracowników \n" +
                    "2: Dodaj pracownika\n" +
                    "3: Popraw dane pracownika\n" +
                    "4: Usuń pracownika z bazy\n" +
                    "0: powrót do menu");
            choise = scanner.nextInt ();
            switch (choise) {
                case 1:
                    employees.list ();
                    break;
                case 2:
                    employees.inssert ();
                    break;
                case 3:
                    employees.update ();
                    break;
                case 4:
                    employees.delete ();
                    break;
                default:
                    System.out.println ("Zły wybór");
            }
        } while (choise != 0);
    }

    @Override
    public void list() {
        conn = null;
        stmt = null;
        try {
            conn = Database.getConnection ();
            System.out.println ("Creating statement...");
            stmt = conn.createStatement ();

            sql = "SELECT * FROM pracownicy";
            ResultSet rs = stmt.executeQuery (sql);
            while (rs.next ()) {
                int id_pracownika = rs.getInt ("id_pracownika");
                String imie = rs.getString ("imie");
                String nazwisko = rs.getString ("nazwisko");
                String kolor_oczu = rs.getString ("kolor_oczu");
                int wzrost = rs.getInt ("wzrost");
                String plec = rs.getString ("plec");
                String telefon = rs.getString ("telefon");
                String email = rs.getString ("email");
                String pesel = rs.getString ("PESEL");
                String data_urodzin = rs.getString ("data_urodzin");
                String login = rs.getString ("login");
                String haslo = rs.getString ("haslo");
                int id_stanowiska = rs.getInt ("id_stanowiska");
                int wynagrodzenie = rs.getInt ("wynagrodzenie");
                int id_firmy = rs.getInt ("id_firmy");



                System.out.print ("ID: " + id_pracownika);
                System.out.print (", imie: " + imie);
                System.out.print (", nazwisko: " + nazwisko);
                System.out.print (", kolor oczu: " + kolor_oczu);
                System.out.print (", wzrost: " + wzrost);
                System.out.print (", plec: " + plec);
                System.out.print (", telefon: " + telefon);
                System.out.print (", email: " + email);
                System.out.print (", PESEL: " + pesel);
                System.out.print (", data urodzin: " + data_urodzin);
                System.out.print (", login: " + login);
                System.out.print (", haslo: " + haslo);
                System.out.print (", id_stanowiska: " + id_stanowiska);
                System.out.print (", wynagrodzenie: " + wynagrodzenie);
                System.out.print (", id firmy: " + id_firmy+"\n");
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
            System.out.println ("Podaj imię: ");
            String newName = scanner.nextLine ();
            System.out.println ("Podaj nazwisko : ");
            String newLastName = scanner.nextLine ();
            System.out.println ("Podaj Pesel: ");
            String newPesel = scanner.nextLine ();
            System.out.println ("Podaj id stanowiska");
            int newIdPosition = scanner.nextInt ();
            scanner.nextLine ();
            System.out.println ("Podaj wynagrodzenie pracownika");
            int newReward = scanner.nextInt ();
            scanner.nextLine ();
            System.out.println ("Podaj id firmy");
            int newIdCompany = scanner.nextInt ();
            scanner.nextLine ();

                    sql = "INSERT INTO pracownicy (id_pracownika, imie, nazwisko, pesel, id_stanowiska, wynagrodzenie, id_firmy) " +
                    "VALUES (" + newID + ",'" + newName + "','" + newLastName +
                    "','" + newPesel + "','" + newIdPosition + "','" +
                    newReward + "','" + newIdCompany + "')";
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

            sql = "DELETE FROM pracownicy WHERE pracownicy.id_pracownika = \"" + noweId + "\"";


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

            System.out.println ("Podaj Id pracownika do zmodyfikowania");
            int id = scanner.nextInt ();
            scanner.nextLine ();
            System.out.println ("Podaj imię: ");
            String newName = scanner.nextLine ();
            System.out.println ("Podaj nazwisko : ");
            String newLastName = scanner.nextLine ();
            System.out.println ("Podaj Pesel: ");
            String newPesel = scanner.nextLine ();
            System.out.println ("Podaj id stanowiska");
            int newIdPosition = scanner.nextInt ();
            scanner.nextLine ();
            System.out.println ("Podaj wynagrodzenie pracownika");
            int newReward = scanner.nextInt ();
            scanner.nextLine ();
            System.out.println ("Podaj id firmy");
            int newIdCompany = scanner.nextInt ();
            scanner.nextLine ();

            sql =" UPDATE pracownicy SET imie='"+newName+"', nazwisko= '"+newLastName+
            "', pesel= '"+newPesel+"', id_stanowiska= '"+newIdPosition+
                    "', wynagrodzenie = '"+newReward+"', id_firmy= '"+newIdCompany+"' WHERE id_pracownika= "+id;

            int result = stmt.executeUpdate (sql);
            System.out.println ("Result: "+result);

        } catch (SQLException se) {
            se.printStackTrace ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }


}
