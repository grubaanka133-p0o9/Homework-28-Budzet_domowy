import java.time.LocalDate;
import java.util.Scanner;

public class TransactionUpdate {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id transakcji którą chcesz zaktualizować:");
        long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Podaj typ:");
        String type = scanner.nextLine().toUpperCase();

        System.out.println("Podaj opis:");
        String description = scanner.nextLine();

        System.out.println("Podaj kwotę:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Podaj datę:");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        Transaction transaction = new Transaction(id, Type.valueOf(type), description, amount, date);

        TransactionDao transactionDao = new TransactionDao();
        transactionDao.update(transaction);
    }
}

