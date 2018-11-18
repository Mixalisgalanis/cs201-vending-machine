package console;

import machine.SoftwareMachine;

public class ConsoleInterface {

    public static void main(String[] args) {
        SoftwareMachine sm = (SoftwareMachine.getInstance() != null)? SoftwareMachine.getInstance(): new SoftwareMachine();
        sm.startCycle();
    }

}
