package KinomotoSakuraMod.Powers;

import KinomotoSakuraMod.Actions.KSMOD_DarkAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class KSMOD_DarkPower extends KSMOD_CustomPower
{
    public static final String POWER_ID = "KSMOD_DarkPower";
    private static final String POWER_NAME;
    private static final String[] POWER_DESCRIPTIONS;
    private static final String POWER_IMG_PATH = "img/powers/default_power.png";
    private static final AbstractPower.PowerType POWER_TYPE = AbstractPower.PowerType.BUFF;
    private int upgradedNum = 0;

    static
    {
        PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        POWER_NAME = powerStrings.NAME;
        POWER_DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

    public KSMOD_DarkPower(AbstractCreature target, int amount, boolean upgraded)
    {
        super(POWER_ID, POWER_NAME, POWER_IMG_PATH, POWER_TYPE, target, amount);
        this.upgradedNum += upgraded ? 1 : 0;
        this.updateDescription();
    }

    public void updateDescription()
    {
        this.description = POWER_DESCRIPTIONS[0] + this.amount + POWER_DESCRIPTIONS[1] + (this.upgradedNum > 0 ? (POWER_DESCRIPTIONS[2] + upgradedNum + POWER_DESCRIPTIONS[3]) : "");
    }

    public void atEndOfTurn(boolean isPlayer)
    {
        if (isPlayer && !AbstractDungeon.player.hand.isEmpty())
        {
            AbstractDungeon.actionManager.addToBottom(new KSMOD_DarkAction(this.amount, this.upgradedNum));
        }
    }

    public void upgrade()
    {
        this.upgradedNum += 1;
    }
}
