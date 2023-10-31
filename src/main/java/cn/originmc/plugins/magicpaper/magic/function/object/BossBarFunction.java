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
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
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
                Component component= (Component) args.get(1).getObject();
                String progress= (String) args.get(2).getObject();
                String color= (String) args.get(3).getObject();
                String overlay= (String) args.get(4).getObject();
                if (VariableUtil.tryDouble(progress)){
                    return new ErrorResult("ARGS_ERROR","The third arg must be double str");
                }
                boolean flag= MagicPaper.getBossBarManager().createBossBar(
                        bossbarId,
                        component,
                        Float.parseFloat(progress),
                        BossBar.Color.valueOf(color),
                        BossBar.Overlay.valueOf(overlay)
                );
                return new BooleanResult(flag);
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
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings = new ArrayList<>();
        argsSettings.add(
                new ArgsSetting("A")
                        .addArgType("String Component String String String")
                        .addInfo("id component progress color overlay")
                        .addInfo("Create a bossbar")
                        .addInfo("progress: 0.0-1.0")
                        .addInfo("color: BLUE, GREEN, PINK, PURPLE, RED, WHITE, YELLOW")
                        .addInfo("overlay: NOTCHED_6, NOTCHED_10, NOTCHED_12, NOTCHED_20")
                        .setResultType("Boolean")
        );
        argsSettings.add(
                new ArgsSetting("B")
                        .addArgType("Player String String")
                        .addInfo("player id action")
                        .addInfo("Show or hide a bossbar")
                        .addInfo("action: show, hide")
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
