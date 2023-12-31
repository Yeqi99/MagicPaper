package cn.originmc.plugins.magicpaper.magic.function.hook.placeholderapi;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.ListResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.hook.PlaceholderAPIHook;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderAPIFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2) {
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "PlaceholderAPI function requires at least two arguments.");
        }
        FunctionResult p = args.get(0);
        FunctionResult message = args.get(1);
        if (p instanceof PlayerResult){
            if (message instanceof StringResult) {
                Player player=((PlayerResult) p).getPlayer();
                StringResult stringResult = (StringResult) message;
                String messageString = stringResult.getString();
                messageString="%"+messageString+"%";
                String result = PlaceholderAPIHook.getPlaceholder(player, messageString);
                return new StringResult(result);
            }else  if (message instanceof ListResult){
                Player player=((PlayerResult) p).getPlayer();
                ListResult listResult=(ListResult) message;
                List<?> list=listResult.getList();

                List<Object> messageList= new ArrayList<>();
                for (Object object:list){
                    if (object instanceof String) {
                        messageList.add(PlaceholderAPIHook.getPlaceholder(player, "%"+object+"%"));
                    }
                }
                return new ListResult(messageList);
            }else {
                return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
            }
        }else {
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER_HOOK";
    }

    @Override
    public String getName() {
        return "placeholderAPI";
    }
}
