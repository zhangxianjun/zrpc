/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/9 09:54
 * Description: .
 */

public class Client {

    public static void main(String[] args) {
        RpcClient client = new RpcClient();

        CalcService service = (CalcService) client.getProxy(CalcService.class);

        int a = service.add(10,  2);

        System.out.println(a);
    }
}
