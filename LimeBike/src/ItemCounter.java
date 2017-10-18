import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by MaheshKayara on 10/9/2017.
 */

public class ItemCounter {
    //Variable to store rideEvents
    private ArrayList<RideEvent> mEventList = new ArrayList<>();

    //Process the ride object which contains metadata
    public void process_ride(Ride ride) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            Basket basket = new Basket(ride.bike_basket_items);
            //Create a ride start and end events for each ride
            RideEvent rideStartEvent = new RideEvent(simpleDateFormat.parse(ride.start), true, basket);
            RideEvent rideEndEvent = new RideEvent(simpleDateFormat.parse(ride.end), false, basket);
            mEventList.add(rideStartEvent);
            mEventList.add(rideEndEvent);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //Prints a summary of the numbers of each type of item in transit during any given time interval.
    public void print_items_per_interval() {
        // Create a basket object to hold all the items being transported during
        // a time interval.
        Basket currentBasket = new Basket();
        //Sort the ride events in the increasing order of time
        Collections.sort(mEventList, new Comparator<RideEvent>() {
            @Override
            public int compare(RideEvent event1, RideEvent event2) {
                return (event1.mTime.compareTo(event2.mTime));
            }
        });
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        // Walk through the sorted events determining the items carried during each
        // interval and printing.
        Date lastEventTime = null; //Store the last processed event's time
        for (RideEvent rideEvent : mEventList) {
            Date currentTime = rideEvent.mTime; // Stores current processing time
            //Check if the interval is valid for printing
            if (!currentBasket.isEmpty() && !lastEventTime.equals(null) && !currentTime.equals(lastEventTime)) {
                //Write the output to file
                System.out.println(sdf.format(lastEventTime) + "-" + sdf.format(currentTime) + " -> " + currentBasket.getStringRepresentation());
            }
            //Update the current basket.
            // If rideEvent.mIsStart is true, then, it adds the items of rideEvent.mBasket to currentBasket,
            // else, it removes them from currentBasket
            currentBasket.updateCurrentBasket(rideEvent.mBasket, rideEvent.mIsStart);
            lastEventTime = currentTime;
        }
    }

    public static void main(String[] args) {
        ItemCounter itemCounter = new ItemCounter();
        try {
            //File Location with ride data
            String fileName = "input.txt";
            BufferedReader b = new BufferedReader(new FileReader(fileName));
            String logDataLineByLine;
            while ((logDataLineByLine = b.readLine()) != null) {
                String[] fileContent = logDataLineByLine.split("  ");
                String[] parts = fileContent[0].split("-");
                //create ride with details.
                Ride ride = new Ride(parts[0], parts[1], fileContent[1]);
                itemCounter.process_ride(ride);
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        itemCounter.print_items_per_interval();
    }
}