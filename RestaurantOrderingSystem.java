
class CustomerInformation {

    String name;
    String contact;

    CustomerInformation(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Contact: " + contact + "\n";
    }
}

class RestaurantOrderingSystem {
    public static void main(String[] args) {

    }
}
