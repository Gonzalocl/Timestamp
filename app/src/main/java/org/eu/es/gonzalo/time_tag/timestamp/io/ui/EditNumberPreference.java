package org.eu.es.gonzalo.time_tag.timestamp.io.ui;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;

public class EditNumberPreference extends EditTextPreference {

    public EditNumberPreference(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setNumberInputType();
    }

    public EditNumberPreference(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
    }
}
