package list.infinite.com.infinitelist;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.HashMap;

/**
 * Created by mkankana on 7/27/16.
 */
public class ListFragment extends Fragment {

    private static final String TAG = ListFragment.class.getName();

    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment_layout, container);
        listView = (ListView) view.findViewById(R.id.list_view);
        new GetListData().execute();
        return view;
    }

    private class GetListData extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            HashMap<String, String> conditions = new HashMap<>();
            conditions.put("feature", "popular");
            conditions.put("sort", "created_at");
            conditions.put("rpp", "20");
            conditions.put("image_size", "3");
            conditions.put("include_store", "store_download");
            conditions.put("include_states", "voted");
            conditions.put("page", "1");
            return RestServiceManager.getData(conditions);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, "Web service Result " + result);
        }
    }

}
