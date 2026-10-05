import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.DataType;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Exp11_AccessStudentDatabase {
    private static final File DATABASE_FILE = Path.of("ICE_PUST.accdb").toAbsolutePath().toFile();
    private static final String URL = "jdbc:ucanaccess://" + DATABASE_FILE;

    private static void createStudentTable(Database database) throws IOException {
        new TableBuilder("Student")
                .addColumn(new ColumnBuilder("Name", DataType.TEXT))
                .addColumn(new ColumnBuilder("Email", DataType.TEXT))
                .addColumn(new ColumnBuilder("Phone", DataType.TEXT))
                .toTable(database);
    }

    private static void ensureDatabaseAndTable() throws IOException {
        if (!DATABASE_FILE.exists()) {
            System.out.println("ICE_PUST database does not exist; creating " + DATABASE_FILE + ".");
            try (Database database = DatabaseBuilder.create(Database.FileFormat.V2010, DATABASE_FILE)) {
                createStudentTable(database);
            }
            return;
        }

        try (Database database = DatabaseBuilder.open(DATABASE_FILE)) {
            Table studentTable = database.getTable("Student");
            if (studentTable == null) {
                System.out.println("Student table does not exist; creating it.");
                createStudentTable(database);
            } else {
                System.out.println("ICE_PUST database and Student table already exist.");
            }
        }
    }

    private static Connection openConnection() throws IOException, SQLException {
        ensureDatabaseAndTable();
        return DriverManager.getConnection(URL);
    }

    public static void insertStudent(String name, String email, String phone) throws IOException, SQLException {
        String sql = "INSERT INTO Student (Name, Email, Phone) VALUES (?, ?, ?)";

        try (Connection connection = openConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, phone);
            int rows = statement.executeUpdate();
            System.out.println(rows + " row inserted into Student table.");
        }
    }

    public static void showAllStudents() throws IOException, SQLException {
        String sql = "SELECT Name, Email, Phone FROM Student";

        try (Connection connection = openConnection();
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
    }

    public static void main(String[] args) throws IOException, SQLException {
        insertStudent("Rahim", "rahim@gmail.com", "01700000001");
        insertStudent("Sadia", "sadia@gmail.com", "01700000002");
        showAllStudents();
    }
}
