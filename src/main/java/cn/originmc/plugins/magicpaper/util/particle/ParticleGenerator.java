package cn.originmc.plugins.magicpaper.util.particle;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Vibration;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;

public class ParticleGenerator {
    private Particle particle;
    private int count;
    private double offsetX,offsetY,offsetZ,extra;
    private Color color,fromColor,toColor;
    private float redStoneSize=1,dustSize=1;
    private ItemStack itemStack;
    private Vibration vibration;
    private BlockData blockData;
    public ParticleGenerator(int count,double offsetX,double offsetY,double offsetZ,double extra){
        setParticle(Particle.ASH);
        setCount(count);
        setOffsetX(offsetX);
        setOffsetY(offsetY);
        setOffsetZ(offsetZ);
        setExtra(extra);
    }
    public ParticleGenerator(Particle particle,int count,double offsetX,double offsetY,double offsetZ,double extra){
        setParticle(particle);
        setCount(count);
        setOffsetX(offsetX);
        setOffsetY(offsetY);
        setOffsetZ(offsetZ);
        setExtra(extra);
    }
    public void setRedStoneData(int red,int green,int blue,int size){
        setColor(Color.fromRGB(red,green,blue));
        setRedStoneSize(size);
    }
    public void setDustColorTransition(int fromRed,int fromGreen,int fromBlue,int toRed,int toGreen,int toBlue,int size){
        setFromColor(Color.fromRGB(fromRed,fromGreen,fromBlue));
        setToColor(Color.fromRGB(toRed,toGreen,toBlue));
        setDustSize(size);
    }
    public boolean generator(Particle particle,Location location){
        if (location.getWorld()==null){
            return false;
        }
        switch (particle){
            case REDSTONE:{
                if (getColor()==null){
                    return false;
                }
                Particle.DustOptions dustOptions=new Particle.DustOptions(getColor(),getRedStoneSize());
                location.getWorld().spawnParticle(particle,location,getCount(),getOffsetX(),getOffsetY(),getOffsetZ(),getExtra(),dustOptions);
                return true;
            }
            case DUST_COLOR_TRANSITION:{
                if (getFromColor()==null || getToColor()==null){
                    return false;
                }
                Particle.DustTransition dustTransition=new Particle.DustTransition(getFromColor(),getToColor(),getDustSize());
                location.getWorld().spawnParticle(particle,location,getCount(),getOffsetX(),getOffsetY(),getOffsetZ(),getExtra(),dustTransition);
                return true;
            }
            case ITEM_CRACK:{
                if (getItemStack()==null){
                    return false;
                }
                location.getWorld().spawnParticle(particle,location,getCount(),getOffsetX(),getOffsetY(),getOffsetZ(),getExtra(),getItemStack());
                return true;
            }
            case VIBRATION:{
                if (getVibration()==null){
                    return false;
                }
                location.getWorld().spawnParticle(particle,location,getCount(),getOffsetX(),getOffsetY(),getOffsetZ(),getExtra(),getVibration());
                return true;
            }
            case BLOCK_CRACK:
            case BLOCK_DUST:
            case BLOCK_MARKER:
            case FALLING_DUST:{
                if (getBlockData()==null){
                    return false;
                }
                location.getWorld().spawnParticle(particle,location,getCount(),getOffsetX(),getOffsetY(),getOffsetZ(),getExtra(),getBlockData());
                return true;
            }
            default:{
                location.getWorld().spawnParticle(particle,location,getCount(),getOffsetX(),getOffsetY(),getOffsetZ(),getExtra());
                return true;
            }
        }
    }
    public boolean generator(Location location){
        if (location.getWorld()==null){
            return false;
        }
        switch (particle){
            case REDSTONE:{
                if (getColor()==null){
                    return false;
                }
                Particle.DustOptions dustOptions=new Particle.DustOptions(getColor(),getRedStoneSize());
                location.getWorld().spawnParticle(particle,location,getCount(),getOffsetX(),getOffsetY(),getOffsetZ(),getExtra(),dustOptions);
                return true;
            }
            case DUST_COLOR_TRANSITION:{
                if (getFromColor()==null || getToColor()==null){
                    return false;
                }
                Particle.DustTransition dustTransition=new Particle.DustTransition(getFromColor(),getToColor(),getDustSize());
                location.getWorld().spawnParticle(particle,location,getCount(),getOffsetX(),getOffsetY(),getOffsetZ(),getExtra(),dustTransition);
                return true;
            }
            case ITEM_CRACK:{
                if (getItemStack()==null){
                    return false;
                }
                location.getWorld().spawnParticle(particle,location,getCount(),getOffsetX(),getOffsetY(),getOffsetZ(),getExtra(),getItemStack());
                return true;
            }
            case VIBRATION:{
                if (getVibration()==null){
                    return false;
                }
                location.getWorld().spawnParticle(particle,location,getCount(),getOffsetX(),getOffsetY(),getOffsetZ(),getExtra(),getVibration());
                return true;
            }
            case BLOCK_CRACK:
            case BLOCK_DUST:
            case BLOCK_MARKER:
            case FALLING_DUST:{
                if (getBlockData()==null){
                    return false;
                }
                location.getWorld().spawnParticle(particle,location,getCount(),getOffsetX(),getOffsetY(),getOffsetZ(),getExtra(),getBlockData());
                return true;
            }
            default:{
                location.getWorld().spawnParticle(particle,location,getCount(),getOffsetX(),getOffsetY(),getOffsetZ(),getExtra());
                return true;
            }
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }

    public double getOffsetZ() {
        return offsetZ;
    }

    public void setOffsetZ(double offsetZ) {
        this.offsetZ = offsetZ;
    }

    public double getExtra() {
        return extra;
    }

    public void setExtra(double extra) {
        this.extra = extra;
    }


    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getToColor() {
        return toColor;
    }

    public void setToColor(Color toColor) {
        this.toColor = toColor;
    }

    public Vibration getVibration() {
        return vibration;
    }

    public void setVibration(Vibration vibration) {
        this.vibration = vibration;
    }

    public BlockData getBlockData() {
        return blockData;
    }

    public void setBlockData(BlockData blockData) {
        this.blockData = blockData;
    }

    public Color getFromColor() {
        return fromColor;
    }

    public void setFromColor(Color fromColor) {
        this.fromColor = fromColor;
    }

    public float getRedStoneSize() {
        return redStoneSize;
    }

    public void setRedStoneSize(float redStoneSize) {
        this.redStoneSize = redStoneSize;
    }

    public float getDustSize() {
        return dustSize;
    }

    public void setDustSize(float dustSize) {
        this.dustSize = dustSize;
    }

    public Particle getParticle() {
        return particle;
    }

    public void setParticle(Particle particle) {
        this.particle = particle;
    }
}
