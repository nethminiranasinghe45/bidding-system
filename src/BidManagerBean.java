
public class BidManagerBean {
    private final AuctionStateSingleton auctionState = AuctionStateSingleton.getInstance();

    public boolean placeBid(String item, double bidAmount) {
        double currentHighest = auctionState.getHighestBid(item);
        if (bidAmount > currentHighest) {
            auctionState.updateHighestBid(item, bidAmount);
            return true;
        }
        return false;
    }
}
