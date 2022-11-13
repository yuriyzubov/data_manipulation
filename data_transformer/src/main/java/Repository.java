import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Repository {
    // PostgreConn conn;

    private String url;
    private String user;
    private String password;

    public Connection conn;

    public Repository(String url, String user, String password) {

        this.url = url;
        this.user = user;
        this.password = password;

        this.conn = getConnection(url, user, password);

    }

    public Connection getConnection(String url, String user, String password){
        Connection c = null;
        try {
            //connect to postgresql
            c = DriverManager
                    .getConnection(url, user, password);
            System.out.println("Database opened successfully.");

        }catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return c;
    }

    public void writeIntoTable(UserLogin userLogin, String tableName){

        try {
             String query = "INSERT INTO " + tableName +  " (user_id, device_type," +
                " masked_ip, masked_device_id, locale, app_version) VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, userLogin.getUserId());
            statement.setString(2, userLogin.getDeviceType());
            statement.setString(3, userLogin.getMaskedIp());
            statement.setString(4, userLogin.getMaskedDeviceId());
            statement.setString(5, userLogin.getLocale());
            statement.setInt(6, userLogin.getAppMajorVer());
            statement.executeUpdate();


        } catch (Exception e) {
        e.printStackTrace();
        }
    }

    public void clearTable( String tableName) {

        try {
            String query = "TRUNCATE TABLE " + tableName + " ;" ;

            PreparedStatement statement = conn.prepareStatement(query);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
