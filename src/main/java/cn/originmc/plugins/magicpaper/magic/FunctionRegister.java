package cn.originmc.plugins.magicpaper.magic;

import cn.origincraft.magic.MagicManager;
import cn.originmc.plugins.magicpaper.magic.function.behavior.*;
import cn.originmc.plugins.magicpaper.magic.function.hook.luckperms.PlayerLPMetaGetFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.luckperms.PlayerLPMetaSetFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.luckperms.PlayerLPTempMetaSetFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.placeholderapi.PlaceholderAPIFunction;
import cn.originmc.plugins.magicpaper.magic.function.object.LocationFunction;
import cn.originmc.plugins.magicpaper.magic.function.object.PlayerFunction;
import dev.rgbmc.expression.managers.FunctionManager;

public class FunctionRegister {
    public static void register(MagicManager magicManager) {
        FunctionManager fm = magicManager.getFastExpression().getFunctionManager();
        // hook.luckperms
        fm.register(new PlayerLPMetaGetFunction(),"getlpmeta");
        fm.register(new PlayerLPMetaSetFunction(),"lpmeta");
        fm.register(new PlayerLPTempMetaSetFunction(),"lptmeta");
        // hook.placeholderapi
        fm.register(new PlaceholderAPIFunction(),"papi");
        // object
        fm.register(new PlayerFunction(),"p");
        fm.register(new LocationFunction(),"loc");
        // behavior
        fm.register(new ConsoleCommandFunction(),"ccommand");
        fm.register(new PlayerCommandFunction(),"pcommand");
        fm.register(new SenderToPlayerFunction(),"stp");
        fm.register(new SenderToAllPlayerFunction(),"stap");
        fm.register(new PlayerTeleportFunction(),"ptp");
    }
}
