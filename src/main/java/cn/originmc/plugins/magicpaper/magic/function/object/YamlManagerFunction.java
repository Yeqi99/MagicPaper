package cn.originmc.plugins.magicpaper.magic.function.object;


import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.FunctionUtils;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.magic.result.YamlManagerResult;
import cn.originmc.tools.minecraft.yamlcore.object.YamlManager;

import java.util.ArrayList;
import java.util.List;

public class YamlManagerFunction extends ArgsFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id = argsSetting.getId();
        switch (id) {
            case "A": {
                String path = (String) args.get(0).getObject();
                String name = (String) args.get(1).getObject();
                String deep = (String) args.get(2).getObject();
                YamlManager yamlManager=new YamlManager(MagicPaper.getInstance(),path,name,Boolean.parseBoolean(deep));
                return new YamlManagerResult(yamlManager);
            }
            case "B": {
                String name = (String) args.get(0).getObject();
                String deep = (String) args.get(1).getObject();
                YamlManager yamlManager=new YamlManager(MagicPaper.getInstance(),name,Boolean.parseBoolean(deep));
                return new YamlManagerResult(yamlManager);
            }
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings = new ArrayList<>();
        argsSettings.add(
                FunctionUtils
                        .createArgsSetting(
                                "A",
                                "String String String",
                                "path name deep",
                                "YamlManager")
                        .addInfo("Create a YamlManager.")
                        .addInfo("path: The path of the space.")
                        .addInfo("name: The name of the yaml space.")
                        .addInfo("deep: Whether to load the file recursively.")
        );
        argsSettings.add(
                FunctionUtils
                        .createArgsSetting(
                                "B",
                                "String String",
                                "name deep",
                                "YamlManager")
                        .addInfo("Create a YamlManager to plugin's data folder.")
                        .addInfo("name: The name of the yaml file.")
                        .addInfo("deep: Whether to load the file recursively.")
        );
        argsSettings.add(
                FunctionUtils
                        .createArgsSetting(
                                "C",
                                "YamlManager String",
                                "yamlManager action",
                                "YamlManager")
                        .addInfo("Create a YamlManager to plugin's data folder.")
                        .addInfo("action: amount")
        );
        return argsSettings;
    }

    @Override
    public String getName() {
        return "yamlManager";
    }

    @Override
    public String getType() {
        return "SUPER_OBJECT";
    }
}
