package jp.seesaa.android.example.awesomedialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.seesaa.android.library.dialog.MaterialDialogFragment;

public class DemoActivity extends AppCompatActivity {

    protected static final String ARGS_CANCELABLE = "cancelable";

    public static Intent createIntent(Context context) {
        return new Intent(context, DemoActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    void clickButton() {
        boolean isCancelable = getIntent().getBooleanExtra(ARGS_CANCELABLE, true);

        new MaterialDialogFragment.Builder(this)
                .title("Title")
                .message("Message")
                .positive("Positive")
                .negative("Negative")
                .neutral("Neutral")
                .requestCode(12345)
                .cancelable(isCancelable)
                .show();
    }

}
