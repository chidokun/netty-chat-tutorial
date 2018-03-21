package letschat.storage;

public abstract class Storage<T, U> {

    public abstract boolean put(T key, U value);

    public abstract boolean remove(T key);

    public abstract boolean contains(T key);

    public abstract U get(T key);

}