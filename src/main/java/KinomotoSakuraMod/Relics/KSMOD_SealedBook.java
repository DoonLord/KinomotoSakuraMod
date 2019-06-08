package KinomotoSakuraMod.Relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class KSMOD_SealedBook extends CustomRelic
{
    public static final String RELIC_ID = "KSMOD_SealedBook";
    private static final String RELIC_IMG_PATH = "img/relics/icon/SealedBook.png";
    private static final String RELIC_IMG_OTL_PATH = "img/relics/outline/SealedBook.png";
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    private static final LandingSound RELIC_SOUND = AbstractRelic.LandingSound.MAGICAL;
    public static final int CHARGE_ACTIVE_NUMBER = 10;

    public KSMOD_SealedBook()
    {
        super(RELIC_ID, ImageMaster.loadImage(RELIC_IMG_PATH), ImageMaster.loadImage(RELIC_IMG_OTL_PATH), RELIC_TIER, RELIC_SOUND);
    }

    public String getUpdatedDescription()
    {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy()
    {
        return new KSMOD_SealedBook();
    }

    public static int applyPowerNumberOnce()
    {
        int amount = 1;
        return amount;
    }
}
