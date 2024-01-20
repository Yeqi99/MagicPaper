package cn.originmc.plugins.magicpaper.magic.result;

import cn.origincraft.magic.function.results.ObjectResult;
import cn.originmc.plugins.magicpaper.util.particle.ParticleGenerator;

public class ParticleGeneratorResult extends ObjectResult {
    public ParticleGeneratorResult(Object object) {
        super(object);
    }
    public ParticleGenerator getParticleGenerator() {
        return (ParticleGenerator) getObject();
    }
    public String getName() {
        return "ParticleGenerator";
    }
}
