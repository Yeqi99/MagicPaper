package cn.originmc.plugins.magicpaper.magic.function.dataentity;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.BooleanResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.ResultUtils;
import cn.originmc.plugins.magicpaper.MagicPaper;

import java.util.ArrayList;
import java.util.List;

public class DataEntityFunction extends ArgsFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id = argsSetting.getId();
        switch (id){
            case "A":{
                String dataEntityId = args.get(0).toString();
                String dataId = args.get(1).toString();
                FunctionResult data = args.get(2);

                MagicPaper.dataEntityManager.putData(dataEntityId,dataId,ResultUtils.reduction(data));
                return new NullResult();
            }
            case "B":{
                String dataEntityId = args.get(0).toString();
                String dataId = args.get(1).toString();
                Object o= MagicPaper.dataEntityManager.getData(dataEntityId,dataId);
                if (o==null){
                    return new NullResult();
                }

                return ResultUtils.reduction(o);
            }
            case "C":{
                String dataEntityId = args.get(0).toString();
                return new BooleanResult(MagicPaper.dataEntityManager.hasDataEntity(dataEntityId));
            }

        }
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
