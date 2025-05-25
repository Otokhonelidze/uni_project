package oto.project.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private String name;
    private String user;
    private String password;
    private Connection con = null;

    public Database(String name, String user, String password) {
        this.name = name;
        this.user = user;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void startDatabase() {
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            this.con = DriverManager.getConnection(this.name, this.user, this.password);
            if (con != null) {
                System.out.println("Connected");
            }
        } catch (SQLException e) {
            System.out.println("Error while connecting to the database");
            e.printStackTrace();
        } 
    }

   public void endDatabase() {
        try {
            this.con.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            System.out.println("Can't close the database");
            e.printStackTrace();
        }
    }

    public void executeSql(String sqlCode) {
        try {
            Statement stmt = this.con.createStatement();
            boolean isResultSet = stmt.execute(sqlCode);

            if (isResultSet) {
                try (ResultSet rs = stmt.getResultSet()) {
                    int columnCount = rs.getMetaData().getColumnCount();
                    System.out.println("Columns: " + columnCount);
                    while (rs.next()) {
                        System.out.println("Row: " + rs.getString(1));
                    }
                }
            }
            else {
                int updateCount = stmt.getUpdateCount();
                if (updateCount == -1) {
                    System.out.println("no rows affected");
                } else {
                    System.out.println("Rows affected: " + updateCount);
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
}