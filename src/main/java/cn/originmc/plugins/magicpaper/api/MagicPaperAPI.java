package cn.originmc.plugins.magicpaper.api;

import cn.origincraft.magic.object.MagicWords;
import cn.origincraft.magic.object.NormalContext;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.cooldown.CoolDownManager;
import cn.originmc.plugins.magicpaper.data.manager.MagicDataManager;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;
import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;
import cn.originmc.plugins.magicpaper.timer.MagicTimer;
import cn.originmc.plugins.magicpaper.timer.MagicTimerManager;
import cn.originmc.plugins.magicpaper.util.item.MagicItem;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MagicPaperAPI {
    /**
     * 注册方法
     * @param function 方法
     * @param alies 别名
     */
    public static void registerFunction(PaperFunction function,String... alies){
        MagicPaper.getMagicManager().registerFunction(function,alies);
    }
    /**
     * 注册方法
     * @param function 方法
     */
    public static void registerFunction(PaperFunction function){
        MagicPaper.getMagicManager().registerFunction(function);
    }
    /**
     * 获取魔咒
     * @param name 魔咒名
     */
    public static Spell getSpell(String name){
        return MagicDataManager.getSpell(name);
    }
    /**
     * 获取已载入魔咒名列表
     */
    public static List<String> getSpells(){
        return MagicDataManager.getSpellsID();
    }
    /**
     * 创建一个数据上下文
     */
    public static NormalContext createNormalContext(){
        return new NormalContext();
    }
    /**
     * 创建一个魔咒上下文
     * @param context 数据上下文
     */
    public static SpellContext createSpellContext(NormalContext context){
        SpellContext spellContext=new SpellContext();
        spellContext.setContextMap(context);
        return spellContext;
    }
    /**
     * 创建一个魔咒
     * @param words 魔咒词语
     */
    public static Spell getSpell(List<String> words){
        return new Spell(words,MagicPaper.getMagicManager());
    }
    /**
     * 判断是否存储有指定id的魔咒
     * @param id 魔咒名
     */
    public static boolean isSpell(String id){
        return MagicDataManager.isSpell(id);
    }
    /**
     * 执行魔咒
     * @param spell 魔咒
     * @param context 数据上下文
     */
    public static void execute(Spell spell,NormalContext context){
        spell.execute(context);
    }
    /**
     * 执行魔语
     * @param words 魔咒词语
     * @param context 魔咒上下文
     */
    public static void execute(MagicWords words,SpellContext context){
        words.execute(context);
    }
    /**
     * 获取全局数据上下文
     * @return 全局数据上下文
     */
    public static NormalContext getMainContext(){
        return MagicPaper.getContext();
    }

    /**
     * 注册触发器
     * @param trigger 自定义触发器
     */
    public static void registerTrigger(MagicPaperTrigger trigger){
        MagicPaperTriggerManager.registerTrigger(trigger);
    }

    /**
     * 注册触发器
     * @param id 触发器id
     * @param magicTimer 触发器
     */
    public static void registerTimer(String id, MagicTimer magicTimer){
        MagicTimerManager.registerTimer(id,magicTimer);
    }
    /**
     * 获取冷却管理器
     */
    public static CoolDownManager getCoolDownManager(){
        return MagicPaper.getCoolDownManager();
    }
    /**
     * 获取魔法物品
     * @param itemStack 物品
     */
    public static MagicItem getMagicItem(ItemStack itemStack){
        return new MagicItem(itemStack);
    }
}
