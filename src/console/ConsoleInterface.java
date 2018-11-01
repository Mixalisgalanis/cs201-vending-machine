package console;

import machine.VendingMachine;

public class ConsoleInterface {

    public static void main(String[] args){

        VendingMachine vm = new VendingMachine();

        vm.addModules();
    }

}
