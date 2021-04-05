import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
/**
 * class that implement data modify. using transaction when modify fk
 *
 * @FileName ModifyData.java
 * @Project SampleJDBCConnector
 * @Date 2020. 6. 16.
 * @author iw040
 */
public class ModifyData {
	Connection myConn = MyDriver.getConn();
	Statement myState = MyDriver.getState();
	PreparedStatement myPStmt = MyDriver.getPStmt();
	PreparedStatement myPStmt2 = MyDriver.getPStmt2();
	ResultSet myResSet = MyDriver.getResSet();
	String sql = "";
	public ModifyData(MyDriver md) {
	}
	/**
	 * data modify in SONG table & transaction
	 *
	 * @Method Name modifySong
	 * @Date 2020. 6. 16.
	 * @author iw040
	 * @throws SQLException
	 * @throws IOException
	 */
	public void modifySong() throws SQLException, IOException {int cnt = 0, i = 0;
		String f_artist = "";
		int m_song_id = -1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		myPStmt = myConn.prepareStatement(
				"UPDATE SONG set song_id = ?, title = ?, artist = ?, released_year
						= ? WHERE song_id = ?");
		myPStmt2 = myConn.prepareStatement("UPDATE ARTIST set name = ? WHERE name = ?");
		sql = "SELECT count(*) FROM SONG";
		myResSet = myState.executeQuery(sql);
		if (myResSet.next()) {
			cnt = myResSet.getInt(1);
		}
		int[] m_song_id_arr = new int[cnt];
		String[] m_artist_arr = new String[cnt];
		sql = "SELECT song_id, artist FROM SONG";
		myResSet = myState.executeQuery(sql);
		i = 0;
		while (myResSet.next()) {
			m_song_id_arr[i] = myResSet.getInt("song_id");
			m_artist_arr[i] = myResSet.getString("artist");
			i++;
		}
		while (true) {
			int flag = 1;
			System.out.print("수정할 row의 song_id를 입력하세요 : ");
			m_song_id = Integer.parseInt(br.readLine());
			for (int j = 0; j < cnt; j++) {
				if (m_song_id == m_song_id_arr[j]) {
					f_artist = m_artist_arr[j];
					flag = 1;
					break;
				} else
					flag = 0;
			}
			if (flag == 0) {
				System.out.println("일치하는 song_id가 없습니다.");
			} else
				break;
		}
		System.out.print("수정된 song_id를 입력하세요 : ");
		int n_song_id = Integer.parseInt(br.readLine());
		System.out.print("수정된 title을 입력하세요 : ");
		String n_title = br.readLine();
		System.out.print("수정된 artist를 입력하세요 : ");
		String n_artist = br.readLine();
		System.out.print("수정된 released_year를 입력하세요 : ");
		int n_released_year = Integer.parseInt(br.readLine());
		try {
			myConn.setAutoCommit(false);
			myPStmt2.setString(1, n_artist);
			myPStmt2.setString(2, f_artist);
			myPStmt2.executeUpdate();
			myPStmt.setInt(1, n_song_id);
			myPStmt.setString(2, n_title);
			myPStmt.setString(3, n_artist);
			myPStmt.setInt(4, n_released_year);
			myPStmt.setInt(5, m_song_id);
			myPStmt.executeUpdate();
			myConn.commit();
			System.out.println("수정이 완료됐습니다.");System.out.println();
		} catch (SQLException se) {
			myConn.rollback();
		}finally {
			myConn.setAutoCommit(true);
		}
	}
	/**
	 * data modify in ARTIST table & transaction
	 *
	 * @Method Name modifyArtist
	 * @Date 2020. 6. 16.
	 * @author iw040
	 * @throws SQLException
	 * @throws IOException
	 */
	public void modifyArtist() throws SQLException, IOException {
		int cnt = 0, i = 0;
		String f_agency = "";
		String m_name = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		myPStmt = myConn.prepareStatement(
				"UPDATE ARTIST set name = ?, member_num = ?, debut_year = ?,
				agency = ? WHERE name = ?");
		myPStmt2 = myConn.prepareStatement("UPDATE AGENCY set name = ? WHERE name = ?");
		sql = "SELECT count(*) FROM ARTIST";
		myResSet = myState.executeQuery(sql);
		if (myResSet.next()) {
			cnt = myResSet.getInt(1);
		}
		String[] m_name_arr = new String[cnt];
		String[] m_agency_arr = new String[cnt];
		sql = "SELECT name, agency FROM ARTIST";
		myResSet = myState.executeQuery(sql);
		i = 0;
		while (myResSet.next()) {
			m_name_arr[i] = myResSet.getString("name");
			m_agency_arr[i] = myResSet.getString("agency");
			i++;
		}
		while (true) {
			int flag = 1;
			System.out.print("수정할 row의 name을 입력하세요 : ");
			m_name = br.readLine();
			for (int j = 0; j < cnt; j++) {
				if (m_name.contentEquals(m_name_arr[j])) {
					f_agency = m_agency_arr[j];
					flag = 1;
					break;
				} else
					flag = 0;
			}
			if (flag == 0) {
				System.out.println("일치하는 name이 없습니다.");
			} else
				break;
		}
		System.out.print("수정된 name을 입력하세요 : ");
		String n_name = br.readLine();
		System.out.print("수정된 member_num를 입력하세요 : ");
		int n_member_num = Integer.parseInt(br.readLine());
		System.out.print("수정된 debut_year를 입력하세요 : ");
		int n_debut_year = Integer.parseInt(br.readLine());
		System.out.print("수정된 agency를 입력하세요 : ");
		String n_agency = br.readLine();try {
			myConn.setAutoCommit(false);
			myPStmt2.setString(1, n_agency);
			myPStmt2.setString(2, f_agency);
			myPStmt2.executeUpdate();
			myPStmt.setString(1, n_name);
			myPStmt.setInt(2, n_member_num);
			myPStmt.setInt(3, n_debut_year);
			myPStmt.setString(4, n_agency);
			myPStmt.setString(5, m_name);
			myPStmt.executeUpdate();
			myConn.commit();
			System.out.println("수정이 완료됐습니다.");
			System.out.println();
		} catch (SQLException se) {
			myConn.rollback();
		}finally {
			myConn.setAutoCommit(true);
		}
	}
	/**
	 * data modify in AGENCY table
	 *
	 * @Method Name modifyAgency
	 * @Date 2020. 6. 16.
	 * @author iw040
	 * @throws SQLException
	 * @throws IOException
	 */
	public void modifyAgency() throws SQLException, IOException {
		int cnt = 0, i = 0;
		String m_A_name = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		myPStmt = myConn.prepareStatement("UPDATE AGENCY set name = ?, street = ?,
				artists_num = ? WHERE name = ?");
		sql = "SELECT count(*) FROM AGENCY";
		myResSet = myState.executeQuery(sql);
		if (myResSet.next()) {
			cnt = myResSet.getInt(1);
		}
		String[] m_A_name_arr = new String[cnt];
		sql = "SELECT name FROM AGENCY";
		myResSet = myState.executeQuery(sql);
		i = 0;
		while (myResSet.next()) {
			m_A_name_arr[i] = myResSet.getString("name");
			i++;
		}
		while (true) {
			int flag = 1;
			System.out.print("수정할 row의 name을 입력하세요 : ");
			m_A_name = br.readLine();
			for (int j = 0; j < cnt; j++) {
				if (m_A_name.contentEquals(m_A_name_arr[j])) {
					flag = 1;
					break;
				} else
					flag = 0;
			}
			if (flag == 0) {
				System.out.println("일치하는 name이 없습니다.");
			} else
				break;}
		System.out.print("수정된 name을 입력하세요 : ");
		String n_A_name = br.readLine();
		System.out.print("수정된 street를 입력하세요 : ");
		String n_street = br.readLine();
		System.out.print("수정된 artists_num을 입력하세요 : ");
		int n_artists_num = Integer.parseInt(br.readLine());
		myPStmt.setString(1, n_A_name);
		myPStmt.setString(2, n_street);
		myPStmt.setInt(3, n_artists_num);
		myPStmt.setString(4, m_A_name);
		myPStmt.executeUpdate();
		System.out.println("수정이 완료됐습니다.");
		System.out.println();
	}
}