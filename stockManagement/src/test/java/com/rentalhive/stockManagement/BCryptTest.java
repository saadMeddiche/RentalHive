package com.rentalhive.stockManagement;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptTest {

        public static final Logger log = LoggerFactory.getLogger(BCryptTest.class);

        public static void main(String[] args) {
                testHashWithSalt();
        }

        @Test
        public static void testHashWithSalt() {

                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

                String hashedPass1 = bCryptPasswordEncoder.encode("password");

                String hashedPass2 = bCryptPasswordEncoder.encode("password");

                System.out.println("Hashed Password #1: " + hashedPass1);
                System.out.println("Hashed Password #2: " + hashedPass2);

                System.out.println("Result of matching: " + bCryptPasswordEncoder.matches("password", hashedPass1));
                System.out.println("Result of matching: " + bCryptPasswordEncoder.matches("password", hashedPass2));

                System.out.println("Result of matching: " + bCryptPasswordEncoder.matches("password",
                                "$2a$10$mP.DNH3LIy/PeIM84y1nhuq76w98b8ANcxH3bzxPjiXHUdSl3XFri"));

                System.out.println("Result of matching: " + bCryptPasswordEncoder.matches("password",
                                "$2a$10$mP.DNH3LIy/PeIM84y1nhuq77w98b8ANcxH3bzxPjiXHUdSl3XFri"));

                System.out.println("Result of matching: " + bCryptPasswordEncoder.matches("paSSword",
                                "$2a$10$mP.DNH3LIy/PeIM84y1nhuq76w98b8ANcxH3bzxPjiXHUdSl3XFri"));

        }

}