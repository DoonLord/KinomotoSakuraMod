package KinomotoSakuraMod.Patches;

import KinomotoSakuraMod.Powers.KSMOD_LightPower;
import KinomotoSakuraMod.Powers.KSMOD_MagickChargePower;
import KinomotoSakuraMod.Relics.KSMOD_SealedBook;
import KinomotoSakuraMod.Relics.KSMOD_TouyasBicycle;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class KSMOD_VoidCardPatch
{
    @SpirePatch(clz = VoidCard.class, method = "triggerWhenDrawn", paramtypez = {})
    public static class triggerWhenDrawn
    {
        public static SpireReturn<Object> Prefix(VoidCard card)
        {
            if (AbstractDungeon.player.hasPower(KSMOD_LightPower.POWER_ID))
            {
                if (AbstractDungeon.player.hasPower("Evolve") && !AbstractDungeon.player.hasPower("No Draw"))
                {
                    AbstractDungeon.player.getPower("Evolve").flash();
                    AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, AbstractDungeon.player.getPower("Evolve").amount));
                }
                return SpireReturn.Return(null);
            }
            else
            {
                return SpireReturn.Continue();
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "canUse", paramtypez = {
            AbstractPlayer.class,
            AbstractMonster.class
    })
    public static class canUse
    {
        public static SpireReturn<Boolean> Prefix(AbstractCard card, AbstractPlayer player, AbstractMonster monster)
        {
            if (card instanceof VoidCard && AbstractDungeon.player.hasPower(KSMOD_LightPower.POWER_ID) && ((KSMOD_LightPower) AbstractDungeon.player.getPower(KSMOD_LightPower.POWER_ID)).upgraded)
            {
                card.exhaust = true;
                return SpireReturn.Return(true);
            }
            else
            {
                return SpireReturn.Continue();
            }
        }
    }

    @SpirePatch(clz = VoidCard.class, method = "use", paramtypez = {
            AbstractPlayer.class,
            AbstractMonster.class
    })
    public static class use
    {
        public static SpireReturn<Object> Prefix(AbstractCard card, AbstractPlayer player, AbstractMonster monster)
        {
            if (AbstractDungeon.player.hasPower(KSMOD_LightPower.POWER_ID))
            {
                card.exhaust = true;
                KSMOD_LightPower power = (KSMOD_LightPower) AbstractDungeon.player.getPower(KSMOD_LightPower.POWER_ID);
                power.flash();
                if (power.upgraded)
                {
                    AbstractDungeon.actionManager.addToBottom(new DrawCardAction(player, 1));
                }
                return SpireReturn.Return(null);
            }
            else
            {
                return SpireReturn.Continue();
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "triggerOnExhaust", paramtypez = {})
    public static class triggerOnExhaust
    {
        public static SpireReturn<Object> Prefix(AbstractCard card)
        {
            if (card instanceof VoidCard && AbstractDungeon.player.hasRelic(KSMOD_TouyasBicycle.RELIC_ID))
            {

                if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic(KSMOD_SealedBook.RELIC_ID))
                {
                    AbstractDungeon.player.getRelic(KSMOD_TouyasBicycle.RELIC_ID).flash();
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new KSMOD_MagickChargePower(AbstractDungeon.player, KSMOD_TouyasBicycle.CHARGE_NUMBER), KSMOD_TouyasBicycle.CHARGE_NUMBER));
                }
                return SpireReturn.Return(null);
            }
            else
            {
                return SpireReturn.Continue();
            }
        }
    }
}
