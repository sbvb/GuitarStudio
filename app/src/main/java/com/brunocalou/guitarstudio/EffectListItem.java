package com.brunocalou.guitarstudio;

/**
 * Created by bruno on 10/02/16.
 */
public class EffectListItem {
    public String name;

    private Effect effect;

    public EffectListItem(String name, final Effect effect) {
        this.name = name;
        this.effect = effect;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }
}
