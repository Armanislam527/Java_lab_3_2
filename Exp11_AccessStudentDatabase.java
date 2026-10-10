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
import java.sql.SQLException;

public class Exp11_AccessStudentDatabase {
    private static final File DATABASE_FILE = Path.of("ICE_PUST.accdb").toAbsolutePath().toFile();
    private static final String URL = "jdbc:ucanaccess://" + DATABASE_FILE;

    private Exp11_AccessStudentDatabase() {
    }

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

    static Connection openConnection() throws IOException, SQLException {
        ensureDatabaseAndTable();
        return DriverManager.getConnection(URL);
    }
}
