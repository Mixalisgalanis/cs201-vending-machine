package machine;

import modules.containers.DosingContainer;
import modules.Module;
import modules.external.*;

import java.util.ArrayList;

public class VendingMachine {

    private ArrayList<Module> modules = new ArrayList<>();

    //Other Methods
    public void addModule(Module module) {
        modules.add(module);
    }

    public void addModules() {
        //Adding External Modules
        addModule((new CoinReader()));
        addModule((new DisplayPanel()));
        addModule((new NumPad()));
        addModule((new ProductCase()));
        addModule((new ChangeCase()));

        //Adding Containers
        //modules.add(new DosingContainer("Water Container", ));
    }

    public Module getModule(String name) {
        for (Module module : modules) {
            if (module.getName().equals(name)) return module;
        }
        return null;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }
}
