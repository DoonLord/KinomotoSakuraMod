package KinomotoSakuraMod.Actions;

import KinomotoSakuraMod.Patches.CustomCardColor;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;

public class MirrorAction extends AbstractGameAction
{
    public static final String ACTION_ID = "MirrorAction";
    private static final String[] TEXT;
    private static final float DURATION = Settings.ACTION_DUR_FAST;
    private AbstractPlayer player;
    private int dupeAmount = 1;
    private ArrayList<AbstractCard> cannotDuplicateList = new ArrayList<AbstractCard>();

    static
    {
        UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ACTION_ID);
        TEXT = uiStrings.TEXT;
    }

    public MirrorAction(int amount)
    {
        this.setValues(AbstractDungeon.player, AbstractDungeon.player, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = DURATION;
        this.player = AbstractDungeon.player;
        this.dupeAmount = amount;
    }

    public void update()
    {
        if (this.duration == DURATION)
        {
            for (AbstractCard card : this.player.hand.group)
            {
                if (!this.IsCorrectCardType(card))
                {
                    this.cannotDuplicateList.add(card);
                }
            }
            if (this.cannotDuplicateList.size() == this.player.hand.group.size())
            {
                this.isDone = true;
                return;
            }

            this.player.hand.group.removeAll(this.cannotDuplicateList);
            if (this.player.hand.group.size() == 1)
            {
                for (int i = 0; i < this.dupeAmount; ++i)
                {
                    AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(this.player.hand.getTopCard().makeStatEquivalentCopy()));
                }
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
            for (AbstractCard card : AbstractDungeon.handCardSelectScreen.selectedCards.group)
            {
                AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(card.makeStatEquivalentCopy()));
                for (int j = 0; j < this.dupeAmount; ++j)
                {
                    AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(card.makeStatEquivalentCopy()));
                }
            }
            this.returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }
        this.tickDuration();
    }

    private void returnCards()
    {

        for (AbstractCard card : this.cannotDuplicateList)
        {
            this.player.hand.addToTop(card);
        }
        this.player.hand.refreshHandLayout();
    }

    private boolean IsCorrectCardType(AbstractCard card)
    {
        return card.color == CustomCardColor.CLOWCARD_COLOR || card.color == CustomCardColor.SAKURACARD_COLOR;
    }
}