package recipes.step;

import modules.containers.processor.Processor;

public class OperateStep extends RecipeStep {

    //Class variables
    private String processor;
    private int duration;


    //Constructor
    public OperateStep(String processor, int duration) {
        this.processor = processor;
        this.duration = duration;
    }

    public OperateStep(String[] data, int duration) {
        processor = data[0];
        this.duration = duration;
    }

    //Getters & Setters
    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    //Other Methods

    /**
     * Creates a string which describes this step - ex: "OPERATE BOILER 5"
     *
     * @return the String created
     */
    @Override
    public String describe() {
        return "OPERATE " + " " + getProcessor() + " " + getDuration();
    }

    @Override
    public void executeStep() {
        Processor tempProcessor = sm.findProcessor(nameDecoder(processor));
        tempProcessor.process(duration);
    }
}