
import java.util.ArrayList;
import java.util.List;

public class AuctionManagerBean {
    public List<String> getAuctionItems() {
        List<String> items = new ArrayList<>();
        items.add("Smart Watch");
        items.add("Laptop");
        items.add("Mobile Phone");
        items.add("Headphones");
        items.add("Tablet");
        return items;
    }
}
