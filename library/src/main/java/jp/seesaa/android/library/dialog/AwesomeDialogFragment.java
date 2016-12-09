package jp.seesaa.android.library.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import jp.seesaa.android.library.dialog.util.FragmentUtil;

public abstract class AwesomeDialogFragment extends DialogFragment {
    private static final String TAG = AwesomeDialogFragment.class.getSimpleName();

    /**
     * Dialog で何か処理が起こった場合にコールバックされるリスナ.
     */
    public interface Callback {
        /**
         * Dialog で positiveButton, NegativeButton, リスト選択など行われた際に呼ばれる.
         *
         * @param requestCode DialogFragment 実行時 requestCode
         * @param resultCode  DialogInterface.BUTTON_POSITIVE, DialogInterface.BUTTON_NEGATIVE, DialogInterface.BUTTON_NEUTRAL もしくはリストの position
         * @param params      DialogFragment に受渡した引数
         */
        void onMyDialogSucceeded(int requestCode, int resultCode, Bundle params);

        /**
         * Dialog がキャンセルされた時に呼ばれる.
         *
         * @param requestCode DialogFragment 実行時 requestCode
         * @param params      DialogFragment に受渡した引数
         */
        void onMyDialogCancelled(int requestCode, Bundle params);
    }

    protected Callback callback;

    protected String getTitle() {
        return getArguments().getString(AbstractBuilder.ARGS_TITLE);
    }

    protected String getMessage() {
        return getArguments().getString(AbstractBuilder.ARGS_MESSAGE);
    }

    protected String[] getItems() {
        return getArguments().getStringArray(AbstractBuilder.ARGS_ITEMS);
    }

    protected String getPositiveLabel() {
        return getArguments().getString(AbstractBuilder.ARGS_POSITIVE_LABEL);
    }

    protected String getNegativeLabel() {
        return getArguments().getString(AbstractBuilder.ARGS_NEGATIVE_LABEL);
    }

    protected String getNeutralLabel() {
        return getArguments().getString(AbstractBuilder.ARGS_NEUTRAL_LABEL);
    }

    protected boolean getCancelable() {
        return getArguments().getBoolean(AbstractBuilder.ARGS_CANCELABLE);
    }

    @StyleRes
    protected int getDialogTheme() {
        return getArguments().getInt(AbstractBuilder.ARGS_DIALOG_THEME);
    }

    protected Bundle getParams() {
        return getArguments().getBundle(AbstractBuilder.ARGS_PARAMS);
    }

    /**
     * リクエストコードを取得する. Activity と ParentFragment 双方に対応するため.
     *
     * @return requestCode
     */
    protected int getRequestCode() {
        return getArguments().containsKey(AbstractBuilder.ARGS_REQUEST_CODE) ?
                getArguments().getInt(AbstractBuilder.ARGS_REQUEST_CODE) :
                getTargetRequestCode();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = FragmentUtil.registeredListener(this, Callback.class);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "Catch!", e);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        callback.onMyDialogCancelled(getRequestCode(), getArguments().getBundle(AbstractBuilder.ARGS_PARAMS));
    }

}
