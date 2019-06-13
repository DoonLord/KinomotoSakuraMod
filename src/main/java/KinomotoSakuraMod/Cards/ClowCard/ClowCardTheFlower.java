package KinomotoSakuraMod.Cards.ClowCard;

import KinomotoSakuraMod.Actions.ApplyElementAction;
import KinomotoSakuraMod.Cards.KSMOD_AbstractMagicCard;
import KinomotoSakuraMod.Patches.CustomCardColor;
import KinomotoSakuraMod.Patches.CustomTag;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ClowCardTheFlower extends KSMOD_AbstractMagicCard
{
    public static final String ID = "ClowCardTheFlower";
    private static final String NAME;
    private static final String DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION;
    private static final String IMAGE_PATH = "img/cards/clowcard/the_flower.png";
    private static final int COST = 1;
    private static final CardType CARD_TYPE = CardType.SKILL;
    private static final CardColor CARD_COLOR = CustomCardColor.CLOWCARD_COLOR;
    private static final CardRarity CARD_RARITY = CardRarity.UNCOMMON;
    private static final CardTarget CARD_TARGET = CardTarget.ENEMY;
    private static final float HEAL_RATE = 0.25F;
    private static final float BLOCK_RATE = 0.5F;
    private static final int BASE_MAGIC_NUMBER = 2;

    static
    {
        CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

    public ClowCardTheFlower()
    {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, CARD_TYPE, CARD_COLOR, CARD_RARITY, CARD_TARGET, CustomTag.ELEMENT_CARD);
        this.setBaseMagicNumber(BASE_MAGIC_NUMBER);
        this.exhaust = true;
    }

    @Override
    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public KSMOD_AbstractMagicCard makeCopy()
    {
        return new ClowCardTheFlower();
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        int amount = 0;
        for (AbstractMonster mon: AbstractDungeon.getMonsters().monsters)
        {
            if (mon.hasPower(WateryElementPower.POWER_ID))
            {
                AbstractPower power = mon.getPower(WateryElementPower.POWER_ID);
                power.flash();
                amount += power.amount;
            }
        }
        AbstractDungeon.actionManager.addToBottom(new HealAction(player, player, MathUtils.ceil(amount * HEAL_RATE)));
        if (upgraded)
        {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(player, player, MathUtils.ceil(amount * BLOCK_RATE)));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyElementAction(monster, player, new EarthyElementPower(monster, this.magicNumber), this.magicNumber, true));
    }
}