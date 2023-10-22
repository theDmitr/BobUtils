package dmitr.bobutils.database;

import com.j256.ormlite.table.DatabaseTable;
import dmitr.bobutils.model.Fragment;
import dmitr.bobutils.model.FragmentFragmentsGroup;
import dmitr.bobutils.model.FragmentsGroup;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FragmentsManager {

    public static List<FragmentsGroup> getAllFragmentsGroups() {
        List<FragmentsGroup> result;

        try {
            result = Database.getFragmentsGroupDao().queryForAll();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

        return result;
    }

    public static List<Fragment> getFragmentsByFragmentsGroup(FragmentsGroup fragmentsGroup) {
        List<Fragment> result = new ArrayList<>();

        try {
            List<Integer> fragmentIds =
                    Database.getFragmentFragmentsGroupDao().queryForEq("fragmentsGroupId", fragmentsGroup.getId())
                    .stream().map(FragmentFragmentsGroup::getFragmentId).toList();

            result = Database.getFragmentsDao().queryForAll()
                    .stream().filter(f -> fragmentIds.contains(f.getId())).toList();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

        return result;
    }

    public static void insertFragmentToFragmentsGroup(FragmentsGroup fragmentsGroup, String content) {
        try {
            List<Fragment> fragments = Database.getFragmentsDao().queryForEq("content", content);
            Fragment fragment = fragments.isEmpty() ? new Fragment(content) : fragments.get(0);
            Database.getFragmentsDao().create(fragment);
            FragmentFragmentsGroup fragmentFragmentsGroup =
                    new FragmentFragmentsGroup(fragmentsGroup.getId(), fragment.getId());
            Database.getFragmentFragmentsGroupDao().create(fragmentFragmentsGroup);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void removeFragmentFragmentsGroup(FragmentsGroup fragmentsGroup, Fragment fragment) {
        try {
            List<FragmentFragmentsGroup> fragmentFragmentsGroups =
                    Database.getFragmentFragmentsGroupDao().queryForEq("fragmentId", fragment.getId())
                    .stream().filter(f -> f.getFragmentsGroupId() == fragmentsGroup.getId()).toList();
            FragmentFragmentsGroup fragmentFragmentsGroup = fragmentFragmentsGroups.get(0);
            Database.getFragmentFragmentsGroupDao().delete(fragmentFragmentsGroup);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void insertFragmentsGroup(String caption) {
        try {
            List<FragmentsGroup> fragmentsGroups =
                    Database.getFragmentsGroupDao().queryForEq("caption", caption);
            if (!fragmentsGroups.isEmpty())
                return;
            FragmentsGroup fragmentsGroup = new FragmentsGroup(caption);
            Database.getFragmentsGroupDao().create(fragmentsGroup);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void removeFragmentsGroup(FragmentsGroup fragmentsGroup) {
        try {
            List<FragmentsGroup> fragmentsGroups =
                    Database.getFragmentsGroupDao().queryForMatching(fragmentsGroup);
            if (fragmentsGroups.isEmpty())
                return;
            List<FragmentFragmentsGroup> fragmentFragmentsGroups =
                    Database.getFragmentFragmentsGroupDao().queryForEq("fragmentsGroupId", fragmentsGroup.getId());
            for (FragmentFragmentsGroup fragmentFragmentsGroup : fragmentFragmentsGroups)
                Database.getFragmentFragmentsGroupDao().delete(fragmentFragmentsGroup);
            Database.getFragmentsGroupDao().delete(fragmentsGroup);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

}
