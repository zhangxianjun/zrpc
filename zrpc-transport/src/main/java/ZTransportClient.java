import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/8 08:29
 * Description: 客户端接口.
 */

public interface ZTransportClient {
    void createConnect(ZAddress address);
    InputStream sendData(InputStream data);
    void closeConnect();
}
