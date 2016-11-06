package jp.seesaa.android.example.awesomedialog.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import jp.seesaa.android.library.dialog.AwesomeDialogFragment;

public class CallbackDemoFragment extends DemoFragment implements AwesomeDialogFragment.Callback {
    private static final String TAG = CallbackDemoFragment.class.getSimpleName();

    public static CallbackDemoFragment newInstance() {
        Bundle args = new Bundle();
        CallbackDemoFragment fragment = new CallbackDemoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onMyDialogSucceeded(int requestCode, int resultCode, Bundle params) {
        Log.d(TAG, "onMyDialogSucceeded: " + requestCode + " / " + resultCode);
        Toast.makeText(getContext(), "Succeeded: " + requestCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMyDialogCancelled(int requestCode, Bundle params) {
        Log.d(TAG, "onMyDialogCancelled: " + requestCode);
        Toast.makeText(getContext(), "Cencelled: " + requestCode, Toast.LENGTH_SHORT).show();
    }
}
