package cart;

public class Cart {
    public static String validateInput(int noItems, double price, double tax) {
        if (noItems <= 0) return "Number of items must be a positive integer.";
        if (price <= 0) return "Price must be a positive number.";
        if (tax < 0 || tax > 100) return "Tax rate must be between 0 and 100.";
        return null;
    }

    public static double calculateTotal(int noItems, double price, double tax, double shippingFees) {
        String error = validateInput(noItems, price, tax);
        if (error != null) throw new IllegalArgumentException(error);
        double total = shippingFees + noItems * price * (1 + tax / 100);
        return Math.round(total * 100.0) / 100.0;
    }
}
