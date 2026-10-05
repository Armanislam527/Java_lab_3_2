import java.sql.*;

public class Exp11_AccessStudentDatabase {
    private static final String URL = "jdbc:odbc:ICE_PUST";

    public static void insertStudent(String name, String email, String phone) {
        String sql = "INSERT INTO Student (Name, Email, Phone) VALUES (?, ?, ?)";

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            try (Connection connection = DriverManager.getConnection(URL);
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, phone);
                int rows = statement.executeUpdate();
                System.out.println(rows + " row inserted into Student table.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAllStudents() {
        String sql = "SELECT Name, Email, Phone FROM Student";

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            try (Connection connection = DriverManager.getConnection(URL);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                System.out.println("\nStudent records:");
                while (resultSet.next()) {
                    System.out.println(
                            "Name: " + resultSet.getString("Name") +
                            ", Email: " + resultSet.getString("Email") +
                            ", Phone: " + resultSet.getString("Phone"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        insertStudent("Rahim", "rahim@gmail.com", "01700000001");
        insertStudent("Sadia", "sadia@gmail.com", "01700000002");
        showAllStudents();
    }
}
