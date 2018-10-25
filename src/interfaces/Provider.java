package interfaces;

public interface Provider extends Pluggable{

    void provide(Consumer consumer, int quantity);

    void provide(Consumer consumer);
}
