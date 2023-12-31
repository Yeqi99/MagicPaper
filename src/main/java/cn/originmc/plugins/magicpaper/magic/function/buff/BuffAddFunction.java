package cn.originmc.plugins.magicpaper.magic.function.buff;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.SpellResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.buff.MagicBuff;

import java.util.List;

public class BuffAddFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<3){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "BuffAdd don't have enough args.");
        }
        FunctionResult arg1 = args.get(0);
        FunctionResult arg2 = args.get(1);
        FunctionResult arg3 = args.get(2);
        if (!(arg1 instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "The first arg must be string.");
        }
        if (!(arg2 instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "The second arg must be string.");
        }
        if (!(arg3 instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "The third arg must be string int.");
        }
        String targetId = ((StringResult) arg1).getString();
        String buffId = ((StringResult) arg2).getString();
        String time = ((StringResult) arg3).getString();
        String setting="";
        if (args.size()>3){
            FunctionResult arg4 = args.get(3);
            if (!(arg4 instanceof StringResult)){
                return new ErrorResult("TYPE_ERROR", "The fourth arg must be string.");
            }
            setting=((StringResult) arg4).getString();
        }
        MagicBuff magicBuff=new MagicBuff(buffId,Integer.parseInt(time));
        if (args.size()>4){
            List<FunctionResult> spells = args.subList(4, args.size());
            for (FunctionResult spell : spells) {
                if (!(spell instanceof SpellResult)){
                    return new ErrorResult("TYPE_ERROR", "The fifth arg must be spell.");
                }
                Spell spell1 = ((SpellResult) spell).getSpell();
                magicBuff.addSpell(spell1);
            }

        }
        magicBuff.setBuffSetting(setting);
        MagicPaper.getMagicBuffManager().addMagicBuff(targetId,magicBuff);
        return new StringResult(magicBuff.getNowToEnd()+"");
    }

    @Override
    public String getType() {
        return "PAPER_BEHAVIOR";
    }

    @Override
    public String getName() {
        return "buffAdd";
    }
}
