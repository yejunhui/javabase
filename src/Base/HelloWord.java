package Base;

import javax.mail.MessagingException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloWord {
    public static void main(String[] args) throws SQLException {

        System.out.println("测试类");

        //测试邮件
        //SendEmail so = new SendEmail();
        //so.sendEmail("1589082159@qq.com","测试邮件","这是一个测试邮件，抱歉打扰您了！请忽略！");

        //测试数据库
        MyDB m = new MyDB();
        m.myDBInit(8,"user","000000","aiplan");
        ResultSet rs = m.myDB("select * from user;");
        while (rs.next()){

            String user = rs.getNString("user");
            String name = rs.getNString("name");
            String email = rs.getNString("email");
            String ran = rs.getNString("ran");

            System.out.println("++++++++++++++++++++");
            System.out.println("User:"+user);
            System.out.println("++++++++++++++++++++");
            System.out.println("Name:"+name);
            System.out.println("Email:"+email);
            System.out.println("Ran:"+ran);
            System.out.println("++++++++++++++++++++");
            System.out.println();
        }

        m.myDBClose();

    }
}
