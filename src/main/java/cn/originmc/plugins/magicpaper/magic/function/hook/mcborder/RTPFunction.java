package cn.originmc.plugins.magicpaper.magic.function.hook.mcborder;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.hook.McBorderHook;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RTPFunction extends ArgsFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id = argsSetting.getId();
        switch (id){
            case "A":{
                Player player= (Player) args.get(0).getObject();
                String world = args.get(1).toString();
                McBorderHook.rtp(player.getName(),world);
                return new NullResult();
            }
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings = new ArrayList<>();

        argsSettings.add(
                new ArgsSetting("A")
                        .addArgType("Player").addArgType("String")
                        .addInfo("player worldName")
                        .addInfo("Random in world from McBorder")
                        .setResultType("Null")
        );
        return argsSettings;
    }

    @Override
    public String getName() {
        return "rtp";
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER_HOOK";
    }
}
