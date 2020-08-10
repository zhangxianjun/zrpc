import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/9 09:08
 * Description: 选择连接服务.
 */

public interface TransportSelector {

    /**
     *
     * @param addresses
     * @param count client和server建立多少连接
     * @param cls
     */
    void init(List<ZAddress> addresses,
              int count,
              Class<? extends ZTransportClient> cls);

    ZTransportClient select();

    void release(ZTransportClient client);

    void close();
}
