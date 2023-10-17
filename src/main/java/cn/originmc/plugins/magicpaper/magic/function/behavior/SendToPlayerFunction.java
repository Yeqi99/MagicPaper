package cn.originmc.plugins.magicpaper.magic.function.behavior;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.ListResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SendToPlayerFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "SenderToPlayer function requires at least two arguments.");
        }

        if (args.get(0) instanceof PlayerResult){
            PlayerResult playerResult=(PlayerResult) args.get(0);
            Player player=playerResult.getPlayer();
            if (args.get(1) instanceof StringResult){
                StringResult message=(StringResult) args.get(1);
                MagicPaper.getSender().sendToPlayer(player, message.getString());
                return new NullResult();
            }else if (args.get(1) instanceof ListResult){
                ListResult listResult=(ListResult) args.get(1);
                List<Object> list=listResult.getList();

                List<String> messageList= new ArrayList<>();
                for (Object object:list){
                    if (object instanceof String) {
                        messageList.add((String) object);
                        MagicPaper.getSender().sendToAllPlayer((String) object);
                    }
                }
                MagicPaper.getSender().sendToPlayer(player, messageList);
                return new NullResult();
            }else {
                return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
            }
        }else {
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
    }

    @Override
    public String getType() {
        return "PAPER_BEHAVIOR";
    }

    @Override
    public String getName() {
        return "senderToPlayer";
    }
}
