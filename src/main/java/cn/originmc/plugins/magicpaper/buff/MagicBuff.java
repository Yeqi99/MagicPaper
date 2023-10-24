package cn.originmc.plugins.magicpaper.buff;

public class MagicBuff {
    private String id;
    private long startTime;
    private long duration;
    private float reduction=1;
    private long fixedReduction=0;
    public MagicBuff(String id,long startTime, long duration, float reduction, long fixedReduction){
        this.id = id;
        this.startTime = startTime;
        this.duration = duration;
        this.reduction = reduction;
        this.fixedReduction = fixedReduction;
    }
    public MagicBuff(String id, long duration, float reduction, long fixedReduction){
        this.id = id;
        this.startTime = System.currentTimeMillis();
        this.duration = duration;
        this.reduction = reduction;
        this.fixedReduction = fixedReduction;
    }
    public MagicBuff(String id, long duration){
        this.id = id;
        this.startTime =  System.currentTimeMillis();
        this.duration = duration;
    }
    public long getResultDuration(){
        return (long) (duration*reduction)+fixedReduction;
    }
    public boolean isEnd(){
        return System.currentTimeMillis()>=startTime+getResultDuration();
    }
    public boolean isEnd(long time){
        return time>=startTime+getResultDuration();
    }
    public long getEndTime(){
        return startTime+getResultDuration();
    }
    public long getNowToEnd(){
        return getEndTime()-System.currentTimeMillis();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public float getReduction() {
        return reduction;
    }

    public void setReduction(float reduction) {
        this.reduction = reduction;
    }

    public long getFixedReduction() {
        return fixedReduction;
    }

    public void setFixedReduction(long fixedReduction) {
        this.fixedReduction = fixedReduction;
    }
}
