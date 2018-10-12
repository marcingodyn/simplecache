package quick.project;

import org.junit.Test;
import quick.project.TimeExpiringCache.Element;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TimeExpiringCacheElementTest {

    @Test
    public void shouldPositivelyCompareObjects(){
        //given
        
        Element<Object> element1 = new Element<>("1");
        Element<Object> element2 = new Element<>("1");
        
        //when
        boolean answer = element1.equals(element2);
        //then
        assertTrue(answer);
    }
    
    @Test
    public void shouldNotMatchObjectDueToDifferentType() {
        //given
        Element<Object> element1 = new Element<>("1");
        Element<Object> element2 = new Element<>(1);
        //when
        boolean answer = element1.equals(element2);
        //then
        assertFalse(answer);
        
        
    }
    
    @Test
    public void shouldNotMatchObjectDueToDifferentValue() {
        //given
        Element<Object> element1 = new Element<>("1");
        Element<Object> element2 = new Element<>("2");
        //when
        boolean answer = element1.equals(element2);
        //then
        assertFalse(answer);
        
        
    }
    
    @Test
    public void shouldNotMatchObjectDueToDifferentObjectTypes() {
        //given
        Element<Object> element1 = new Element<>("1");
        Object element2 = "2";
        //when
        boolean answer = element1.equals(element2);
        //then
        assertFalse(answer);
        
        
    }
}
