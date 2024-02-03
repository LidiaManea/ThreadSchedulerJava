public class TimerForHosts {
    long time_initial;

    public void init() {
        this.time_initial = System.currentTimeMillis();
    }

    public long time_passed() {
        return (System.currentTimeMillis() - time_initial);
    }


}
