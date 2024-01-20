package cn.originmc.plugins.magicpaper.gui;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.results.ListResult;
import cn.origincraft.magic.object.ContextMap;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class NormalDataButton implements DataButton {
    private final String buttonId;
    private Spell spell;

    public NormalDataButton(Spell spell, String buttonId) {
        this.spell = spell;
        this.buttonId = buttonId;
    }

    @Override
    public List<ItemStack> getButtons(ContextMap contextMap) {
        SpellContext spellContext = spell.execute(contextMap);
        FunctionResult listResult = spellContext.getSpellReturn();
        if (!(listResult instanceof ListResult)) {
            return new ArrayList<>();
        }
        ListResult listResult1 = (ListResult) listResult;
        List<?> buttons = listResult1.getList();
        List<ItemStack> itemStacks = new ArrayList<>();
        for (Object button : buttons) {
            if (button instanceof ItemStackResult) {
                ItemStackResult itemStackResult = (ItemStackResult) button;
                itemStacks.add(itemStackResult.getItemStack());
            } else if (button instanceof ItemStack) {
                itemStacks.add((ItemStack) button);
            }
        }
        return itemStacks;
    }

    @Override
    public char getButtonChar() {
        return buttonId.charAt(0);
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }
    public String getButtonId() {
        return buttonId;
    }
}
