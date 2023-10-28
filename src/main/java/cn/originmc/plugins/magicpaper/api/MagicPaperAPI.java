package cn.originmc.plugins.magicpaper.api;

import cn.origincraft.magic.object.MagicWords;
import cn.origincraft.magic.object.NormalContext;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.buff.MagicBuff;
import cn.originmc.plugins.magicpaper.cooldown.CoolDown;
import cn.originmc.plugins.magicpaper.cooldown.CoolDownManager;
import cn.originmc.plugins.magicpaper.data.manager.MagicDataManager;
import cn.originmc.plugins.magicpaper.gui.MagicGuiSetting;
import cn.originmc.plugins.magicpaper.timer.MagicTimer;
import cn.originmc.plugins.magicpaper.timer.MagicTimerManager;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;
import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;
import cn.originmc.plugins.magicpaper.util.item.MagicItem;
import org.bukkit.entity.Player;
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

    /**
     * 判断是否存在魔法buff
     * @param targetId 目标id
     * @param buffId buff id
     * @return 是否存在
     */
    public static boolean hasMagicBuff(String targetId,String buffId){
        return MagicPaper.getMagicBuffManager().isBuffActive(targetId,buffId);
    }

    /**
     * 添加魔法buff
     * @param targetId 目标id
     * @return 魔法buff
     */
    public static List<MagicBuff> getMagicBuffs(String targetId){
        return MagicPaper.getMagicBuffManager().getMagicBuffs(targetId);
    }

    /**
     * 获取魔法buff剩余时间
     * @param targetId 目标id
     * @param buffId buff id
     * @return 剩余时间
     */
    public static long getMagicBuffTime(String targetId,String buffId){
        return MagicPaper.getMagicBuffManager().getNowToEnd(targetId,buffId);
    }

    /**
     * 获取魔法buff
     * @param targetId 目标id
     * @param buffId buff id
     * @return 魔法buff
     */
    public static MagicBuff getMagicBuff(String targetId,String buffId){
        return MagicPaper.getMagicBuffManager().getMagicBuff(targetId,buffId);
    }

    /**
     * 打开魔法纸gui
     * @param player 玩家
     * @param id gui id
     */
    public static void openGui(Player player,String id){
        MagicPaper.getMagicGuiManager().getGui(player,id).open(player);
    }

    /**
     * 添加gui设置
     * @param magicGuiSetting gui设置
     */
    public static void addGuiSetting(MagicGuiSetting magicGuiSetting){
        MagicPaper.getMagicGuiManager().register(magicGuiSetting);
    }

    /**
     * 判断冷却是否存在
     * @param id 冷却id
     * @return 是否存在
     */
    public static boolean isCoolDownActive(String id){
        return MagicPaper.getCoolDownManager().isCoolDownActive(id);
    }

    /**
     * 添加冷却
     * @param id 冷却id
     * @param time 冷却时间
     * @param reduction 冷却缩减
     * @param fixedReduction 冷却固定缩减
     */
    public static void addCoolDown(String id,long time,float reduction,long fixedReduction){
        CoolDown coolDown=new CoolDown(id,time,reduction,fixedReduction);
        MagicPaper.getCoolDownManager().addCoolDown(coolDown);
    }

    /**
     * 添加冷却
     * @param id 冷却id
     * @param time 冷却时间
     */
    public static void addCoolDown(String id,long time){
        CoolDown coolDown=new CoolDown(id,time);
        MagicPaper.getCoolDownManager().addCoolDown(coolDown);
    }

    /**
     * 添加冷却
     * @param id 冷却id
     * @return 冷却
     */
    public static CoolDown getCoolDown(String id){
        return MagicPaper.getCoolDownManager().getCoolDown(id);
    }


}
