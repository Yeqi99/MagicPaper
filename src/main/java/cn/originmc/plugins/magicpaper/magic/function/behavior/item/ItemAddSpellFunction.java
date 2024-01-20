package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.function.results.NumberResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.MagicItem;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemAddSpellFunction extends ArgsFunction {

    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id=argsSetting.getId();
        switch (id){
            case "A":{
                ItemStack itemStack= (ItemStack) args.get(0).getObject();
                String spellName=args.get(1).toString();
                NumberResult coolDownResult = (NumberResult) args.get(2);
                String slot = args.get(3).toString();
                String action = args.get(4).toString();
                MagicItem magicItem=new MagicItem(itemStack);
                switch (action){
                    case "left":{
                        magicItem.setLeftSpell(spellName,coolDownResult.toLong(),slot);
                        break;
                    }
                    case "right":{
                        magicItem.setRightSpell(spellName,coolDownResult.toLong(),slot);
                        break;
                    }
                    case "shiftLeft":{
                        magicItem.setShiftLeftSpell(spellName,coolDownResult.toLong(),slot);
                        break;
                    }
                    case "shiftRight":{
                        magicItem.setShiftRightSpell(spellName,coolDownResult.toLong(),slot);
                        break;
                    }
                    default:{
                        return new ErrorResult("ARGUMENTS_ERROR","Function itemAddSpell need action as fifth argument");
                    }
                }
                return new ItemStackResult(magicItem.getItemStack());
            }
            case "B":{
                ItemStack itemStack= (ItemStack) args.get(0).getObject();
                String spellName=args.get(1).toString();
                String coolDown=args.get(2).toString();
                String slot = args.get(3).toString();
                String action = args.get(4).toString();
                MagicItem magicItem=new MagicItem(itemStack);
                switch (action){
                    case "left":{
                        magicItem.setLeftSpell(spellName,new NumberResult(coolDown).toLong(),slot);
                        break;
                    }
                    case "right":{
                        magicItem.setRightSpell(spellName,new NumberResult(coolDown).toLong(),slot);
                        break;
                    }
                    case "shiftLeft":{
                        magicItem.setShiftLeftSpell(spellName,new NumberResult(coolDown).toLong(),slot);
                        break;
                    }
                    case "shiftRight":{
                        magicItem.setShiftRightSpell(spellName,new NumberResult(coolDown).toLong(),slot);
                        break;
                    }
                    default:{
                        return new ErrorResult("ARGUMENTS_ERROR","Function itemAddSpell need action as fifth argument");
                    }
                }
                return new ItemStackResult(magicItem.getItemStack());
            }
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings = new ArrayList<>();
        argsSettings.add(
                new ArgsSetting("A")
                        .addArgType("ItemStack").addArgType("String").addArgType("Number").addArgType("String").addArgType("String")
                        .addInfo("item spellName coolDown slot action")
                        .addInfo("Set item spell")
                        .addInfo("slot:h,c,l,s,mh,oh")
                        .addInfo("action:left,right,shiftLeft,shiftRight")
                        .setResultType("ItemStack")
        );
        argsSettings.add(
                new ArgsSetting("B")
                        .addArgType("ItemStack").addArgType("String").addArgType("String").addArgType("String").addArgType("String")
                        .addInfo("item spellName coolDown slot action")
                        .addInfo("Set item spell")
                        .addInfo("slot:h,c,l,s,mh,oh")
                        .addInfo("action:left,right,shiftLeft,shiftRight")
                        .setResultType("ItemStack")
        );
        return argsSettings;
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemAddSpell";
    }
}
