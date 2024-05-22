package uz.pdp.online.onlinepayment.common.jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uz.pdp.online.onlinepayment.jwt.JwtProvider;

class JwtProviderTest {

//    @MockBean
    private JwtProvider jwtProvider;

    @BeforeEach
    void setUp() {
        jwtProvider = Mockito.mock(JwtProvider.class);
}

    @Test
    void generateToken() {
    String token = jwtProvider.generateToken("Jayxunbey", true);
        System.out.println("token = " + token);
    }

    @Test
    void testGenerateToken() {
    }
}