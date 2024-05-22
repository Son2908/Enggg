package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Soundbank;
import model.Booking;

/**
 * 
 * @author sodok
 */
public class BookingDAO extends DBcontext {

    public void insertNewBooking(String email, int idRoom,String imageRoom, String dobBefore, String dobAfter, int quantity, int price) {
        try {
            String sql = "INSERT INTO bookingDetail"
                    + " VALUES(?,?,?,?, ? ,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setInt(2, idRoom);
            statement.setString(3, imageRoom);
            statement.setString(4, dobBefore);
            statement.setString(5, dobAfter);
            statement.setInt(6, quantity);
            statement.setInt(7, price);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Hủy một đặt phòng dựa trên ID đặt phòng.
    public void cancelBooking(String id) {
        try {
            String sql = "DELETE FROM bookingDetail WHERE idBooking = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Lấy lịch sử đặt phòng của một người dùng cụ thể dựa trên email. 
    // Phương thức này truy vấn cơ sở dữ liệu để lấy thông tin các đặt phòng của người dùng và trả về một danh sách các đối tượng Booking chứa thông tin chi tiết về các đặt phòng.
       public ArrayList<Booking> getBookingHistory(String email) {
        ArrayList<Booking> Booking = new ArrayList<>();
        try {
            String sql = "select * from bookingDetail where email = '" + email + "'" ;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Booking s = new Booking();
                s.setEmail(rs.getString("email"));
                s.setIdRoom(rs.getInt("idRoom"));
                s.setIdBooking(rs.getString("idBooking"));
                s.setImageRoom(rs.getString("imageRoom"));
                s.setDobBefore(rs.getString("dobBefore"));
                s.setDobAfter(rs.getString("dobAfter"));
                s.setQuantity(rs.getInt("quantity"));
                s.setPrice(rs.getInt("price"));              
                Booking.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Booking;
    }
    
     // Lấy toàn bộ lịch sử đặt phòng từ cơ sở dữ liệu. 
       // Phương thức này truy vấn cơ sở dữ liệu để lấy thông tin về tất cả các đặt phòng trong hệ thống và trả về một danh sách các đối tượng Booking.
     public ArrayList<Booking> getBookingHistory() {
        ArrayList<Booking> Booking = new ArrayList<>();
        try {
            String sql = "select * from bookingDetail";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Booking s = new Booking();
                s.setEmail(rs.getString("email"));
                s.setIdRoom(rs.getInt("idRoom"));
                s.setIdBooking(rs.getString("idBooking"));
                s.setImageRoom(rs.getString("imageRoom"));
                s.setDobBefore(rs.getString("dobBefore"));
                s.setDobAfter(rs.getString("dobAfter"));
                s.setQuantity(rs.getInt("quantity"));
                s.setPrice(rs.getInt("price"));              
                Booking.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Booking;
    }
}
