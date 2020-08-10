import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/8 08:34
 * Description: 处理网络请求.
 */

public interface ZRequestHandler {
    void request(InputStream input, OutputStream output);
}
