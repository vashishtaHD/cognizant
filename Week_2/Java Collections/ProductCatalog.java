import java.util.HashSet;

public class ProductCatalog {
    HashSet<String> products = new HashSet<>();

    void addProduct(String productName) {
        products.add(productName);
    }

    void removeProduct(String productName) {
        products.remove(productName);
    }

    boolean searchProduct(String productName) {
        return products.contains(productName);
    }

    void displayProducts() {
        System.out.println(products);
    }
}

class ProductCatalogTest {
    public static void main(String[] args) {
        ProductCatalog catalog = new ProductCatalog();
        catalog.addProduct("Laptop");
        catalog.addProduct("Smartphone");
        catalog.addProduct("Tablet");
        catalog.displayProducts();
        catalog.removeProduct("Tablet");
        System.out.println(catalog.searchProduct("Laptop"));
        catalog.displayProducts();
    }
}
