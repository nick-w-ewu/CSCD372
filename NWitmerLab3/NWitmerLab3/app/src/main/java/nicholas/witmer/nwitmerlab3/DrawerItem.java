package nicholas.witmer.nwitmerlab3;

/**
 * Created by nicho_000 on 10/14/2015.
 */
public class DrawerItem
{
    private int res_id;
    private String title;

    public DrawerItem(int id, String title)
    {
        this.res_id = id;
        this.title = title;
    }

    public int getId()
    {
        return this.res_id;
    }

    public String getTitle()
    {
        return this.title;
    }

}
