import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/5 14:05
 * Description: ZRPC响应.
 */

@Data
public class ZResponse {

    private int code = 1;

    private String msg = "成功";

    private Object data;

}
