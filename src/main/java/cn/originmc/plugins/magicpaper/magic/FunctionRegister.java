package cn.originmc.plugins.magicpaper.magic;

import cn.origincraft.magic.MagicManager;
import cn.originmc.plugins.magicpaper.magic.function.behavior.*;
import cn.originmc.plugins.magicpaper.magic.function.behavior.item.*;
import cn.originmc.plugins.magicpaper.magic.function.control.execute.*;
import cn.originmc.plugins.magicpaper.magic.function.hook.abolethplus.*;
import cn.originmc.plugins.magicpaper.magic.function.hook.luckperms.PlayerLPMetaGetFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.luckperms.PlayerLPMetaSetFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.luckperms.PlayerLPTempMetaSetFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.placeholderapi.PlaceholderAPIFunction;
import cn.originmc.plugins.magicpaper.magic.function.info.PlayerNameFunction;
import cn.originmc.plugins.magicpaper.magic.function.magictimer.AddToTimerFunction;
import cn.originmc.plugins.magicpaper.magic.function.magictimer.FoliaTimerFunction;
import cn.originmc.plugins.magicpaper.magic.function.magictimer.PaperTimerFunction;
import cn.originmc.plugins.magicpaper.magic.function.object.CloneItemFunction;
import cn.originmc.plugins.magicpaper.magic.function.object.ItemFunction;
import cn.originmc.plugins.magicpaper.magic.function.object.LocationFunction;
import cn.originmc.plugins.magicpaper.magic.function.object.PlayerFunction;
import cn.originmc.plugins.magicpaper.magic.function.trigger.AddSpellToTriggerFunction;
import cn.originmc.plugins.magicpaper.magic.function.trigger.TriggerClearSpellFunction;
import cn.originmc.plugins.magicpaper.magic.function.yaml.*;
import dev.rgbmc.expression.managers.FunctionManager;

import java.util.HashMap;
import java.util.Map;

public class FunctionRegister {
    public static Map<String,String> funInfo=new HashMap<>();
    public static Map<String,String> argsInfo=new HashMap<>();
    public static void register(MagicManager magicManager) {
        FunctionManager fm = magicManager.getFastExpression().getFunctionManager();
        // hook.luckperms
        fm.register(new PlayerLPMetaGetFunction(),"getlpmeta");
        fm.register(new PlayerLPMetaSetFunction(),"lpmeta");
        fm.register(new PlayerLPTempMetaSetFunction(),"lptmeta");
        // hook.placeholderapi
        fm.register(new PlaceholderAPIFunction(),"papi");
        // hook.abolethplus
        fm.register(new AboGetUserFunction(),"abou");
        fm.register(new AboGetFunction(),"abog");
        fm.register(new AboAddFunction(),"aboa");
        fm.register(new AboSetFunction(),"abos");
        fm.register(new AboEditFunction(),"aboe");
        fm.register(new AboSetTimeFunction(),"abost");
        fm.register(new AboAddItemFunction(),"aboai");
        fm.register(new AboGetItemFunction(),"abogi");
        // object
        fm.register(new PlayerFunction(),"p");
        fm.register(new LocationFunction(),"loc");
        fm.register(new ItemFunction());
        fm.register(new CloneItemFunction(),"citem");
        // behavior
        fm.register(new ConsoleCommandFunction(),"ccommand");
        fm.register(new PlayerCommandFunction(),"pcommand");
        fm.register(new SendToPlayerFunction(),"stp");
        fm.register(new SendToAllPlayerFunction(),"stap");
        fm.register(new PlayerTeleportFunction(),"ptp");
        // behavior.item
        fm.register(new ItemLoreAddLineFunction(),"iladd");
        fm.register(new ItemLoreRemoveFunction(),"ilremove");
        fm.register(new ItemSetPlayerFunction(),"iset");
        fm.register(new ItemGivePlayerFunction(),"igive");
        fm.register(new ItemNBTRemoveFunction(),"inbtr");
        fm.register(new ItemNBTAddFunction(),"inbta");
        fm.register(new ItemNBTGetFunction(),"inbtg");
        fm.register(new ItemToStringFunction(),"itostr");
        fm.register(new StringToItemFunction(),"strtoi");
        fm.register(new ItemSetNameFunction(),"inames");
        fm.register(new ItemGetNameFunction(),"inameg");
        fm.register(new ItemTypeSetFunction(),"itypes");
        fm.register(new ItemTypeGetFunction(),"itypeg");
        fm.register(new ItemLoreHasLineFunction(),"ilhas");
        fm.register(new ItemLoreHasLineNoColorFunction(),"ilhasnc");
        fm.register(new ItemVarParseFunction(),"ivarp");

        // control.execute
        fm.register(new PaperSpellExecuteFunction(),"paperse");
        fm.register(new PaperSpellAsyncExecuteFunction(),"papersae");
        fm.register(new FoliaSpellExecuteFunction(),"foliase");
        fm.register(new FoliaSpellAsyncExecuteFunction(),"foliasae");
        fm.register(new PublicContextGetFunction(),"getpc");
        // magictimer
        fm.register(new AddToTimerFunction(),"att");
        fm.register(new FoliaTimerFunction(),"ftimer");
        fm.register(new PaperTimerFunction(),"ptimer");
        // trigger
        fm.register(new AddSpellToTriggerFunction(),"astt");
        fm.register(new TriggerClearSpellFunction(),"tcs");
        // info
        fm.register(new PlayerNameFunction(),"pname");
        // yaml
        fm.register(new YamlManagerFunction(),"yamlm");
        fm.register(new YamlSaveAllFunction(),"yalmsa");
        fm.register(new YamlSaveFunction(),"yamls");
        fm.register(new YamlGetFunction(),"yamlg");
        fm.register(new YamlSetFunction(),"yamls");
        fm.register(new NewYamlFunction(),"nyaml");
    }
    public static void registerInfo(){
        funInfo.put("playerLPMetaGet","获取玩家的LuckPerms的meta");
        funInfo.put("playerLPMetaSet","设置玩家的LuckPerms的meta");
        funInfo.put("playerLPTempMetaSet","设置玩家的LuckPerms的临时meta");
        funInfo.put("placeholderAPI","获取PlaceholderAPI的变量");
        funInfo.put("player","获取玩家");
        funInfo.put("location","获取位置");
        funInfo.put("consoleCommand","控制台执行命令");
        funInfo.put("playerCommand","玩家执行命令");
        funInfo.put("senderToPlayer","向玩家发送消息");
        funInfo.put("senderToAllPlayer","向所有玩家发送消息");
        funInfo.put("playerTeleport","玩家传送");
        funInfo.put("paperSpellExecute","Paper执行魔咒");
        funInfo.put("paperSpellAsyncExecute","Paper异步执行魔咒");
        funInfo.put("foliaSpellExecute","Folia执行魔咒");
        funInfo.put("foliaSpellAsyncExecute","Folia异步执行魔咒");
        funInfo.put("paperSpellTimer","Paper计时器");
        funInfo.put("paperSpellAsyncTimer","Paper异步计时器");
        funInfo.put("paperTimer","创建Paper计时器");
        funInfo.put("foliaTimer","创建Folia计时器");
        funInfo.put("addToTimer","添加魔咒到计时器");
        funInfo.put("itemLoreAddLine","添加物品lore");
        funInfo.put("itemSetPlayer","设置物品所有者");
        funInfo.put("itemGivePlayer","给予玩家物品");
        funInfo.put("itemNBTRemove","移除物品NBT");
        funInfo.put("itemNBTAdd","添加物品NBT");
        funInfo.put("itemNBTGet","获取物品NBT");
        funInfo.put("itemToString","物品转字符串");
        funInfo.put("stringToItem","字符串转物品");
        funInfo.put("publicContextGet","获取公共数据");
        funInfo.put("addSpellToTrigger","添加魔咒到触发器");
        funInfo.put("triggerClearSpell","清除触发器的魔咒");
        funInfo.put("playerName","获取玩家名");

    }
    public static void registerArgsInfo(){
        argsInfo.put("playerLPMetaGet","玩家(player) meta名(str)");
        argsInfo.put("playerLPMetaSet","玩家(player) meta名(str) meta值(str)");
        argsInfo.put("playerLPTempMetaSet","玩家(player) meta名(str) meta值(str) 持续时间(int)");
        argsInfo.put("placeholderAPI","变量名(str)");
        argsInfo.put("player","玩家名(player)");
        argsInfo.put("location","玩家(player)|x(double) y(double) z(double) world(str)");
        argsInfo.put("consoleCommand","命令(str)");
        argsInfo.put("playerCommand","玩家名(player) 命令(str)");
        argsInfo.put("senderToPlayer","玩家名(player) 消息(str)");
        argsInfo.put("senderToAllPlayer","消息(str)");
        argsInfo.put("playerTeleport","玩家名(player) 坐标(location)");
        argsInfo.put("paperSpellExecute","魔咒(spell)...");
        argsInfo.put("paperSpellAsyncExecute","魔咒(spell)...");
        argsInfo.put("foliaSpellExecute","魔咒(spell)...");
        argsInfo.put("foliaSpellAsyncExecute","魔咒(spell)...");
        argsInfo.put("paperSpellTimer","魔咒名(str) 延迟开始(int) 间隔(int)");
        argsInfo.put("paperSpellAsyncTimer","魔咒名(str) 延迟开始(int) 间隔(int)");
        argsInfo.put("paperTimer","计时器名(str) 延迟开始(int) 间隔(int) 数据语境(ContextMap)");
        argsInfo.put("foliaTimer","计时器名(str) 延迟开始(int) 间隔(int) 数据语境(ContextMap)");
        argsInfo.put("addToTimer","计时器名(str) 魔咒(spell)");
    }
}
