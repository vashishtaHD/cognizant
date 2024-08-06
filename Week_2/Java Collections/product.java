import java.util.HashMap;

class Product {
    int id;
    String name;
    int quantity;
}

class InventoryManagement {
    HashMap<Integer, Product> inventory = new HashMap<>();

    void addProduct(Product product) {
        inventory.put(product.id, product);
    }

    void removeProduct(int productId) {
        inventory.remove(productId);
    }

    void updateProductQuantity(int productId, int newQuantity) {
        Product product = inventory.get(productId);
        if (product != null) {
            product.quantity = newQuantity;
        }
    }

    void displayProducts() {
        inventory.values().forEach(product -> System.out.println(product.id + " " + product.name + " " + product.quantity));
    }
}

class InventoryManagementTest {
    public static void main(String[] args) {
        InventoryManagement management = new InventoryManagement();
        Product product1 = new Product();
        product1.id = 1;
        product1.name = "Product1";
        product1.quantity = 10;
        management.addProduct(product1);
        management.displayProducts();
        management.removeProduct(1);
        management.displayProducts();
        management.addProduct(product1);
        management.updateProductQuantity(1, 20);
        management.displayProducts();
    }
}
