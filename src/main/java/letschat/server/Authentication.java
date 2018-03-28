package letschat.server;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Authentication {
    private static String privateKey = "ahihiconcho";

    public static boolean verifyToken(String payload, String token) {
        if (payload == null || token == null) {
            return false;
        }

        String[] part = token.split("\\.");
        String payloadDecoded = base64Decode(part[0]);
        String payloadSecret = hmacSha256(part[0]);

        return payload.equals(payloadDecoded) && part[1].equals(payloadSecret);
    }

    private static String hmacSha256(String msg) {
        String digest = null;
        try {
            SecretKeySpec key = new SecretKeySpec(privateKey.getBytes("UTF-8"), "hmacSha256");
            Mac mac = Mac.getInstance("hmacSha256");
            mac.init(key);

            byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

            StringBuffer hash = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            digest = hash.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return digest;
    }

    private static String base64Encode(String msg) {
        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(msg.getBytes());
    }

    private static String base64Decode(String msg) {
        byte[] decoded = Base64.getUrlDecoder().decode(msg);
        return new String(decoded);
    }

    public static String generateToken(String payload) {
        String encodedPayload = base64Encode(payload);
        return encodedPayload + "." + hmacSha256(encodedPayload);
    }
}
