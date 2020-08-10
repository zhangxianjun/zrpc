import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zxj
 * @date: 2020/8/9 09:16
 * Description: .
 */

@Slf4j
public class RandomTransportSelector implements TransportSelector {

    private List<ZTransportClient> clients;

    public RandomTransportSelector() {
        this.clients = new ArrayList<>();
    }

    @Override
    public synchronized void init(List<ZAddress> addresses,
                     int count,
                     Class<? extends ZTransportClient> cls) {
        count = Math.max(count, 1);

        for (ZAddress address : addresses) {
            for (int i = 0; i < count; i++) {
                ZTransportClient client = ZReflection.newInstance(ZHttpClient.class);
                client.createConnect(address);
                clients.add(client);
            }
            log.info("connect server : {}", address);
        }
    }

    @Override
    public synchronized ZTransportClient select() {
        int i = new Random().nextInt(clients.size());

        return clients.remove(i);
    }

    @Override
    public synchronized void release(ZTransportClient client) {
        clients.add(client);

    }

    @Override
    public synchronized void close() {
        for (ZTransportClient client : clients) {
            client.closeConnect();
        }
        clients.clear();
    }
}
