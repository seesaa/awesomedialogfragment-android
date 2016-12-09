package jp.seesaa.android.example.awesomedialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.seesaa.android.library.dialog.AwesomeDialogFragment;
import jp.seesaa.android.library.dialog.MaterialDialogFragment;

public class CallbackDemoActivity extends DemoActivity implements AwesomeDialogFragment.Callback {
    private static final String TAG = CallbackDemoActivity.class.getSimpleName();

    public static Intent createIntent(Context context) {
        return new Intent(context, CallbackDemoActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    void clickButton() {
        new MaterialDialogFragment.Builder(this)
                .title("Title")
                .message("Message")
                .positive("Positive")
                .negative("Negative")
                .neutral("Neutral")
                .requestCode(12345)
                .show();
    }

    @Override
    public void onMyDialogSucceeded(int requestCode, int resultCode, Bundle params) {
        Log.d(TAG, "onMyDialogSucceeded: " + requestCode + " / " + resultCode);
        Toast.makeText(this, "Succeeded: " + requestCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMyDialogCancelled(int requestCode, Bundle params) {
        Log.d(TAG, "onMyDialogCancelled: " + requestCode);
        Toast.makeText(this, "Cencelled: " + requestCode, Toast.LENGTH_SHORT).show();
    }
}
