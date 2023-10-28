package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.LongResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.MagicItem;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemAddSpellFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<5){
            return new ErrorResult("ARGUMENTS_NOT_ENOUGH","Function itemAddSpell need 5 arguments");
        }
        FunctionResult itemResult=args.get(0);
        if (!(itemResult instanceof ItemStackResult)){
            return new ErrorResult("ARGUMENTS_ERROR","Function itemAddSpell need item as first argument");
        }
        FunctionResult spellNameResult=args.get(1);
        if (!(spellNameResult instanceof StringResult)){
            return new ErrorResult("ARGUMENTS_ERROR","Function itemAddSpell need spellName as second argument");
        }
        FunctionResult coolDownResult=args.get(2);
        if (!(coolDownResult instanceof LongResult)){
            return new ErrorResult("ARGUMENTS_ERROR","Function itemAddSpell need coolDown as third argument");
        }
        FunctionResult slotResult=args.get(3);
        if (!(slotResult instanceof StringResult)){
            return new ErrorResult("ARGUMENTS_ERROR","Function itemAddSpell need slot as fourth argument");
        }
        FunctionResult action=args.get(4);
        if (!(action instanceof StringResult)){
            return new ErrorResult("ARGUMENTS_ERROR","Function itemAddSpell need action as fifth argument");
        }
        ItemStack itemStack=((ItemStackResult) itemResult).getItemStack();
        String spellName=((StringResult) spellNameResult).getString();
        long coolDown=((LongResult) coolDownResult).getLong();
        String slot=((StringResult) slotResult).getString();
        String actionString=((StringResult) action).getString();
        MagicItem magicItem=new MagicItem(itemStack);
        switch (actionString){
            case "left":{
                magicItem.setLeftSpell(spellName,coolDown,slot);
                break;
            }
            case "right":{
                magicItem.setRightSpell(spellName,coolDown,slot);
                break;
            }
            case "shiftLeft":{
                magicItem.setShiftLeftSpell(spellName,coolDown,slot);
                break;
            }
            case "shiftRight":{
                magicItem.setShiftRightSpell(spellName,coolDown,slot);
                break;
            }
            default:{
                return new ErrorResult("ARGUMENTS_ERROR","Function itemAddSpell need action as fifth argument");
            }
        }
        return new ItemStackResult(magicItem.getItemStack());
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
