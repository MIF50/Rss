package mif50.com.rssreader;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;

import adapter.FeedAdapter;
import common.HTTPDataHandler;
import model.RSSObject;

public class MainActivity extends AppCompatActivity {
    // find view in main_activity.xml
    Toolbar toolbar;
    RecyclerView recycler_rss;
    RecyclerView.LayoutManager layoutManager;

    private final String rss_link="http://rss.nytimes.com/services/xml/rss/nyt/Science.xml"; // link that we read data from it
    private final String rss_to_json_api=" https://api.rss2json.com/v1/api.json?rss_url="; // convert data to json object

    RSSObject rssObject; // model that hold data that we read it

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // find view in main_activity.xml
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Reader");
        setSupportActionBar(toolbar);
        recycler_rss=findViewById(R.id.recycler_rss_reader);
        recycler_rss.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recycler_rss.setLayoutManager(layoutManager);
        // read data
        loadRSS();

    }
    /*
    * this method that will read data */
    private void loadRSS() {
        AsyncTask<String,String,String> task=new AsyncTask<String, String, String>() {
            ProgressDialog dialog=new ProgressDialog(MainActivity.this);

            @Override
            protected void onPreExecute() {
                dialog.setMessage("please wait ....");
                dialog.show();
            }

            @Override
            protected String doInBackground(String... strings) {
                String result;
                HTTPDataHandler httpDataHandler=new HTTPDataHandler(); // create connection
                result=httpDataHandler.getHTTPData(strings[0]);// read data as json object
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                // s is result that return from doInBackground()
                dialog.dismiss();
                rssObject=new Gson().fromJson(s,RSSObject.class); // get data as Model object of result
                FeedAdapter adapter=new FeedAdapter(MainActivity.this,rssObject);// create adapter
                recycler_rss.setAdapter(adapter); // set up adapter
                adapter.notifyDataSetChanged(); // when refresh change data if changed 
            }
        };
        // now we prepare link of json obj of RSS to be rss_to_json_api+rss_link
        StringBuilder url_get_data=new StringBuilder(rss_to_json_api);
        url_get_data.append(rss_link); // now data as json obj

        task.execute(url_get_data.toString()); // set uri to doInBackground()
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.menu_refresh){
            loadRSS(); // read data
        }
        return true;
    }
}
