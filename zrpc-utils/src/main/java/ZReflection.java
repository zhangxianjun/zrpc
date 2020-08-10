import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/6 17:48
 * Description: 反射工具类.
 */

public class ZReflection {

    /**
     * 根据clazz创建对象
     * @param clazz 要创建对象的类
     * @param <T>   对象类型
     * @return
     */
    public static <T> T newInstance(Class clazz) {
        try {
            return (T) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw  new IllegalStateException(e);
        }
    }

    /**
     * 获取公开方法
     * @param clazz
     * @return
     */
    public static Method[] getPublicMethods(Class clazz) {
        // 返回当前类的所有方法
        Method[] methods = clazz.getDeclaredMethods();

        List<Method> publicMethods = new ArrayList<>();

        for (int i = 0; i < methods.length; i++) {
            Method m = methods[i];
            // 判断方法是否为公开方法
            if (Modifier.isPublic(m.getModifiers())) {
                publicMethods.add(m);
            }
        }

        return publicMethods.toArray(new Method[0]);
    }

    /**
     * 调用指定对象的指定方法
     * @param obj 被调用的对象
     * @param method 被调用的方法
     * @param args
     * @return
     */
    public static Object invoke(Object obj, Method method, Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (Exception e) {
            throw  new IllegalStateException(e);
        }
    }
}
