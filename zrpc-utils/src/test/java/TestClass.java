import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/8 08:02
 * Description: .
 */

public class TestClass {
//    private String age;
//    private String name;

    private String a() {
        return "private";
    }

    public String b() {
        return "public";
    }
}
