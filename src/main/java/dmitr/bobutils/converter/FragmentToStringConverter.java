package dmitr.bobutils.converter;

import dmitr.bobutils.model.Fragment;
import javafx.util.StringConverter;

public class FragmentToStringConverter extends StringConverter<Fragment> {

    @Override
    public String toString(Fragment fragment) {
        return fragment.getContent();
    }

    @Override
    public Fragment fromString(String s) {
        return null;
    }

}
