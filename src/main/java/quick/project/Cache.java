package quick.project;

/**
 * Cache interface. It is allow to store object and key for it, and search quickly for it.
 * @author marcin.godyn
 *
 * @param <K>
 * @param <V>
 */
public interface Cache<K,V> {
    
    /**
     * Add method. For adding object to cache.
     * @param key Object key.
     * @param value Object value.
     */
    void add(K key, V value);
    
    /**
     * Get method. It's looking in cache for given key, and return it's value.
     * @param key
     * @return
     */
    V get(K key);
    
}
