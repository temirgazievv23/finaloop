package sample;

import java.sql.*;

public class PostgreSQL {
    Connection connection;
    String url = "jdbc:postgresql://localhost:5432/dbname";
    String username = "username";
    String dbPassword = "password";
    String nickname;
    String password;

    public PostgreSQL(){
        try{
            this.connection = DriverManager.getConnection(url, username, dbPassword);
        }catch (SQLException e){
            System.out.println("Error in connecting to database!");
        }
    }

    public void setCredentials(String nickname, String password){
        this.nickname = nickname;
        this.password = password;
    }

    public void setScore(int n) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "UPDATE users SET score = " + n + " WHERE name = '" + nickname + "';";
        statement.executeUpdate(query);
    }

    public boolean checkUser() throws SQLException {
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM users WHERE name = '" + nickname + "';";
        ResultSet rs = statement.executeQuery(query);
        if(rs.next()){
            String pass = rs.getString("password");
            return pass.equals(password);
        }else{
            return false;
        }
    }

    public int getScore() throws SQLException {
        Statement statement = connection.createStatement();
        String query = "SELECT score FROM users WHERE name = '" + nickname + "';";
        ResultSet rs = statement.executeQuery(query);
        rs.next();
        return rs.getInt("score");
    }
}
