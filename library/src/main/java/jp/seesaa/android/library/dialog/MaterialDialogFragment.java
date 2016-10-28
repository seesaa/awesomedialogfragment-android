package jp.seesaa.android.library.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;

import static android.text.TextUtils.isEmpty;

public class MaterialDialogFragment extends AwesomeDialogFragment {

    public static class Builder extends AbstractBuilder<Builder, MaterialDialogFragment> {

        public Builder(@NonNull FragmentActivity mActivity) {
            super(mActivity);
        }

        public Builder(Fragment mParentFragment) {
            super(mParentFragment);
        }

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public MaterialDialogFragment build() {
            return new MaterialDialogFragment();
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), getDialogTheme());

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MaterialDialogFragment.this.dismiss();
                if (callback != null) {
                    callback.onMyDialogSucceeded(getRequestCode(), i, getParams());
                }
            }
        };

        builder.setTitle(getTitle()).setMessage(getMessage());

        if (getItems() != null) {
            builder.setItems(getItems(), listener);
        }

        if (!isEmpty(getPositiveLabel())) {
            builder.setPositiveButton(getPositiveLabel(), listener);
        }

        if (!isEmpty(getNegativeLabel())) {
            builder.setNegativeButton(getNegativeLabel(), listener);
        }

        return builder.create();
    }
}
