package Base;

import java.sql.*;

public class MyDB {

    //static  String databaseName = "aiplan";//设置数据库
    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";//设置驱动
    //static String DB_URL = "jdbc:mysql://localhost:3306/"+databaseName;//连接数据库
    //MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //static String DB_URL = "jdbc:mysql://localhost:3306/"+databaseName+"?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static String JDBC_DRIVER = null;
    static String DB_URL = null;

    //数据库用户名和密码
    static  String USER = null;
    static  String PASS = null;

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public void myDBInit(int v,String user,String password,String db){
        if (v >= 8) {
            JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
            DB_URL = "jdbc:mysql://localhost:3306/"+db+"?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";//连接数据库
        }
        else {
            JDBC_DRIVER = "com.mysql.jdbc.Driver";
            DB_URL = "jdbc:mysql://localhost:3306/"+db;//连接数据库
        }
        USER = user;
        PASS = password;

    }

    public ResultSet myDB(String sql){

        try {
            //注册驱动
            Class.forName(JDBC_DRIVER);
            //打开链接
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            //获得游标
            statement = connection.createStatement();
            //获得结果对象
            resultSet = statement.executeQuery(sql);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;
    }

    public void myDBClose(){
        try {
            //关闭连接
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
