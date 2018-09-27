package jp.seesaa.android.example.awesomedialog.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import jp.seesaa.android.example.awesomedialog.R;
import jp.seesaa.android.example.awesomedialog.databinding.ActivityDemoBinding;
import jp.seesaa.android.library.dialog.MaterialDialogFragment;


public class DemoFragment extends Fragment {

    protected static final String ARGS_CANCELABLE = "ARGS_CANCELABLE";

    private ActivityDemoBinding binding;

    public static DemoFragment newInstance(boolean isCancelable) {
        Bundle args = new Bundle();
        args.putBoolean(ARGS_CANCELABLE, isCancelable);
        DemoFragment fragment = new DemoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_demo, container, false);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButton();
            }
        });
        return binding.getRoot();
    }

    void onClickButton() {
        boolean isCancelable = getArguments().getBoolean(ARGS_CANCELABLE, true);

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
