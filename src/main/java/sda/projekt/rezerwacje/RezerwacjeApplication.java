package sda.projekt.rezerwacje;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.HashMap;
import java.util.Map;

@EnableAsync
@SpringBootApplication
@PropertySource("classpath:application.properties")
@PropertySource("classpath:application-ext.properties")
public class RezerwacjeApplication {

    @Value("${cloudinary.cloud_name}")
    private String cloudName;

    @Value("${cloudinary.api_key}")
    private String apiKey;

    @Value("${cloudinary.api_secret}")
    private String apiSecret;

    public static void main(String[] args) {

        SpringApplication.run(RezerwacjeApplication.class, args);

    }

    @Bean
    public Cloudinary cloudinaryConfig() {
        Cloudinary cloudinary = null;
        Map config = new HashMap();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }

//    @Bean
//    CommandLineRunner init() {
//        return (args) -> {
//            try {
//                Files.createDirectories(Paths.get("nameOfDirToCreate"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        };
//    }

}


