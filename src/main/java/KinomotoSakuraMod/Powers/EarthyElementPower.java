package KinomotoSakuraMod.Powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class EarthyElementPower extends CustomPower
{
    public static final String POWER_ID = "EarthyElementPower";
    private static final String POWER_NAME;
    private static final String[] POWER_DESCRIPTIONS;
    private static final String POWER_IMG_PATH = "img/powers/default_power.png";
    private static final PowerType POWER_TYPE = PowerType.DEBUFF;
    private static final int BLOCK_AMOUNT = 4;

    static
    {
        PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        POWER_NAME = powerStrings.NAME;
        POWER_DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

    public EarthyElementPower(AbstractCreature target, int amount)
    {
        super(POWER_ID, POWER_NAME, POWER_IMG_PATH, POWER_TYPE, target, amount);
        this.updateDescription();
    }

    public void updateDescription()
    {
        this.description = POWER_DESCRIPTIONS[0] + BLOCK_AMOUNT + POWER_DESCRIPTIONS[1];
    }

    public static boolean TryActiveEarthyElement(AbstractMonster monster, int needAmount, boolean isExhaust)
    {
        AbstractPower power;
        if (monster.hasPower(EarthyElementPower.POWER_ID))
        {
            power = monster.getPower(EarthyElementPower.POWER_ID);
        }
        else
        {
            return false;
        }
        if (power.amount >= needAmount)
        {
            AbstractPlayer player = AbstractDungeon.player;
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(player, player, BLOCK_AMOUNT));
            if (isExhaust)
            {
                if (monster.hasPower(LockPower.POWER_ID))
                {
                    LockPower lockpower = (LockPower)monster.getPower(LockPower.POWER_ID);
                    lockpower.OnActived();
                }
                else
                {
                    if (power.amount == needAmount)
                    {
                        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(monster, player, power));
                    }
                    else
                    {
                        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(monster, player, power, needAmount));
                    }
                }
            }
            power.flash();
            return true;
        }
        return false;
    }
}