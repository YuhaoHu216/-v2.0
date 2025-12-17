package hu.utils;
import org.springframework.util.DigestUtils;
import java.nio.charset.StandardCharsets;

public class HashUtil {

    public static String md5(String text) {
        return DigestUtils.md5DigestAsHex(
                text.getBytes(StandardCharsets.UTF_8)
        );
    }
}
