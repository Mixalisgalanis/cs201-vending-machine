package recipes.step;

import machine.SoftwareMachine;

abstract public class RecipeStep {

    //Class Variable
    SoftwareMachine sm = SoftwareMachine.getInstance();

    //Other Methods
    abstract public String describe();

    abstract public void executeStep();

    public String NameDecoder(String name) {
        switch (name) {
            case "MIXER":
                return "Blender";
            case "BOILER":
                return "Boiler";
            case "CUP_CASE":
                return "ProductCase";
            case "BIG_CUP":
                return "BigCup";
            case "SMALL_CUP":
                return "SmallCup";
        }
        return name;
    }

}
