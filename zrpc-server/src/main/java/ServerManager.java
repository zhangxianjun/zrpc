import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/8 09:29
 * Description: .
 */

@Slf4j
public class ServerManager {
    private Map<ZService, ZServerInstance> services;

    public ServerManager() {
        this.services = new ConcurrentHashMap<>();
    }


    /**
     * 把接口类的方法扫描出来
     * @param interfaceCls 接口Class
     * @param bean 具体的实现类
     * @param <T>
     */
    public <T >void register(Class<T> interfaceCls, Object bean) {
        Method[] methods = ZReflection.getPublicMethods(interfaceCls);

        for (Method method : methods) {
            ZServerInstance zsi = new ZServerInstance(bean, method);
            ZService zs = ZService.from(interfaceCls, method);

            services.put(zs, zsi);

            log.info("register service: {} {}", zs.getClazz(), zs.getMethod());
        }
    }

    public ZServerInstance query(ZRequest request) {
        ZService zs = request.getService();
        return services.get(zs);
    }

}
