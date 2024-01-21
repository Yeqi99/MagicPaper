package cn.originmc.plugins.magicpaper.magic;

import cn.origincraft.magic.MagicManager;
import cn.origincraft.magic.expression.managers.FunctionManager;
import cn.originmc.plugins.magicpaper.magic.function.behavior.*;
import cn.originmc.plugins.magicpaper.magic.function.behavior.entity.NewEntityFunction;
import cn.originmc.plugins.magicpaper.magic.function.behavior.item.*;
import cn.originmc.plugins.magicpaper.magic.function.behavior.potion.PotionAddFunction;
import cn.originmc.plugins.magicpaper.magic.function.behavior.skill.JumpSkillFunction;
import cn.originmc.plugins.magicpaper.magic.function.behavior.skill.JumpToLocationSkillFunction;
import cn.originmc.plugins.magicpaper.magic.function.buff.BuffAddFunction;
import cn.originmc.plugins.magicpaper.magic.function.buff.BuffTimeGetFunction;
import cn.originmc.plugins.magicpaper.magic.function.bungeecord.BCSendDataFunction;
import cn.originmc.plugins.magicpaper.magic.function.bungeecord.ServerTPFunction;
import cn.originmc.plugins.magicpaper.magic.function.control.execute.*;
import cn.originmc.plugins.magicpaper.magic.function.cooldown.AddCoolDownFunction;
import cn.originmc.plugins.magicpaper.magic.function.cooldown.CheckCoolDownFunction;
import cn.originmc.plugins.magicpaper.magic.function.cooldown.GETCDFunction;
import cn.originmc.plugins.magicpaper.magic.function.cooldown.GOCDFunction;
import cn.originmc.plugins.magicpaper.magic.function.dataentity.DataEntityFunction;
import cn.originmc.plugins.magicpaper.magic.function.dataentity.DataEntityRemoverFunction;
import cn.originmc.plugins.magicpaper.magic.function.gui.*;
import cn.originmc.plugins.magicpaper.magic.function.gui.databuttons.OnlinePlayerButtonsFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.abolethplus.*;
import cn.originmc.plugins.magicpaper.magic.function.hook.authme.IsLoggedFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.itemsadder.IAItemFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.itemsadder.PlaceIABlockFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.luckperms.PlayerHasPermissionFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.luckperms.PlayerLPMetaGetFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.luckperms.PlayerLPMetaSetFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.luckperms.PlayerLPTempMetaSetFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.mcborder.RTPFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.mythicmobs.CastSkillFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.mythicmobs.SpawnMobFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.placeholderapi.PapiStrFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.placeholderapi.PlaceholderAPIFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.playerpoints.*;
import cn.originmc.plugins.magicpaper.magic.function.hook.vault.VaultGetFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.vault.VaultGiveFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.vault.VaultHasFunction;
import cn.originmc.plugins.magicpaper.magic.function.hook.vault.VaultTakeFunction;
import cn.originmc.plugins.magicpaper.magic.function.info.*;
import cn.originmc.plugins.magicpaper.magic.function.magictimer.AddToTimerFunction;
import cn.originmc.plugins.magicpaper.magic.function.magictimer.FoliaTimerFunction;
import cn.originmc.plugins.magicpaper.magic.function.magictimer.PaperTimerFunction;
import cn.originmc.plugins.magicpaper.magic.function.object.*;
import cn.originmc.plugins.magicpaper.magic.function.random.RandomDoubleFunction;
import cn.originmc.plugins.magicpaper.magic.function.random.RandomUUIDFunction;
import cn.originmc.plugins.magicpaper.magic.function.random.WeightRandomFunction;
import cn.originmc.plugins.magicpaper.magic.function.trigger.AddSpellToTriggerFunction;
import cn.originmc.plugins.magicpaper.magic.function.trigger.TriggerClearSpellFunction;
import cn.originmc.plugins.magicpaper.magic.function.yaml.*;

public class FunctionRegister {

    public static void register(MagicManager magicManager) {
        FunctionManager fm = magicManager.getFastExpression().getFunctionManager();
        // dataentity
        fm.register(new DataEntityFunction(),"data");
        fm.register(new DataEntityRemoverFunction(),"dataremover");
        // hook.authme
        fm.register(new IsLoggedFunction(), "logged");
        // hook.mcborder
        fm.register(new RTPFunction());
        // hook.luckperms
        fm.register(new PlayerLPMetaGetFunction(), "getlpmeta");
        fm.register(new PlayerLPMetaSetFunction(), "lpmeta");
        fm.register(new PlayerLPTempMetaSetFunction(), "lptmeta");
        fm.register(new PlayerHasPermissionFunction(), "lphasp");
        // hook.placeholderapi
        fm.register(new PlaceholderAPIFunction(), "papi");
        fm.register(new PapiStrFunction());
        // hook.abolethplus
        fm.register(new AboGetUserFunction(), "abou");
        fm.register(new AboGetFunction(), "abog");
        fm.register(new AboAddFunction(), "aboa");
        fm.register(new AboSetFunction(), "abos");
        fm.register(new AboEditFunction(), "aboe");
        fm.register(new AboSetTimeFunction(), "abost");
        fm.register(new AboAddItemFunction(), "aboai");
        fm.register(new AboGetItemFunction(), "abogi");
        // hook.mythicmobs
        fm.register(new CastSkillFunction(), "mmskill");
        fm.register(new SpawnMobFunction(), "mmob");
        // hook.itemsadder
        fm.register(new IAItemFunction(), "iaitem");
        fm.register(new PlaceIABlockFunction(), "placeiab");
        // hook.vault
        fm.register(new VaultGetFunction());
        fm.register(new VaultGiveFunction());
        fm.register(new VaultHasFunction());
        fm.register(new VaultTakeFunction());
        // hook.playerpoints
        fm.register(new PlayerPointsGetFunction(), "ppget");
        fm.register(new PlayerPointsGiveFunction(), "ppgive");
        fm.register(new PlayerPointsHasFunction(), "pphas");
        fm.register(new PlayerPointsSetFunction(), "ppset");
        fm.register(new PlayerPointsTakeFunction(), "pptake");
        // object
        fm.register(new PlayerFunction(), "p");
        fm.register(new LocationFunction(), "loc");
        fm.register(new ItemFunction());
        fm.register(new EntityFunction());
        fm.register(new BossBarFunction());
        fm.register(new ComponentFunction(), "minimsg");
        fm.register(new PotionFunction());
        // behavior
        fm.register(new ConsoleCommandFunction(), "ccommand");
        fm.register(new PlayerCommandFunction(), "pcommand");
        fm.register(new SendToPlayerFunction(), "stp");
        fm.register(new SendToAllPlayerFunction(), "stap");
        fm.register(new PlayerTeleportFunction(), "ptp");
        fm.register(new PlayerAsyncTeleportFunction(), "patp");
        fm.register(new UpdateInventoryFunction(), "upinv");
        fm.register(new SendActionMsgFunction());
        fm.register(new PlayerCloseInvFunction(), "closeinv");
        // behavior.item
        fm.register(new ItemLoreAddLineFunction(), "iladd");
        fm.register(new ItemLoreRemoveFunction(), "ilremove");
        fm.register(new ItemSetPlayerFunction(), "iset");
        fm.register(new ItemGivePlayerFunction(), "igive");
        fm.register(new ItemNBTRemoveFunction(), "inbtr");
        fm.register(new ItemNBTAddFunction(), "inbta");
        fm.register(new ItemNBTGetFunction(), "inbtg");
        fm.register(new ItemToStringFunction(), "itostr");
        fm.register(new StringToItemFunction(), "strtoi");
        fm.register(new ItemSetNameFunction(), "inames");
        fm.register(new ItemGetNameFunction(), "inameg");
        fm.register(new ItemTypeSetFunction(), "itypes");
        fm.register(new ItemTypeGetFunction(), "itypeg");
        fm.register(new ItemLoreHasLineFunction(), "ilhas");
        fm.register(new ItemLoreHasLineNoColorFunction(), "ilhasnc");
        fm.register(new ItemVarParseFunction(), "ivarp");
        fm.register(new NewItemFunction(), "newi");
        fm.register(new RefreshVarFunction(), "rfv");
        fm.register(new ItemModelSetFunction(), "imodels");
        fm.register(new ItemModelGetFunction(), "imodelg");
        fm.register(new ItemAmountSetFunction(), "iamounts");
        fm.register(new ItemAmountGetFunction(), "iamountg");
        fm.register(new ItemDamageSetFunction(), "idamages");
        fm.register(new ItemDamageGetFunction(), "idamageg");
        fm.register(new ItemEnchantmentSetFunction(), "ienchants");
        fm.register(new ItemEnchantmentGetFunction(), "ienchantg");
        fm.register(new ItemAttributeSetFunction(), "iattributes");
        fm.register(new ItemAttributeGetFunction(), "iattributeg");
        fm.register(new ItemFlagFunction(), "iflags");
        fm.register(new ItemEnableRefreshFunction(), "irefreshe");
        fm.register(new ItemFormatSetFunction(), "iformats");
        fm.register(new ItemInfoSetFunction(), "iinfos");
        fm.register(new ItemInfoAddFunction(), "iinfoa");
        fm.register(new ItemBoreSetFunction(), "ibores");
        fm.register(new ItemSupportBoreAddressSetFunction(), "iboresupport");
        fm.register(new AddItemToBoreFunction(), "iboreadd");
        fm.register(new RemoveItemFromBoreFunction(), "iboreremove");
        fm.register(new ItemAddSpellFunction(), "iaddspell");
        fm.register(new MergeNBTFunction());
        // behavior.entity
        fm.register(new NewEntityFunction(), "newe");
        // behavior.skill
        fm.register(new JumpSkillFunction(), "pjump");
        fm.register(new JumpToLocationSkillFunction(), "pjumpto");
        // behavior.potion
        fm.register(new PotionAddFunction());
        // buff
        fm.register(new BuffAddFunction());
        fm.register(new BuffTimeGetFunction());
        // control.execute
        fm.register(new PaperSpellExecuteFunction(), "paperse");
        fm.register(new PaperSpellAsyncExecuteFunction(), "papersae");
        fm.register(new FoliaSpellExecuteFunction(), "foliase");
        fm.register(new FoliaSpellAsyncExecuteFunction(), "foliasae");
        fm.register(new PublicContextGetFunction(), "getpc");
        // magictimer
        fm.register(new AddToTimerFunction(), "att");
        fm.register(new FoliaTimerFunction(), "ftimer");
        fm.register(new PaperTimerFunction(), "ptimer");
        // trigger
        fm.register(new AddSpellToTriggerFunction(), "astt");
        fm.register(new TriggerClearSpellFunction(), "tcs");
        // info
        fm.register(new PlayerNameFunction(), "pname");
        fm.register(new PaperConstantsFunction(), "pconst");
        fm.register(new IgnoreCaseStringComparisonFunction(), "stric");
        fm.register(new StringComparisonFunction(), "strc");
        fm.register(new ColorFunction());
        fm.register(new IsTimeRangeFunction(), "istime");
        fm.register(new InLocationRangeFunction(), "inlrange");
        fm.register(new PlayerUUIDFunction(), "puuid");
        fm.register(new FacingOffsetFunction(), "locoffset");
        fm.register(new CheckLocationFunction(), "checkloc");
        // yaml
        fm.register(new YamlManagerFunction(), "yamlm");
        fm.register(new YamlSaveAllFunction(), "yalmsa");
        fm.register(new YamlSaveFunction(), "yamls");
        fm.register(new YamlGetFunction(), "yamlg");
        fm.register(new YamlSetFunction(), "yamls");
        fm.register(new NewYamlFunction(), "nyaml");
        fm.register(new HasYamlFunction(), "hasym");
        fm.register(new HasYamlKeyFunction(), "hasymk");
        // random
        fm.register(new RandomDoubleFunction(), "randd");
        fm.register(new WeightRandomFunction(), "randw");
        fm.register(new RandomUUIDFunction(), "ruuid");
        // cooldown
        fm.register(new AddCoolDownFunction(), "addcd");
        fm.register(new CheckCoolDownFunction(), "checkcd");
        fm.register(new GOCDFunction());
        fm.register(new GETCDFunction());
        // gui
        fm.register(new GuiOpenFunction(), "opengui");
        fm.register(new GuiDataClearFunction(), "cleargui");
        fm.register(new GuiNextPageFunction(), "npage");
        fm.register(new GuiPreviousPageFunction(), "ppage");
        fm.register(new GuiUpdateFunction(), "upgui");
        // gui.databuttons
        fm.register(new OnlinePlayerButtonsFunction(), "pbuttons");
        // particle
        fm.register(new ParticleGeneratorFunction(), "particle");
        // bungeecord
        fm.register(new ServerTPFunction(), "servertp");
        fm.register(new BCSendDataFunction(), "bcmsg");
    }
}
