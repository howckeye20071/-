package ticket;

import java.sql.*;

public class BasicSearch {
	String conn = "jdbc:sqlserver://localhost:1433;databaseName=DB01";
	String account = "sa";
	String password = "password";
	boolean checkPlaylist_movieid;
	boolean checkSeats_num;

	public void checkPlaylist(int u_movie) {
		try (Connection connection = DriverManager.getConnection(conn, account, password);
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM playlist WHERE movie=?");) {
			pstmt.setInt(1, u_movie);
			ResultSet rs = pstmt.executeQuery();
//			rs.next();
			if (rs.next()) {
				checkPlaylist_movieid = true;
			} else {
				checkPlaylist_movieid = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showPlaylist() {
		try (Connection connection = DriverManager.getConnection(conn, account, password);
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM playlist");) {

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Timestamp ptime = rs.getTimestamp("ptime");
				int movie = rs.getInt("movie");
				String roomid = rs.getString("roomid");
				System.out.println("播放時間\t\t\t電影id\t廳號");
				System.out.println(ptime + "\t" + movie + "\t" + roomid);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("查詢場次完成");
	}

	public void showPlaylist(int u_movie) {
		try (Connection connection = DriverManager.getConnection(conn, account, password);
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM playlist WHERE movie=?");) {

			if (checkPlaylist_movieid) {
				pstmt.setInt(1, u_movie);
				ResultSet rs = pstmt.executeQuery();
				System.out.println("播放時間\t\t\t電影id\t廳號");
				while (rs.next()) {
					Timestamp ptime = rs.getTimestamp("ptime");
					int movie = rs.getInt("movie");
					String roomid = rs.getString("roomid");
					System.out.println(ptime + "\t" + movie + "\t" + roomid);
				}
			} else {
				System.out.println("您輸入的電影目前暫無播放場次");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

//	public void showPlaylist(int u_movie, String u_roomid) {
//		try (Connection connection = DriverManager.getConnection(conn, account, password);
//				PreparedStatement pstmt = connection
//						.prepareStatement("SELECT * FROM playlist WHERE movie=? AND roomid=?");) {
//
//			pstmt.setInt(1, u_movie);
//			pstmt.setString(2, u_roomid);
//			ResultSet rs = pstmt.executeQuery();
//			if (rs.next()) {
//				Timestamp ptime = rs.getTimestamp("ptime");
//				int movie = rs.getInt("movie");
//				String roomid = rs.getString("roomid");
//				System.out.println("播放時間\t\t\t電影id\t廳號");
//				System.out.println(ptime + "\t" + movie + "\t" + roomid);
//			}
//			System.out.println("查詢場次完成");
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}

	public void showSeats(int u_movie) {
		try (Connection connection = DriverManager.getConnection(conn, account, password);
				PreparedStatement pstmt = connection
						.prepareStatement("SELECT * FROM seats WHERE movie=? AND sold=0");) {

			if (checkPlaylist_movieid) {
				pstmt.setInt(1, u_movie);
				ResultSet rs = pstmt.executeQuery();
				System.out.println("播放時間\t\t\t電影id\t剩餘座位\t是否賣出\t票券流水號");
				while (rs.next()) {
					Timestamp ptime = rs.getTimestamp("ptime");
					int movie = rs.getInt("movie");
					String seat_num = rs.getString("seat_num");
					int sold = rs.getInt("sold");
					int ordid = rs.getInt("ordid");
					System.out.println(ptime + "\t" + movie + "\t" + seat_num + "\t" + sold + "\t" + ordid);
				}
				System.out.println("已查詢電影編號" + u_movie + "的剩餘座位");
			} else {
				System.out.println("您輸入的電影目前暫無播放場次");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void checkSeats(int u_movie, String u_seats) {
		try (Connection connection = DriverManager.getConnection(conn, account, password);
				PreparedStatement pstmt = connection
						.prepareStatement("SELECT * FROM seats WHERE movie=? AND seat_num=? AND sold=0");) {

			pstmt.setInt(1, u_movie);
			pstmt.setString(2, u_seats);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				checkSeats_num = true;
			} else {
				checkSeats_num = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void searchSeats(int u_movie, String u_seats) {

		try (Connection connection = DriverManager.getConnection(conn, account, password);
				PreparedStatement pstmt = connection
						.prepareStatement("SELECT * FROM seats WHERE movie=? AND seat_num=? AND sold=0");) {

			if (checkSeats_num) {
				pstmt.setInt(1, u_movie);
				pstmt.setString(2, u_seats);
				ResultSet rs = pstmt.executeQuery();
				System.out.println("已選取播放時間\t\t\t電影id\t剩餘座位\t是否賣出\t票券流水號");
				while (rs.next()) {
					Timestamp ptime = rs.getTimestamp("ptime");
					int movie = rs.getInt("movie");
					String seat_num = rs.getString("seat_num");
					int sold = rs.getInt("sold");
					int ordid = rs.getInt("ordid");
					System.out.println(ptime + "\t" + movie + "\t" + seat_num + "\t" + sold + "\t" + ordid);
				}
			} else {
				System.out.println("查無此座位");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void orderSeats(int u_movie, String u_seats, String order) {
		try (Connection connection = DriverManager.getConnection(conn, account, password);
				PreparedStatement pstmt = connection
						.prepareStatement("UPDATE DB01 SET sold=1 WHERE movie=? AND seat_num=? AND sold=0");) {

			pstmt.setInt(1, u_movie);
			pstmt.setString(2, u_seats);
			ResultSet rs = pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
