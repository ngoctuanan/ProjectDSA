package GameEngine;

public class Time {
    private long intTime;

    public Time(long time){
        this.intTime = time;
    }

    @Override
    public String toString() {
        long second,minute,hour;
        //get time in second
        second =(int) (intTime / 1e9);

        //get time in second and minute 
        minute = second / 60;
        second = second % 60;

        //get time in minute and hour
        hour = minute / 60;
        minute = minute % 60;
        
        String stringTime = String.valueOf(hour) + " : " + String.valueOf(minute) + " : " + String.valueOf(second);
        
        return stringTime;
    }
}
