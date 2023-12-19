package cn.originmc.plugins.magicpaper;

import cn.origincraft.magic.MagicManager;
import cn.origincraft.magic.function.FunctionRegister;
import cn.origincraft.magic.manager.MagicInstance;
import cn.origincraft.magic.manager.MagicPackage;
import cn.origincraft.magic.object.ContextMap;
import cn.origincraft.magic.object.NormalContext;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.bossbar.BossBarManager;
import cn.originmc.plugins.magicpaper.buff.BuffListener;
import cn.originmc.plugins.magicpaper.buff.MagicBuffManager;
import cn.originmc.plugins.magicpaper.bungeecord.BungeeCordListener;
import cn.originmc.plugins.magicpaper.command.MagicPaperCommand;
import cn.originmc.plugins.magicpaper.command.MagicPaperTabCompleter;
import cn.originmc.plugins.magicpaper.cooldown.CoolDownManager;
import cn.originmc.plugins.magicpaper.data.config.LangData;
import cn.originmc.plugins.magicpaper.data.config.MagicData;
import cn.originmc.plugins.magicpaper.data.gui.GuiData;
import cn.originmc.plugins.magicpaper.data.item.format.ItemFormatData;
import cn.originmc.plugins.magicpaper.data.manager.TimerDataManager;
import cn.originmc.plugins.magicpaper.data.manager.TriggerDataManager;
import cn.originmc.plugins.magicpaper.data.timer.TimerData;
import cn.originmc.plugins.magicpaper.data.trigger.TriggerData;
import cn.originmc.plugins.magicpaper.gui.MagicGuiListener;
import cn.originmc.plugins.magicpaper.gui.MagicGuiManager;
import cn.originmc.plugins.magicpaper.hook.*;
import cn.originmc.plugins.magicpaper.hook.placeholderapi.SpellExpansion;
import cn.originmc.plugins.magicpaper.listener.AdditionalItemListener;
import cn.originmc.plugins.magicpaper.listener.CodingListener;
import cn.originmc.plugins.magicpaper.listener.ItemTriggerListener;
import cn.originmc.plugins.magicpaper.listener.ItemVariableRefreshListener;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;
import cn.originmc.plugins.magicpaper.util.error.PaperErrorUtils;
import cn.originmc.plugins.magicpaper.util.text.Sender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;

public final class MagicPaper extends JavaPlugin {
    private static JavaPlugin instance;
    private static Sender sender;
    private static MagicManager magicManager;
    private static NormalContext context;
    private static CoolDownManager coolDownManager;
    private static MagicBuffManager magicBuffManager;
    private static MagicGuiManager magicGuiManager;
    public static MagicPackage magicPackage;
    public static BungeeCordListener bungeeCordListener;
    public static BossBarManager bossBarManager;

    public static JavaPlugin getInstance() {
        return instance;
    }

    public static Sender getSender() {
        return sender;
    }

    public static MagicManager getMagicManager() {
        return magicManager;
    }

    public static NormalContext getContext() {
        return context;
    }

    public static void setContext(NormalContext context) {
        MagicPaper.context = context;
    }

    public static CoolDownManager getCoolDownManager() {
        return coolDownManager;
    }


    public static MagicBuffManager getMagicBuffManager() {
        return magicBuffManager;
    }


    public static MagicGuiManager getMagicGuiManager() {
        return magicGuiManager;
    }

    public static BossBarManager getBossBarManager() {
        return bossBarManager;
    }
    @Override
    public void onLoad() {
        MagicPaperTriggerManager.trigger("ServerOnLoad", new NormalContext());
    }

    @Override
    public void onEnable() {
        int pluginId = 19713;
        Metrics metrics = new Metrics(this, pluginId);
        if (getConfig().getBoolean("bungee-cord-mode", false)) {
            bungeeCordListener = new BungeeCordListener(this);
        }
        // 初始化插件实例
        instance = this;
        // 初始化发送器
        sender = new Sender(this);
        // 保存默认配置
        saveRes();
        // 初始化魔法管理器
        initMagicManager();
        // 初始化Gui管理器
        magicGuiManager = new MagicGuiManager();
        // 加载数据
        loadData();
        // 初始化魔法管理器
        initMagicManager();
        // 加载全局上下文
        loadContext();
        MagicPaper.getSender().sendToLogger(LangData.get(MagicPaper.getLang(), "context-init", "§a[§bMagicPaper§a] §e全局上下文初始化完成"));
        importSpell(getContext());
        MagicPaper.getSender().sendToLogger(LangData.get(MagicPaper.getLang(), "import-spell-init-start", "§a[§bMagicPaper§a] §e执行import魔咒..."));
        onLoadSpell();
        MagicPaper.getSender().sendToLogger(LangData.get(MagicPaper.getLang(), "onload-spell-init-start", "§a[§bMagicPaper§a] §e执行onload魔咒..."));
        // 初始化冷却管理器
        coolDownManager = new CoolDownManager();
        // 初始化Buff管理器
        magicBuffManager = new MagicBuffManager();
        // 初始化BossBar管理器
        bossBarManager = new BossBarManager();
        hook();
        MagicPaper.getSender().sendToLogger(LangData.get(MagicPaper.getLang(), "cooldown-init", "§a[§bMagicPaper§a] §e冷却管理器初始化完成"));
        MagicPaper.getSender().sendToLogger(LangData.get(MagicPaper.getLang(), "buff-init", "§a[§bMagicPaper§a] §eBuff管理器初始化完成"));
        // 注册命令
        registerCommand();
        // 注册监听器
        registerListener();
        MagicPaperTriggerManager.trigger("ServerOnEnable", new NormalContext());
        // 初始化触发器管理器
        MagicPaperTriggerManager.init();
        // 载入触发器魔咒
        MagicPaper.getSender().sendToLogger(LangData.get(MagicPaper.getLang(), "trigger-init-start", "§a[§bMagicPaper§a] §e载入触发器魔咒..."));
        TriggerDataManager.initDefaultTrigger();

        // 启动计时器
        MagicPaper.getSender().sendToLogger(LangData.get(MagicPaper.getLang(), "timer-init-start", "§a[§bMagicPaper§a] §e启动计时器..."));
        TimerDataManager.initConfigTimer();
        sender.sendOnEnableMsgToLogger("MagicPaper", "Yeqi", getVersion(), "Public");
    }

    public static void initMagicManager() {
        // 初始化魔法管理器
        magicManager = new MagicManager();
        // 注册魔法函数
        if (enableExtendedSyntax("system")) {
            FunctionRegister.regDefault(getMagicManager());
        }
        if (enableExtendedSyntax("paper")) {
            cn.originmc.plugins.magicpaper.magic.FunctionRegister.register(getMagicManager());
        }
    }

    @Override
    public void onDisable() {
        if (bungeeCordListener != null) {
            bungeeCordListener.unRegister();
        }
        MagicPaperTriggerManager.trigger("ServerOnDisable", new NormalContext());
        sender.sendOnDisableMsgToLogger("MagicPaper", "Yeqi", getVersion(), "Public");
    }

    public void loadContext() {
        context = new NormalContext();
    }

    public static String getVersion() {
        return "1.5.10";
    }

    public static String getLang() {
        return getInstance().getConfig().getString("lang");
    }

    public void registerCommand() {
        Objects.requireNonNull(getCommand("MagicPaper")).setExecutor(new MagicPaperCommand());
        Objects.requireNonNull(getCommand("MagicPaper")).setTabCompleter(new MagicPaperTabCompleter());
        MagicPaper.getSender().sendToLogger(LangData.get(MagicPaper.getLang(), "register-command", "§a[§bMagicPaper§a] §e命令注册完成"));
    }

    public void registerListener() {
        if (getConfig().getBoolean("buff-system-enable")) {
            getServer().getPluginManager().registerEvents(new BuffListener(), this);
        }
        if (getConfig().getBoolean("coding", false)) {
            getServer().getPluginManager().registerEvents(new CodingListener(), this);
        }
        if (getConfig().getBoolean("gui-listener", false)) {
            getServer().getPluginManager().registerEvents(new MagicGuiListener(), this);
        }

        if (PlaceholderAPIHook.status) {
            new SpellExpansion().register();
        }
        // protocolLib修改物品发包解析监听器
        if (ProtocolLibHook.status) {
            ProtocolLibHook.pm.addPacketListener(new ItemVariableRefreshListener(this));
        }
        if (getConfig().getBoolean("bore-listener", false)) {
            getServer().getPluginManager().registerEvents(new AdditionalItemListener(), this);
        }
        if (getConfig().getBoolean("item-trigger-listener", false)) {
            getServer().getPluginManager().registerEvents(new ItemTriggerListener(), this);
        }
        MagicPaper.getSender().sendToLogger(LangData.get(MagicPaper.getLang(), "register-listener", "§a[§bMagicPaper§a] §e监听器注册完成"));
    }

    public void saveRes() {
        getInstance().saveDefaultConfig();
        if (getConfig().getBoolean("default-file", true)) {
            getInstance().saveResource("lang/Chinese.yml", false);
            getInstance().saveResource("lang/English.yml", false);
            getInstance().saveResource("magic/HelloWorld.yml", false);
            getInstance().saveResource("magic/keyjump.yml", false);
            getInstance().saveResource("magic/nothing.yml", false);
            getInstance().saveResource("onload/HelloWorld.m", false);
            getInstance().saveResource("import/HelloWorld.m", false);
            getInstance().saveResource("item-format/default.yml", false);
            getInstance().saveResource("trigger/PlayerJoinTrigger.yml", false);
            getInstance().saveResource("trigger/ServerOnDisableTrigger.yml", false);
            getInstance().saveResource("trigger/ServerOnEnableTrigger.yml", false);
            getInstance().saveResource("trigger/ServerOnLoadTrigger.yml", false);
            getInstance().saveResource("trigger/PlayerInteractTrigger.yml", false);
            getInstance().saveResource("trigger/PlayerDropTrigger.yml", false);
            getInstance().saveResource("trigger/TimerTrigger.yml", false);
            getInstance().saveResource("trigger/PlayerBreakTrigger.yml", false);
            getInstance().saveResource("trigger/PlayerPlaceTrigger.yml", false);
            getInstance().saveResource("trigger/EntityDamageTrigger.yml", false);
            getInstance().saveResource("trigger/ItemDropTrigger.yml", false);
            getInstance().saveResource("trigger/PlayerTeleportTrigger.yml", false);
            getInstance().saveResource("trigger/AsyncPlayerChatTrigger.yml", false);
            getInstance().saveResource("trigger/PlayerRespawnTrigger.yml", false);
            getInstance().saveResource("gui/example.yml", false);
            if (getConfig().getBoolean("bungee-cord-mode", false)) {
                getInstance().saveResource("trigger/BungeeCordMessageTrigger.yml", false);
            }
            if (RemoteKeyboardBukkitHook.status) {
                getInstance().saveResource("trigger/PlayerKeyboardTrigger.yml", false);
            }
            if (EpicCraftingsPlusHook.status) {
                getInstance().saveResource("trigger/EpicCraftingsPreCraftTrigger.yml", false);
                getInstance().saveResource("trigger/EpicCraftingsCraftTrigger.yml", false);
                getInstance().saveResource("trigger/EpicCraftingsPlaceClickTrigger.yml", false);
            }
            getInstance().saveResource("timer/default.yml", false);
        }
    }

    public void hook() {
        MagicPaper.getSender().sendToLogger(LangData.get(MagicPaper.getLang(), "hook-start", "§a[§bMagicPaper§a] §e开始挂钩插件"));
        MagicPaper.getSender().sendToLogger(LangData.get(MagicPaper.getLang(), "hook-info", "§8挂钩仅用于提供更多功能,不挂钩不影响插件正常使用"));
        ProtocolLibHook.hook();
        PlaceholderAPIHook.hook();
        LuckPermsHook.hook();
        AbolethplusHook.hook();
        MythicMobsHook.hook();
        ItemsAdderHook.hook();
        PlayerPointsHook.hook();
        VaultHook.hook();
        RemoteKeyboardBukkitHook.hook();
        EpicCraftingsPlusHook.hook();
        MagicPaper.getSender().sendToLogger(LangData.get(MagicPaper.getLang(), "hook-finish", "§a[§bMagicPaper§a] §e挂钩完成"));
    }

    public static void loadData() {
        // 加载魔咒数据
        MagicData.load();
        // 加载语言文件数据
        LangData.load();
        // 加载物品格式数据
        ItemFormatData.load();
        // 加载触发器数据
        TriggerData.load();
        // 加载计时器数据
        TimerData.load();
        // 加载GUI数据
        GuiData.load();
        MagicPaper.getSender().sendToLogger(LangData.get(MagicPaper.getLang(), "load-data", "§a[§bMagicPaper§a] §e数据加载完成"));
    }

    public static boolean enableExtendedSyntax(String id) {
        return getInstance().getConfig().getBoolean("extended-syntax." + id, false);
    }

    public static boolean isDebug() {
        return getInstance().getConfig().getBoolean("debug", false);
    }

    public static void onLoadSpell() {
        MagicPackage magicPackage = new MagicPackage("paper.onload");
        magicPackage.loadFiles(getInstance().getDataFolder() + "/onload");
        for (MagicInstance value : magicPackage.getMagicInstances().values()) {
            SpellContext spellContext = value.getSpell(getMagicManager()).execute(getContext());
            if (MagicPaper.isDebug() && spellContext.hasExecuteError()) {
                List<String> log = PaperErrorUtils.getErrorAllLog(spellContext, "&c");
                MagicPaper.getSender().sendToLogger(log);
            }
        }
    }

    public static void importSpell(ContextMap contextMap) {
        magicPackage = new MagicPackage("paper.import");
        magicPackage.loadFiles(getInstance().getDataFolder() + "/import");
        magicPackage.importPackage(contextMap, getMagicManager());
    }

}
