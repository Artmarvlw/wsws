import java.time.LocalDate;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;



public class StreamApiTasks {
    enum OrderStatus {
        NEW, PAID, SHIPPED, DELIVERED, CANCELLED
    }

    record Product(String name, String category, double price) {
    }


    record OrderItem(Product product, int quantity) {
        double totalPrice() {
            return product.price() * quantity;
        }

    }


    record Order(
            String id,
            String customerName,
            LocalDate orderDate,
            List<OrderItem> items,
            OrderStatus status
    ) {

        double totalValue() {
            return items.stream()
                    .mapToDouble(OrderItem::totalPrice)
                    .sum();

        }

    }


    static List<Order> sampleOrders() {
        Product laptop = new Product("Laptop", "Electronics", 4200.0);
        Product phone = new Product("Phone", "Electronics", 2600.0);
        Product headphones = new Product("Headphones", "Electronics", 350.0);
        Product novel = new Product("Novel", "Books", 45.0);
        Product textbook = new Product("Textbook", "Books", 120.0);
        Product tshirt = new Product("T-Shirt", "Fashion", 80.0);
        Product jacket = new Product("Jacket", "Fashion", 300.0);
        Product coffee = new Product("Coffee", "Grocery", 35.0);
        Product tea = new Product("Tea", "Grocery", 25.0);


        return List.of(
                new Order("ORD-001", "Anna Kowalska", LocalDate.of(2024, 2, 3),
                        List.of(new OrderItem(laptop, 1), new OrderItem(headphones, 2)),
                        OrderStatus.DELIVERED),
                new Order("ORD-002", "Jan Nowak", LocalDate.of(2024, 2, 5),
                        List.of(new OrderItem(novel, 3), new OrderItem(textbook, 1)),
                        OrderStatus.DELIVERED),
                new Order("ORD-003", "Anna Kowalska", LocalDate.of(2024, 2, 10),
                        List.of(new OrderItem(phone, 1), new OrderItem(tshirt, 2)),
                        OrderStatus.SHIPPED),
                new Order("ORD-004", "Maria Wisniewska", LocalDate.of(2024, 3, 1),
                        List.of(new OrderItem(jacket, 1), new OrderItem(coffee, 3), new OrderItem(tea, 5)),
                        OrderStatus.PAID),
                new Order("ORD-005", "Jan Nowak", LocalDate.of(2024, 3, 12),
                        List.of(new OrderItem(laptop, 2)),
                        OrderStatus.NEW),
                new Order("ORD-006", "Piotr Zielinski", LocalDate.of(2024, 3, 20),
                        List.of(new OrderItem(phone, 1), new OrderItem(headphones, 1)),
                        OrderStatus.CANCELLED),
                new Order("ORD-007", "Maria Wisniewska", LocalDate.of(2024, 4, 2),
                        List.of(new OrderItem(novel, 1), new OrderItem(coffee, 2), new OrderItem(tshirt, 1)),
                        OrderStatus.DELIVERED),
                new Order("ORD-008", "Tomasz Lewandowski", LocalDate.of(2024, 4, 8),
                        List.of(new OrderItem(laptop, 1), new OrderItem(textbook, 2)),
                        OrderStatus.SHIPPED)
        );

    }


    //Task 1

    static List<String> activeOrderIds(List<Order> orders) {
        return orders.stream()
                .filter(order -> order.status() != OrderStatus.CANCELLED)
                .map(Order::id)
                .toList();

    }
}


