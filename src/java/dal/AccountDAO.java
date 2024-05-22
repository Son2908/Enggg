/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AccountUser;

/**
 *
 * @author sodok
 */
public class AccountDAO extends DBcontext {

    // Truy vấn cơ sở dữ liệu để lấy thông tin tài khoản dựa trên email và mật khẩu được cung cấp. 
    // Nếu tìm thấy, phương thức trả về một đối tượng AccountUser chứa thông tin tài khoản, nếu không, trả về null.
    public AccountUser getAccountByUP(String email, String password) {
        try {
            String sql = "select * from account where email = ? and password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                AccountUser s = new AccountUser(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                return s;

            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public AccountUser getAccountByEmail(String email) {
        AccountUser account = null;
        try {
            String sql = "SELECT * FROM account WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                account = new AccountUser(
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }

    //Truy vấn cơ sở dữ liệu để lấy danh sách tất cả các tài khoản trong bảng account và trả về một ArrayList chứa các đối tượng AccountUser.
    public ArrayList<AccountUser> listAcc() {
        ArrayList acc = new ArrayList();

        try {
            String sql = "select * from account ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                AccountUser s = new AccountUser(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                acc.add(s);

            }
            return acc;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Thêm một tài khoản mới vào bảng account với thông tin cung cấp.
    public void insertAccount(String email, String name, String phone, String password) {
        String role = "user";
        try {
            String sql = "INSERT INTO account"
                    + " VALUES(?,?,?,? ,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, name);
            statement.setString(3, phone);
            statement.setString(4, password);
            statement.setString(5, role);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Thay đổi mật khẩu của tài khoản có địa chỉ email được cung cấp.
    public void changePassword(String email, String password) {
        try {
            String sql = "UPDATE account\n"
                    + "SET password = '" + password
                    + "'WHERE email = '" + email + "'";
            System.out.println(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Thay đổi thông tin cá nhân của tài khoản (tên và số điện thoại) dựa trên địa chỉ email được cung cấp.
    public void changeInformations(String email, String name, String phone) {
        try {
            String sql = "UPDATE account\n"
                    + "SET name = N'" + name
                    + "', phone = '" + phone
                    + "'WHERE email = '" + email + "'";
            System.out.println(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePasswordByEmail(String email, String newPassword) {
        try {
            String sql = "UPDATE account\n"
                    + "SET password = '" + newPassword
                    + "'WHERE email = '" + email + "'";
            System.out.println(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
