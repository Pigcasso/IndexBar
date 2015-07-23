package io.github.brightyoyo.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.brightyoyo.IndexBar;
import io.github.brightyoyo.sample.common.dummydata.Cheeses;


public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private IndexBar mIndexBar;
    private TextView mPreviewText;

    private final List<String> dummyData = Cheeses.asList();
    private Map<String, Integer> mSections = new HashMap<String, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int length = dummyData.size();
        for (int i = 0; i < length; i++) {
            String alphabet = dummyData.get(i).substring(0, 1);
            if (!mSections.containsKey(alphabet)) {
                mSections.put(alphabet, i);
            }
        }

        mListView = (ListView) findViewById(android.R.id.list);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, dummyData));
        mIndexBar = (IndexBar) findViewById(R.id.index_bar);
        mIndexBar.setSections(alphabets());
        mPreviewText = (TextView) findViewById(R.id.previewText);

        mIndexBar.setIndexBarFilter(new IndexBar.IIndexBarFilter() {
            @Override
            public void filterList(float sideIndex, int position, String previewText) {
                Integer selection = mSections.get(previewText);
                if (selection != null) {
                    mPreviewText.setVisibility(View.VISIBLE);
                    mPreviewText.setText(previewText);
                    mListView.setSelection(selection);
                } else {
                    mPreviewText.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String[] alphabets() {
        final int length = 26;
        final String[] alphabets = new String[length];
        char c = 'A';
        for (int i = 0; i < length; i++) {
            alphabets[i] = String.valueOf(c++);
        }
        return alphabets;
    }
}
