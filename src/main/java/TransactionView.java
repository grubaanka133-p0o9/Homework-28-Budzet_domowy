import java.util.Scanner;

public class TransactionView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Transaction transaction = new Transaction();
        String type;
        TransactionDao transactionDao = new TransactionDao();

        do {
            System.out.println("Podaj typ:");
            type = scanner.nextLine().toUpperCase();
        } while (!(type.equals("P") || (type.equals("W"))));




    }
}
