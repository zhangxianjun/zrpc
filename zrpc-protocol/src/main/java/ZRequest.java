import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/5 14:04
 * Description: ZRPC请求.
 */

@Data
public class ZRequest {
    private ZService service;
    private Object[] parameters;

}
