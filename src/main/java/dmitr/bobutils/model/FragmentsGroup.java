package dmitr.bobutils.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "fragmentsGroup")
public class FragmentsGroup {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String caption;

    FragmentsGroup() {
    }

    public FragmentsGroup(String caption) {
        this.caption = caption;
    }

    public int getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

}
