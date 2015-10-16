package nicholas.witmer.nwitmerlab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by nicho on 10/16/2015.
 */
public class SelectionAdapter extends ArrayAdapter<DrawerItem>
{
    Context activity;
    int layoutID;
    DrawerItem[] options;

    public SelectionAdapter(Context caller, int id, DrawerItem[] options)
    {
        super(caller, id, options);
        this.activity = caller;
        this.layoutID = id;
        this.options = options;
    }

    @Override
    public View getView(int groupPosition, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)
                    this.activity.getSystemService(this.activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.layoutID, null);
        }
        TextView text = (TextView)convertView.findViewById(R.id.title);
        text.setText(this.options[groupPosition].getTitle());
        return convertView;
    }

}
