package cn.originmc.plugins.magicpaper.magic.function.gui;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.gui.MagicGui;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.entity.Player;

import java.util.List;

public class GuiDataClearFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("ARGS_ERROR", "GuiDataClearFunction need 2 args");
        }
        FunctionResult arg0=args.get(0);
        FunctionResult arg1=args.get(1);
        if (!(arg0 instanceof PlayerResult)){
            return new ErrorResult("ARGS_ERROR", "The first arg of GuiDataClearFunction must be a player");
        }
        if (!(arg1 instanceof StringResult)){
            return new ErrorResult("ARGS_ERROR", "The second arg of GuiDataClearFunction must be a string");
        }
        Player player=((PlayerResult) arg0).getPlayer();
        String guiId=((StringResult) arg1).getString();
        MagicPaper.getMagicGuiManager().removeGui(player, guiId);
        return new NullResult();
    }

    @Override
    public String getType() {
        return "PAPER_GUI";
    }

    @Override
    public String getName() {
        return "guiDataClear";
    }
}
