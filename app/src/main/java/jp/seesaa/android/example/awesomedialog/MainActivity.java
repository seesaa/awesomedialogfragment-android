package jp.seesaa.android.example.awesomedialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import jp.seesaa.android.example.awesomedialog.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String[] ITEMS = {
            "Activity w/ Callbacks",
            "Activity w/o Callbacks",
            "Activity w/ SuccessCallback only",
            "Activity w/ cancelable = false",

            "Fragment w/ Callbacks",
            "Fragment w/o Callbacks",
            "Fragment w/ SuccessCallback only",
            "Fragment w/ cancelable = false",
    };

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ITEMS);
        binding.listView.setAdapter(adapter);
        binding.listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = null;

        switch (position) {
            case 0:
                intent = CallbackDemoActivity.createIntent(this);
                break;
            case 1:
                intent = DemoActivity.createIntent(this);
                break;
            case 2:
                intent = SuccessCallbackDemoActivity.createIntent(this, true);
                break;
            case 3:
                intent = SuccessCallbackDemoActivity.createIntent(this, false);
                break;

            case 4:
                intent = FragmentDemoActivity.createIntent(this, FragmentDemoActivity.FragmentType.WITH_CALLBACK, true);
                break;
            case 5:
                intent = FragmentDemoActivity.createIntent(this, FragmentDemoActivity.FragmentType.WITHOUT_CALLBACK, true);
                break;
            case 6:
                intent = FragmentDemoActivity.createIntent(this, FragmentDemoActivity.FragmentType.WITH_SUCCESS_CALLBACK_ONLY, true);
                break;
            case 7:
                intent = FragmentDemoActivity.createIntent(this, FragmentDemoActivity.FragmentType.WITH_SUCCESS_CALLBACK_ONLY, false);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
