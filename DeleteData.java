import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;

/**
 * class that implement data deletion. check whether exist or not for chosen row
 *
 * @author iw040
 * @FileName DeleteData.java
 * @Project SampleJDBCConnector
 * @Date 2020. 6. 16.
 */
public class DeleteData {
    Connection myConn = MyDriver.getConn();
    Statement myState = MyDriver.getState();
    PreparedStatement myPStmt = MyDriver.getPStmt();
    ResultSet myResSet = MyDriver.getResSet();
    String sql = "";

    public DeleteData(MyDriver md) {
    }

    /**
     * data delete in SONG table
     *
     * @throws SQLException
     * @throws NumberFormatException
     * @throws IOException
     * @Method Name deleteSong
     * @Date 2020. 6. 16.
     * @author iw040
     */
    public void deleteSong() throws SQLException, NumberFormatException, IOException {
        int cnt = 0, i = 0;
        int d_song_id = -1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        myPStmt = myConn.prepareStatement("DELETE from SONG WHERE song_id = ?");
        sql = "SELECT count(*) FROM SONG";
        myResSet = myState.executeQuery(sql);
        if (myResSet.next()) {
            cnt = myResSet.getInt(1);
        }
        int[] d_song_id_arr = new int[cnt];
        sql = "SELECT song_id FROM SONG";
        myResSet = myState.executeQuery(sql);
        i = 0;
        while (myResSet.next()) {
            d_song_id_arr[i] = myResSet.getInt("song_id");
            i++;
        }
        while (true) {
            int flag = 1;
            System.out.print("삭제할 row의 song_id를 입력하세요 : ");
            d_song_id = Integer.parseInt(br.readLine());
            for (int j = 0; j < cnt; j++) {
                if (d_song_id == d_song_id_arr[j]) {
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
        myPStmt.setInt(1, d_song_id);
        myPStmt.executeUpdate();
        System.out.println("삭제가 완료됐습니다.");
        System.out.println();
    }

    /**
     * data delete in ARTIST table
     *
     * @throws SQLException* @throws IOException
     * @Method Name deleteArtist
     * @Date 2020. 6. 16.
     * @author iw040
     */
    public void deleteArtist() throws SQLException, IOException {
        int cnt = 0, i = 0;
        String d_name = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        myPStmt = myConn.prepareStatement("DELETE from ARTIST WHERE name = ?");
        sql = "SELECT count(*) FROM ARTIST";
        myResSet = myState.executeQuery(sql);
        if (myResSet.next()) {
            cnt = myResSet.getInt(1);
        }
        String[] d_name_arr = new String[cnt];
        sql = "SELECT name FROM ARTIST";
        myResSet = myState.executeQuery(sql);
        i = 0;
        while (myResSet.next()) {
            d_name_arr[i] = myResSet.getString("name");
            i++;
        }
        while (true) {
            int flag = 1;
            System.out.print("삭제할 row의 name을 입력하세요 : ");
            d_name = br.readLine();
            for (int j = 0; j < cnt; j++) {
                if (d_name.contentEquals(d_name_arr[j])) {
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
        myPStmt.setString(1, d_name);
        myPStmt.executeUpdate();
        System.out.println("삭제가 완료됐습니다.");
        System.out.println();
    }

    /**
     * data delete in AGENCY table
     *
     * @throws SQLException
     * @throws IOException
     * @Method Name deleteAgency
     * @Date 2020. 6. 16.
     * @author iw040
     */
    public void deleteAgency() throws SQLException, IOException {
        int cnt = 0, i = 0;
        String d_A_name = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        myPStmt = myConn.prepareStatement("DELETE from AGENCY WHERE name = ?");
        sql = "SELECT count(*) FROM AGENCY";
        myResSet = myState.executeQuery(sql);
        if (myResSet.next()) {
            cnt = myResSet.getInt(1);
        }
        String[] d_A_name_arr = new String[cnt];
        sql = "SELECT name FROM AGENCY";
        myResSet = myState.executeQuery(sql);
        i = 0;
        while (myResSet.next()) {
            d_A_name_arr[i] = myResSet.getString("name");
            i++;
        }
        while (true) {
            int flag = 1;
            System.out.print("삭제할 row의 name을 입력하세요 : ");
            d_A_name = br.readLine();
            for (int j = 0; j < cnt; j++) {
                if (d_A_name.contentEquals(d_A_name_arr[j])) {
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
        myPStmt.setString(1, d_A_name);
        myPStmt.executeUpdate();
        System.out.println("삭제가 완료됐습니다.");
        System.out.println();
    }
}