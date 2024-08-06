

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@FunctionalInterface
interface FeedbackFilter {
    boolean filter(Feedback feedback);
}

@FunctionalInterface
interface FeedbackProcessor {
    void process(Feedback feedback);
}

class Feedback {
    private int feedbackId;
    private String customerName;
    private int rating;
    private String comments;

    public Feedback(int feedbackId, String customerName, int rating, String comments) {
        this.feedbackId = feedbackId;
        this.customerName = customerName;
        this.rating = rating;
        this.comments = comments;
    }

    // Getters
    public int getFeedbackId() { return feedbackId; }
    public String getCustomerName() { return customerName; }
    public int getRating() { return rating; }
    public String getComments() { return comments; }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", customerName='" + customerName + '\'' +
                ", rating=" + rating +
                ", comments='" + comments + '\'' +
                '}';
    }
}

public class FeedbackAnalysis {
    private List<Feedback> feedbackList;

    public FeedbackAnalysis() {
        feedbackList = new ArrayList<>();
        // Add sample data
        feedbackList.add(new Feedback(1, "Alice", 4, "Good product, fast delivery"));
        feedbackList.add(new Feedback(2, "Bob", 2, "Product quality could be better"));
        feedbackList.add(new Feedback(3, "Charlie", 5, "Excellent service!"));
        feedbackList.add(new Feedback(4, "David", 3, "Average experience"));
        feedbackList.add(new Feedback(5, "Eve", 1, "Disappointed with the product"));
    }

    public void processFeedback(FeedbackFilter filter, FeedbackProcessor processor) {
        feedbackList.stream()
                .filter(filter::filter)
                .forEach(processor::process);
    }

    public void analyzePositiveNegativeFeedback(int threshold) {
        Map<Boolean, Long> feedbackAnalysis = feedbackList.stream()
                .collect(Collectors.partitioningBy(
                        feedback -> feedback.getRating() >= threshold,
                        Collectors.counting()
                ));

        System.out.println("Positive feedback count: " + feedbackAnalysis.get(true));
        System.out.println("Negative feedback count: " + feedbackAnalysis.get(false));
    }

    public void extractCustomerNamesAndComments(int minRating) {
        System.out.println("Customer names and comments for ratings >= " + minRating + ":");
        feedbackList.stream()
                .filter(feedback -> feedback.getRating() >= minRating)
                .forEach(feedback -> System.out.println(feedback.getCustomerName() + ": " + feedback.getComments()));
    }

    public static void main(String[] args) {
        FeedbackAnalysis analysis = new FeedbackAnalysis();

        // Filter feedbacks with rating > 3 and process them
        FeedbackFilter highRatingFilter = feedback -> feedback.getRating() > 3;
        FeedbackProcessor printProcessor = System.out::println;

        System.out.println("High-rated feedbacks:");
        analysis.processFeedback(highRatingFilter, printProcessor);
        System.out.println();

        // Analyze positive (rating >= 4) and negative feedback
        analysis.analyzePositiveNegativeFeedback(4);
        System.out.println();

        // Extract customer names and comments for feedbacks with rating >= 3
        analysis.extractCustomerNamesAndComments(3);
    }
}
