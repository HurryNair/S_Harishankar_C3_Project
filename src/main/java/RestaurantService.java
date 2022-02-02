import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        // DELETE ABOVE STATEMENT AND WRITE CODE HERE
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(restaurantName)) {
                return restaurant;
            }
        }
        throw new restaurantNotFoundException(restaurantName);
    }

    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public int findOrderValue(Restaurant restaurant, String... pickedItems) {
        int orderValue = 0;
        for (String item : pickedItems) {
            Item pickedItem = findItemByName(restaurant, item);
            orderValue += pickedItem.getPrice();
        }
        return orderValue;
    }

    // Utility function to fetch a specific item obj by its name from the associated
    // restaurant obj
    private Item findItemByName(Restaurant restaurant, String item) {
        List<Item> menuItems = new ArrayList<Item>();
        Item foundItem = null;
        menuItems = restaurant.getMenu();
        for (Item menuItem : menuItems) {
            if (menuItem.getName().equals(item)) {
                foundItem = menuItem;
                break;
            }
        }
        return foundItem;
    }

}