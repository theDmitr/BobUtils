package dmitr.bobutils.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "fragmentFragmentsGroup")
public class FragmentFragmentsGroup {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private int fragmentsGroupId;

    @DatabaseField(canBeNull = false)
    private int fragmentId;

    FragmentFragmentsGroup() {
    }

    public FragmentFragmentsGroup(int fragmentsGroupId, int fragmentId) {
        this.fragmentsGroupId = fragmentsGroupId;
        this.fragmentId = fragmentId;
    }

    public int getFragmentsGroupId() {
        return fragmentsGroupId;
    }

    public int getFragmentId() {
        return fragmentId;
    }
}
