import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TransactionAdd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String type;
        String description;
        final int LIMIT = 200;
        double amount = 0;


        do {
            System.out.println("Podaj typ transakcji 'P'- przychód lub 'W'- wydatek:");
            type = scanner.nextLine().toUpperCase();
        } while (!(type.equals("P") || (type.equals("W"))));


        do {
            System.out.println("Opis:");
            description = scanner.nextLine();
            if (description.length() > LIMIT) {
                System.out.println("Twój opis przekracza wymaganą liczbę znaków");
            }
        }

        while (description.length() > LIMIT);


        System.out.println("Podaj kwotę:");
        try {
            amount += scanner.nextDouble();
        } catch (InputMismatchException exception) {
            System.err.println("To nie jest liczba");
        }

        System.out.println("Data transakcji:");
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        System.out.println(localDate);


        Transaction transaction = new Transaction(Type.valueOf(type), description, amount, localDate);

        TransactionDao transactionDao = new TransactionDao();
        transactionDao.insert(transaction);

    }
}
