package jp.seesaa.android.example.awesomedialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import jp.seesaa.android.example.awesomedialog.databinding.ActivityDemoBinding;
import jp.seesaa.android.library.dialog.MaterialDialogFragment;

public class DemoActivity extends AppCompatActivity {

    protected static final String ARGS_CANCELABLE = "cancelable";
    private ActivityDemoBinding binding;

    public static Intent createIntent(Context context) {
        return new Intent(context, DemoActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_demo);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton();
            }
        });
    }

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
