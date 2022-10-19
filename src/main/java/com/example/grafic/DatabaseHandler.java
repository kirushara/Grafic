package com.example.grafic;


import java.sql.*;
import java.util.Objects;

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
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet getUser(String login, String password) {
        ResultSet rs;
        String select = "SELECT * FROM " + Const.USERS_TABLE + " WHERE " +
                Const.USERS_LOGIN + "=? AND " + Const.USERS_PASS + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            prSt.setString(2, password);
            rs=prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public boolean loginCheck(String login) {
        String select = "SELECT * FROM " + Const.USERS_TABLE + " WHERE " +
                Const.USERS_LOGIN + "=?";
        try (PreparedStatement prSt = getDbConnection().prepareStatement(select)) {
            prSt.setString(1, login);
            ResultSet rs = prSt.executeQuery();
            while (rs.next())
                if (!Objects.equals(rs.getString(Const.USERS_LOGIN), ""))
                    return true;
            return false;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public ResultSet getFormula(String login) {
        ResultSet rs;
        String select = "SELECT * FROM " + Const.FORMULAS_TABLE + " WHERE " +
                Const.FORMULAS_LOGIN + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            rs=prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public void setFormula(String login, String formula){
        String insert = "INSERT INTO " + Const.FORMULAS_TABLE + "(" +
                Const.FORMULAS_LOGIN + "," + Const.FORMULAS_FORMULA + ")" +
                "VALUES(?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, login);
            prSt.setString(2, formula);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean formulaCheck(String login, String formula){
        ResultSet rs;
        String select = "SELECT * FROM " + Const.FORMULAS_TABLE + " WHERE " +
                Const.FORMULAS_LOGIN + "=? AND " + Const.FORMULAS_FORMULA + "=?";
        try( PreparedStatement prSt = getDbConnection().prepareStatement(select)){
            prSt.setString(1, login);
            prSt.setString(2, formula);
            rs=prSt.executeQuery();
            while (rs.next())
                if (!Objects.equals(rs.getString(Const.FORMULAS_LOGIN), ""))
                    return true;
            return false;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
