package KinomotoSakuraMod.Cards.ClowCard;

import KinomotoSakuraMod.Cards.AbstractClowCard;
import KinomotoSakuraMod.Patches.CustomCardColor;
import KinomotoSakuraMod.Patches.CustomTag;
import KinomotoSakuraMod.Powers.FlyPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;

public class ClowCardTheFly extends AbstractClowCard
{
    public static final String ID = "ClowCardTheFly";
    private static final String NAME;
    private static final String DESCRIPTION;
    private static final String IMAGE_PATH = "img/cards/default_power_card.png";
    private static final int COST = 2;
    private static final CardType CARD_TYPE = CardType.POWER;
    private static final CardColor CARD_COLOR = CustomCardColor.CLOWCARD_COLOR;
    private static final CardRarity CARD_RARITY = CardRarity.UNCOMMON;
    private static final CardTarget CARD_TARGET = CardTarget.SELF;
    private static final int BASE_BLOCK = 6;
    private static final int UPGRADE_BLOCK = 4;
    private static final int BASE_MAGIC_NUMBER = 2;
    private static final int UPGRADE_MAGIC_NUMBER = 2;

    static
    {
        CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }

    public ClowCardTheFly()
    {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, CARD_TYPE, CARD_COLOR, CARD_RARITY, CARD_TARGET);
        this.tags.add(CustomTag.PHYSICS_CARD);
        this.baseBlock = BASE_BLOCK;
        this.setBaseMagicNumber(BASE_MAGIC_NUMBER);
    }

    public AbstractClowCard makeCopy()
    {
        return new ClowCardTheFly();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_BLOCK);
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new FlyPower(player, this.correctMagicNumber()), this.correctMagicNumber()));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(player, player, this.correctBlock()));
    }
}
