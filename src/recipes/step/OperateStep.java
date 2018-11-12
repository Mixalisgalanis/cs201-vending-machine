package recipes.step;

public class OperateStep extends RecipeStep {

    //Class variables
    private String processor;
    private int duration;

    //Constructor
    public OperateStep(String processor, int duration) {
        this.processor = processor;
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
    public String describe(){
        return "OPERATE " + " " + getProcessor() + " " + getDuration();
    }
}