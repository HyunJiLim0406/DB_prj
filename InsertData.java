import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;

/**
 * class that implement data insertion. avoid insertion of same pk, give list of fk.
 *
 * @author iw040
 * @FileName InsertData.java
 * @Project SampleJDBCConnector
 * @Date 2020. 6. 16.
 */
public class InsertData {
    Connection myConn = MyDriver.getConn();
    Statement myState = MyDriver.getState();
    PreparedStatement myPStmt = MyDriver.getPStmt();
    ResultSet myResSet = MyDriver.getResSet();
    String sql = "";

    public InsertData(MyDriver md) {
    }

    /**
     * data insert in SONG table
     *
     * @throws SQLException
     * @throws NumberFormatException
     * @throws IOException
     * @Method Name insertSong
     * @Date 2020. 6. 16.
     * @author iw040
     */
    public void insertSong() throws SQLException, NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0, i = 0;
        int i_song_id = -1;
        String i_artist = "";
        myPStmt = myConn.prepareStatement("insert into SONG values(?,?,?,?)");
        sql = "SELECT count(*) FROM SONG";
        myResSet = myState.executeQuery(sql);
        if (myResSet.next()) {
            cnt = myResSet.getInt(1);
        }
        int[] i_song_id_arr = new int[cnt];
        sql = "SELECT song_id FROM SONG";
        myResSet = myState.executeQuery(sql);
        i = 0;
        while (myResSet.next()) {
            i_song_id_arr[i] = myResSet.getInt("song_id");
            i++;
        }
        while (true) {
            int flag = 1;
            System.out.print("song_id를 입력하세요(int) : ");
            i_song_id = Integer.parseInt(br.readLine());
            for (int j = 0; j < cnt; j++) {
                if (i_song_id == i_song_id_arr[j]) {
                    System.out.println("pk는 중복될 수 없습니다. 다시
                            입력하세요");
                            flag = 0;
                    break;
                }
            }
            if (flag == 1)
                break;
        }
        System.out.print("title을 입력하세요(String) : ");
        String i_title = br.readLine();
        sql = "SELECT count(*) FROM ARTIST";
        myResSet = myState.executeQuery(sql);
        if (myResSet.next()) {
            cnt = myResSet.getInt(1);
        }
        String[] i_artist_arr = new String[cnt];
        sql = "SELECT name FROM ARTIST";
        myResSet = myState.executeQuery(sql);
        i = 0;
        System.out.println("---------------");
        while (myResSet.next()) {
            i_artist_arr[i] = myResSet.getString("name");
            System.out.println(i_artist_arr[i]);
            i++;
        }
        System.out.println("---------------");
        while (true) {
            int flag = 1;
            System.out.print("위 목록에서 artist를 입력하세요(String) : ");
            i_artist = br.readLine();
            for (int j = 0; j < cnt; j++) {
                if (i_artist.contentEquals(i_artist_arr[j])) {
                    flag = 1;
                    break;
                } else
                    flag = 0;
            }
            if (flag == 0) {
                System.out.println("일치하는 가수가 없습니다.");
            } else
                break;
        }
        System.out.print("released_year를 입력하세요(int) : ");
        int i_released_year = Integer.parseInt(br.readLine());
        myPStmt.setInt(1, i_song_id);
        myPStmt.setString(2, i_title);
        myPStmt.setString(3, i_artist);
        myPStmt.setInt(4, i_released_year);
        myPStmt.executeUpdate();
        System.out.println("입력이 완료됐습니다.");
        System.out.println();
    }

    /**
     * data insert in ARTIST table
     *
     * @throws NumberFormatException
     * @throws IOException
     * @throws SQLException
     * @Method Name insertArtist
     * @Date 2020. 6. 16.
     * @author iw040
     */
    public void insertArtist() throws NumberFormatException, IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0, i = 0;
        String i_name = "";
        String i_agency = "";
        myPStmt = myConn.prepareStatement("insert into ARTIST values(?,?,?,?)");
        sql = "SELECT count(*) FROM ARTIST";
        myResSet = myState.executeQuery(sql);
        if (myResSet.next()) {
            cnt = myResSet.getInt(1);
        }
        String[] i_name_arr = new String[cnt];
        sql = "SELECT name FROM ARTIST";
        myResSet = myState.executeQuery(sql);
        i = 0;
        while (myResSet.next()) {
            i_name_arr[i] = myResSet.getString("name");
            i++;
        }
        while (true) {
            int flag = 1;
            System.out.print("name을 입력하세요(String) : ");
            i_name = br.readLine();
            for (int j = 0; j < cnt; j++) {
                if (i_name.contentEquals(i_name_arr[j])) {
                    System.out.println("pk는 중복될 수 없습니다. 다시
                            입력하세요");flag=0;
                    break;
                }
            }
            if (flag == 1)
                break;
        }
        System.out.print("member_num을 입력하세요(int) : ");
        int i_member_num = Integer.parseInt(br.readLine());
        System.out.print("debut_year를 입력하세요(int) : ");
        int i_debut_year = Integer.parseInt(br.readLine());
        sql = "SELECT count(*) FROM AGENCY";
        myResSet = myState.executeQuery(sql);
        if (myResSet.next()) {
            cnt = myResSet.getInt(1);
        }
        String[] i_agency_arr = new String[cnt];
        sql = "SELECT name FROM AGENCY";
        myResSet = myState.executeQuery(sql);
        i = 0;
        System.out.println("---------------");
        while (myResSet.next()) {
            i_agency_arr[i] = myResSet.getString("name");
            System.out.println(i_agency_arr[i]);
            i++;
        }
        System.out.println("---------------");
        while (true) {
            int flag = 1;
            System.out.print("위 목록에서 agency를 입력하세요(String) : ");
            i_agency = br.readLine();
            for (int j = 0; j < cnt; j++) {
                if (i_agency.contentEquals(i_agency_arr[j])) {
                    flag = 1;
                    break;
                } else
                    flag = 0;
            }
            if (flag == 0) {
                System.out.println("일치하는 소속사가 없습니다.");
            } else
                break;
        }
        myPStmt.setString(1, i_name);
        myPStmt.setInt(2, i_member_num);
        myPStmt.setInt(3, i_debut_year);
        myPStmt.setString(4, i_agency);
        myPStmt.executeUpdate();
        System.out.println("입력이 완료됐습니다.");
        System.out.println();
    }

    /**
     * data insert in AGENCY table
     *
     * @throws SQLException
     * @throws IOException
     * @Method Name insertAgency
     * @Date 2020. 6. 16.
     * @author iw040
     */
    public void insertAgency() throws SQLException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0, i = 0;
        String i_A_name = "";
        myPStmt = myConn.prepareStatement("insert into AGENCY values(?,?,?)");
        sql = "SELECT count(*) FROM AGENCY";
        myResSet = myState.executeQuery(sql);
        if (myResSet.next()) {
            cnt = myResSet.getInt(1);
        }
        String[] i_A_name_arr = new String[cnt];
        sql = "SELECT name FROM AGENCY";
        myResSet = myState.executeQuery(sql);
        i = 0;
        while (myResSet.next()) {
            i_A_name_arr[i] = myResSet.getString("name");
            i++;
        }
        while (true) {
            int flag = 1;
            System.out.print("name을 입력하세요(String) : ");
            i_A_name = br.readLine();
            for (int j = 0; j < cnt; j++) {
                if (i_A_name.contentEquals(i_A_name_arr[j])) {
                    System.out.println("pk는 중복될 수 없습니다. 다시
                            입력하세요");
                            flag = 0;
                    break;
                }
            }
            if (flag == 1)
                break;
        }
        System.out.print("street를 입력하세요(String) : ");
        String i_street = br.readLine();
        System.out.print("artist_num을 입력하세요(int) : ");
        int i_artists_num = Integer.parseInt(br.readLine());
        myPStmt.setString(1, i_A_name);
        myPStmt.setString(2, i_street);
        myPStmt.setInt(3, i_artists_num);
        myPStmt.executeUpdate();
        System.out.println("입력이 완료됐습니다.");
        System.out.println();
    }
}