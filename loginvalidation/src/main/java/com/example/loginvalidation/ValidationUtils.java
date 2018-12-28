package com.example.loginvalidation;

import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.regex.Pattern;

public class ValidationUtils {

    public static boolean isMobileNumberValid(String mobileNumber) {
        return mobileNumber.trim().length() >= 10 && mobileNumber.length() <= 15;
    }

    public static boolean isNameValid(String about) {
        Pattern USER_NAME_PATTERN = Pattern.compile("^[0-9@.#$%^&*_&\\\\]+$");
        // UserName Validation Pattern String
        if (USER_NAME_PATTERN.matcher(about).matches()) {
            return true;
        }
        return false;
    }


    public static void disableSpecialChar(EditText editText) {
//        final Pattern USER_NAME_PATTERN = Pattern.compile("[^()[\\\\]{}*&^%$#@!]+");
        final Pattern USER_NAME_PATTERN = Pattern.compile("^[0-9@.#$%^&*_&\\\\]+$");
//        final Pattern USER_NAME_PATTERN = Pattern.compile("\"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$\"");
        final String blockCharacterSet = "0123456789<>•√π÷×¶∆£¢€¥°©®™✓!/\"#$%&'()*+,-./:;<=>?@][^_`{|}~१२३४५६७८९०…⟩»≥›⟨‹≤«♪♠♣♥♦ΩΠμ§←↑↓→∞≠≈‰℅₹\\";

        InputFilter filter = new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                Log.d("validation ", "filter() called with: source = [" + source + "], start = [" + start + "], end = [" + end + "], dest = [" + dest + "], dstart = [" + dstart + "], dend = [" + dend + "]");
                if (end > 1)
                    return "";
                for (int i = start; i < end; i++) {
                    if (blockCharacterSet.contains(("" + source.charAt(i)))) {
                        return source.toString().replace(source.charAt(i) + "", "");
                    }
                }
                return null;
            }
        };

        editText.setLongClickable(false);
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);

        editText.setCustomSelectionActionModeCallback(new ActionMode.Callback() {

            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            public void onDestroyActionMode(ActionMode mode) {
            }

            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }
        });
        editText.setFilters(new InputFilter[]{filter});
    }

}
