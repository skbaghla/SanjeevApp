package com.sanjeev.sanjeevapp.module_7;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sanjeev.sanjeevapp.R;

/**
 * Created by Sanjeev on 02-08-2022.
 */
public class ListMenuFragment extends ListFragment {
    String[] users = new String[] { "Sanjeev","Rohini","Trishika","Praveen","Sateesh","Madhav" };
    String[] location = new String[]{"Bathinda","Toronto","Mohali","Bangalore","Mumbai","Delhi"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.listitems_info, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, users);
        setListAdapter(adapter);
        return view;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        DetailsFragment txt = (DetailsFragment)getFragmentManager().findFragmentById(R.id.fragment2);
        txt.change("Name: "+ users[position],"Location : "+ location[position]);
        getListView().setSelector(android.R.color.holo_blue_dark);
    }
}

