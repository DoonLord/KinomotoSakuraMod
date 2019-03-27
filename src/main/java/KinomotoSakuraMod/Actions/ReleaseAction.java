package KinomotoSakuraMod.Actions;

import KinomotoSakuraMod.Cards.AbstractMagicCard;
import KinomotoSakuraMod.Cards.SpellCard.SpellCardRelease;
import KinomotoSakuraMod.Patches.CustomCardColor;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;

public class ReleaseAction extends AbstractGameAction
{
    private static final String ACTION_ID = "ReleaseAction";
    private static final String[] TEXT;
    private AbstractPlayer player;
    private static final float DURATION = Settings.ACTION_DUR_XFAST;
    private static final float RELEASE_UPGRADE_RATE = 0.5F;
    private int damage;
    private ArrayList<AbstractCard> cannotReleaseList = new ArrayList<AbstractCard>();

    static
    {
        UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ACTION_ID);
        TEXT = uiStrings.TEXT;
    }

    public ReleaseAction(int damage)
    {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.player = AbstractDungeon.player;
        this.damage = damage;
        this.duration = DURATION;
    }

    public void update()
    {
        if (this.duration == Settings.ACTION_DUR_XFAST)
        {
            for (AbstractCard card : this.player.hand.group)
            {
                if (!this.IsCorrectCardType(card))
                {
                    this.cannotReleaseList.add(card);
                }
            }
            if (this.cannotReleaseList.size() == this.player.hand.group.size())
            {
                this.isDone = true;
                return;
            }

            this.player.hand.group.removeAll(this.cannotReleaseList);
            if (this.player.hand.group.size() == 1)
            {
                ReleaseCard(this.player.hand.getTopCard());
                this.returnCards();
                this.isDone = true;
            }

            if (this.player.hand.group.size() > 1)
            {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false);
                this.tickDuration();
                return;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved)
        {
            if (AbstractDungeon.handCardSelectScreen.selectedCards.size() > 0)
            {
                for (AbstractCard card : AbstractDungeon.handCardSelectScreen.selectedCards.group)
                {
                    ReleaseCard(card);
                    int size = AbstractDungeon.getMonsters().monsters.size();
                    int[] damageList = new int[size];
                    for (int i = 0; i < size; i++)
                    {
                        damageList[i] = this.damage;
                    }
                    AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(player, damageList, DamageInfo.DamageType.HP_LOSS, AttackEffect.FIRE));
                }
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        tickDuration();
    }

    private void returnCards()
    {

        for (AbstractCard card : this.cannotReleaseList)
        {
            this.player.hand.addToTop(card);
        }
        this.player.hand.refreshHandLayout();
    }

    private boolean IsCorrectCardType(AbstractCard card)
    {
        return card.color == CustomCardColor.CLOWCARD_COLOR || card.color == CustomCardColor.SAKURACARD_COLOR;
    }

    private void ReleaseCard(AbstractCard card)
    {
        if (card.costForTurn > 0)
        {
            card.setCostForTurn(0);
            card.superFlash(Color.GOLD.cpy());
        }
        if (card.type == AbstractCard.CardType.POWER)
        {
            reloadCardDescription(card, !card.isEthereal, !card.exhaust);
            card.isEthereal = true;
        }
        else
        {
            ((AbstractMagicCard) card).release(RELEASE_UPGRADE_RATE);
            reloadCardDescription(card, !card.isEthereal, !card.exhaust);
            card.isEthereal = true;
            card.exhaust = true;
        }
        AbstractDungeon.player.hand.addToTop(card);
    }

    private void reloadCardDescription(AbstractCard card, boolean isAddEthereal, boolean isAddExhaust)
    {
        if (isAddEthereal)
        {
            card.rawDescription = SpellCardRelease.EXTENDED_DESCRIPTION[0] + card.rawDescription;
        }
        if (isAddExhaust)
        {
            card.rawDescription = SpellCardRelease.EXTENDED_DESCRIPTION[1] + card.rawDescription;
        }
        card.rawDescription = SpellCardRelease.EXTENDED_DESCRIPTION[2] + card.rawDescription;
        card.initializeDescription();
    }
}
