package myProject.Coupons_Project.jwt;

import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import myProject.Coupons_Project.beans.Role;
import myProject.Coupons_Project.beans.UserModel;
import org.springframework.stereotype.Service;

import java.security.Key;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JWT {
    private String signatureAlgorithm;
    private String headerName;
    private String prefix;
    private String encodedSecretKey;
    private Key decodedSecretKey;

    /**
     * generates JWT token by User Details
     * @param userModel UserModel
     * @return String of JWT token
     */
    public String generateToken(UserModel userModel) {
        Map<String, Object> claims = new HashMap<>();        //create new hash map for claims
        claims.put("password", userModel.getPassword());  //insert password
        claims.put("role", userModel.getRole().name());  //insert user type (role)
        return createToken(claims, userModel.getEmail()); //send the subject (email)
    }

    /**
     * creates token by the details provided
     * @param claims User's map of details (claim)
     * @param email User's email
     * @return String of JWT token
     */
    private String createToken(Map<String, Object> claims, String email) {
        Instant now = Instant.now();//get current time
        return prefix +
                Jwts.builder()
                        .setClaims(claims)
                        .setSubject(email)
                        .setIssuedAt(Date.from(now))
                        .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                        .signWith(this.decodedSecretKey)
                        .compact();
    }

    /**
     * deletes prefix from JWT token
     * @param token String of JWT token
     * @return String of JWT token without prefix
     */
    private String deletePrefix(String token) {
        return token.replace(prefix, "");
    }

    /**
     * extracts User's claims from JWT token
     * @param token String of JWT token
     * @return User's Claims (Map)
     * @throws ExpiredJwtException if token expired
     */
    private Claims extractAllClaims(String token) throws ExpiredJwtException {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(this.decodedSecretKey)
                .build();
        return jwtParser.parseClaimsJws(deletePrefix(token)).getBody();
    }

    /**
     * extracts User's emil from JWT token
     * @param token String of JWT token
     * @return User's email
     */
    private String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * extracts User's role from JWT token
     * @param token String of JWT token
     * @return User's role
     */
    private Role extractRole(String token) {
        return Role.valueOf(extractAllClaims(token).get("role", String.class));
    }

    /**
     * extracts token expiration Date
     * @param token String of JWT token
     * @return Date of expiration
     */
    private Date extractExpirationDate(String token) {
        return extractAllClaims(token).getExpiration();
    }

    /**
     * extracts User's password from JWT token
     * @param token String of JWT token
     * @return User's password
     */
    private String extractPassword(String token) {
        return extractAllClaims(token).get("password", String.class);
    }

    /**
     * checks if token expired
     * @param token String of JWT token
     * @return boolean
     */
    private boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    /**
     * checks if token is valid and the provided role is the same as the required one
     * @param token String of JWT token
     * @param role User's role
     * @return boolean
     */
    public boolean validateTokenAndRole(String token, Role role) {
        return (!isTokenExpired(token) && extractRole(token).equals(role));
    }

    /**
     * extracts user details from the token and builds identical UserModel
     * @param token String of JWT token
     * @return UserModel
     */
    private UserModel extractUserModel(String token) {
        return UserModel.builder()
                .email(extractEmail(token))
                .password(extractPassword(token))
                .role(extractRole(token))
                .build();
    }

    /**
     * generates new JWT token by the extracted details from the provided token
     * @param token String of JWT token
     * @return String of JWT token
     */
    public String generateUpdatedToken(String token) {
        return generateToken(extractUserModel(token));
    }

    public String getHeaderName() {
        return headerName;
    }
}

