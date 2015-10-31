import java.util.HashMap;
import java.util.Map;

/**
 * Created by OmarTech on 15-10-31.
 */
public class TestJava {
    public static void main(String[] args) {

        char a = 'a';

        Map<Character, String> map  = new HashMap<>();

        map.put(a, "1");

        String s = map.get(a);
        System.out.println(s);
    }
}
