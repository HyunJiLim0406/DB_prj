import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * class that implement retrieve and print table data
 *
 * @author iw040
 * @FileName TakeData.java
 * @Project SampleJDBCConnector
 * @Date 2020. 6. 16.
 */
public class TakeData {
    Connection myConn = MyDriver.getConn();
    Statement myState = MyDriver.getState();
    PreparedStatement myPStmt = MyDriver.getPStmt();
    ResultSet myResSet = MyDriver.getResSet();
    String sql = "";

    public TakeData(MyDriver md) {
    }

    /**
     * data print in SONG table
     *
     * @throws SQLException
     * @Method Name takeSong
     * @Date 2020. 6. 16.
     * @author iw040
     */
    public void takeSong() throws SQLException {
        sql = "SELECT * FROM SONG";
        myResSet = myState.executeQuery(sql);
        int song_id = -1;
        String title = "";
        String artist = "";
        int released_year = -1;
        System.out.println(String.format("%93s", "").replace(' ', '-'));
        System.out.println(String.format("song_id %6s | title %21s | artist %16s |
                released_year % 6s", "", "", "", ""));
                System.out.println(String.format("%93s", "").replace(' ', '-'));
        while (myResSet.next()) {
            song_id = myResSet.getInt("song_id");
            title = myResSet.getString("title");
            artist = myResSet.getString("artist");
            released_year = myResSet.getInt("released_year");
            System.out.println(String.format("song_id: %5d | title: %20s | artist: %15s
                    | released_year: %5d ", song_id,
            title, artist, released_year));
        }
        System.out.println(String.format("%93s", "").replace(' ', '-'));
        System.out.println();
    }

    /**
     * data print in ARTIST table
     *
     * @throws SQLException
     * @Method Name takeArtist
     * @Date 2020. 6. 16.
     * @author iw040
     */
    public void takeArtist() throws SQLException {
        sql = "SELECT * FROM ARTIST";
        myResSet = myState.executeQuery(sql);
        String name = "";
        int member_num = -1;
        int debut_year = -1;
        String agency = "";
        System.out.println(String.format("%87s", "").replace(' ', '-'));
        System.out.println(String.format("name %16s | member_num %6s | debut_year %6s |
                agency % 16s", "", "", "", ""));
                System.out.println(String.format("%87s", "").replace(' ', '-'));
        while (myResSet.next()) {
            name = myResSet.getString("name");
            member_num = myResSet.getInt("member_num");
            debut_year = myResSet.getInt("debut_year");
            agency = myResSet.getString("agency");
            System.out.println(String.format("name: %15s | member_num: %5d |
                    debut_year: %5d | agency: %15 s ", name,
            member_num, debut_year, agency));
        }
        System.out.println(String.format("%87s", "").replace(' ', '-'));
        System.out.println();
    }

    /**
     * data print in AGENCY table
     *
     * @throws SQLException
     * @Method Name takeAgency
     * @Date 2020. 6. 16.
     * @author iw040
     */
    public void takeAgency() throws SQLException {
        sql = "SELECT * FROM AGENCY";
        myResSet = myState.executeQuery(sql);
        String A_name = "";
        String street = "";
        int artists_num = -1;
        System.out.println(String.format("%63s", "").replace(' ', '-'));
        System.out.println(String.format("name %16s | street %11s | artists_num %6s", "",
                "", ""));
        System.out.println(String.format("%63s", "").replace(' ', '-'));
        while (myResSet.next()) {
            A_name = myResSet.getString("name");
            street = myResSet.getString("street");
            artists_num = myResSet.getInt("artists_num");
            System.out.println(
                    String.format("name: %15s | street: %10s |
                            artists_num: %5d ", A_name, street, artists_num));
        }
        System.out.println(String.format("%63s", "").replace(' ', '-'));
        System.out.println();
    }
}