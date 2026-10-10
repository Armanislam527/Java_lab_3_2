import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Exp11a_InsertStudent {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter student name: ");
            String name = input.nextLine().trim();
            System.out.print("Enter student email: ");
            String email = input.nextLine().trim();
            System.out.print("Enter student phone: ");
            String phone = input.nextLine().trim();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                System.err.println("Name, email, and phone must not be empty.");
                System.exit(1);
            }

            insertStudent(name, email, phone);
        } catch (IOException | SQLException e) {
            System.err.println("Could not insert the student: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void insertStudent(String name, String email, String phone)
            throws IOException, SQLException {
        String sql = "INSERT INTO Student (Name, Email, Phone) VALUES (?, ?, ?)";

        try (Connection connection = Exp11_AccessStudentDatabase.openConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, phone);
            int rows = statement.executeUpdate();
            System.out.println(rows + " student record inserted.");
        }
    }
}
