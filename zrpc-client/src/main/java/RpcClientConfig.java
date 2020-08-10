import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/9 09:25
 * Description: .
 */

@Data
public class RpcClientConfig {
    private Class<? extends ZTransportClient> transportClient = ZHttpClient.class;

    private Class<? extends ZSerialize> serialize = ZJsonSerialize.class;

    private Class<? extends TransportSelector> transportSelector = RandomTransportSelector.class;

    private int connectCount = 1;

    private List<ZAddress> servers = Arrays.asList(new ZAddress("127.0.0.1", 3000));
}
