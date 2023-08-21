package cn.originmc.plugins.magicpaper.magic.function.behavior;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.ListResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.utils.text.Sender;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;

import java.util.List;

public class SenderToAllPlayerFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()) {
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "SenderToAllPlayer function requires at least one argument.");
        }
        FunctionResult message = args.get(0);
        if (message instanceof StringResult) {
            MagicPaper.getSender().sendToAllPlayer(((StringResult) message).getString());
        }else if (message instanceof ListResult){
            ListResult listResult=(ListResult) message;
            List<Object> list=listResult.getList();

            List<String> messageList= new java.util.ArrayList<>();
            for (Object object:list){
                if (object instanceof String) {
                    messageList.add((String) object);
                }
            }
            MagicPaper.getSender().sendToAllPlayer(messageList);
        }
        return new NullResult();
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "senderToAllPlayer";
    }
}
