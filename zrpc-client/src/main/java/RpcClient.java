import java.lang.reflect.Proxy;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/9 09:31
 * Description: .
 */

public class RpcClient {
    private RpcClientConfig config;
    private ZSerialize serialize;
    private TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config) {
        this.config = config;
        this.serialize = ZReflection.newInstance(config.getSerialize());
        this.selector = ZReflection.newInstance(config.getTransportSelector());

        this.selector.init(this.config.getServers(),
                this.config.getConnectCount(),
                this.config.getTransportClient());
    }

    public <T> Object getProxy(Class<T> cls) {
        return Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{cls},
                new RemoteInvoker(cls, serialize, selector));
    }
}
