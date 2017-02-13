package jp.seesaa.android.example.awesomedialog.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import jp.seesaa.android.library.dialog.AwesomeDialogFragment;

public class SuccessCallbackDemoFragment extends DemoFragment implements
        AwesomeDialogFragment.SuccessCallback {
    private static final String TAG = SuccessCallbackDemoFragment.class.getSimpleName();

    public static SuccessCallbackDemoFragment newInstance(boolean isCancelable) {
        Bundle args = new Bundle();
        args.putBoolean(ARGS_CANCELABLE, isCancelable);
        SuccessCallbackDemoFragment fragment = new SuccessCallbackDemoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onMyDialogSucceeded(int requestCode, int resultCode, Bundle params) {
        Log.d(TAG, "onMyDialogSucceeded: " + requestCode + " / " + resultCode);
        Toast.makeText(getContext(), "Succeeded: " + requestCode, Toast.LENGTH_SHORT).show();
    }
}
