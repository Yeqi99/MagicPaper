package cn.originmc.plugins.magicpaper.magic.function.dataentity;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.object.SpellContext;

import java.util.ArrayList;
import java.util.List;

public class DataEntityRemoverFunction extends ArgsFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> list, ArgsSetting argsSetting) {
        return null;
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings = new ArrayList<>();
        argsSettings.add(new ArgsSetting("A")
                .addArgType("String").addArgType("String")
                .addInfo("dataEntityId dataId")
                .addInfo("Remove dataEntity's data by id")
                .setResultType(".")
        );
        argsSettings.add(new ArgsSetting("B")
                .addArgType("String")
                .addInfo("dataEntityId")
                .addInfo("Remove dataEntity by id")
                .setResultType("Null")
        );
        return argsSettings;
    }

    @Override
    public String getName() {
        return "dataEntityRemover";
    }

    @Override
    public String getType() {
        return "DATA_ENTITY";
    }
}
