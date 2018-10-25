package interfaces;

public interface Pluggable {

    void plug(Consumer consumer);

    void unPlug(Consumer consumer);

    void unPlugAll();

    boolean isPlugged();
}
