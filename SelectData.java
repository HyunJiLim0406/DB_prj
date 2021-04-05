import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;

/**
 * class that implement select in data
 *
 * @author iw040
 * @FileName SelectData.java
 * @Project SampleJDBCConnector
 * @Date 2020. 6. 16.
 */
public class SelectData {
    Connection myConn = MyDriver.getConn();
    Statement myState = MyDriver.getState();
    PreparedStatement myPStmt = MyDriver.getPStmt();
    ResultSet myResSet = MyDriver.getResSet();
    String sql = "";

    public SelectData(MyDriver md) {
    }

    /**
     * select from one table
     *
     * @throws IOException
     * @throws SQLException
     * @Method Name select
     * @Date 2020. 6. 16.
     * @author iw040
     */
    public void select() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("특정 년도 이후에 나온 노래와 그 가수를 출력합니다.");
        System.out.print("년도를 입력해주세요 : ");
        int year = Integer.parseInt(br.readLine());
        myPStmt = myConn.prepareStatement("SELECT title, artist FROM SONG WHERE
                released_year > ? ");
                myPStmt.setInt(1, year);
        print_Result();
    }

    /**
     * select from two tables using nested subquery and join
     *
     * @throws IOException
     * @throws SQLException
     * @Method Name select_NQ_and_JOIN
     * @Date 2020. 6. 16.
     * @author iw040
     */
    public void select_NQ_and_JOIN() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("특정 지역에 있는 소속사에 소속된 가수가 발매한 노래와 그 가수를
                출력합니다.");
                System.out.print("지역을 입력해주세요 : ");
        String street = br.readLine();
        myPStmt = myConn.prepareStatement("SELECT title, artist FROM SONG, ARTIST WHERE
                artist = name and"
                        + "agency in(SELECT name FROM AGENCY WHERE street = ?)");
        myPStmt.setString(1, street);
        print_Result();
    }

    /**
     * select from two table using view
     *
     * @throws IOException
     * @throws SQLException
     * @Method Name select_VIEW
     * @Date 2020. 6. 16.
     * @author iw040
     */
    public void select_VIEW() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("특정 소속사의 2015년 이전 데뷔한 가수가 2015년 이후에 발매한 노래와 그
                가수를 출력합니다");
                System.out.print("소속사를 입력해주세요 : ");
        String agency = br.readLine();
        myPStmt = myConn.prepareStatement("SELECT title, artist FROM debut_released_2015
                WHERE"
                        + "artist in (SELECT name FROM ARTIST WHERE agency = ?)");
        myPStmt.setString(1, agency);
        print_Result();
    }

    /**
     * print result data
     *
     * @throws SQLException
     * @Method Name print_Result
     * @Date 2020. 6. 16.
     * @author iw040
     */
    void print_Result() throws SQLException {
        myResSet = myPStmt.executeQuery();
        if (!myResSet.next()) {
            System.out.println("해당하는 결과가 없습니다.");
            System.out.println();
        } else {
            System.out.println("-----------------------------------------------------
                    ");
                    System.out.println(String.format("title %21s | artist %16s", "", ""));
            System.out.println("-----------------------------------------------------
                    ");
                    String title = myResSet.getString("title");
            String artist = myResSet.getString("artist");
            System.out.println(String.format("title: %20s | artist: %15s", title,
                    artist));
            while (myResSet.next()) {
                title = myResSet.getString("title");
                artist = myResSet.getString("artist");
                System.out.println(String.format("title: %20s | artist: %15s",
                        title, artist));
            }
            System.out.println("-----------------------------------------------------
                    ");
                    System.out.println();
        }
    }
}