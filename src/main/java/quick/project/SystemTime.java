package quick.project;

import java.time.LocalDateTime;


public class SystemTime implements TimeManager {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

}
