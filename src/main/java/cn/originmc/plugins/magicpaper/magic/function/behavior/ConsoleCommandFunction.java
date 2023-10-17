package cn.originmc.plugins.magicpaper.magic.function.behavior;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.Bukkit;

import java.util.List;

public class ConsoleCommandFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()) {
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "ConsoleCommand function requires at least two arguments.");
        }
        if (args.get(0) instanceof StringResult) {
            StringResult command = (StringResult) args.get(0);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.getString());
            return new NullResult();
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
        return "consoleCommand";
    }
}
