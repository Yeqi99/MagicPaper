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

public class BCSendDataFunction extends ArgsFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id=argsSetting.getId();
        switch (id){
            case "A":{
                Player player= (Player) args.get(0).getObject();
                List<?> data= (List<?>) args.get(1).getObject();
                List<String> dataString=new ArrayList<>();
                for (Object datum : data) {
                    dataString.add(datum.toString());
                }
                BungeeCordSender.sendMagicBungeeCordMessage(player,dataString);
                return new NullResult();
            }
            case "B":{
                List<?> data= (List<?>) args.get(0).getObject();
                List<String> dataString=new ArrayList<>();
                for (Object datum : data) {
                    dataString.add(datum.toString());
                }
                BungeeCordSender.sendMagicBungeeCordMessage(null,dataString);
                return new NullResult();
            }
            case "C":{
                Player player= (Player) args.get(0).getObject();
                String data= (String) args.get(1).getObject();
                List<String> dataString=new ArrayList<>();
                dataString.add(data);
                BungeeCordSender.sendMagicBungeeCordMessage(player,dataString);
                return new NullResult();
            }
            case "D":{
                String data= (String) args.get(0).getObject();
                List<String> dataString=new ArrayList<>();
                dataString.add(data);
                BungeeCordSender.sendMagicBungeeCordMessage(null,dataString);
                return new NullResult();
            }
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings=new ArrayList<>();
        ArgsSetting argsSetting1= FunctionUtils.createArgsSetting(
                "Player List",
                "player dataList" +
                        "\nSend data to the all server",
                "Null");
        argsSetting1.setId("A");
        ArgsSetting argsSetting2= FunctionUtils.createArgsSetting(
                "List",
                "dataList" +
                        "\nSend data to the all server",
                "Null");
        argsSetting2.setId("B");
        ArgsSetting argsSetting3= FunctionUtils.createArgsSetting(
                "Player String",
                "player data" +
                        "\nSend data to the all server",
                "Null");
        argsSetting3.setId("C");
        ArgsSetting argsSetting4= FunctionUtils.createArgsSetting(
                "String",
                "data" +
                        "\nSend data to the all server",
                "Null");
        argsSetting4.setId("D");
        argsSettings.add(argsSetting1);
        argsSettings.add(argsSetting2);
        argsSettings.add(argsSetting3);
        argsSettings.add(argsSetting4);
        return argsSettings;
    }

    @Override
    public String getType() {
        return "BC";
    }

    @Override
    public String getName() {
        return "BCSendData";
    }
}
