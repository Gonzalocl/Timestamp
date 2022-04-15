package org.eu.es.gonzalo.time_tag.timestamp.io.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.InputType;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceDataStore;
import androidx.preference.PreferenceManager;

public class EditNumberPreference extends EditTextPreference {

    public EditNumberPreference(@NonNull Context context, @Nullable AttributeSet attrs,
                                int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setNumberInputType();
    }

    public EditNumberPreference(@NonNull Context context, @Nullable AttributeSet attrs,
                                int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setNumberInputType();
    }

    public EditNumberPreference(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setNumberInputType();
    }

    public EditNumberPreference(@NonNull Context context) {
        super(context);
        setNumberInputType();
    }

    private void setNumberInputType() {
        setOnBindEditTextListener(editText -> editText.setInputType(InputType.TYPE_CLASS_NUMBER));
        setPreferenceDataStore(new PreferenceDataStore() {
            @Override
            public void putString(String key, @Nullable String value) {
                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(EditNumberPreference.this.getContext());
                sharedPreferences.edit().putLong(key, Long.parseLong(value)).apply();
            }

            @Nullable
            @Override
            public String getString(String key, @Nullable String defValue) {
                if (defValue == null) {
                    return null;
                }
                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(EditNumberPreference.this.getContext());
                return String.valueOf(sharedPreferences.getLong(key, Long.parseLong(defValue)));
            }
        });
    }
}
