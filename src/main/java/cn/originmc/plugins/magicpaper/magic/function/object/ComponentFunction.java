package cn.originmc.plugins.magicpaper.magic.function.object;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtil;
import cn.originmc.plugins.magicpaper.magic.result.ComponentResult;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ComponentFunction extends ArgsFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id = argsSetting.getId();
        switch (id) {
            case "A": {
                String s = (String) args.get(0).getObject();
                MiniMessage miniMessage = MiniMessage.miniMessage();
                if (s.contains("§")) {
                    s = s.replace("§", "&");
                }
                if (s.contains("&")) {
                    Component componentTemp = LegacyComponentSerializer.legacyAmpersand().deserialize(s);
                    String temp = componentTemp.toString();
                    Component result = miniMessage.deserialize(temp);
                    return new ComponentResult(result);
                } else {
                    Component component = miniMessage.deserialize(s);
                    return new ComponentResult(component);
                }
            }
            case "B": {
                Component component = (Component) args.get(0).getObject();
                ItemStack itemStack = (ItemStack) args.get(1).getObject();
                return new ComponentResult(component.hoverEvent(itemStack.asHoverEvent()));
            }
            case "C": {
                Component component = (Component) args.get(0).getObject();
                String type = (String) args.get(1).getObject();
                String value = (String) args.get(2).getObject();
                switch (type) {
                    case "showText": {
                        return new ComponentResult(component.hoverEvent(HoverEvent.showText(Component.text(value))));
                    }
                    case "openUrl": {
                        return new ComponentResult(component.clickEvent(ClickEvent.openUrl(value)));
                    }
                    case "openFile": {
                        return new ComponentResult(component.clickEvent(ClickEvent.openFile(value)));
                    }
                    case "runCommand": {
                        return new ComponentResult(component.clickEvent(ClickEvent.runCommand(value)));
                    }
                    case "suggestCommand": {
                        return new ComponentResult(component.clickEvent(ClickEvent.suggestCommand(value)));
                    }
                    case "copy":{
                        return new ComponentResult(component.clickEvent(ClickEvent.copyToClipboard(value)));
                    }
                    case "changePage": {
                        if (value.equalsIgnoreCase("next")) {
                            return new ComponentResult(component.clickEvent(ClickEvent.changePage(1)));
                        } else if (value.equalsIgnoreCase("previous")) {
                            return new ComponentResult(component.clickEvent(ClickEvent.changePage(-1)));
                        } else {
                            return new ComponentResult(component.clickEvent(ClickEvent.changePage(Integer.parseInt(value))));
                        }
                    }
                    case "font": {
                        Key key = Key.key(value);
                        return new ComponentResult(component.font(key));
                    }
                    case "insertion": {
                        return new ComponentResult(component.insertion(value));
                    }
                    case "rgb":{
                        String[] split = value.split(",");
                        if (split.length!=3){
                            return new ErrorResult("ARGS_ERROR","The rgb arg must be 3 int str");
                        }
                        if (!VariableUtil.tryInt(split[0])){
                            return new ErrorResult("ARGS_ERROR","The first arg must be int str");
                        }
                        if (!VariableUtil.tryInt(split[1])){
                            return new ErrorResult("ARGS_ERROR","The second arg must be int str");
                        }
                        if (!VariableUtil.tryInt(split[2])){
                            return new ErrorResult("ARGS_ERROR","The third arg must be int str");
                        }
                        int r=Integer.parseInt(split[0]);
                        int g=Integer.parseInt(split[1]);
                        int b=Integer.parseInt(split[2]);
                        return new ComponentResult(component.color(TextColor.color(r,g,b)));
                    }
                    case "decoration":{
                        return new ComponentResult(component.decoration(TextDecoration.valueOf(value)));
                    }
                }
            }
            case "D": {
                Component component = (Component) args.get(0).getObject();
                Component component1 = (Component) args.get(1).getObject();
                return new ComponentResult(component.append(component1));
            }
            case "E": {
                Component component = (Component) args.get(0).getObject();
                Player player = (Player) args.get(1).getObject();
                String type = (String) args.get(1).getObject();
                switch (type) {
                    case "chat": {
                        player.sendMessage(component);
                        break;
                    }
                    case "actionbar": {
                        player.sendActionBar(component);
                        break;
                    }
                }
                return new ComponentResult(component);
            }
            case "F": {
                Component titleComponent = (Component) args.get(0).getObject();
                Component subTitleComponent = (Component) args.get(1).getObject();
                Player player = (Player) args.get(2).getObject();
                String fadeIn = (String) args.get(3).getObject();
                String stay = (String) args.get(4).getObject();
                String fadeOut = (String) args.get(5).getObject();
                if (!VariableUtil.tryInt(fadeIn)) {
                    return new ErrorResult("ARGS_ERROR", "The fadeIn arg must be int str");
                }
                if (!VariableUtil.tryInt(stay)) {
                    return new ErrorResult("ARGS_ERROR", "The stay arg must be int str");
                }
                if (!VariableUtil.tryInt(fadeOut)) {
                    return new ErrorResult("ARGS_ERROR", "The fadeOut arg must be int str");
                }
                long fadeIn1 = Long.parseLong(fadeIn);
                long stay1 = Long.parseLong(stay);
                long fadeOut1 = Long.parseLong(fadeOut);
                Duration fadeIn2 = Duration.ofMillis(fadeIn1 * 50);
                Duration stay2 = Duration.ofMillis(stay1 * 50);
                Duration fadeOut2 = Duration.ofMillis(fadeOut1 * 50);
                Title.Times times = Title.Times.times(fadeIn2, stay2, fadeOut2);
                Title title = Title.title(titleComponent, subTitleComponent, times);
                player.showTitle(title);
                return new ComponentResult(titleComponent);
            }
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings = new ArrayList<>();
        argsSettings.add(
                new ArgsSetting("A")
                        .addArgType("String")
                        .addInfo("string")
                        .addInfo("Create a string component")
                        .setResultType("Component")
        );
        argsSettings.add(
                new ArgsSetting("B")
                        .addArgType("Component").addArgType("ItemStack")
                        .addInfo("component itemStack")
                        .addInfo("Add a itemStack to component")
                        .setResultType("Component")
        );
        argsSettings.add(
                new ArgsSetting("C")
                        .addArgType("Component").addArgType("String").addArgType("String")
                        .addInfo("component type value")
                        .addInfo("Add value to component by type")
                        .addInfo("type: showText,openUrl,openFile,runCommand,copy,suggestCommand,changePage,font,insertion,rgb")
                        .addInfo("showText: value is a string")
                        .addInfo("openUrl: value is a url")
                        .addInfo("openFile: value is a file path")
                        .addInfo("runCommand: value is a command")
                        .addInfo("copy: value is a string")
                        .addInfo("suggestCommand: value is a command")
                        .addInfo("changePage: value is a number or next or previous")
                        .addInfo("font: value is a font key")
                        .addInfo("insertion: value is a string")
                        .addInfo("rgb: value is a r,g,b")
                        .addInfo("decoration: value is a decoration type")
                        .addInfo("decoration type: bold,italic,underlined,strikethrough,obfuscated")
                        .setResultType("Component")
        );
        argsSettings.add(
                new ArgsSetting("D")
                        .addArgType("Component").addArgType("Component")
                        .addInfo("component component")
                        .addInfo("Add component to component")
                        .setResultType("Component")
        );
        argsSettings.add(
                new ArgsSetting("E")
                        .addArgType("Component Player String")
                        .addInfo("component player type")
                        .addInfo("Send component to player by type")
                        .addInfo("type: chat,actionbar")
                        .setResultType("Null")
        );
        argsSettings.add(
                new ArgsSetting("F")
                        .addArgType("Component Component Player String String String")
                        .addInfo("titleComponent subTitleComponent player fadeIn stay fadeOut")
                        .addInfo("Send title to player")
                        .setResultType("Null")
        );
        return argsSettings;
    }

    @Override
    public String getName() {
        return "Component";
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER";
    }
}
