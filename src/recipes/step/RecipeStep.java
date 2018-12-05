package recipes.step;

import machine.SoftwareMachine;

abstract public class RecipeStep {

    //Class Variable
    SoftwareMachine sm = SoftwareMachine.getInstance();

    //Other Methods
    abstract public String describe();

    abstract public void executeStep();

    String nameDecoder(String name) {
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

    String nameEncoder(String name) {
        switch (name) {
            case "Blender":
                return "MIXER";
            case "Boiler":
                return "BOILER";
            case "ProductCase":
                return "CUP_CASE";
            case "BigCup":
                return "BIG_CUP";
            case "SmallCup":
                return "SMALL_CUP";
        }
        return name;
    }

}
