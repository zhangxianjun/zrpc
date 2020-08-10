/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/8 08:12
 * Description: .
 */

public interface ZSerialize {

    byte[] enSerialize(Object obj);

    <T> T deSerialize(byte[] bytes, Class<T> cls);
}
