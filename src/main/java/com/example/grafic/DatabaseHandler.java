package com.example.grafic;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost
                + ":" + dbPort + "/" + dbName + "?useUnicode=true&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void regUser(String login, String password) {
        String insert = "INSERT INTO " + Const.USERS_TABLE + "(" +
                Const.USERS_LOGIN + "," + Const.USERS_PASS + ")" +
                "VALUES(?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, login);
            prSt.setString(2, password);
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    boolean loginCheck(String login) {
        String select = "SELECT EXISTS(SELECT " + Const.USERS_LOGIN + " FROM " + Const.USERS_TABLE + " WHERE "
                + Const.USERS_LOGIN + " = '" + login + "')";
        try (PreparedStatement prSt = getDbConnection().prepareStatement(select)) {
            ResultSet rs = prSt.executeQuery();
            while (rs.next())
                if (rs.getInt(1) != 0)
                    return true;
            return false;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
