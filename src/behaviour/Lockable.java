package behaviour;

public interface Lockable {

    void lock();

    void unLock();

    boolean isLocked();
}
