import base.Addresses;
import base.Company;
import base.Employees;
import base.Positions;
import config.Database;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int choice;
        Scanner scanner = new Scanner (System.in);

        do {
            System.out.println ("Na czym chcesz pracować? \n" +
                    "1 - adresy \n" +
                    "2 - firmy\n" +
                    "3 - pracownicy\n" +
                    "4 - stanowiska\n" +
                    "0 - EXIT");
            choice = scanner.nextInt ();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    Addresses addresses = new Addresses ();
                    addresses.choiceOfAction ();
                    break;
                case 2:
                    Company company = new Company ();
                    company.choiceOfAction ();
                    break;

                case 3:
                    Employees employees = new Employees ();
                    employees.choiceOfAction ();
                    break;
                case 4:
                    Positions positions = new Positions();
                    positions.choiceOfAction();
                    break;
                default:
                    System.out.println ("Spróbuj jeszcze raz");
                    break;
            }
        }
        while (choice != 0);
        Connection conn = Database.closeConnection ();
        System.out.println ("Goodbye!");
    }
}
