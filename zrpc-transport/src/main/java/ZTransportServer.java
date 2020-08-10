/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/8 08:32
 * Description: 服务器端.
 */

public interface ZTransportServer {

    void init(int port, ZRequestHandler requestHandler);
    void start();
    void stop();
}
