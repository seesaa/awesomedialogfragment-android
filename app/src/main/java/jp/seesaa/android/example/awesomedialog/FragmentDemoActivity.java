package jp.seesaa.android.example.awesomedialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import jp.seesaa.android.example.awesomedialog.fragment.CallbackDemoFragment;
import jp.seesaa.android.example.awesomedialog.fragment.DemoFragment;


public class FragmentDemoActivity extends AppCompatActivity {

    public static final String EXTRA_HAS_CALLBACK = "EXTRA_HAS_CALLBACK";

    public static Intent createIntent(Context context, boolean fragmentHasCallback) {
        Intent intent = new Intent(context, FragmentDemoActivity.class);
        intent.putExtra(EXTRA_HAS_CALLBACK, fragmentHasCallback);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            boolean hasCallback = extras.getBoolean(EXTRA_HAS_CALLBACK);

            Fragment f = hasCallback ? CallbackDemoFragment.newInstance() : DemoFragment.newInstance();

            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, f)
                    .commit();
        }

    }
}
