package Start;

import LoginWindow.LoginWindow;
import KMPSearch.KMPSearch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

public class ProgrammeStart {
    public static Connection         con = null;
    public static PreparedStatement  stmt = null;
    public static boolean            verbose = false;
    public static String userName;
    public static String userID;

    public static void main(String[] args) {
        userID = null;
        userName = null;

        LoginWindow.startGUI();
    }

    public static void openDB(String host, String dbname,
                              String user, String pwd) {
        try {
            //
            Class.forName("org.postgresql.Driver");
        } catch(Exception e) {
            System.err.println("Cannot find the Postgres driver. Check CLASSPATH.");
            System.exit(1);
        }
        String url = "jdbc:postgresql://" + host + "/" + dbname;
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", pwd);
        try {
            con = DriverManager.getConnection(url, props);
            if (verbose) {
                System.out.println("Successfully connected to the database "
                        + dbname + " as " + user);
            }
            con.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println("Database connection failed");
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void closeDB() {
        if (con != null) {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                con.close();
                con = null;
            } catch (Exception e) {
                // Forget about it
            }
        }
    }

    // 生成随机数
    public static int generatingIntegerBounded(int a, int b){

        int min = a;
        int max = b;
        int intBounded = min + ((int) (new Random().nextFloat() * (max - min)));
        return intBounded;
    }

    public static boolean isSqlInjection(String input){
        boolean isInjection = false;
        int repeat = 0;
        repeat = KMPSearch.KMPSearchTimes(input.toLowerCase(), "select");
        if (repeat >=1){
            isInjection = true;
            return isInjection;
        }
        repeat = KMPSearch.KMPSearchTimes(input.toLowerCase(), "update");
        if (repeat >=1){
            isInjection = true;
            return isInjection;
        }
        repeat = KMPSearch.KMPSearchTimes(input.toLowerCase(), "drop");
        if (repeat >=1){
            isInjection = true;
            return isInjection;
        }
        repeat = KMPSearch.KMPSearchTimes(input.toLowerCase(), "create");
        if (repeat >=1){
            isInjection = true;
            return isInjection;
        }
        repeat = KMPSearch.KMPSearchTimes(input.toLowerCase(), "alter");
        if (repeat >=1){
            isInjection = true;
            return isInjection;
        }
        repeat = KMPSearch.KMPSearchTimes(input.toLowerCase(), "delete");
        if (repeat >=1){
            isInjection = true;
            return isInjection;
        }
        repeat = KMPSearch.KMPSearchTimes(input.toLowerCase(), "truncate");
        if (repeat >=1){
            isInjection = true;
            return isInjection;
        }
        repeat = KMPSearch.KMPSearchTimes(input.toLowerCase(), "exec");
        if (repeat >=1){
            isInjection = true;
            return isInjection;
        }
        repeat = KMPSearch.KMPSearchTimes(input.toLowerCase(), "remove");
        if (repeat >=1){
            isInjection = true;
            return isInjection;
        }
        repeat = KMPSearch.KMPSearchTimes(input.toLowerCase(), "modify");
        if (repeat >=1){
            isInjection = true;
            return isInjection;
        }
        repeat = KMPSearch.KMPSearchTimes(input.toLowerCase(), "add");
        if (repeat >=1){
            isInjection = true;
            return isInjection;
        }
        return isInjection;
    }
}
