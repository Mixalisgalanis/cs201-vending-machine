package recipes.step;

import machine.SoftwareMachine;

abstract public class RecipeStep {

    //Class Variable
    SoftwareMachine sm = SoftwareMachine.getInstance();

    //Other Methods
    abstract public String describe();

    abstract public void executeStep();

}
