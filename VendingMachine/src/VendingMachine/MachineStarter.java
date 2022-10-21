package VendingMachine;

public class MachineStarter {
    public static VendingMachine machineStarter() {

        //  Creating the snackMachine object
        SnackMachine snackMachine = new SnackMachine();

        //  adding Items to the machine
        snackMachine.addingItems();

        return snackMachine;
    }
}
