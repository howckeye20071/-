package ticket;

import java.sql.*;

public class Basic {
		
		public void showPlaylist() {
			try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=DB01",
				"sa", "password");
				PreparedStatement pstmt=connection.prepareStatement("SELECT * FROM playlist");
					){
				
				pstmt.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("查詢場次完成");
		}
		
		public void showPlaylist(int u_movie) {
			try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=DB01",
				"sa", "password");
				PreparedStatement pstmt=connection.prepareStatement("SELECT * FROM playlist WHERE movie=u_movie");
					){
				
				pstmt.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("查詢場次完成");
		}
		
		public void showPlaylist(int u_movie,String u_roomid) {
			try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=DB01",
				"sa", "password");
				PreparedStatement pstmt=connection.prepareStatement("SELECT * FROM playlist WHERE movie=u_movie AND roomd=u_roomid");
					){
				
				pstmt.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("查詢場次完成");
		}
		
		public void showSeats(int u_movie) {
			try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=DB01",
				"sa", "password");
				PreparedStatement pstmt=connection.prepareStatement("SELECT * FROM seats WHERE movie=u_movie");				
					){
				
				pstmt.execute();
					
			} catch (SQLException e) {
					e.printStackTrace();
			}
			
			System.out.println("已查詢電影編號" + u_movie + "的剩餘座位");
		}
	
}
