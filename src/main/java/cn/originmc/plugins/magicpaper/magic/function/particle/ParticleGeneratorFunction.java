package cn.originmc.plugins.magicpaper.magic.function.particle;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.FunctionUtils;
import cn.originmc.plugins.magicpaper.magic.result.ParticleGeneratorResult;
import cn.originmc.plugins.magicpaper.util.particle.ParticleGenerator;
import org.bukkit.Location;
import org.bukkit.Particle;

import java.util.ArrayList;
import java.util.List;

public class ParticleGeneratorFunction extends ArgsFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {

        if (argsSetting.getId().equalsIgnoreCase("A")){
            String count= (String) args.get(0).getObject();
            String offsetX= (String) args.get(1).getObject();
            String offsetY= (String) args.get(2).getObject();
            String offsetZ= (String) args.get(3).getObject();
            String extra= (String) args.get(4).getObject();
            int count1=Integer.parseInt(count);
            double offsetX1=Double.parseDouble(offsetX);
            double offsetY1=Double.parseDouble(offsetY);
            double offsetZ1=Double.parseDouble(offsetZ);
            double extra1=Double.parseDouble(extra);
            return new ParticleGeneratorResult(new ParticleGenerator(count1,offsetX1,offsetY1,offsetZ1,extra1));
        }else if (argsSetting.getId().equalsIgnoreCase("B")){
            String count= (String) args.get(0).getObject();
            String offsetX= (String) args.get(1).getObject();
            String offsetY= (String) args.get(2).getObject();
            String offsetZ= (String) args.get(3).getObject();
            String extra= (String) args.get(4).getObject();
            String particle= (String) args.get(5).getObject();
            int count1=Integer.parseInt(count);
            double offsetX1=Double.parseDouble(offsetX);
            double offsetY1=Double.parseDouble(offsetY);
            double offsetZ1=Double.parseDouble(offsetZ);
            double extra1=Double.parseDouble(extra);
            Particle particle1=Particle.valueOf(particle);
            return new ParticleGeneratorResult(new ParticleGenerator(particle1,count1,offsetX1,offsetY1,offsetZ1,extra1));
        }else if(argsSetting.getId().equalsIgnoreCase("C")){
            ParticleGenerator particleGenerator= (ParticleGenerator) args.get(0).getObject();
            Location location= (Location) args.get(1).getObject();
            particleGenerator.generator(location);
            return new NullResult();
        }else if (argsSetting.getId().equalsIgnoreCase("D")){
            ParticleGenerator particleGenerator= (ParticleGenerator) args.get(0).getObject();
            Location location= (Location) args.get(1).getObject();
            String particle= (String) args.get(2).getObject();
            Particle particle1=Particle.valueOf(particle);
            particleGenerator.generator(particle1,location);
            return new NullResult();
        }else if(argsSetting.getId().equalsIgnoreCase("E")){
            ParticleGenerator particleGenerator= (ParticleGenerator) args.get(0).getObject();
            String red= (String) args.get(1).getObject();
            String green= (String) args.get(2).getObject();
            String blue= (String) args.get(3).getObject();
            String size= (String) args.get(4).getObject();
            int red1=Integer.parseInt(red);
            int green1=Integer.parseInt(green);
            int blue1=Integer.parseInt(blue);
            int size1=Integer.parseInt(size);
            particleGenerator.setRedStoneData(red1,green1,blue1,size1);
            return new ParticleGeneratorResult(particleGenerator);
        }else if(argsSetting.getId().equalsIgnoreCase("F")){
            ParticleGenerator particleGenerator= (ParticleGenerator) args.get(0).getObject();
            String fromRed= (String) args.get(1).getObject();
            String fromGreen= (String) args.get(2).getObject();
            String fromBlue= (String) args.get(3).getObject();
            String toRed= (String) args.get(4).getObject();
            String toGreen= (String) args.get(5).getObject();
            String toBlue= (String) args.get(6).getObject();
            String size= (String) args.get(7).getObject();
            int fromRed1=Integer.parseInt(fromRed);
            int fromGreen1=Integer.parseInt(fromGreen);
            int fromBlue1=Integer.parseInt(fromBlue);
            int toRed1=Integer.parseInt(toRed);
            int toGreen1=Integer.parseInt(toGreen);
            int toBlue1=Integer.parseInt(toBlue);
            int size1=Integer.parseInt(size);
            particleGenerator.setDustColorTransition(fromRed1,fromGreen1,fromBlue1,toRed1,toGreen1,toBlue1,size1);
            return new ParticleGeneratorResult(particleGenerator);
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings=new ArrayList<>();
        ArgsSetting argsSetting1= FunctionUtils.createArgsSetting(
                "String String String String String",
                "count offsetX offsetY offsetZ extra" +
                        "\ncreate a particle generator.",
                "ParticleGenerator");
        argsSetting1.setId("A");
        ArgsSetting argsSetting2= FunctionUtils.createArgsSetting(
                "String String String String String String",
                "count offsetX offsetY offsetZ extra particleName" +
                        "\ncreate a particle generator.",
                "ParticleGenerator");
        argsSetting2.setId("B");
        ArgsSetting argsSetting3= FunctionUtils.createArgsSetting(
                "ParticleGenerator Location",
                "generate particles.",
                "Null");
        argsSetting3.setId("C");
        ArgsSetting argsSetting4= FunctionUtils.createArgsSetting(
                "ParticleGenerator Location String",
                "generate particles.",
                "Null");
        argsSetting4.setId("D");
        ArgsSetting argsSetting5= FunctionUtils.createArgsSetting(
                "ParticleGenerator String String String String",
                "ParticleGenerator red green blue size" +
                        "\nset rgb.",
                "Null");
        argsSetting5.setId("E");
        ArgsSetting argsSetting6= FunctionUtils.createArgsSetting(
                "ParticleGenerator String String String String String String String",
                "ParticleGenerator fromRed fromGreen fromBlue toRed toGreen toBlue size" +
                        "\nset rgb gradient.",
                "Null");
        argsSetting6.setId("F");
        argsSettings.add(argsSetting1);
        argsSettings.add(argsSetting2);
        argsSettings.add(argsSetting3);
        argsSettings.add(argsSetting4);
        argsSettings.add(argsSetting5);
        argsSettings.add(argsSetting6);
        return argsSettings;
    }

    @Override
    public String getType() {
        return "PAPER_PARTICLE";
    }

    @Override
    public String getName() {
        return "particleGenerator";
    }
}
