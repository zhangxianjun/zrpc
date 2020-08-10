import com.alibaba.fastjson.JSON;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/8 08:15
 * Description: .
 */

public class ZJsonSerialize implements ZSerialize {
    @Override
    public byte[] enSerialize(Object obj) {

        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deSerialize(byte[] bytes, Class<T> cls) {
        return JSON.parseObject(bytes, cls);
    }
}
