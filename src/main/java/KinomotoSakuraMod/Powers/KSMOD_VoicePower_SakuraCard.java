package KinomotoSakuraMod.Powers;

import KinomotoSakuraMod.Cards.ClowCard.ClowCardTheVoice;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class KSMOD_VoicePower_SakuraCard extends KSMOD_CustomPower
{
    public static final String POWER_ID = "KSMOD_VoicePower_SakuraCard";
    private static final String POWER_NAME;
    private static final String[] POWER_DESCRIPTIONS;
    private static final String POWER_IMG_PATH = "img/powers/voice_power_sakuracard.png";
    private static final PowerType POWER_TYPE = PowerType.BUFF;

    static
    {
        PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        POWER_NAME = powerStrings.NAME;
        POWER_DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

    public KSMOD_VoicePower_SakuraCard(AbstractCreature target, int amount)
    {
        super(POWER_ID, POWER_NAME, POWER_IMG_PATH, POWER_TYPE, target, amount, true);
        this.updateDescription();
    }

    public void updateDescription()
    {
        this.description = POWER_DESCRIPTIONS[0] + this.amount + POWER_DESCRIPTIONS[1];
    }

    public void atStartOfTurn()
    {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new ClowCardTheVoice(), this.amount));
    }
}