package physics;



import java.time.Duration;
import java.time.Instant;

import java.util.Timer;
import java.util.TimerTask;

public class Time {

    public static float time = 0.0f;


    public static float getTime() {
        return time;
    }

    public static void setTime(float time) {
        Time.time = time;
    }

    public static void IncTime(){

        time += 0.00005d;
    }

}
