package VendingMachine;

import Payment.PaymentInterface;

import java.security.Key;
import java.util.ArrayList;


public interface VendingMachine {
    Keypad getKeypad();
    void selectAnItem();
    ArrayList<PaymentInterface> returnChange();
    Product giveTheProduct();
    double payByMoney(PaymentInterface paymentInterface);
    PaymentInterface payByCard(PaymentInterface PaymentInterface);
    void beingKicked();
    void addingItems();
}
