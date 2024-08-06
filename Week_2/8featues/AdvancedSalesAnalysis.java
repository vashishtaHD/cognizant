
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class SalesRecord {
    private int recordId;
    private String salesPerson;
    private String region;
    private double amount;
    private LocalDate date;
    private String productCategory;
    private int quantity;

    public SalesRecord(int recordId, String salesPerson, String region, double amount, LocalDate date, String productCategory, int quantity) {
        this.recordId = recordId;
        this.salesPerson = salesPerson;
        this.region = region;
        this.amount = amount;
        this.date = date;
        this.productCategory = productCategory;
        this.quantity = quantity;
    }

    // Getters
    public int getRecordId() { return recordId; }
    public String getSalesPerson() { return salesPerson; }
    public String getRegion() { return region; }
    public double getAmount() { return amount; }
    public LocalDate getDate() { return date; }
    public String getProductCategory() { return productCategory; }
    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return "SalesRecord{" +
                "recordId=" + recordId +
                ", salesPerson='" + salesPerson + '\'' +
                ", region='" + region + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", productCategory='" + productCategory + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

public class AdvancedSalesAnalysis {
    private List<SalesRecord> salesRecords;

    public AdvancedSalesAnalysis() {
        salesRecords = new ArrayList<>();
        // Add sample data
        salesRecords.add(new SalesRecord(1, "John", "North", 1000.0, LocalDate.of(2024, 1, 15), "Electronics", 5));
        salesRecords.add(new SalesRecord(2, "Alice", "South", 1500.0, LocalDate.of(2024, 2, 1), "Clothing", 10));
        salesRecords.add(new SalesRecord(3, "Bob", "East", 800.0, LocalDate.of(2024, 1, 20), "Electronics", 3));
        salesRecords.add(new SalesRecord(4, "John", "West", 2000.0, LocalDate.of(2024, 2, 10), "Furniture", 2));
        salesRecords.add(new SalesRecord(5, "Alice", "North", 1200.0, LocalDate.of(2024, 1, 25), "Clothing", 8));
        // Add more records for better parallel processing demonstration
        for (int i = 6; i <= 1000; i++) {
            salesRecords.add(new SalesRecord(i, "Salesperson" + i, "Region" + i, i * 100.0, LocalDate.now(), "Category" + i, i));
        }
    }

    public void filterAndSortRecords(String productCategory) {
        System.out.println("Filtered and sorted records for category: " + productCategory);
        salesRecords.stream()
                .filter(record -> record.getProductCategory().equals(productCategory))
                .sorted(Comparator.comparing(SalesRecord::getDate))
                .forEach(System.out::println);
    }

    public void calculateAverageSales(String region) {
        double averageSales = salesRecords.stream()
                .filter(record -> record.getRegion().equals(region))
                .mapToDouble(SalesRecord::getAmount)
                .average()
                .orElse(0.0);
        System.out.println("Average sales for region " + region + ": " + averageSales);
    }

    public void findTopSalesRecord() {
        Optional<SalesRecord> topSalesRecord = salesRecords.stream()
                .max(Comparator.comparingDouble(SalesRecord::getAmount));
        topSalesRecord.ifPresent(record -> System.out.println("Top sales record: " + record));
    }

    public void performSequentialVsParallelOperations() {
        String targetCategory = "Electronics";

        long sequentialStart = System.currentTimeMillis();
        long sequentialCount = salesRecords.stream()
                .filter(record -> record.getProductCategory().equals(targetCategory))
                .sorted(Comparator.comparing(SalesRecord::getDate))
                .count();
        long sequentialEnd = System.currentTimeMillis();

        long parallelStart = System.currentTimeMillis();
        long parallelCount = salesRecords.parallelStream()
                .filter(record -> record.getProductCategory().equals(targetCategory))
                .sorted(Comparator.comparing(SalesRecord::getDate))
                .count();
        long parallelEnd = System.currentTimeMillis();

        System.out.println("Sequential operation time: " + (sequentialEnd - sequentialStart) + "ms");
        System.out.println("Parallel operation time: " + (parallelEnd - parallelStart) + "ms");
        System.out.println("Sequential count: " + sequentialCount);
        System.out.println("Parallel count: " + parallelCount);
    }

    public static void main(String[] args) {
        AdvancedSalesAnalysis salesAnalysis = new AdvancedSalesAnalysis();

        salesAnalysis.filterAndSortRecords("Electronics");
        System.out.println();

        salesAnalysis.calculateAverageSales("North");
        System.out.println();

        salesAnalysis.findTopSalesRecord();
        System.out.println();

        salesAnalysis.performSequentialVsParallelOperations();
    }
}
