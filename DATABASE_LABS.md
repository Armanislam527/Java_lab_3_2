# Database lab setup

Experiments 11 and 12 use JDBC dependencies managed by Maven. From this folder, compile with:

```bash
mvn compile
```

## Experiment 11: MS Access

Run:

```bash
mvn exec:java -Dexec.mainClass=Exp11_AccessStudentDatabase
```

The program creates `ICE_PUST.accdb` in the current folder when it is missing, creates the `Student` table if needed, then inserts and displays sample rows. It uses UCanAccess/Jackcess because the JDBC-ODBC bridge (`sun.jdbc.odbc.JdbcOdbcDriver`) was removed from modern Java.

## Experiment 12: MySQL

Start the MySQL server and set credentials that have permission to create databases:

```bash
sudo systemctl start mysql
sudo systemctl status mysql
export MYSQL_USER=root
export MYSQL_PASSWORD='your-password'
mvn exec:java -Dexec.mainClass=Exp12_MySQLStudentOperations
```

If the service is named MariaDB on your system, use `sudo systemctl start mariadb` instead. The program connects to `localhost:3306` by default and checks for the `ICE_PUST` database and `Student` table, creates either one if missing, then demonstrates insert, show, edit, and delete operations. Override the connection settings with `MYSQL_HOST`, `MYSQL_PORT`, `MYSQL_USER`, and `MYSQL_PASSWORD`. If the server is not listening, the program reports the connection problem and service-start command instead of continuing with database operations.
