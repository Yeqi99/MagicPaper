package cn.originmc.plugins.magicpaper.magic.function.control;

import cn.origincraft.magic.function.HasVariableFunction;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.entity.Player;


import java.util.List;

public class PlayerTraversalFunction extends HasVariableFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<3){
            return new ErrorResult("FUNCTION_ARGS_ERROR","PlayerTraversalFunction need 3 args.");
        }
        FunctionResult container=args.get(0);
        FunctionResult value=args.get(1);
        FunctionResult index=args.get(2);
        if (!(container instanceof StringResult)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Argument need to be a string.");
        }
        if (!(value instanceof StringResult && index instanceof StringResult)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Argument need to be a string.");
        }
        String containerString=((StringResult) container).getString();
        String valueString=((StringResult) value).getString();
        String indexString=((StringResult) index).getString();
        List<Player> players= (List<Player>) spellContext.getContextMap().getVariable(containerString);
        if (!spellContext.getContextMap().hasVariable(indexString)){
            spellContext.getContextMap().putVariable(indexString, 0);
        }
        int oi= (int) spellContext.getContextMap().getVariable(indexString);
        if (oi<players.size()){
            Player o=players.get(oi);
            spellContext.getContextMap().putVariable(valueString, new PlayerResult(o));
            spellContext.getContextMap().putVariable(indexString, oi+1);
            spellContext.putExecuteNext(spellContext.getExecuteIndex());
            return new StringResult(valueString);
        }else {
            spellContext.putExecuteIndexAllow(spellContext.getExecuteIndex(),false);
            return new StringResult(valueString);
        }
    }

    @Override
    public String getType() {
        return "PAPER_SYSTEM";
    }

    @Override
    public String getName() {
        return "playerTraversal";
    }
}
