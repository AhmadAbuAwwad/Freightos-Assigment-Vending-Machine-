package Payment;

public class Card implements PaymentInterface {

    // Set it's values for true and 50$ since the card should has the value changed from the bank not the machine.
    private int creditCardBalance_ = 5000;

    @Override
    public void takeValue(int value) {
        creditCardBalance_ -= value;
    }

    @Override
    public int getValue() {
        return creditCardBalance_;
    }
}
