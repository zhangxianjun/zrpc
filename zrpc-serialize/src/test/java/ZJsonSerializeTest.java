import junit.framework.TestCase;

public class ZJsonSerializeTest extends TestCase {

    public void testEnSerialize() {
        ZJsonSerialize zjs = new ZJsonSerialize();
        TestClass tc = new TestClass();

        tc.name = "zjs";

        byte[] bytes = zjs.enSerialize(tc);

        assertNotNull(bytes);

    }

    public void testDeSerialize() {
        ZJsonSerialize zjs = new ZJsonSerialize();
        TestClass tc = new TestClass();

        tc.name = "zjs";

        byte[] bytes = zjs.enSerialize(tc);

        TestClass deTc = zjs.deSerialize(bytes, TestClass.class);

        assertEquals(tc.name, deTc.name);

    }
}