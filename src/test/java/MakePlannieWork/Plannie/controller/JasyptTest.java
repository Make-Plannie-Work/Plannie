package MakePlannieWork.Plannie.controller;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.Test;

public class JasyptTest {

    @Test
    public void contextLoads() {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //Salt required for encryption
        textEncryptor.setPassword("1Qaz0oKm");
        //Data to be encrypted (database user name or password)
        String username = textEncryptor.encrypt("root");
        String password = textEncryptor.encrypt("root");
        System.out.println("username:"+username);
        System.out.println("password:"+password);
    }
}
