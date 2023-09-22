package cn.originmc.plugins.magicpaper.magic.function.random;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtil;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.DoubleResult;
import dev.rgbmc.expression.results.StringResult;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

public class RandomDoubleFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size() < 3) {
            return new ErrorResult("ARGS_NOT_ENOUGH","Dont have enough args");
        }
        FunctionResult arg0 = args.get(0);
        FunctionResult arg1 = args.get(1);
        FunctionResult arg2 = args.get(2);
        if (!(arg0 instanceof DoubleResult)) {
            return new ErrorResult("ARGS_ERROR","The first arg must be double");
        }
        if (!(arg1 instanceof DoubleResult)) {
            return new ErrorResult("ARGS_ERROR","The second arg must be double");
        }
        if (!(arg2 instanceof StringResult)) {
            return new ErrorResult("ARGS_ERROR","The third arg must be string");
        }
        String str=((StringResult) arg2).getString();
        if (!VariableUtil.tryInt(str)){
            return new ErrorResult("ARGS_ERROR","The third arg must be int str");
        }
        int decimalPlaces=Integer.parseInt(str);
        double minValue = ((DoubleResult) arg0).getDouble();
        double maxValue = ((DoubleResult) arg1).getDouble();
        double randomValue = generateRandomNumber(minValue, maxValue, decimalPlaces);
        return new DoubleResult(randomValue);
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
