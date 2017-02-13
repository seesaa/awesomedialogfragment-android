package jp.seesaa.android.example.awesomedialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jp.seesaa.android.example.awesomedialog.fragment.CallbackDemoFragment;
import jp.seesaa.android.example.awesomedialog.fragment.DemoFragment;
import jp.seesaa.android.example.awesomedialog.fragment.SuccessCallbackDemoFragment;


public class FragmentDemoActivity extends AppCompatActivity {

    private static final String EXTRA_FRAGMENT_TYPE = "EXTRA_FRAGMENT_TYPE";
    private static final String EXTRA_CANCELABLE = "EXTRA_CANCELABLE";

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({FragmentType.WITH_CALLBACK, FragmentType.WITHOUT_CALLBACK, FragmentType.WITH_SUCCESS_CALLBACK_ONLY})
    @interface FragmentType {
        int WITH_CALLBACK = 0;
        int WITHOUT_CALLBACK = 1;
        int WITH_SUCCESS_CALLBACK_ONLY = 2;
    }

    public static Intent createIntent(Context context, @FragmentType int fragmentType, boolean isCancelable) {
        Intent intent = new Intent(context, FragmentDemoActivity.class);
        intent.putExtra(EXTRA_FRAGMENT_TYPE, fragmentType);
        intent.putExtra(EXTRA_CANCELABLE, isCancelable);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            @FragmentType int fragmentType = extras.getInt(EXTRA_FRAGMENT_TYPE);
            boolean isCancelable = extras.getBoolean(EXTRA_CANCELABLE, true);

            Fragment f = null;
            switch (fragmentType) {
                case FragmentType.WITHOUT_CALLBACK:
                    f = DemoFragment.newInstance(isCancelable);
                    break;
                case FragmentType.WITH_CALLBACK:
                    f = CallbackDemoFragment.newInstance(isCancelable);
                    break;
                case FragmentType.WITH_SUCCESS_CALLBACK_ONLY:
                    f = SuccessCallbackDemoFragment.newInstance(isCancelable);
                    break;
            }

            if (f != null) {
                getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, f)
                        .commit();
            }
        }

    }
}
