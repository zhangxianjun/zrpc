import junit.framework.TestCase;

import java.lang.reflect.Method;

public class ZReflectionTest extends TestCase {

    public void testNewInstance() {
        TestClass tc = ZReflection.newInstance(TestClass.class);
        assertNotNull(tc);
    }

    public void testGetPublicMethods() {
        Method[] methods = ZReflection.getPublicMethods(TestClass.class);
        assertTrue(methods.length > 0);
    }

    public void testInvoke() {
        Method[] methods = ZReflection.getPublicMethods(TestClass.class);
        Method b = methods[0];

        TestClass tc = new TestClass();

        Object o = ZReflection.invoke(tc, b);

        assertEquals("public", o);
    }
}