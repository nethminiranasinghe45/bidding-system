
import java.util.HashMap;
import java.util.Map;

public class AuctionStateSingleton {
    private static AuctionStateSingleton instance;
    private final Map<String, Double> highestBids;

    private AuctionStateSingleton() {
        highestBids = new HashMap<>();
    }

    public static synchronized AuctionStateSingleton getInstance() {
        if (instance == null) {
            instance = new AuctionStateSingleton();
        }
        return instance;
    }

    public double getHighestBid(String item) {
        return highestBids.getOrDefault(item, 0.0);
    }

    public void updateHighestBid(String item, double bid) {
        highestBids.put(item, bid);
    }
}
