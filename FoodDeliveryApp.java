package AbstractClass;

abstract class FoodOrder {
    abstract double calculateTotal(); // Varies per order type

    void generateBill() { // Common for all
        System.out.println("----- BILL -----");
        System.out.println("Total Amount: $" + calculateTotal());
    }
}

class RestaurantOrder extends FoodOrder {
    double restaurantBill;

    RestaurantOrder(double bill) {
        this.restaurantBill = bill;
    }

    @Override
    double calculateTotal() {
        // e.g., add 10% restaurant service charge
        return restaurantBill + restaurantBill * 0.10;
    }
}

class CloudKitchenOrder extends FoodOrder {
    double cloudKitchenBill;

    CloudKitchenOrder(double bill) {
        this.cloudKitchenBill = bill;
    }

    @Override
    double calculateTotal() {
        // e.g., add flat delivery charge
        return cloudKitchenBill + 5;
    }
}

public class FoodDeliveryApp {
    public static void main(String[] args) {
        FoodOrder rOrder = new RestaurantOrder(100);
        rOrder.generateBill();

        FoodOrder cOrder = new CloudKitchenOrder(80);
        cOrder.generateBill();
    }
}