package cn.originmc.plugins.magicpaper.magic.function.object;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.ListResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.FunctionUtils;
import cn.origincraft.magic.utils.VariableUtil;
import cn.originmc.plugins.magicpaper.magic.result.EntityResult;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class EntityFunction extends ArgsFunction {


    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id=argsSetting.getId();
        switch (id){
            case "A":{
                Player player= (Player) args.get(0).getObject();
                return new EntityResult(player);
            }
            case "B":{
                Location location= (Location) args.get(0).getObject();
                String redis= (String) args.get(1).getObject();
                String amount= (String) args.get(2).getObject();
                if (!VariableUtil.tryDouble(redis)){
                    return new ErrorResult("ARGS_ERROR","The second arg must be double str");
                }
                if (!VariableUtil.tryInt(amount)){
                    return new ErrorResult("ARGS_ERROR","The third arg must be int str");
                }
                double r=Double.parseDouble(redis);
                int a=Integer.parseInt(amount);
                Collection<LivingEntity> entities = location.getNearbyLivingEntities(r);
                Entity[] entitiesArray = entities.toArray(new Entity[0]);
                List<Entity> entityList = new ArrayList<>(Arrays.asList(entitiesArray).subList(0, a));
                return new ListResult(entityList);
            }
            case "C":{
                Object object = args.get(0).getObject();
                if (object instanceof Entity) {
                    return new EntityResult((Entity) object);
                }else {
                    return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Entity is not a player.");
                }
            }
            case "D":{
                Location location= (Location) args.get(0).getObject();
                String type= (String) args.get(1).getObject();
                String display= (String) args.get(2).getObject();
                if (!VariableUtil.tryInt(type)){
                    return new ErrorResult("ARGS_ERROR","The second arg must be int str");
                }
                if (!VariableUtil.tryInt(display)){
                    return new ErrorResult("ARGS_ERROR","The third arg must be int str");
                }
                EntityType entityType = EntityType.valueOf(type);
                Entity entity = location.getWorld().spawnEntity(location, entityType);
                entity.setCustomNameVisible(true);
                entity.setCustomName(display);
                return new EntityResult(entity);
            }
        }
        return null;
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings = new ArrayList<>();
        ArgsSetting argsSetting1 = FunctionUtils.createArgsSetting(
                "Player",
                "player" +
                        "\nGet the entity by player.",
                "Entity");
        argsSetting1.setId("A");
        ArgsSetting argsSetting2 = FunctionUtils.createArgsSetting(
                "Location String String",
                "location redis amount" +
                        "\nGet entityList nearby location.",
                "Entity");
        argsSetting2.setId("B");
        ArgsSetting argsSetting3 = FunctionUtils.createArgsSetting(
                "Object",
                "object" +
                        "\nGet the entity by object.",
                "Entity");
        argsSetting3.setId("C");
        ArgsSetting argsSetting4 = FunctionUtils.createArgsSetting(
                "Location String String",
                "location type display" +
                        "\nspawn a entity.",
                "Entity");
        argsSetting4.setId("D");

        argsSettings.add(argsSetting1);
        argsSettings.add(argsSetting2);
        argsSettings.add(argsSetting3);
        argsSettings.add(argsSetting4);
        return argsSettings;
    }

    @Override
    public String getType() {
        return "SUPER_OBJECT";
    }

    @Override
    public String getName() {
        return "entity";
    }
}
