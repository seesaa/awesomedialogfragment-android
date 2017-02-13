package jp.seesaa.android.example.awesomedialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import jp.seesaa.android.library.dialog.AwesomeDialogFragment;

public class SuccessCallbackDemoActivity extends DemoActivity implements
        AwesomeDialogFragment.SuccessCallback {
    private static final String TAG = SuccessCallbackDemoActivity.class.getSimpleName();

    public static Intent createIntent(Context context, boolean isCancelable) {
        Intent intent = new Intent(context, SuccessCallbackDemoActivity.class);
        intent.putExtra(ARGS_CANCELABLE, isCancelable);
        return intent;
    }

    @Override
    public void onMyDialogSucceeded(int requestCode, int resultCode, Bundle params) {
        Log.d(TAG, "onMyDialogSucceeded: " + requestCode + " / " + resultCode);
        Toast.makeText(this, "Succeeded: " + requestCode, Toast.LENGTH_SHORT).show();
    }
}
