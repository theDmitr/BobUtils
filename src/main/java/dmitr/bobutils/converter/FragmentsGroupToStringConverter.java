package dmitr.bobutils.converter;

import dmitr.bobutils.model.FragmentsGroup;
import javafx.util.StringConverter;

public class FragmentsGroupToStringConverter extends StringConverter<FragmentsGroup> {

    @Override
    public String toString(FragmentsGroup fragmentsGroup) {
        return fragmentsGroup != null ? fragmentsGroup.getCaption() : null;
    }

    @Override
    public FragmentsGroup fromString(String s) {
        return null;
    }

}
