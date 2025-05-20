package oto.project.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

}