import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/9 08:47
 * Description: .
 */

@Slf4j
public class RpcServer {
    private ZRpcServerConfig config;
    private ZTransportServer net;
    private ZSerialize serialize;
    private ServerManager serverManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer(ZRpcServerConfig config) {
        this.config = config;
        this.net = ZReflection.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);

        this.serialize = ZReflection.newInstance(config.getSerialize());

        this.serverManager = new ServerManager();

        this.serviceInvoker = new ServiceInvoker();
    }

    public <T >void register(Class<T> interfaceCls, Object bean) {
        serverManager.register(interfaceCls, bean);
    }

    public void start() {
        this.net.start();
    }

    public void stop() {
        this.net.stop();
    }

    private ZRequestHandler handler = new ZRequestHandler() {
        @Override
        public void request(InputStream input, OutputStream output) {
            ZResponse response = new ZResponse();

            //
            try {
                byte[] inBytes = IOUtils.readFully(input, input.available());

                ZRequest request = serialize.deSerialize(inBytes, ZRequest.class);

                log.info("request {}", request);

                ZServerInstance zsi = serverManager.query(request);

                Object rep = serviceInvoker.invoke(zsi, request);

                response.setData(rep);

            } catch (IOException e) {
                log.warn(e.getMessage(), e);
                response.setCode(0);
                response.setMsg("rpc server error : " + e.getClass().getName() + " message : " + e.getMessage());
            } finally {
                byte[] outBytes = serialize.enSerialize(response);
                try {
                    output.write(outBytes);

                    log.info("finish");

                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    };
}
