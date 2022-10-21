package Payment;

public enum Notes implements PaymentInterface {
    TWENTY_DOLLARS(2000), FIFTY_DOLLARS(5000), FIVE_DOLLARS(500), TEN_DOLLARS(1000), HUNDERD_DOLLARS(10000);
    int value_;

    Notes(int value) {
        this.value_ = value;
    }


    @Override
    public int getValue() {
        return value_;
    }

    @Override
    public void takeValue(int value) {}

}
