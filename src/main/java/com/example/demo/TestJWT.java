package com.example.demo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;

public class TestJWT {
    public static void main(String[] args) {
        String header = "{\"alg\": \"HS256\", \"typ\": \"JWT\"}";
        String payload = "{\"sub\": \"adua\", \"name\": \"giangadua\", \"iat\": 1516239022}";
        String base64UrlEncodedHeader = base64UrlEncode(header);
        String base64UrlEncodedPayload = base64UrlEncode(payload);
        String intermediateString = base64UrlEncodedHeader + "." + base64UrlEncodedPayload;

        String secretKeyString = "bXlzZWNyZXRrZXk";
        byte[] secretKeyBytes = Base64.getEncoder().encode(secretKeyString.getBytes());
        Key secretKey = Keys.hmacShaKeyFor(secretKeyBytes);

        String signatureAlgorithm = "HS256";
        String signature = Jwts.builder()
                .setPayload(intermediateString)
                .signWith(secretKey, SignatureAlgorithm.forName(signatureAlgorithm))
                .compact();

        String jwt = intermediateString + "." + signature;
        System.out.println("JWT: " + jwt);
    }

    public static String base64UrlEncode(String input) {
        byte[] base64Encoded = Base64.getUrlEncoder().encode(input.getBytes());
        return new String(base64Encoded);
    }
}
