import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/5 14:07
 * Description: 传输协议的端点.
 */

@Data
public class ZAddress {
    private String host;
    private int port;


    public ZAddress(String host, int port) {
        this.host = host;
        this.port = port;
    }
}
