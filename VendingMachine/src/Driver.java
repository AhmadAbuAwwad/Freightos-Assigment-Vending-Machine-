import Payment.Card;
import VendingMachine.MachineStarter;
import VendingMachine.VendingMachine;
import Payment.Coin;
import Payment.Notes;

public class Driver {
    public static void main(String args[]) throws InterruptedException {

        // Handling different behavioral from the user
        VendingMachine vendingMachine = MachineStarter.machineStarter();

        //  Flow 1: BASIC one
        System.out.println("Flow 1");
        vendingMachine.getKeypad().setValue(12);
        vendingMachine.selectAnItem();
        vendingMachine.payByMoney(Coin.ONE_DOLLAR);
        vendingMachine.payByMoney(Coin.ONE_DOLLAR);
        vendingMachine.payByMoney(Coin.ONE_DOLLAR);
        vendingMachine.payByMoney(Coin.ONE_DOLLAR);
        vendingMachine.payByMoney(Notes.TWENTY_DOLLARS);



        //  Flow 2: Inserting money then selecting an Item
        System.out.println("Flow 2");
        vendingMachine.payByMoney(Notes.TWENTY_DOLLARS);
        vendingMachine.getKeypad().setValue(11);
        vendingMachine.selectAnItem();


        //  Flow 3: Taking the change without selecting an Item
        System.out.println("Flow 3");
        vendingMachine.returnChange();    //  Dispensing without adding money
        vendingMachine.payByMoney(Notes.TWENTY_DOLLARS);
        vendingMachine.returnChange();

        //  Flow 4: Card pay
        System.out.println("Flow 4");
        Card card = new Card();
        vendingMachine.payByCard(card);
        System.out.println("****************************************************");
        vendingMachine.getKeypad().setValue(43);
        vendingMachine.selectAnItem();
        vendingMachine.payByCard(card);

/*
        //  Flow 5: Not Valid Options (ERROR CASES)

        System.out.println("Flow 5");
        // 1- Purchasing from Empty Item inventory
        vendingMachine.getKeypad().setValue(41);
        vendingMachine.selectAnItem();
        vendingMachine.payByMoney(Notes.TWENTY_DOLLARS);

        // 2- Paying a not valid money
        vendingMachine.payByMoney(Notes.TEN_DOLLARS);
        vendingMachine.payByMoney(Notes.HUNDERD_DOLLARS);
        vendingMachine.payByMoney(Notes.FIVE_DOLLARS);
        vendingMachine.payByMoney(Coin.OLD_ONES);

        // 3- User kicking the machine
        vendingMachine.beingKicked();


        // 4- Selecting a non exist option
        vendingMachine.getKeypad().setValue(03);
        vendingMachine.selectAnItem();
        
        * */
    }
}
