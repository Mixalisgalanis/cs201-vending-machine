package recipes.step;

import machine.Data;

abstract public class RecipeStep {

    //Class Variable
    Data data = Data.getInstance();

    //Other Methods
    abstract public String describe();

    abstract public void executeStep();

}
