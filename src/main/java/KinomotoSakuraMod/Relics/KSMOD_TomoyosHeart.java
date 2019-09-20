package KinomotoSakuraMod.Relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class KSMOD_TomoyosHeart extends CustomRelic
{
    public static final String RELIC_ID = "KSMOD_TomoyosHeart";
    private static final String RELIC_IMG_PATH = "img/relics/icon/default.png";
    private static final String RELIC_IMG_OTL_PATH = "img/relics/outline/default.png";
    private static final RelicTier RELIC_TIER = RelicTier.UNCOMMON;
    private static final LandingSound RELIC_SOUND = AbstractRelic.LandingSound.MAGICAL;
    private static final int BLOCK_NUMBER = 3;

    public KSMOD_TomoyosHeart()
    {
        super(RELIC_ID, ImageMaster.loadImage(RELIC_IMG_PATH), ImageMaster.loadImage(RELIC_IMG_OTL_PATH), RELIC_TIER, RELIC_SOUND);
    }

    public String getUpdatedDescription()
    {
        return this.DESCRIPTIONS[0] + BLOCK_NUMBER + this.DESCRIPTIONS[1];
    }

    public AbstractRelic makeCopy()
    {
        return new KSMOD_TomoyosHeart();
    }

    public void onExhaust(AbstractCard card)
    {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, BLOCK_NUMBER));
    }
}