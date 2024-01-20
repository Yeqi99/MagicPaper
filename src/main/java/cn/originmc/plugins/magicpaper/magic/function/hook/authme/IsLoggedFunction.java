package cn.originmc.plugins.magicpaper.magic.function.hook.authme;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.BooleanResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.hook.AuthMeHook;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class IsLoggedFunction extends ArgsFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id=argsSetting.getId();
        switch (id){
            case "A":{
                Player player= (Player) args.get(0).getObject();
                return new BooleanResult(AuthMeHook.isLogged(player));
            }
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings = new ArrayList<>();

        argsSettings.add(
                new ArgsSetting("A")
                        .addArgType("Player")
                        .addInfo("player")
                        .addInfo("Determine if the player has logged in from authme")
                        .setResultType("Boolean")
        );
        return argsSettings;
    }

    @Override
    public String getName() {
        return "isLogged";
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER_HOOK";
    }
}
