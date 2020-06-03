
public class CashMachineMain {

    public static void main(String args[]) {
	CashMachine cashMachine = new CashMachine(13);

	cashMachine.printMenu();
	
	while (cashMachine.processMenu() == true) {
	    cashMachine.printMenu();
	}
    }
}
