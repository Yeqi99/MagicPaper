package cn.originmc.plugins.magicpaper.magic.function.cooldown;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtil;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.cooldown.CoolDown;
import cn.originmc.plugins.magicpaper.cooldown.CoolDownManager;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import cn.originmc.plugins.magicpaper.util.text.Color;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.entity.Player;

import java.util.List;

public class GOCDFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size() < 2) {
            return new ErrorResult("FUNCTION_ARGS_ERROR", "GOCd don't have enough args.");
        }
        FunctionResult arg1 = args.get(0);
        FunctionResult arg2 = args.get(1);
        if (!(arg1 instanceof StringResult)) {
            return new ErrorResult("TYPE_ERROR", "The first arg must be string.");
        }
        if (!(arg2 instanceof StringResult)) {
            return new ErrorResult("TYPE_ERROR", "The second arg must be string.");
        }
        String cdId = ((StringResult) arg1).getString();
        String cdTime = ((StringResult) arg2).getString();
        boolean inCd = MagicPaper.getCoolDownManager().isCoolDownActive(cdId);
        if (inCd) {
            spellContext.putExecuteBreak(true);
            if (args.size() > 2) {
                if (!spellContext.getContextMap().hasVariable("self")) {
                    return new NullResult();
                }
                FunctionResult functionResult = args.get(2);
                if (!(functionResult instanceof StringResult)) {
                    return new ErrorResult("TYPE_ERROR", "The third arg must be string.");
                }
                String str = ((StringResult) functionResult).getString();
                PlayerResult playerResult= (PlayerResult) spellContext.getContextMap().getVariable("self");
                Player self = playerResult.getPlayer();
                self.sendActionBar(Color.toColor(str));
                return new NullResult();
            }
        } else {
            CoolDown coolDown = new CoolDown(cdId, Long.parseLong(cdTime));
            MagicPaper.getCoolDownManager().addCoolDown(coolDown);
        }
        return new NullResult();
    }

    @Override
    public String getType() {
        return "COOLDOWN";
    }

    @Override
    public String getName() {
        return "gocd";
    }
}
