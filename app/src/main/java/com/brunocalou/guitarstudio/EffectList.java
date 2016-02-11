package com.brunocalou.guitarstudio;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 10/02/16.
 */
public class EffectList extends ArrayList<EffectListItem> {
    @Override
    public boolean add(EffectListItem object) {
        Log.d("EffectList", "Added effect to the effect list");
        Log.d("EffectList", "Total = " + (this.size() + 1));
        return super.add(object);
    }
}
