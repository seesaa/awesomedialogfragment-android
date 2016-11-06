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
            "Fragment w/ Callbacks",
            "Fragment w/o Callbacks"
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
            case 3:
                intent = FragmentDemoActivity.createIntent(this, position == 2);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
