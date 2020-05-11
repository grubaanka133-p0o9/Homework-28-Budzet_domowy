import java.util.Scanner;

public class TransactionApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.println("Co chcesz zrobić?");
            System.out.println("1. Dodawanie");
            System.out.println("2. Modyfikacja");
            System.out.println("3. Usuwanie");
            System.out.println("4. Wyświetlenie wszystkich przychodów lub wydatków");
            System.out.println("0. Koniec");

            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    TransactionAdd.main(new String[0]);
                    break;
                case "2":
                    TransactionUpdate.main(new String[0]);
                    break;
                case "3":
                    TransactionDelete.main(new String[0]);
                    break;
                case "4":
                    TransactionView.main(new String[0]);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Błędny wybór!");
            }
        }
    }
}
