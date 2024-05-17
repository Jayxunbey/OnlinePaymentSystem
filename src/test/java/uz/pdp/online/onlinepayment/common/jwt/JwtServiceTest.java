package uz.pdp.online.onlinepayment.common.jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

//    @MockBean
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = Mockito.mock(JwtService.class);
}

    @Test
    void generateToken() {
    String token = jwtService.generateToken("Jayxunbey", true);
        System.out.println("token = " + token);
    }

    @Test
    void testGenerateToken() {
    }
}