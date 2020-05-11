import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {
    public void insert(Transaction transaction) {
        Connection connection = connect();

        PreparedStatement preparedStatement = null;
        try {
            String sql = "INSERT INTO transaction(type, description, amount, date) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(transaction.getType()));
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setDate(4, Date.valueOf(transaction.getDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu do bazy: " + e.getMessage());
        }

        closeConnection(connection);
    }

    public void update(Transaction transaction) {
        Connection connection = connect();

        PreparedStatement preparedStatement = null;
        try {
            String sql = "UPDATE transaction SET type = ? , description = ?, amount = ?, date = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(transaction.getType()));
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setDate(4, Date.valueOf(transaction.getDate()));
            preparedStatement.setLong(5, transaction.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu do bazy: " + e.getMessage());
        }

        closeConnection(connection);
    }

    public void deleteById(Long id) {
        Connection connection = connect();

        PreparedStatement preparedStatement = null;
        try {
            String sql = "DELETE FROM transaction WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu do bazy: " + e.getMessage());
        }

        closeConnection(connection);
    }

    public List<Transaction> findByType(String type) {
        Connection connection = connect();
        List<Transaction> transactions = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            String transactionType = "SELECT * FROM transaction WHERE type = ";
            preparedStatement = connection.prepareStatement(transactionType);
            preparedStatement.setString(1, String.valueOf(type));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long idNumber = resultSet.getLong("id");
                Type type1 = Type.valueOf(resultSet.getString(String.valueOf(type)));
                String description = resultSet.getString("description");
                double amount = resultSet.getInt("amount");
                LocalDate localDate = LocalDate.parse(resultSet.getString("date"));
                transactions.add(new Transaction(idNumber, Type.valueOf(String.valueOf(type)), description, amount, localDate));
            }
            closeConnection(connection);
            return transactions;

        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu do bazy: " + e.getMessage());
        }
        closeConnection(connection);
        return null;
    }



    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/budżet_domowy?serverTimezone=UTC&characterEncoding=utf8";
        try {
            return DriverManager.getConnection(url, "root", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
