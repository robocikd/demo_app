package sda.projekt.rezerwacje;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class PasswordEncoder {
    @Test
    public void encodePasswordAdmin(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPass = bCryptPasswordEncoder.encode("admin");
        System.out.println(encryptedPass);
    }

    @Test
    public void encodePasswordUser(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPass = bCryptPasswordEncoder.encode("user");
        System.out.println(encryptedPass);
    }
}
