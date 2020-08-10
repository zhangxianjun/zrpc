/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/9 08:45
 * Description: .
 */

public class ServiceInvoker {

    public Object invoke(ZServerInstance instance, ZRequest request) {
        return ZReflection.invoke(instance.getTarget(),
                instance.getMethod(),
                request.getParameters());
    }
}
