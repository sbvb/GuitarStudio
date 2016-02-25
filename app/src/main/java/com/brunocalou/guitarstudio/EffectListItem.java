package com.brunocalou.guitarstudio;

import android.app.Activity;

/**
 * Created by bruno on 10/02/16.
 */
public class EffectListItem {
    public String name;

    private Effect effect;

    private Class effect_activity;

    public EffectListItem(String name, final Effect effect, Class activity) {
        this.name = name;
        this.effect = effect;
        this.effect_activity = activity;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public Class getEffectActivity() {
        return effect_activity;
    }
}
