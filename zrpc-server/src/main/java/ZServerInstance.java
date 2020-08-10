import lombok.Data;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/8 09:28
 * Description: .
 */

@Data
public class ZServerInstance {
    private Object target;
    private Method method;

    public ZServerInstance(Object target, Method method) {
        this.target = target;
        this.method = method;
    }
}
