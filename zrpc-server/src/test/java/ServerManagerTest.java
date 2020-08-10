import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Request;

import java.lang.reflect.Method;

public class ServerManagerTest extends TestCase {

    ServerManager sm;

    @Before
    public void init() {
        sm = new ServerManager();
    }

    @Test
    public void testRegister() {
        ServerManager sm = new ServerManager();

        TestInterface bean = new TestClass();
        sm.register(TestInterface.class, bean);
    }

    public void testQuery() {

        ServerManager sm = new ServerManager();
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class, bean);


        Method method = ZReflection.getPublicMethods(TestInterface.class)[0];

        ZService zs = ZService.from(TestInterface.class, method);

        ZRequest request = new ZRequest();

        request.setService(zs);

        ZServerInstance zsi = sm.query(request);

        assertNotNull(zsi);

    }
}