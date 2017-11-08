package base;

import Interface.BaseOperations;
import config.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Addresses implements BaseOperations{
    private static Scanner scanner;
    private static Connection conn;
    private static Statement stmt;
    private static String sql;

    @Override
    public void choiceOfAction() {
    Addresses addresses = new Addresses ();
        scanner = new Scanner (System.in);
        int choise;
        do {
            System.out.println ("Wybierz operacje: \n" +
                    "1: Lista adresów \n" +
                    "2: Dodaj adres\n" +
                    "3: Popraw adres\n" +
                    "4: Usuń adres\n" +
                    "0: powrót do menu");
            choise = scanner.nextInt ();

            switch (choise) {
                case 1:
                    addresses.list ();
                    break;
                case 2:
                    addresses.insert ();
                    break;
                case 3:
                    addresses.update ();
                    break;
                case 4:
                    addresses.delete ();
                    break;
                default:
                    System.out.println ("Podaj poprawná wartość");
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
            sql = "SELECT * FROM adresy";
            ResultSet rs = stmt.executeQuery (sql);
            while (rs.next ()) {
                int id_adresu = rs.getInt ("id_adresu");
                String kod_pocztowy = rs.getString ("kod_pocztowy");
                String miasto = rs.getString ("miasto");
                String numer_domu = rs.getString ("numer_domu");
                String numer_mieszkania = rs.getString ("numer_mieszkania");
                String panstwo = rs.getString ("panstwo");
                String ulica = rs.getString ("ulica");
                String wojewodztwo = rs.getString ("wojewodztwo");


                System.out.print ("ID: " + id_adresu);
                System.out.print (", kod pocztowy: " + kod_pocztowy);
                System.out.print (", miasto: " + miasto);
                System.out.print (", numer domu: " + numer_domu);
                System.out.print (", numer_mieszkania: " + numer_mieszkania);
                System.out.print (", panstwo: " + panstwo);
                System.out.print (", ulica: " + ulica);
                System.out.print (", wojewodztwo: " + wojewodztwo+"\n");
            }
        } catch (SQLException se) {
            se.printStackTrace ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    @Override
    public void insert() {
        try {
            conn = Database.getConnection ();
            System.out.println ("Creating statement...");
            stmt = conn.createStatement ();
            System.out.println ("Podaj ID");
            int newID = scanner.nextInt ();
            scanner.nextLine ();
            System.out.println ("Podaj miasto: ");
            String newTown = scanner.nextLine ();

            System.out.println ("Podaj kod pocztowy : ");
            String newPostcode = scanner.nextLine ();


            sql = "INSERT INTO adresy (id_adresu, kod_pocztowy, miasto) " +
                    "VALUES (" + newID + ",'" + newPostcode + "','" + newTown + "')";
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

            sql = "DELETE FROM adresy WHERE adresy.id_adresu = \"" + noweId + "\"";


            int result = stmt.executeUpdate (sql);
            System.out.println ("Result: " + result);
        } catch (SQLException se) {
            se.printStackTrace ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }
    @Override
    public void update(){

        try {
            conn = Database.getConnection ();
            System.out.println ("Creating statement...");
            stmt = conn.createStatement ();

            System.out.println ("Podaj ID adresu, który chcesz zmodyfikować");
            int id = scanner.nextInt ();
            scanner.nextLine ();
            System.out.println ("Podaj ulice: ");
            String newStreet = scanner.nextLine ();
            System.out.println ("Podaj numer domu");
            String newHouseNumber = scanner.nextLine ();
            System.out.println ("Podaj numer mieszkania");
            String newApartmetNumber = scanner.nextLine ();
            System.out.println ("Podaj kod pocztowy : ");
            String newPostcode = scanner.nextLine ();
            System.out.println ("Podaj miasto : ");
            String newCity = scanner.nextLine ();
            System.out.println ("Podaj państwo : ");
            String newState = scanner.nextLine ();
            System.out.println ("Podaj wojewodztwo : ");
            String newProvince = scanner.nextLine ();


            sql = "UPDATE adresy SET ulica='"+newStreet+"', numer_domu="+newHouseNumber+
            ", numer_mieszkania="+newApartmetNumber+", kod_pocztowy= "+newPostcode +
                    ", miasto= '"+newCity+"', panstwo='"+newState+"' , wojewodztwo='"+newProvince +
                    "'WHERE adresy.id_adresu = " + id;

            int result = stmt.executeUpdate (sql);
            System.out.println ("Result: " + result);
        } catch (SQLException se) {
            se.printStackTrace ();
        } catch (Exception e) {
            e.printStackTrace ();
        }

    }
}
