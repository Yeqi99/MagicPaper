package cn.originmc.plugins.magicpaper.magic.function.bungeecord;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.FunctionUtils;
import cn.originmc.plugins.magicpaper.bungeecord.BungeeCordSender;
import org.bukkit.entity.Player;


import java.util.ArrayList;
import java.util.List;

public class ServerTPFunction extends ArgsFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id=argsSetting.getId();
        switch (id){
            case "A":{
                Player player= (Player) args.get(0).getObject();
                String serverName= (String) args.get(0).getObject();
                BungeeCordSender.playerToServer(player,serverName);
            }
            case "B":{
                String playerName= (String) args.get(0).getObject();
                String serverName= (String) args.get(1).getObject();
                BungeeCordSender.playerToServer(playerName,serverName);
            }
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings=new ArrayList<>();
        ArgsSetting argsSetting1= FunctionUtils.createArgsSetting(
                "Player String",
                "player serverName" +
                        "\nLet the player tp to the server",
                "Null");
        argsSetting1.setId("A");
        ArgsSetting argsSetting2= FunctionUtils.createArgsSetting(
                "String String",
                "playerName serverName" +
                        "\nLet the player tp to the server",
                "Null");
        argsSetting2.setId("B");
        argsSettings.add(argsSetting1);
        argsSettings.add(argsSetting2);
        return argsSettings;
    }

    @Override
    public String getType() {
        return "BC";
    }

    @Override
    public String getName() {
        return "serverTP";
    }
}
