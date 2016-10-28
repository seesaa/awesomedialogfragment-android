package jp.seesaa.android.library.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public abstract class AbstractBuilder<T extends AbstractBuilder<T, F>, F extends AwesomeDialogFragment> {
    private static final String TAG = AbstractBuilder.class.getSimpleName();

    public static final String ARGS_TITLE = "title";
    public static final String ARGS_MESSAGE = "message";
    public static final String ARGS_ITEMS = "items";
    public static final String ARGS_POSITIVE_LABEL = "positive_label";
    public static final String ARGS_NEGATIVE_LABEL = "negative_label";
    public static final String ARGS_CANCELABLE = "cancelable";
    public static final String ARGS_DIALOG_THEME = "dialog_theme";
    public static final String ARGS_PARAMS = "params";
    public static final String ARGS_REQUEST_CODE = "request_code";

    private final FragmentActivity activity;
    private final Fragment parentFragment;

    private String mTitle;
    private String mMessage;
    private String[] mItems;

    private String mPositiveLabel;
    private String mNegativeLabel;

    /**
     * リクエストコード. 親 Fragment 側の戻りで受け取る.
     */
    private int mRequestCode = -1;

    /**
     * リスナに受け渡す任意のパラメータ.
     */
    private Bundle mParams;

    /**
     * DialogFragment のタグ.
     */
    private String mTag = "awesomedialog!";

    /**
     * Dialog をキャンセル可かどうか.
     */
    private boolean mCancelable = true;

    /**
     * ダイアログのテーマ
     */
    private int mDialogTheme = -1;

    AbstractBuilder(@NonNull FragmentActivity activity) {
        this.activity = activity;
        this.parentFragment = null;
    }

    AbstractBuilder(Fragment parentFragment) {
        this.activity = null;
        this.parentFragment = parentFragment;
    }

    public abstract T self();

    public T title(@NonNull String title) {
        this.mTitle = title;
        return self();
    }

    public T title(@StringRes int titleRes) {
        return title(getContext().getString(titleRes));
    }

    public T message(@NonNull String message) {
        this.mMessage = message;
        return self();
    }

    public T message(@StringRes int messageRes) {
        return message(getContext().getString(messageRes));
    }

    public T items(@NonNull String... items) {
        this.mItems = items;
        return self();
    }

    public T items(@ArrayRes int itemsRes) {
        return items(getContext().getResources().getStringArray(itemsRes));
    }

    public T positive(@NonNull String positiveLabel) {
        this.mPositiveLabel = positiveLabel;
        return self();
    }

    public T positive(@StringRes int positiveLabelRes) {
        return positive(getContext().getString(positiveLabelRes));
    }

    public T negative(@NonNull String negativeLabel) {
        this.mNegativeLabel = negativeLabel;
        return self();
    }

    public T negative(@StringRes int negativeLabelRes) {
        return negative(getContext().getString(negativeLabelRes));
    }

    /**
     * Dialogからのコールバック判定に用いるrequestCodeを設定する
     *
     * @param requestCode リクエストコード
     * @return this
     */
    public T requestCode(int requestCode) {
        this.mRequestCode = requestCode;
        return self();
    }

    /**
     * DialogFragmentのタグを設定する
     *
     * @param tag タグ
     * @return this
     */
    public T tag(@NonNull String tag) {
        this.mTag = tag;
        return self();
    }

    /**
     * Positive / Negative 押下時のリスナに受け渡すパラメータを設定する.
     *
     * @param params リスナに受け渡すパラメータ
     * @return this
     */
    public T params(Bundle params) {
        this.mParams = new Bundle(params);
        return self();
    }

    /**
     * Dialogをボタン操作以外でキャンセルできるか否かを設定する
     *
     * @param cancelable キャンセル可否
     * @return this
     */
    public T cancelable(boolean cancelable) {
        this.mCancelable = cancelable;
        return self();
    }

    public T dialogTheme(@StyleRes int theme) {
        this.mDialogTheme = theme;
        return self();
    }

    public Bundle getBundle() {
        Bundle args = new Bundle();
        args.putString(ARGS_TITLE, mTitle);
        args.putString(ARGS_MESSAGE, mMessage);
        args.putStringArray(ARGS_ITEMS, mItems);
        args.putString(ARGS_POSITIVE_LABEL, mPositiveLabel);
        args.putString(ARGS_NEGATIVE_LABEL, mNegativeLabel);
        args.putBoolean(ARGS_CANCELABLE, mCancelable);
        args.putInt(ARGS_DIALOG_THEME, mDialogTheme);

        if (mParams != null) {
            args.putBundle(ARGS_PARAMS, mParams);
        }
        return args;
    }

    public abstract F build();

    public F show() {
        F f = build();
        Bundle args = getBundle();

        if (parentFragment != null) {
            f.setTargetFragment(parentFragment, mRequestCode);
        } else {
            args.putInt(ARGS_REQUEST_CODE, mRequestCode);
        }
        f.setArguments(args);


        FragmentManager fm;
        if (parentFragment != null) {
            fm = parentFragment.getChildFragmentManager();
        } else {
            fm = activity.getSupportFragmentManager();
        }
        if (fm == null) {
            Log.e(TAG, "FragmentManager is null!");
            return f;
        }

        // TODO: remove fragment if tagged fragment is added
//        FragmentTransaction ft = fm.beginTransaction();
//        Fragment byTag = fm.findFragmentByTag(mTag);
//        if (byTag != null) {
//            ft.remove(byTag);
//        }

        f.show(fm, mTag);
        return f;
    }

    /**
     * コンテキストを取得する. getString() 呼び出しの為.
     *
     * @return Context
     */
    protected Context getContext() {
        return (activity == null ? parentFragment.getContext() : activity).getApplicationContext();
    }
}
