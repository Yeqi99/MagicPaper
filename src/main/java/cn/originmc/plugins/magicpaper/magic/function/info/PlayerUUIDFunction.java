package cn.originmc.plugins.magicpaper.magic.function.info;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.FunctionUtils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerUUIDFunction extends ArgsFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        Player player= (Player) args.get(0).getObject();
        return new StringResult(player.getUniqueId().toString());
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings=new ArrayList<>();
        ArgsSetting argsSetting= FunctionUtils.createArgsSetting(
                1,
                "Player ",
                "Get the player's UUID.",
                "String");
        argsSetting.setId("playerUUID");
        argsSettings.add(argsSetting);
        return argsSettings;
    }

    @Override
    public String getType() {
        return "PAPER_INFO";
    }

    @Override
    public String getName() {
        return "playerUUID";
    }
}
