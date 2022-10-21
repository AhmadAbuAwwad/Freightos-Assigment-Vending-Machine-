package VendingMachine;

import Payment.Coin;
import Payment.Notes;
import Payment.PaymentInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class SnackMachine implements VendingMachine {

    HashMap<Integer, Slot> slots_ = new HashMap<>();
    Screen screen_ = new Screen();


    Keypad keypad_ = new Keypad();
    double moneyAdded = 0;
    int selectedItem_ = 0;

    /**
     * Selecting an Item using keypads
     */
    @Override
    public void selectAnItem(){
        this.selectedItem_ = keypad_.getValue();

        if (!isAvailableOnTheMachine(selectedItem_)) {
            screen_.dispalyMessage("This Item is not available in the vending machine");
            throw new RuntimeException("This Item is not available in the vending machine");
        }
        screen_.dispalyMessage(slots_.get(selectedItem_).getProduct().toString());
        isFoundOnTheMachine();
        isFoundInMachineInventory();

        if(moneyAdded > slots_.get(selectedItem_).getProduct().getPrice()) {
            grappingTheItem();
        }
    }

    /**
     * giving the product to User
     * @return
     */
    @Override
    public Product giveTheProduct() {
        screen_.dispalyMessage("The purchase has done successfully:\nNotice the machine on number " + (selectedItem_/10 % 5 == 0 ? 5 : selectedItem_/10 % 5) + (selectedItem_ % 5 == 0 ? 5 : selectedItem_ % 5));
        return slots_.get(selectedItem_).getProduct();
    }

    /**
     * giving back the change to the user
     * @return
     */
    @Override
    public ArrayList<PaymentInterface> returnChange(){
        double change = 0;
        //  when user wants his change back without buying anything
        if(selectedItem_ == 0)
            change = moneyAdded;
        else {
            change = moneyAdded - slots_.get(selectedItem_).getProduct().getPrice();
            if (moneyAdded == 0)
                change = 0;
        }

        double savedChange = change;
        ArrayList<PaymentInterface> changeList = new ArrayList<>();

        while (change > 0){
            if(change >= 5000) {                    // When Change over 50$
                changeList.add(Notes.FIFTY_DOLLARS);
                change -= 5000;
                continue;
            }
            if(change >= 2000) {                    // When Change over 20$
                changeList.add(Notes.TWENTY_DOLLARS);
                change -= 2000;
                continue;
            }
            if(change >= 100) {                     // When Change over 1$
                changeList.add(Coin.ONE_DOLLAR);
                change -= 100;
                continue;
            }
            if(change >= 50) {                      // When Change over 50c
                changeList.add(Coin.FIFTY_CENTS);
                change -= 50;
                continue;
            }
            if(change >= 20) {                      // When Change over 20c
                changeList.add(Coin.TWENTY_CENTS);
                change -= 20;
                continue;
            }
            if(change >= 10) {                      // When Change over 10c
                changeList.add(Coin.TEN_CENTS);
                change -= 10;
                continue;
            }
        }
        screen_.dispalyMessage("Your Change has been returned: " + savedChange + " Cent");
        screen_.dispalyMessage("****************************************************");

        //  return a list of the money using the accepted money in the machine only
        return changeList;
    }
    /**
     * Adding money into the machine
     * @param money
     * @return
     */
    @Override
    public double payByMoney(PaymentInterface money){
        if(isValidMoney(money)){    // check money validity
            moneyAdded += money.getValue();
            screen_.dispalyMessage("You have added " + moneyAdded + " Cent");
            grappingTheItem();
        }
        else {
            screen_.dispalyMessage("Machine do not accept this kind of Money");
            throw new RuntimeException("Machine do not accept this kind of Money");
        }
        return moneyAdded;
    }

    /**
     * Payment by Card
     * @param paymentInterface
     * @return
     */
    @Override
    public PaymentInterface payByCard(PaymentInterface paymentInterface){
        if(selectedItem_ != 0) {
            if (paymentInterface.getValue() > slots_.get(selectedItem_).getProduct().getPrice()) {
                paymentInterface.takeValue(slots_.get(selectedItem_).getProduct().getPrice());
                buyNow(selectedItem_);
            }
        }else{
            screen_.dispalyMessage("Please Select An Item before inserting the Card");
            reset();
        }
        return paymentInterface;
    }

    /**
     * keypad input getter
     * @return
     */
    @Override
    public Keypad getKeypad() {
        return keypad_;
    }


    /**
     * buying the item
     * @param slotNumberSelected
     */
    private void buyNow(int slotNumberSelected){
        slots_.get(slotNumberSelected).decQuantity(screen_);
        giveTheProduct();
        returnChange();
        reset();
    }


    /**
     * Ready for purchase when money inserted or when selecting an Item
     */
    private void grappingTheItem(){
        if(selectedItem_ != 0 && isEnoughMoneyToBuy()) {
                buyNow(selectedItem_);
                return;
        }
    }

    /**
     * check if the money is enough to buy the product
     * @return
     */
    private boolean isEnoughMoneyToBuy() {
        return !(slots_.get(selectedItem_).getProduct().getPrice() > moneyAdded);
    }

    /**
     * checking if the Item number exist
     */
    private void isFoundOnTheMachine() {
        if (selectedItem_ == 0) {
            screen_.dispalyMessage("Item is not found in the vending machine");
            throw new RuntimeException();
        }
    }

    /**
     * Checking money inserted if valid ones
     * @param money
     * @return
     */
    private boolean isValidMoney(PaymentInterface money){
        return !(money == Notes.HUNDERD_DOLLARS || money == Notes.FIVE_DOLLARS || money == Notes.TEN_DOLLARS || money == Coin.OLD_ONES);
    }

    /**
     *   checking if the machine still has the Item or Sold out
     */
    private void isFoundInMachineInventory() {
        if (slots_.get(selectedItem_).getQuantity() == 0) {
            screen_.dispalyMessage("This item is empty. Please try again");
            throw new RuntimeException();
        }
    }

    /**
     * check the entered number has an Item on the machine
     * @param slotNumber
     * @return
     */
    private boolean isAvailableOnTheMachine(int slotNumber) {
        return slots_.containsKey(slotNumber);
    }

    /**
     * Reset the machine
     */
    private void reset() {
        selectedItem_ = 0;
        moneyAdded = 0;
    }

    /**
     * kicking the machine
     */
    @Override
    public void beingKicked() {
        screen_.dispalyMessage("Please don't kick me");
        throw new RuntimeException();
    }


    /**
     *  Adding Items into the machine
     */
    @Override
    public void addingItems() {
        this.slots_.put(11,new Slot(new Product(300, "Water Bottle"), 7));
        this.slots_.put(12,new Slot( new Product(450, "Iced Coffe"), 7));
        this.slots_.put(13,new Slot( new Product(250, "Orange Juice"), 7));
        this.slots_.put(14,new Slot( new Product(250, "Grape Juice"), 7));
        this.slots_.put(15,new Slot( new Product(200, "Apple Juice"), 7));
        this.slots_.put(21,new Slot( new Product(300, "Twix"), 7));
        this.slots_.put(22,new Slot( new Product(450, "Kinder Bueno"), 0));
        this.slots_.put(23,new Slot( new Product(300, "Kinder Joy"), 7));
        this.slots_.put(24,new Slot( new Product(400, "Reese's"), 7));
        this.slots_.put(25,new Slot( new Product(400, "Hershey's"), 7));
        this.slots_.put(31,new Slot( new Product(200, "Pepsi"), 7));
        this.slots_.put(32,new Slot( new Product(200, "Sprite"), 7));
        this.slots_.put(33,new Slot( new Product(500, "Iced Tea"), 7));
        this.slots_.put(34,new Slot( new Product(400, "Soda"), 7));
        this.slots_.put(35,new Slot( new Product(600, "Almond Milk"),7));
        this.slots_.put(41,new Slot( new Product(300, "Kit Kat"),0));
        this.slots_.put(42,new Slot( new Product(300, "Snickers"),7));
        this.slots_.put(43,new Slot( new Product(300, "Galaxy Bar"),7));
        this.slots_.put(44,new Slot( new Product(200, "DORITOS Chips"),7));
        this.slots_.put(45,new Slot( new Product(100, "Lays Chips"),7));
        this.slots_.put(51,new Slot( new Product(400, "Bounty"),7));
        this.slots_.put(52,new Slot( new Product(220, "Seven Days"),7));
        this.slots_.put(53,new Slot( new Product(550, "Pringles"),7));
        this.slots_.put(54,new Slot( new Product(350, "Mars"),7));
        this.slots_.put(55,new Slot( new Product(250, "Nutella Bar"),7));
    }
}
