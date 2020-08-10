/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/9 09:55
 * Description: .
 */

public class Server {

    public static void main(String[] args) {
        RpcServer server = new RpcServer(new ZRpcServerConfig());
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}
