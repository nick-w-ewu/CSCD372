package nicholas.witmer.npwitmerlab8;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by nicho on 10/9/2015.
 */
public class MyListAdapter extends BaseExpandableListAdapter implements View.OnClickListener
{
    Activity caller;
    ArrayList<String> manufacturers;
    ArrayList<ArrayList<String>> models;
    LayoutInflater inflater ;

    public MyListAdapter(Activity act, ArrayList<String> makes,
                         ArrayList<ArrayList<String>> allmodels)
    {
        this.caller = act;
        this.manufacturers = makes;
        this.models = allmodels;
        inflater =  (LayoutInflater)
                act.getSystemService(act.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount()
    {
        return manufacturers.size();
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        return models.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return manufacturers.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        return models.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.group_layout, null);
        }
        TextView text = (TextView)convertView.findViewById(R.id.group1);
        text.setText(this.manufacturers.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.child_layout, null);
        }
        TextView text = (TextView)convertView.findViewById(R.id.childText);
        ImageView btn = (ImageView)convertView.findViewById(R.id.deleteBtn);
        btn.setTag(R.id.group_num, groupPosition);
        btn.setTag(R.id.posn_num, childPosition);
        btn.setOnClickListener(this);
        text.setText(this.models.get(groupPosition).get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }

    @Override
    public void onClick(View v)
    {
        int group = (int)v.getTag(R.id.group_num);
        int child = (int)v.getTag(R.id.posn_num);
        models.get(group).remove(child);
        notifyDataSetChanged();
    }
}
