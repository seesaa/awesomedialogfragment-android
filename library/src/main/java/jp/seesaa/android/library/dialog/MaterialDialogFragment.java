package jp.seesaa.android.library.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.appcompat.app.AlertDialog;

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
                if (successCallback != null) {
                    successCallback.onMyDialogSucceeded(getRequestCode(), i, getParams());
                }
            }
        };

        builder.setTitle(getTitle())
                .setMessage(getMessage());

        if (getItems() != null) {
            builder.setItems(getItems(), listener);
        }

        if (!isEmpty(getPositiveLabel())) {
            builder.setPositiveButton(getPositiveLabel(), listener);
        }

        if (!isEmpty(getNegativeLabel())) {
            builder.setNegativeButton(getNegativeLabel(), listener);
        }

        if (!isEmpty(getNeutralLabel())) {
            builder.setNeutralButton(getNeutralLabel(), listener);
        }

        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(getCancelable());
        setCancelable(getCancelable());

        return alertDialog;
    }
}
