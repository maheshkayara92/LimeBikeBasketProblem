import java.util.Date;

/**
 * Created by MaheshKayara on 10/13/2017.
 */
 
// Class representing the beginning or end event of a ride.
public class RideEvent{
    Date mTime;
    boolean mIsStart;
    Basket mBasket;
	
    public RideEvent(Date time, boolean isStart, Basket basket){
        mTime = time;
        mIsStart = isStart;
        mBasket = basket;
    }
}