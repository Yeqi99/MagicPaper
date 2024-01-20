package cn.originmc.plugins.magicpaper.magic.function.dataentity;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;

import java.util.ArrayList;
import java.util.List;

public class DataEntityFunction extends ArgsFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> list, ArgsSetting argsSetting) {
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings = new ArrayList<>();
        argsSettings.add(new ArgsSetting("A")
                .addArgType("String").addArgType("String").addArgType(".")
                .addInfo("dataEntityId dataId data")
                .addInfo("Put data to target dataEntity by dataEntityId")
                .setResultType("Null")
        );
        argsSettings.add(new ArgsSetting("B")
                .addArgType("String").addArgType("String")
                .addInfo("dataEntityId dataId")
                .addInfo("Get data by dataId from a dataEntity")
                .setResultType(".")
        );
        argsSettings.add(new ArgsSetting("C")
                .addArgType("String")
                .addInfo("dataEntityId")
                .addInfo("Check if the data entity exists")
                .setResultType("Boolean")
        );
        return argsSettings;
    }

    @Override
    public String getName() {
        return "dataEntity";
    }

    @Override
    public String getType() {
        return "DATA_ENTITY";
    }
}
