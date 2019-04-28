package Engine.serverInfo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class serverInfo
{
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    public static  final String DB_URL="jdbc:mysql://localhost/StupidLibrary";///StupidLibrary";
    public static  String USER = "root";
    public static  String PASS = "123456";
    private static String dbUseUnicode = "TRUE";
    private static String dbEncoding = "UTF8";
    private static String dbUseSSL = "TRUE";

    public static void init(String filePath)
    {
        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
            USER = in.readLine();
            PASS =in.readLine();
            System.out.println("User : " + USER);
            System.out.println("Pwd : "+PASS);
        }catch(Exception e)
        {
            System.out.println("ERROR : Failed to get DB account");
            System.exit(0);
        }
    }
    public static String getUrl()
    {
            return DB_URL+
            "?user=" + USER +
            "&password=" + PASS +
            "&useUnicode=" + dbUseUnicode +
            "&characterEncoding=" + dbEncoding +
            "&useSSL=" + dbUseSSL+"&serverTimezone=GMT%2B8";
    }
}