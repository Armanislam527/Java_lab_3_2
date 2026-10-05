import java.sql.*;

public class Exp12_MySQLStudentOperations {
    private static final String URL = "jdbc:mysql://localhost:3306/ICE_PUST";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void insertStudent(String name, String email, String phone) {
        String sql = "INSERT INTO Student (Name, Email, Phone) VALUES (?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, phone);
                int rows = statement.executeUpdate();
                System.out.println(rows + " row inserted.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent(String email) {
        String sql = "DELETE FROM Student WHERE Email = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, email);
                int rows = statement.executeUpdate();
                System.out.println(rows + " row deleted.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editStudent(String oldEmail, String newName, String newEmail, String newPhone) {
        String sql = "UPDATE Student SET Name = ?, Email = ?, Phone = ? WHERE Email = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, newName);
                statement.setString(2, newEmail);
                statement.setString(3, newPhone);
                statement.setString(4, oldEmail);

                int rows = statement.executeUpdate();
                System.out.println(rows + " row updated.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showStudents() {
        String sql = "SELECT Name, Email, Phone FROM Student";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                System.out.println("\nAll students:");
                while (resultSet.next()) {
                    System.out.println("Name: " + resultSet.getString("Name")
                            + ", Email: " + resultSet.getString("Email")
                            + ", Phone: " + resultSet.getString("Phone"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        insertStudent("Ayesha", "ayesha@gmail.com", "01812345678");
        insertStudent("Kabir", "kabir@gmail.com", "01998765432");
        showStudents();

        editStudent("ayesha@gmail.com", "Ayesha Rahman", "ayesha.rahman@gmail.com", "01811111111");
        showStudents();

        deleteStudent("kabir@gmail.com");
        showStudents();
    }
}
