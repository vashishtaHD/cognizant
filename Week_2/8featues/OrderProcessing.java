

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface OrderFilter {
    boolean filter(Order order);
}

@FunctionalInterface
interface OrderProcessor {
    void process(Order order);
}

class Order {
    private int orderId;
    private String customerName;
    private double orderAmount;
    private String status;

    public Order(int orderId, String customerName, double orderAmount, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderAmount = orderAmount;
        this.status = status;
    }

    // Getters and setters
    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public double getOrderAmount() { return orderAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", orderAmount=" + orderAmount +
                ", status='" + status + '\'' +
                '}';
    }
}

public class OrderProcessing {
    private List<Order> orders;

    public OrderProcessing() {
        orders = new ArrayList<>();
        // Add sample orders
        orders.add(new Order(1, "Alice", 100.0, "Pending"));
        orders.add(new Order(2, "Bob", 200.0, "Shipped"));
        orders.add(new Order(3, "Charlie", 150.0, "Pending"));
        orders.add(new Order(4, "David", 300.0, "Delivered"));
    }

    public void filterOrders(OrderFilter filter) {
        for (Order order : orders) {
            if (filter.filter(order)) {
                System.out.println("Filtered order: " + order);
            }
        }
    }

    public void processOrders(OrderProcessor processor) {
        for (Order order : orders) {
            processor.process(order);
        }
    }

    public static void main(String[] args) {
        OrderProcessing orderProcessing = new OrderProcessing();

        // Filter orders with amount greater than 150
        System.out.println("Filtering orders with amount > 150:");
        orderProcessing.filterOrders(order -> order.getOrderAmount() > 150);

        // Process orders by changing status of pending orders to "Processing"
        System.out.println("\nProcessing pending orders:");
        orderProcessing.processOrders(order -> {
            if (order.getStatus().equals("Pending")) {
                order.setStatus("Processing");
                System.out.println("Updated order: " + order);
            }
        });

        // Print all orders after processing
        System.out.println("\nAll orders after processing:");
        orderProcessing.processOrders(System.out::println);
    }
}
