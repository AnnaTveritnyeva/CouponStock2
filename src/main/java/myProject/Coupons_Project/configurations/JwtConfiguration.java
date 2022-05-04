package myProject.Coupons_Project.configurations;

import io.jsonwebtoken.SignatureAlgorithm;
import myProject.Coupons_Project.jwt.JWT;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Configuration
public class JwtConfiguration {
    private final String headerName = "Authorization";
    private final String prefix = "Bearer ";
    private final String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
    private final String encodedSecretKey = "this+is+my+key+and+it+must+be+at+least+256+bit+long";
    private final Key decodedSecretKey = new SecretKeySpec
            (Base64.getDecoder().decode(this.encodedSecretKey),
                    this.signatureAlgorithm);

    @Bean
    public JWT jwt() {
        return JWT.builder()
                .signatureAlgorithm(this.signatureAlgorithm)
                .headerName(this.headerName)
                .prefix(this.prefix)
                .encodedSecretKey(this.encodedSecretKey)
                .decodedSecretKey(this.decodedSecretKey)
                .build();
    }
}
