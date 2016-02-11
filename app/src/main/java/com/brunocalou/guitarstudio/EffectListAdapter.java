package com.brunocalou.guitarstudio;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import java.util.List;

/**
 * Created by bruno on 10/02/16.
 */
public class EffectListAdapter extends BaseAdapter {

    private EffectList effects;

    private LayoutInflater inflater;

    private Activity context;

    public EffectListAdapter(Activity context, EffectList effects) {
        this.effects = effects;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return effects.size();
    }

    @Override
    public Object getItem(int position) {
        return effects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem view_holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.effect_list_item, parent, false);
            view_holder = new ViewHolderItem();
            view_holder.check_box = (CheckBox) convertView.findViewById(R.id.effectCheckBox);
            view_holder.img_button = (ImageButton) convertView.findViewById(R.id.effectSettingsButton);

            convertView.setTag(view_holder);
        } else {
            view_holder = (ViewHolderItem) convertView.getTag();
        }
        final EffectListItem effect_item = effects.get(position);

        if (effect_item != null) {
            view_holder.check_box.setText(effect_item.name);
            view_holder.check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        Log.d("Adapter", "CheckBox is checked");
                        effect_item.getEffect().enable();
                    } else {
                        Log.d("Adapter", "CheckBox is not checked");
                        effect_item.getEffect().disable();
                    }
                }
            });
            view_holder.check_box.setChecked(effect_item.getEffect().isEnabled());

//            view_holder.img_button
        }

        return convertView;
    }

    class ViewHolderItem {
        ImageButton img_button;
        CheckBox check_box;
    }
}

