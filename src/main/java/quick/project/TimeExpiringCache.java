package quick.project;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Time expiring cache. It's a cache which elements are expiring base on time interval for which they were defined.
 * @author marcin.godyn
 *
 * @param <K>
 * @param <V>
 */
public class TimeExpiringCache<K,V> implements Cache<K,V>{
    
    private Map<Element<K>, V> cacheMap = new HashMap<>();
    
    private TimeManager timeManager = new SystemTime();

    private Duration timeToLive; 
    
    public TimeExpiringCache(Duration timeToLive) {
        this.timeToLive=timeToLive;
    }
    
    public TimeExpiringCache(TimeManager timeManager,Duration timeToLive) {
        this.timeToLive=timeToLive;
        this.timeManager=timeManager;
    }

    public void add(K key, V value) {
        Element<K> e=new Element<K>(key);
        e.setLastUse(timeManager.now());
        cacheMap.put(e, value);
    }

    public V get(K key) {
        evict();
        Element<K> element = new Element<>(key);
        V value = cacheMap.get(element);
        element.setLastUse(timeManager.now());
        cacheMap.put(element, value);
        return value;
    }
    
    private void evict() {
        Set<Element<K>> toRemove = cacheMap.keySet().stream().filter(element -> element.getLastUse().plus(timeToLive).isBefore(timeManager.now())).collect(Collectors.toSet());
        toRemove.forEach(element -> cacheMap.remove(element));
    }
    
    /**
     * Element of time expiring cache.
     * @author marcin.godyn
     *
     * @param <K>
     */
    public static class Element<K> {
        private K key;
        private LocalDateTime lastUse;

        public Element(K key) {
            this.key=key;
        }
        
        public K getKey() {
            return key;
        }
        
        public LocalDateTime getLastUse() {
            return lastUse;
        }
        
        public void setLastUse(LocalDateTime lastUse) {
            this.lastUse = lastUse;
        }
        
        /**
         * To easy use this element in maps, it will use hashCode of key as it's own.
         */
        @Override
        public int hashCode() {
            return key.hashCode();
        }
        
        
        /**
         * Besides last use, the element should be compared only based on it's key.
         */
        @Override
        public boolean equals(Object o) {
            if(o instanceof Element) {
                return ((Element) o).getKey().equals(this.getKey());
            }
            return false;
        }

    }


}
