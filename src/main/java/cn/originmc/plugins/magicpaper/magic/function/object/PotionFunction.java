package cn.originmc.plugins.magicpaper.magic.function.object;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.BooleanResult;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.FunctionUtils;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.magic.result.PotionResult;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class PotionFunction extends ArgsFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id = argsSetting.getId();
        switch (id) {
            case "A": {
                String type = (String) args.get(0).getObject();
                String level = (String) args.get(1).getObject();
                String time = (String) args.get(2).getObject();
                PotionEffectType potionEffectType = PotionEffectType.getByName(type);
                if (potionEffectType == null) {
                    return new ErrorResult("TYPE_ERROR", "The potion type is not exist.");
                }
                PotionEffect potionEffect = new PotionEffect(potionEffectType, Integer.parseInt(time), Integer.parseInt(level));
                return new PotionResult(potionEffect);
            }
            case "B": {
                Player player = (Player) args.get(0).getObject();
                PotionEffect potionEffect = (PotionEffect) args.get(1).getObject();
                Bukkit.getScheduler().runTask(MagicPaper.getInstance(), () -> {
                    player.addPotionEffect(potionEffect);
                });
                return new NullResult();
            }
            case "C": {
                Player player = (Player) args.get(0).getObject();
                boolean flag = player.clearActivePotionEffects();
                return new BooleanResult(flag);
            }
            case "D": {
                Player player = (Player) args.get(0).getObject();
                String type = (String) args.get(1).getObject();
                PotionEffectType potionEffectType = PotionEffectType.getByName(type);
                if (potionEffectType == null) {
                    return new ErrorResult("TYPE_ERROR", "The potion type is not exist.");
                }
                Bukkit.getScheduler().runTask(MagicPaper.getInstance(), () -> {
                    player.removePotionEffect(potionEffectType);
                });
                return new NullResult();
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
                                "type level time",
                                "Potion")
                        .addInfo("Create a potion")
                        .addInfo("type: speed(速度) strength(力量) fire_resistance(火焰抗性) poison(中毒) regeneration(再生) weakness(虚弱) water_breathing(水下呼吸) night_vision(夜视) invisibility(隐身)")
                        .addInfo("level: 1, 2, 3, 4, 5")
        );
        argsSettings.add(
                FunctionUtils
                        .createArgsSetting(
                                "B",
                                "Player Potion",
                                "player potion",
                                "Null")
                        .addInfo("Give a potion to a player")
        );
        argsSettings.add(
                FunctionUtils
                        .createArgsSetting(
                                "C",
                                "Player",
                                "player",
                                "Boolean")
                        .addInfo("Clear all potion effects of a player")
        );
        argsSettings.add(
                FunctionUtils
                        .createArgsSetting(
                                "D",
                                "Player String",
                                "player type",
                                "Null")
                        .addInfo("Clear a potion effect of a player")
                        .addInfo("type: speed(速度) strength(力量) fire_resistance(火焰抗性) poison(中毒) regeneration(再生) weakness(虚弱) water_breathing(水下呼吸) night_vision(夜视) invisibility(隐身)")
        );
        return argsSettings;
    }

    @Override
    public String getName() {
        return "potion";
    }

    @Override
    public String getType() {
        return "SUPER_OBJECT";
    }
}
