package VendingMachine;

public class Product {
    private String name_;
    private int price_;

    Product(int price, String name){
        this.name_ = name;
        this.price_ = price;
    }
    public int getPrice() {
        return price_;
    }

    @Override
    public String toString() {
        return "Item: " + name_ + " \tPrice: " + price_ + " Cent";
    }
}
