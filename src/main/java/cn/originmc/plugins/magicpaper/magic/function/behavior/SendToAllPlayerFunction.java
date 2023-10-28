package cn.originmc.plugins.magicpaper.magic.function.behavior;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.ListResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;

import java.util.List;

public class SendToAllPlayerFunction extends NormalFunction {
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
            List<?> list=listResult.getList();

            List<String> messageList= new java.util.ArrayList<>();
            for (Object object:list){
                if (object instanceof String) {
                    messageList.add((String) object);
                }else {
                    messageList.add(object.toString());
                }
            }
            MagicPaper.getSender().sendToAllPlayer(messageList);
        }
        return new NullResult();
    }

    @Override
    public String getType() {
        return "PAPER_BEHAVIOR";
    }

    @Override
    public String getName() {
        return "senderToAllPlayer";
    }
}
