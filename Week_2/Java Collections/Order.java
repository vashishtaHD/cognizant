import java.util.LinkedList;

class Order {
    int orderId;
    String orderDetails;
}

class OrderTracking {
    LinkedList<Order> orders = new LinkedList<>();

    void addOrder(Order order) {
        orders.add(order);
    }

    Order processOrder() {
        return orders.poll();
    }

    void displayOrders() {
        orders.forEach(order -> System.out.println(order.orderId + " " + order.orderDetails));
    }
}

class OrderTrackingTest {
    public static void main(String[] args) {
        OrderTracking tracking = new OrderTracking();
        Order order1 = new Order();
        order1.orderId = 1;
        order1.orderDetails = "Order1";
        tracking.addOrder(order1);
        Order order2 = new Order();
        order2.orderId = 2;
        order2.orderDetails = "Order2";
        tracking.addOrder(order2);
        tracking.displayOrders();
        tracking.processOrder();
        tracking.displayOrders();
    }
}
