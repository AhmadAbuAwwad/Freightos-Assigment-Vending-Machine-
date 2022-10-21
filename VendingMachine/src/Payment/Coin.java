package Payment;

public enum Coin implements PaymentInterface {
    ONE_DOLLAR(100), FIFTY_CENTS(50), TWENTY_CENTS(20), TEN_CENTS(20), OLD_ONES(0);

    int value_;

    Coin(int value) {
        this.value_ = value;
    }

    @Override
    public int getValue() {
        return value_;
    }

    @Override
    public void takeValue(int value) {}

}
