# Database lab setup

Experiments 11 and 12 use JDBC dependencies managed by Maven. From this folder, compile with:

```bash
mvn compile
```

## Experiment 11: MS Access

Part (a), insert a student record:

```bash
mvn exec:java -Dexec.mainClass=Exp11a_InsertStudent
```

Enter the student's name, email, and phone when prompted.

Part (b), display all student records:

```bash
mvn exec:java -Dexec.mainClass=Exp11b_ShowStudents
```

Both programs use the `ICE_PUST.accdb` file in the current folder. The shared `Exp11_AccessStudentDatabase` helper creates the database and `Student` table if either is missing. UCanAccess is used because the old JDBC-ODBC bridge was removed from modern Java.

## Experiment 12: MySQL

Start the MySQL service and configure a MySQL account that can create databases:

```bash
sudo systemctl start mysql
sudo systemctl status mysql
export MYSQL_USER=root
export MYSQL_PASSWORD='your-password'
mvn exec:java -Dexec.mainClass=Exp12_MySQLStudentOperations
```

If the service is named MariaDB on your system, use `sudo systemctl start mariadb` instead. The program connects to `localhost:3306` by default, checks for the `ICE_PUST` database and `Student` table, creates either one if missing, and demonstrates insert, show, edit, and delete operations. Override the connection settings with `MYSQL_HOST`, `MYSQL_PORT`, `MYSQL_USER`, and `MYSQL_PASSWORD`.
