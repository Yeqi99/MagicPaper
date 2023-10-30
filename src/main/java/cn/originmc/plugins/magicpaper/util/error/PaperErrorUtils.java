package cn.originmc.plugins.magicpaper.util.error;

import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;

import java.util.ArrayList;
import java.util.List;

public class PaperErrorUtils {
    public static List<String> getErrorAllLog(SpellContext spellContext, String color){
        List<String> log = new ArrayList<>();
        if (MagicPaper.getLang().equalsIgnoreCase("Chinese")){
            log.add(color+"发生错误，以下是错误信息");
            ErrorResult errorResult=spellContext.getExecuteError();
            log.add(color+"错误ID："+errorResult.getErrorId());
            log.add(color+"错误提示："+errorResult.getInfo());
            log.add(color+"错误分析：");
            for (String s : spellContext.getExecuteErrorLocation()) {
                log.add(color+s);
            }
            return log;
        }else {
            log.add(color+"Error, the following is the error message");
            ErrorResult errorResult=spellContext.getExecuteError();
            log.add(color+"Error ID："+errorResult.getErrorId());
            log.add(color+"Error Info："+errorResult.getInfo());
            log.add(color+"Error Location：");
            for (String s : spellContext.getExecuteErrorLocation()) {
                log.add(color+s);
            }
            return log;
        }
    }
}
