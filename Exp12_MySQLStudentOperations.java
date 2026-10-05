import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Exp12_MySQLStudentOperations {
    private static final String HOST = System.getenv().getOrDefault("MYSQL_HOST", "localhost");
    private static final int PORT = Integer.parseInt(System.getenv().getOrDefault("MYSQL_PORT", "3306"));
    private static final String SERVER_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/";
    private static final String DATABASE_NAME = "ICE_PUST";
    private static final String DATABASE_URL = SERVER_URL + DATABASE_NAME;
    private static final String USER = System.getenv().getOrDefault("MYSQL_USER", "root");
    private static final String PASSWORD = System.getenv().getOrDefault("MYSQL_PASSWORD", "");

    private static void verifyMySqlServerIsRunning() throws SQLException {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(HOST, PORT), 2000);
        } catch (IOException e) {
            throw new SQLException(
                    "Cannot connect to MySQL at " + HOST + ":" + PORT + ". "
                            + "Start the MySQL service and verify MYSQL_HOST/MYSQL_PORT. "
                            + "On Ubuntu, try: sudo systemctl start mysql",
                    e);
        }
    }

    private static boolean databaseExists(Connection connection) throws SQLException {
        String sql = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, DATABASE_NAME);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private static boolean studentTableExists(Connection connection) throws SQLException {
        String sql = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES "
                + "WHERE TABLE_SCHEMA = ? AND TABLE_NAME = 'Student'";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, DATABASE_NAME);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        }
    }

    private static void ensureDatabaseAndTable() throws SQLException {
        try (Connection connection = DriverManager.getConnection(SERVER_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            if (databaseExists(connection)) {
                System.out.println("MySQL database ICE_PUST already exists.");
            } else {
                System.out.println("MySQL database ICE_PUST does not exist; creating it.");
                statement.executeUpdate("CREATE DATABASE ICE_PUST");
            }
        }

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            if (studentTableExists(connection)) {
                System.out.println("Student table already exists.");
            } else {
                System.out.println("Student table does not exist; creating it.");
                statement.executeUpdate(
                        "CREATE TABLE Student ("
                                + "Name VARCHAR(255) NOT NULL, "
                                + "Email VARCHAR(255) NOT NULL, "
                                + "Phone VARCHAR(32) NOT NULL"
                                + ")");
            }
        }
    }

    private static Connection openConnection() throws SQLException {
        ensureDatabaseAndTable();
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }

    public static void insertStudent(String name, String email, String phone) throws SQLException {
        String sql = "INSERT INTO Student (Name, Email, Phone) VALUES (?, ?, ?)";

        try (Connection connection = openConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, phone);
            int rows = statement.executeUpdate();
            System.out.println(rows + " row inserted.");
        }
    }

    public static void deleteStudent(String email) throws SQLException {
        String sql = "DELETE FROM Student WHERE Email = ?";

        try (Connection connection = openConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            int rows = statement.executeUpdate();
            System.out.println(rows + " row(s) deleted.");
        }
    }

    public static void editStudent(String oldEmail, String newName, String newEmail, String newPhone)
            throws SQLException {
        String sql = "UPDATE Student SET Name = ?, Email = ?, Phone = ? WHERE Email = ?";

        try (Connection connection = openConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setString(2, newEmail);
            statement.setString(3, newPhone);
            statement.setString(4, oldEmail);
            int rows = statement.executeUpdate();
            System.out.println(rows + " row(s) updated.");
        }
    }

    public static void showStudents() throws SQLException {
        String sql = "SELECT Name, Email, Phone FROM Student";

        try (Connection connection = openConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            System.out.println("\nAll students:");
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name")
                        + ", Email: " + resultSet.getString("Email")
                        + ", Phone: " + resultSet.getString("Phone"));
            }
        }
    }

    public static void main(String[] args) {
        try {
            verifyMySqlServerIsRunning();
            insertStudent("Ayesha", "ayesha@gmail.com", "01812345678");
            insertStudent("Kabir", "kabir@gmail.com", "01998765432");
            showStudents();

            editStudent("ayesha@gmail.com", "Ayesha Rahman",
                    "ayesha.rahman@gmail.com", "01811111111");
            showStudents();

            deleteStudent("kabir@gmail.com");
            showStudents();
        } catch (SQLException e) {
            System.err.println("Experiment 12 failed: " + e.getMessage());
            System.exit(1);
        }
    }
}
