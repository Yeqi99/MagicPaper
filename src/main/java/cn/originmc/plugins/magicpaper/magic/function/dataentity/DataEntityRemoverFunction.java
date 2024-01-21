package cn.originmc.plugins.magicpaper.magic.function.dataentity;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.ResultUtils;
import cn.originmc.plugins.magicpaper.MagicPaper;

import java.util.ArrayList;
import java.util.List;

public class DataEntityRemoverFunction extends ArgsFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id = argsSetting.getId();
        switch (id){
            case "A":{
                String dataEntityId = args.get(0).toString();
                String dataId = args.get(1).toString();
                if(!MagicPaper.dataEntityManager.hasDataEntity(dataEntityId)){
                    return new NullResult();
                }
                if (!MagicPaper.dataEntityManager.hasData(dataEntityId,dataId)){
                    return new NullResult();
                }
                Object o = MagicPaper.dataEntityManager.getData(dataEntityId,dataId);
                MagicPaper.dataEntityManager.removeData(dataEntityId,dataId);
                return ResultUtils.reduction(o);
            }
            case "B":{
                String dataEntityId = args.get(0).toString();
                MagicPaper.dataEntityManager.removeDataEntity(dataEntityId);
                return new NullResult();
            }
        }
        return new NullResult();
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
