import lombok.Data;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/6 17:43
 * Description: .
 */

@Data
public class ZService {
    private String clazz;
    private String method;
    private String returnType;
    private String[] parameterTypes;

    public static ZService from(Class cls, Method mtd) {
        ZService zs = new ZService();
        zs.setClazz(cls.getName());
        zs.setMethod(mtd.getName());
        zs.setReturnType(mtd.getReturnType().getName());

        // 获取参数类型
        Class[] ptc = mtd.getParameterTypes();

        String[] ptn = new String[ptc.length];

        for (int i = 0; i < ptc.length; i++) {
            ptn[i] = ptc[i].getName();
        }

        zs.setParameterTypes(ptn);

        return zs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true;}
        if (o == null || getClass() != o.getClass()) { return false;}
        ZService zService = (ZService) o;
        return Objects.equals(clazz, zService.clazz) &&
                Objects.equals(method, zService.method) &&
                Objects.equals(returnType, zService.returnType) &&
                Arrays.equals(parameterTypes, zService.parameterTypes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(clazz, method, returnType);
        result = 31 * result + Arrays.hashCode(parameterTypes);
        return result;
    }
}
