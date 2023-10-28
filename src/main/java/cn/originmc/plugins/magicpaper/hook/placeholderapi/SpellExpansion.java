package cn.originmc.plugins.magicpaper.hook.placeholderapi;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.NormalContext;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.data.manager.MagicDataManager;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpellExpansion extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "magic";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Yeqi";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.2.2";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier==null){
            return "";
        }
        Spell spell = MagicDataManager.getSpell(identifier);
        if (spell==null){
            return  "";
        }
        NormalContext normalContext=new NormalContext();
        if (player!=null){
            normalContext.putVariable("self",player);
        }
        SpellContext spellContext = spell.execute(MagicPaper.getContext());
        FunctionResult functionResult= spellContext.getSpellReturn();
        if (functionResult instanceof StringResult){
            return ((StringResult) functionResult).getString();
        }
        return "";
    }
}
