public class Customer {
    String name;
    String id;

    public Customer(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String toString() {
        return name + " (ID: " + id + ")";
    }
}

