package interfaces;

public interface Provider {

    void provide(Consumer consumer, int quantity);

    void provide(Consumer consumer);
}
