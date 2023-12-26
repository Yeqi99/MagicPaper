package cn.originmc.plugins.magicpaper.magic.function.random;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RandomUUIDFunction extends ArgsFunction {

    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        return new StringResult(UUID.randomUUID().toString());
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings = new ArrayList<>();
        argsSettings.add(new ArgsSetting("A")
                .addArgType("...")
                .addInfo("any")
                .addInfo("generate random uuid")
                .setResultType("String")
        );
        return argsSettings;
    }

    @Override
    public String getType() {
        return "RANDOM";
    }

    @Override
    public String getName() {
        return "randomUUID";
    }
}
