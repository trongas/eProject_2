package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    // Phương thức kết nối đến cơ sở dữ liệu
    public static Connection connectDb() throws SQLException {
        // Thử kết nối
        try {
            // Tải lớp trình điều khiển JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Thiết lập thông tin kết nối
            String url = "jdbc:mysql://localhost/student_management_system?useSSL=false";
            String username = "root";
            String password = "trong190724";

            // Trả về kết nối
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            // Xử lý ngoại lệ khi không tìm thấy lớp trình điều khiển
            System.err.println("Không thể tải lớp trình điều khiển JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            // Xử lý ngoại lệ khi không thể kết nối đến cơ sở dữ liệu
            System.err.println("Khong the ket noi den Database.");
            e.printStackTrace();
        }
        // Trả về null nếu có lỗi xảy ra
        return null;
    }
}
