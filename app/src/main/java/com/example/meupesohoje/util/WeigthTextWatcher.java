package com.example.meupesohoje.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class WeigthTextWatcher implements TextWatcher {

    EditText editText;

    public WeigthTextWatcher(EditText editText){
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence str, int i, int i1, int i2) {
        if(!str.toString().isEmpty()){
            this.editText.removeTextChangedListener(this);

            String cleanString = str.toString().replace("kg", "");
            String finalString = cleanString + "kg";
            editText.setText(finalString);
            editText.setSelection(cleanString.length());
            if(finalString.equals("kg")){
                editText.setText("");
            }
            editText.addTextChangedListener(this);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
