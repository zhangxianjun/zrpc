import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/8 09:20
 * Description: .
 */

@Data
public class ZRpcServerConfig {
    private Class<? extends ZTransportServer> transportClass = ZHttpServer.class;
    private Class<? extends ZSerialize> serialize = ZJsonSerialize.class;

    private int port = 3000;

}
