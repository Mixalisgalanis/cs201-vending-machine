package machine;

import modules.containers.DosingContainer;
import modules.containers.FlowContainer;
import modules.containers.MaterialContainer;
import modules.dispensers.ConsumableDispenser;
import modules.external.*;
import recipes.consumables.Consumable;

public class SoftwareMachine {

    //Class Variables
    private Data data;

    public SoftwareMachine() {
        createDataCollection();
        insertData();
    }

    private void createDataCollection() {
        if (Data.getInstance() == null) this.data = new Data();
    }

    private void insertData() {
        /*
        Adding Consumables
         */
        //Powder Consumables
        data.addConsumable(new Consumable("Coffee", data.STANDARD_DOSING_CONTAINER_SIZE, "Powder"));
        data.addConsumable(new Consumable("Sugar", data.STANDARD_DOSING_CONTAINER_SIZE, "Powder"));

        //Liquid Consumables
        data.addConsumable(new Consumable("Water", data.STANDARD_FLOW_CONTAINER_SIZE, "Liquid"));

        //Material Consumables
        data.addConsumable(new Consumable("Cup", data.STANDARD_MATERIAL_CONTAINER_SIZE, "Material"));

        /*
        Adding Modules
         */
        //External Modules
        data.addHardwareEntity(new NumPad());
        data.addHardwareEntity(new CoinReader());
        data.addHardwareEntity(new ChangeCase());
        data.addHardwareEntity(new DisplayPanel());
        data.addHardwareEntity(new ProductCase());

        //ConsumableDispensers
        data.addHardwareEntity(new ConsumableDispenser("POWDERS", "Powder"));
        data.addHardwareEntity(new ConsumableDispenser("LIQUIDS", "Liquid"));
        data.addHardwareEntity(new ConsumableDispenser("MATERIALS", "Material"));

        //DosingContainers
        data.addHardwareEntity(new DosingContainer("Coffee", data.STANDARD_DOSING_CONTAINER_SIZE, data.findConsumable("Coffee")));
        data.addHardwareEntity(new DosingContainer("Sugar", data.STANDARD_DOSING_CONTAINER_SIZE, data.findConsumable("Sugar")));
        //LiquidContainers
        data.addHardwareEntity(new FlowContainer("Water", data.STANDARD_FLOW_CONTAINER_SIZE, data.findConsumable("Water")));
        //MaterialContainers
        data.addHardwareEntity(new MaterialContainer("Cup", data.STANDARD_MATERIAL_CONTAINER_SIZE, data.findConsumable("Cup")));


    }
}
