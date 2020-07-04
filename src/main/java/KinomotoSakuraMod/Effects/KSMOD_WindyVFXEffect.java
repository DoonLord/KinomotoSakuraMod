package KinomotoSakuraMod.Effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class KSMOD_WindyVFXEffect extends AbstractGameEffect
{
    private float timer = 0.0F;
    private Color color = new Color(165F / 252F, 244F / 252F, 200F / 252F, 205F / 252F);

    public KSMOD_WindyVFXEffect(float duration)
    {
        super();
        this.duration = duration;
        this.startingDuration = this.duration;
    }

    public void update()
    {
        if (this.duration == this.startingDuration)
        {
            AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(color));
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        this.timer -= Gdx.graphics.getDeltaTime();
        if (this.timer < 0.0F)
        {
            for (int i = 0; i < 10; i++)
            {
                AbstractDungeon.effectsQueue.add(new KSMOD_WindyEffectElement(this.startingDuration));
            }
            this.timer = 0.05F;
        }

        if (this.duration < 0.0F)
        {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb)
    {
    }

    public void dispose()
    {
    }
}
