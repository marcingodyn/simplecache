package quick.project;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class TimeExpiringCacheTest {
    
    @Mock
    private TimeManager timeManager;
    TimeExpiringCache<String, String> toBeTested ;

    @Before
    public void setUp() {
        initMocks(this);
        toBeTested = new TimeExpiringCache<>(timeManager, Duration.ofSeconds(10));
    }
    
    @Test
    public void shouldReturnInsertedObjects() {
        //given
        when(timeManager.now()).thenReturn(LocalDateTime.now());
        String toBeStored="A String";
        String key = "str1";
        toBeTested.add(key, toBeStored);
       
        //when
        String answer = toBeTested.get(key);
        
        //then
        assertEquals(toBeStored, answer);
    }
    
    @Test
    public void shouldReturnLastInsertedObject() {
        //given
        when(timeManager.now()).thenReturn(LocalDateTime.now());
        String toBeStored="A String";
        String key = "str1";
        toBeTested.add(key, toBeStored);
        String toBeStored2="Another String";
        toBeTested.add(key, toBeStored2);
        
        //when
        String answer = toBeTested.get(key);
        
        //then
        assertEquals(toBeStored2, answer);

    }
    
    @Test
    public void shouldReturnNullDueToEviction() {
        //given
        when(timeManager.now())
            .thenReturn(LocalDateTime.now().minusSeconds(11))
            .thenReturn(LocalDateTime.now());
        String toBeStored="A String";
        String key = "str1";
        toBeTested.add(key, toBeStored);
       
        //when
        String answer = toBeTested.get(key);
        
        //then
        assertNull(answer);
    }
    
    @Test
    public void shouldReturnNullDueToNonExistingObject() {
        //given
        String key="str1";
        
        //when
        String answer = toBeTested.get(key);
        
        //then
        assertNull(answer);
    }
    
}
