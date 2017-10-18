/**
 * Created by MaheshKayara on 10/13/2017.
 */
 
// Class representing a ride.
public class Ride {
    String start = null;
    String end = null;
    String bike_basket_items = null;
	
    Ride(String startTime, String endTime, String basketItems){
        start = startTime;
        end = endTime;
        bike_basket_items = basketItems;
    }
}