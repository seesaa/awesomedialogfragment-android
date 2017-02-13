package jp.seesaa.android.example.awesomedialog.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.seesaa.android.example.awesomedialog.R;
import jp.seesaa.android.library.dialog.MaterialDialogFragment;


public class DemoFragment extends Fragment {

    protected static final String ARGS_CANCELABLE = "ARGS_CANCELABLE";

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
        View view = inflater.inflate(R.layout.activity_demo, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.button)
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
