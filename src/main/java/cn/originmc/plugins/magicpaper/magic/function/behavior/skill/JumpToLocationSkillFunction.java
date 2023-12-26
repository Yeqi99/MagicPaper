package cn.originmc.plugins.magicpaper.magic.function.behavior.skill;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.function.results.NumberResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.LocationResult;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class JumpToLocationSkillFunction extends ArgsFunction {

    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id = argsSetting.getId();
        switch (id) {
            case "A": {
                Player player = (Player) args.get(0).getObject();
                Location jumpTarget = (Location) args.get(1).getObject();
                String jumpPower = args.get(2).toString();
                String jumpHeight = args.get(3).toString();
                Vector jumpVector = jumpTarget.toVector()
                        .subtract(player.getLocation()
                                .toVector())
                        .normalize()
                        .multiply(new NumberResult(jumpPower).toDouble());
                jumpVector.setY(new NumberResult(jumpHeight).toDouble());
                player.setVelocity(jumpVector);
                return new NullResult();
            }
            case "B": {
                Player player = ((PlayerResult) args.get(0)).getPlayer();
                Location jumpTarget = ((LocationResult) args.get(1)).getLocation();
                NumberResult jumpPower = (NumberResult) args.get(2);
                NumberResult jumpHeight = (NumberResult) args.get(3);
                Vector jumpVector = jumpTarget.toVector()
                        .subtract(player.getLocation()
                                .toVector())
                        .normalize()
                        .multiply(jumpPower.toDouble());
                jumpVector.setY(jumpHeight.toDouble());
                player.setVelocity(jumpVector);
                return new NullResult();
            }
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings = new ArrayList<>();
        argsSettings.add(new ArgsSetting("A")
                .addArgType("Player").addArgType("Location").addArgType("String").addArgType("String")
                .addInfo("player targetLocation power height")
                .addInfo("example: jumpSkill(self location(1 1 1) 1 1)")
                .addInfo("means: Player self jump to location(1 1 1) with power 1 and height 1")
                .setResultType("Null")
        );
        argsSettings.add(new ArgsSetting("B")
                .addArgType("Player").addArgType("Location").addArgType("Number").addArgType("Number")
                .addInfo("player power height")
                .addInfo("example: jumpSkill(self location(1 1 1) num(1) num(1))")
                .addInfo("means: Player self jump to location(1 1 1) with power 1 and height 1")
                .setResultType("Null")
        );
        return argsSettings;
    }

    @Override
    public String getType() {
        return "SKILL";
    }

    @Override
    public String getName() {
        return "jumpToLocationSkill";
    }
}
