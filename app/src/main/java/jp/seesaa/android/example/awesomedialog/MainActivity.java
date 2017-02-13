package jp.seesaa.android.example.awesomedialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.listView)
    ListView listView;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ITEMS);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
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
