import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by MaheshKayara on 10/14/2017.
 */

// Class representing a "basket", which is a collection of items.
public class Basket {
    private HashMap mHashMap;

    Basket() {
        mHashMap = new HashMap();
    }

    Basket(String basket_items) {
        mHashMap = processBasketItems(basket_items);
    }

    void updateCurrentBasket(Basket tempBasket, boolean add) {
        Iterator<Map.Entry<String, Integer>> iterator = tempBasket.mHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (add){
                addToCurrentBasket(entry);
            }else{
                removeFromCurrentBasket(entry);
            }
        }
    }

    private void addToCurrentBasket(Map.Entry entry){
        String key = (String)entry.getKey();
        if (mHashMap.containsKey(key)) {
            int oldValue = (int)mHashMap.get(key);
            mHashMap.replace(key,(int)entry.getValue() + oldValue);
        } else {
            mHashMap.put(key,entry.getValue());
        }
    }

    private void removeFromCurrentBasket(Map.Entry entry){
        String key = (String)entry.getKey();
        if (mHashMap.containsKey(key)) {
            int oldValue = (int)mHashMap.get(key);
            int value = oldValue - (int)entry.getValue();
            if (value > 0)
                mHashMap.replace(key, value);
            else
                mHashMap.remove(key);
        }
    }

    // Parses the string representing the items carried in a basket
    // and creates a hash map representation of it.
    private HashMap processBasketItems(String basket_items) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        String[] items = basket_items.split(",");
        for (String basketItem : items) {
            String[] parts = basketItem.split(" ");
            hashMap.put(parts[1], Integer.parseInt(parts[0]));
        }
        return hashMap;
    }

    boolean isEmpty() {
        return mHashMap.isEmpty();
    }

    //Method to build a string representation of basket items.
    String getStringRepresentation() {
        return mHashMap.toString();
    }
}