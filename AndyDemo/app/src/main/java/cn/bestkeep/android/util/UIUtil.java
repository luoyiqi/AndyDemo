package cn.bestkeep.android.util;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import org.xutils.common.util.LogUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;


public class UIUtil {
    public static void moveCursor2End(Spannable text) {
        try {
            Selection.setSelection(text, text.length());
        } catch (Exception e) {
            LogUtil.w("move cursor to end failure.", e);
        }
    }

    public static void changeAlpha(Activity activity, boolean isDarken) {
        changeAlpha(activity, isDarken ? .3f : 1.0f);
    }

    public static void darken(Activity activity) {
        changeAlpha(activity, .3f);
    }

    public static void brighten(Activity activity) {
        changeAlpha(activity, 1.0f);
    }

    private static void changeAlpha(Activity activity, float alpha) {
        if (activity == null) {
            LogUtil.wtf("activity is null");
            return;
        }
        WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();
        layoutParams.alpha = alpha;
        activity.getWindow().setAttributes(layoutParams);
    }


    public static <T> T setTag(RadioGroup radioGroup, List<T> tagList) {
        return setTag(radioGroup, tagList, CollectionUtil.isEmpty(tagList) ? null : tagList.get(0));
    }

    public static <T> T setTag(RadioGroup radioGroup, List<T> tagList, T defaultTag) {
        if (CollectionUtil.isEmpty(tagList) || radioGroup == null) {
            return defaultTag;
        }
        int index = 0;
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            View view = radioGroup.getChildAt(i);
            if (!(view instanceof RadioButton)) {
                continue;
            }
            RadioButton radioButton = (RadioButton) view;
            T tag = tagList.size() > index ? tagList.get(index) : null;
            radioButton.setTag(tag);
            radioButton.setChecked(tag != null && defaultTag != null && tag.equals(defaultTag));
            index++;
        }
        return defaultTag;
    }

    public static void setText(EditText editText, String text) {
        if (editText == null) {
            return;
        }
        text = text == null ? "" : text;
        editText.setText(text);
        moveCursor2End(editText.getText());
    }

    public static String getText(Editable editable) {
        return getText(editable, "");
    }

    public static String getText(Editable editable, String defaultValue) {
        if (editable == null) {
            return defaultValue;
        }
        return editable.toString();
    }

    public static String getText(TextView textView) {
        return getText(textView, "");
    }

    public static String getText(TextView textView, String defaultValue) {
        if (textView == null) {
            return defaultValue;
        }
        CharSequence charSequence = textView.getText();
        if (charSequence == null) {
            return defaultValue;
        }
        return StringUtil.trim(charSequence.toString());
    }

    public static boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    public static void switchVisibleOrGone(View view) {
        if (view == null) {
            return;
        }
        view.setVisibility(isVisible(view) ? View.GONE : View.VISIBLE);
    }

    public static void setVisibleOrGone(View view, boolean condition) {
        if (view != null) {
            view.setVisibility(condition ? View.VISIBLE : View.GONE);
        }
    }

    public static void setVisibleOrInvisible(View view, boolean condition) {
        if (view != null) {
            view.setVisibility(condition ? View.VISIBLE : View.INVISIBLE);
        }
    }

    public static void setInvisible(View view) {
        setVisibleOrInvisible(view, true);
    }

    public static void setGone(View view) {
        setVisibleOrGone(view, false);
    }

    public static void setEllipsis(final TextView textView, final int line) {
        ViewTreeObserver observer = textView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver obs = textView.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (textView.getLineCount() > line) {
                    int lineEndIndex = textView.getLayout().getLineEnd(line - 1);
                    String text = textView.getText().subSequence(0, lineEndIndex - 3) + "...";
                    textView.setText(text);
                }
            }
        });

    }


    public static int getWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void showSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public static void hideSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void setOnclickInterval(View view, Action1 action1) {
        RxView.clicks(view)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .subscribe(action1);
    }

}
