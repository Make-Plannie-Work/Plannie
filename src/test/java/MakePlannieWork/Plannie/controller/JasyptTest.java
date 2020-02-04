package MakePlannieWork.Plannie.controller;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JasyptTest {
    @Autowired
    StringEncryptor jasyptStringEncryptor;

    @Value("${plannie.sleutel}")
    private String sleutel;

    @Test
    public void encryptieMetJasypt() {

        // Stap 1
        // Geef deze variabele een waarde mee die encrypted moet worden.
        String password = "Sleutel";

        // Stap 2
        // Run deze test, 'encryptieMetJasypt'.
        String encryptedPassword = jasyptStringEncryptor.encrypt(password);

        // Stap 3
        System.out.println("Kopieer de onderstaande regel vanuit de terminal.");
        System.out.println(encryptedPassword);

        // Stap 4
        // Haal de opgegeven waarde weer weg, voordat je commtit.
    }

}
