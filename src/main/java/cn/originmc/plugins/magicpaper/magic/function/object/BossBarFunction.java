package cn.originmc.plugins.magicpaper.magic.function.object;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.BooleanResult;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtil;
import cn.originmc.plugins.magicpaper.MagicPaper;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BossBarFunction extends ArgsFunction {


    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id=argsSetting.getId();
        switch (id){
            case "A":{
                String bossbarId= (String) args.get(0).getObject();
                String title= (String) args.get(1).getObject();
                String progress= (String) args.get(2).getObject();
                String color= (String) args.get(3).getObject();
                String style= (String) args.get(4).getObject();
                if (VariableUtil.tryDouble(progress)){
                    return new ErrorResult("ARGS_ERROR","The third arg must be double str");
                }
                MagicPaper.getBossBarManager().createBossBar(
                        bossbarId,
                        title,
                        BarColor.valueOf(color),
                        BarStyle.valueOf(style)
                );
                MagicPaper.getBossBarManager().setBossBarProgress(bossbarId,Double.parseDouble(progress));
                return new NullResult();
            }
            case "B":{
                Player player= (Player) args.get(0).getObject();
                String bossbarId= (String) args.get(1).getObject();
                String action= (String) args.get(2).getObject();
                if (action.equalsIgnoreCase("show")){
                    MagicPaper.getBossBarManager().showBossBar(player,bossbarId);
                }else if (action.equalsIgnoreCase("hide")){
                    MagicPaper.getBossBarManager().hideBossBar(player,bossbarId);
                }
                return new BooleanResult(true);
            }
            case "C":{
                String bossbarId= (String) args.get(0).getObject();
                MagicPaper.getBossBarManager().removeBossBar(bossbarId);
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
                        .addArgType("String String String String String")
                        .addInfo("id title progress color style")
                        .addInfo("Create a bossbar")
                        .addInfo("color: BLUE, GREEN, PINK, PURPLE, RED, WHITE, YELLOW")
                        .addInfo("style: SEGMENTED_10, SEGMENTED_12, SEGMENTED_20, SEGMENTED_6, SOLID")
                        .setResultType("Null")
        );
        argsSettings.add(
                new ArgsSetting("B")
                        .addArgType("Player String String")
                        .addInfo("player id action")
                        .addInfo("Show or hide a bossbar")
                        .addInfo("action: show, hide")
                        .setResultType("Null")
        );
        argsSettings.add(
                new ArgsSetting("C")
                        .addArgType("String")
                        .addInfo("id")
                        .addInfo("Delete a bossbar")
                        .setResultType("Null")
        );
        return argsSettings;
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER";
    }

    @Override
    public String getName() {
        return "bossbar";
    }
}
