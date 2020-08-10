import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/9 09:37
 * Description: 调用远程服务代理类.
 */

@Slf4j
public class RemoteInvoker implements InvocationHandler {

    private Class cls;
    private ZSerialize serialize;
    private TransportSelector selector;

    public RemoteInvoker(Class cls, ZSerialize serialize, TransportSelector selector) {
        this.cls = cls;
        this.serialize = serialize;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        ZRequest request = new ZRequest();
        request.setService(ZService.from(cls, method));
        request.setParameters(args);

        ZResponse response = invokeRemote(request);

        if (response == null || response.getCode() == 0) {
            throw new IllegalStateException("invoke remote fail");
        }

        return response.getData();
    }

    private ZResponse invokeRemote(ZRequest request) {

        ZResponse response = new ZResponse();

        ZTransportClient client = null;
        try {
            client = selector.select();
            byte[] outBytes = serialize.enSerialize(request);

            InputStream resp = client.sendData(new ByteArrayInputStream(outBytes));

            byte[] inByte = IOUtils.readFully(resp, resp.available());

            response = serialize.deSerialize(inByte, ZResponse.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setCode(0);
            response.setMsg("失败");
        } finally {
            if (client != null) {
                selector.release(client);
            }

        }

        return response;
    }
}
