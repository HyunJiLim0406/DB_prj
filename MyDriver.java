import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;

/**
 * main
 *
 * @author iw040
 * @FileName MyDriver.java
 * @Project SampleJDBCConnector
 * @Date 2020. 6. 16.
 */
public class MyDriver {
    private static final MyDriver MyDriver = null;
    static String userID = "dbuser";
    static String userPW = "dbpwd";
    static String dbName = "dbprj";
    static String url = "jdbc:mysql://localhost:3306/" + dbName + "?&serverTimezone=UTC";
    public static Connection myConn = null;
    public static Statement myState = null;
    public static PreparedStatement myPStmt = null;
    public static PreparedStatement myPStmt2 = null;
    public static ResultSet myResSet = null;
    static String sql = "";

    /**
     * return myConn
     *
     * @return
     * @Method Name getConn
     * @Date 2020. 6. 17.
     * @author iw040
     */
    public static Connection getConn() {
        return myConn;
    }

    /**
     * return myState
     *
     * @return
     * @Method Name getState
     * @Date 2020. 6. 17.
     * @author iw040
     */
    public static Statement getState() {
        return myState;
    }

    /**
     * return myPStmt
     *
     * @return
     * @Method Name getPStmt
     * @Date 2020. 6. 17.
     * @author iw040
     */
    public static PreparedStatement getPStmt() {
        return myPStmt;
    }

    /**
     * return myPStmt2
     *
     * @return
     * @Method Name getPStmt2
     * @Date 2020. 6. 17.
     * @author iw040
     */
    public static PreparedStatement getPStmt2() {
        return myPStmt2;
    }

    /**
     * return myResSet
     *
     * @return
     * @Method Name getResSet
     * @Date 2020. 6. 17.
     * @author iw040
     */
    public static ResultSet getResSet() {
        return myResSet;
    }

    /*** data print method. menu input 1
     *
     * @Method Name takeTable
     * @Date 2020. 6. 16.
     * @author iw040
     * @throws SQLException
     */
    static void takeTable() throws SQLException {
        TakeData takeData = new TakeData(MyDriver);
        takeData.takeSong();
        takeData.takeArtist();
        takeData.takeAgency();
    }

    /**
     * data insertion method. menu input 2
     *
     * @throws SQLException
     * @throws NumberFormatException
     * @throws IOException
     * @Method Name insertTable
     * @Date 2020. 6. 16.
     * @author iw040
     */
    static void insertTable() throws SQLException, NumberFormatException, IOException {
        InsertData insertData = new InsertData(MyDriver);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("어떤 테이블에 데이터를 넣으시겠습니까?");
        System.out.println("1. SONG 2. ARTIST 3. AGENCY");
        int insert = Integer.parseInt(br.readLine());
        switch (insert) {
            case 1:
                insertData.insertSong();
                break;
            case 2:
                insertData.insertArtist();
                break;
            case 3:
                insertData.insertAgency();
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }

    /**
     * data deletion method. menu input 3
     *
     * @throws NumberFormatException
     * @throws IOException
     * @throws SQLException
     * @Method Name deleteTable
     * @Date 2020. 6. 16.
     * @author iw040
     */
    static void deleteTable() throws NumberFormatException, IOException, SQLException {
        TakeData takeData = new TakeData(MyDriver);
        DeleteData deleteData = new DeleteData(MyDriver);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("어떤 테이블의 데이터를 삭제하시겠습니까?");
        System.out.println("1. SONG 2. ARTIST 3. AGENCY");
        int delete = Integer.parseInt(br.readLine());
        switch (delete) {
            case 1:
                takeData.takeSong();
                deleteData.deleteSong();
                break;
            case 2:
                takeData.takeArtist();
                deleteData.deleteArtist();
                break;
            case 3:
                takeData.takeAgency();
                deleteData.deleteAgency();
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }

    /**
     * data modification method. menu input 4
     *
     * @throws NumberFormatException
     * @throws IOException
     * @throws SQLException
     * @Method Name modifyTable
     * @Date 2020. 6. 16.
     * @author iw040
     */
    static void modifyTable() throws NumberFormatException, IOException, SQLException {
        TakeData takeData = new TakeData(MyDriver);
        ModifyData modifyData = new ModifyData(MyDriver);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("어떤 테이블에 데이터를 수정하시겠습니까?");
        System.out.println("1. SONG 2. ARTIST 3. AGENCY");
        int modify = Integer.parseInt(br.readLine());
        switch (modify) {
            case 1:
                takeData.takeSong();
                modifyData.modifySong();
                break;
            case 2:
                takeData.takeArtist();
                modifyData.modifyArtist();
                break;
            case 3:
                takeData.takeAgency();
                modifyData.modifyAgency();
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }

    /**
     * data select method. menu input 5
     *
     * @throws NumberFormatException
     * @throws IOException
     * @throws SQLException
     * @Method Name selectTable
     * @Date 2020. 6. 16.
     * @author iw040
     */
    static void selectTable() throws NumberFormatException, IOException, SQLException {
        SelectData selectData = new SelectData(MyDriver);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("어떤 SELECT를 수행하시겠습니까?");
        System.out.println("1. SELECT 2. SELECT using NQ and JOIN 3. SELECT using
                VIEW");
        int select = Integer.parseInt(br.readLine());
        switch (select) {
            case 1:
                selectData.select();
                break;
            case 2:
                selectData.select_NQ_and_JOIN();
                break;
            case 3:
                selectData.select_VIEW();
                break;
            default:
                break;
        }
    }

    /**
     * repetitive input until menu input equal to 0
     *
     * @param args
     * @throws NumberFormatException
     * @throws IOException
     * @Method Name main
     * @Date 2020. 6. 16.
     * @author iw040
     */
    public static void main(String[] args) throws NumberFormatException, IOException {
        int menu = 1;
        try {
/*
* Class.forName("com.mysql.jdbc.Driver"); This is deprecated. The new
driver
* class is `com.mysql.cj.jdbc.Driver'. The driver is automatically
registered
* via the SPI and manual loading of the driver class is generally
unnecessary.
*
*/
            myConn = DriverManager.getConnection(url, userID, userPW);
            System.out.println("... Connected to databse " + dbName + " in MySQL with "
                    + myConn.toString() + " ...");
            myState = myConn.createStatement();
//반복해서 메뉴호출
            while (menu != 0) {
                BufferedReader br = new BufferedReader(new
                        InputStreamReader(System.in));
                System.out.println("무엇을 하시겠습니까?");
                System.out.println("0. 종료 1. 데이터베이스 불러오기 2. 데이터
                        삽입 3. 데이터 삭제 4. 데이터 수정 5. SELECT ");
                menu = Integer.parseInt(br.readLine());
                switch (menu) {
                    case 0:
                        System.out.println("종료합니다.");
                        break;
                    case 1:
                        takeTable();
                        break;
                    case 2:
                        insertTable();
                        break;
                    case 3:
                        deleteTable();
                        break;
                    case 4:
                        modifyTable();
                        break;
                    case 5:
                        selectTable();
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (myResSet != null) {
                try {
                    myResSet.close();
                    System.out.println("... Close ResultSet ...");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (myState != null) {
                try {
                    myState.close();
                    System.out.println("... Close Statement ...");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (myConn != null) {
                try {
                    myConn.close();
                    System.out.println("... Close Connection " +
                            myConn.toString() + " ...");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}