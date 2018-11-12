package recipes.step;

public class TransferStep extends RecipeStep {

    //Class variables
    private String source;
    private String destination;
    private String content;
    private int quantity;

    //Constructor

    public TransferStep(String source, String destination, String content, int quantity) {
        this.source = source;
        this.destination = destination;
        this.content = content;
        this.quantity = quantity;
    }

    //Getters & Setters
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //Other Methods
    public String describe(){
        return "TRANSFER " + getSource() + " " + getDestination() + " " + getContent() + " " + getQuantity();
    }
}
