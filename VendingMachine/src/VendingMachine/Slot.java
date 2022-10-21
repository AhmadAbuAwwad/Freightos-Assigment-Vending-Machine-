package VendingMachine;


public class Slot {
    private Product product_;
    private int quantity_;

    Slot (Product product, int inventory){
        this.product_ = product;
        this.quantity_ = inventory;
    }

    @Override
    public String toString() {
        return "Slots of " + product_.toString() + " and quantity of " + quantity_;
    }

    public Product getProduct() {
        return product_;
    }

    public int getQuantity(){
        return quantity_;
    }
    public void setInventory(int quantity) {
        this.quantity_ = quantity;
    }

    /**
     * Decrement from the quantity of the product
     * @param screen
     */
    public void decQuantity(Screen screen) {
        if (quantity_ == 0) {
            throw new RuntimeException("Item is empty on the vending machine. Please try again");
        }
        setInventory(quantity_--);
    }
}
