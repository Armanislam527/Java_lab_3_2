import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Exp11b_ShowStudents {
    public static void main(String[] args) {
        try {
            showAllStudents();
        } catch (IOException | SQLException e) {
            System.err.println("Could not display student records: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void showAllStudents() throws IOException, SQLException {
        String sql = "SELECT Name, Email, Phone FROM Student";

        try (Connection connection = Exp11_AccessStudentDatabase.openConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            System.out.println("Student records:");
            boolean foundStudent = false;
            while (resultSet.next()) {
                foundStudent = true;
                System.out.println("Name: " + resultSet.getString("Name")
                        + ", Email: " + resultSet.getString("Email")
                        + ", Phone: " + resultSet.getString("Phone"));
            }
            if (!foundStudent) {
                System.out.println("No student records found.");
            }
        }
    }
}
