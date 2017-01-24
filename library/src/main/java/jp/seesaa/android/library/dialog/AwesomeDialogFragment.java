package jp.seesaa.android.library.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;

import jp.seesaa.android.library.dialog.util.FragmentUtil;

public abstract class AwesomeDialogFragment extends DialogFragment {
    private static final String TAG = AwesomeDialogFragment.class.getSimpleName();

    /**
     * Dialog で何か処理が起こった場合にコールバックされるリスナ.
     * {@link SuccessCallback} および {@link CancelCallback} の双方を実装している
     */
    public interface Callback extends SuccessCallback, CancelCallback {
    }

    /**
     * Dialog でボタンやリストが選択された際にコールバックされるリスナ.
     * Cancel関連を実装する必要が無い場合はこちらだけを実装しても機能する.
     */
    public interface SuccessCallback {
        /**
         * Dialog で positiveButton, NegativeButton, リスト選択など行われた際に呼ばれる.
         *
         * @param requestCode DialogFragment 実行時 requestCode
         * @param resultCode  DialogInterface.BUTTON_POSITIVE, DialogInterface.BUTTON_NEGATIVE, DialogInterface.BUTTON_NEUTRAL もしくはリストの position
         * @param params      DialogFragment に受渡した引数
         */
        void onMyDialogSucceeded(int requestCode, int resultCode, Bundle params);
    }

    /**
     * Dialog がキャンセル(DialogInterface.BUTTON_CANCELを除く)された際にコールバックされるリスナ.
     */
    public interface CancelCallback {
        /**
         * Dialog がキャンセルされた時に呼ばれる.
         *
         * @param requestCode DialogFragment 実行時 requestCode
         * @param params      DialogFragment に受渡した引数
         */
        void onMyDialogCancelled(int requestCode, Bundle params);
    }

    /**
     * コールバックを保持するメンバー。
     *
     * @deprecated 代わりに {@link AwesomeDialogFragment#successCallback}, {@link AwesomeDialogFragment#cancelCallback} を用いること。
     */
    @Nullable
    @Deprecated
    protected Callback callback;

    @Nullable
    protected SuccessCallback successCallback;

    @Nullable
    protected CancelCallback cancelCallback;

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
            successCallback = FragmentUtil.registeredListener(this, SuccessCallback.class);
        } catch (ClassNotFoundException ignored) {
        }

        try {
            cancelCallback = FragmentUtil.registeredListener(this, CancelCallback.class);
        } catch (ClassNotFoundException ignored) {
        }

        callback = new Callback() {
            @Override
            public void onMyDialogSucceeded(int requestCode, int resultCode, Bundle params) {
                if (successCallback != null) {
                    successCallback.onMyDialogSucceeded(requestCode, resultCode, params);
                }
            }

            @Override
            public void onMyDialogCancelled(int requestCode, Bundle params) {
                if (cancelCallback != null) {
                    cancelCallback.onMyDialogCancelled(requestCode, params);
                }
            }
        };
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
        successCallback = null;
        cancelCallback = null;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        if (cancelCallback != null) {
            cancelCallback.onMyDialogCancelled(getRequestCode(), getArguments().getBundle(AbstractBuilder.ARGS_PARAMS));
        }
    }

}
