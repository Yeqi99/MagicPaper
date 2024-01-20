package cn.originmc.plugins.magicpaper.magic.function.random;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.FunctionUtils;
import cn.origincraft.magic.utils.VariableUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDoubleFunction extends ArgsFunction {

    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        if (argsSetting.getId().equalsIgnoreCase("A")){
            String start= (String) args.get(0).getObject();
            String end= (String) args.get(1).getObject();
            String decimalPlaces= (String) args.get(2).getObject();
            if (!VariableUtils.tryDouble(start)){
                return new ErrorResult("ARGS_ERROR","The first arg must be double str");
            }
            if (!VariableUtils.tryDouble(end)){
                return new ErrorResult("ARGS_ERROR","The second arg must be double str");
            }
            if (!VariableUtils.tryInt(decimalPlaces)){
                return new ErrorResult("ARGS_ERROR","The third arg must be int str");
            }
            double randomValue = generateRandomNumber(Double.parseDouble(start),Double.parseDouble(end),Integer.parseInt(decimalPlaces));
            if (decimalPlaces.equals("0")){
                return new StringResult(String.valueOf((int)randomValue).replace(".0",""));
            }
            return new StringResult(String.valueOf(randomValue));
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings=new ArrayList<>();
        ArgsSetting argsSetting1= FunctionUtils.createArgsSetting(
                "String String String",
                "start end decimalPlaces" +
                        "\nGet a random double number.",
                "String");
        argsSetting1.setId("A");
        argsSettings.add(argsSetting1);
        return argsSettings;
    }

    @Override
    public String getType() {
        return "RANDOM";
    }

    @Override
    public String getName() {
        return "randomDouble";
    }

    public static double generateRandomNumber(double minValue, double maxValue, int decimalPlaces) {
        // 判断参数合法性
        if (minValue >= maxValue || decimalPlaces < 0) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        // 生成随机数
        double randomValue = new Random().nextDouble() * (maxValue - minValue) + minValue;

        // 格式化保留指定小数位数
        DecimalFormat decimalFormat = new DecimalFormat("#." + "0".repeat(decimalPlaces));
        String formattedValue = decimalFormat.format(randomValue);

        // 将格式化后的字符串转换为double类型
        return Double.parseDouble(formattedValue);
    }
}
