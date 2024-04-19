package cn.originmc.plugins.magicpaper.magic.function.random;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.function.results.NumberResult;
import cn.origincraft.magic.object.SpellContext;

import java.util.ArrayList;
import java.util.List;

public class WeightRandomFunction extends ArgsFunction {


    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id = argsSetting.getId();
        switch (id) {
            case "A": {
                int size = args.size();
                int random = (int) (Math.random() * size);
                return args.get(random);
            }
            case "B": {
                List<FunctionResult> list = new ArrayList<>();
                List<NumberResult> weights = new ArrayList<>();
                for (int i = 0; i < args.size(); i++) {
                    if (i % 2 == 0) {
                        list.add(args.get(i));
                    } else {
                        weights.add((NumberResult) args.get(i));
                    }
                }
                if (list.size() != weights.size()) {
                    return new ErrorResult("USAGE_ERROR", "The number of weights must be equal to the number of elements");
                }
                return weightedRandomSelect(list, weights);
            }
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings = new ArrayList<>();
        argsSettings.add(new ArgsSetting("A")
                .addArgType("...")
                .addInfo("Any...")
                .addInfo("Random select a object from args")
                .addInfo("example: weightRandom(A B C D)")
                .addInfo("means: One of the equal choices A, B, C, and D")
                .setResultType("String")
        );
        argsSettings.add(new ArgsSetting("B")
                .addArgType(".").addArgType("Number").addArgType("...")
                .addInfo("string number...")
                .addInfo("Random select a object from args by weight")
                .addInfo("example: weightRandom(A 1 B 2 C 3 D 4)")
                .addInfo("means: A has a 1/10 chance of being selected, B has a 2/10 chance of being selected, C has a 3/10 chance of being selected, and D has a 4/10 chance of being selected")
                .setResultType("String")
        );
        return argsSettings;
    }

    @Override
    public String getType() {
        return "RANDOM";
    }

    @Override
    public String getName() {
        return "weightRandom";
    }

    private static FunctionResult weightedRandomSelect(List<FunctionResult> list, List<NumberResult> weights) {
        if (list.size() != weights.size()) {
            throw new IllegalArgumentException("List and weights must be of the same size");
        }

        double totalWeight = 0.0;
        for (NumberResult weight : weights) {
            totalWeight += weight.toDouble(); // 确保NumberResult有一个合适的方法来获取其数值
        }

        double randomValue = Math.random() * totalWeight;
        double cumulativeWeight = 0.0;

        for (int i = 0; i < list.size(); i++) {
            cumulativeWeight += weights.get(i).toDouble();
            if (cumulativeWeight >= randomValue) {
                return list.get(i);
            }
        }

        return null; // 在理论上不应该发生，除非权重总和为0
    }
}
