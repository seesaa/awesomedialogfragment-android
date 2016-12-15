package jp.seesaa.android.example.awesomedialog.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import jp.seesaa.android.library.dialog.AbstractBuilder;
import jp.seesaa.android.library.dialog.AwesomeDialogFragment;

/**
 * Sample implementation of ProgressDialog and AwesomeDialogFragment.
 */
public class ProgressDialogFragment extends AwesomeDialogFragment {

    public static class Builder extends AbstractBuilder<ProgressDialogFragment.Builder, ProgressDialogFragment> {

        public static final String ARGS_MAX = "progress_max";

        private int progressMax = 100;

        public Builder(@NonNull FragmentActivity mActivity) {
            super(mActivity);
        }

        public Builder(Fragment mParentFragment) {
            super(mParentFragment);
        }

        public Builder setMax(int max) {
            progressMax = max;
            return this;
        }

        @Override
        public Bundle getBundle() {
            Bundle bundle = super.getBundle();
            bundle.putInt(ARGS_MAX, progressMax);
            return bundle;
        }

        @Override
        public ProgressDialogFragment.Builder self() {
            return this;
        }

        @Override
        public ProgressDialogFragment build() {
            return new ProgressDialogFragment();
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getContext(), getDialogTheme());
        int progressMax = getArguments().getInt(Builder.ARGS_MAX);

        dialog.setMessage(getMessage());
        dialog.setCancelable(getCancelable());
        dialog.setCanceledOnTouchOutside(getCancelable());
        dialog.setMax(progressMax);

        return dialog;
    }

    public int getMax() {
        return getProgressDialog().getMax();
    }

    public void setProgress(int value) {
        getProgressDialog().setProgress(value);
    }

    private ProgressDialog getProgressDialog() {
        return (ProgressDialog) getDialog();
    }
}
