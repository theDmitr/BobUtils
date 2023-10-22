package dmitr.bobutils.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import dmitr.bobutils.model.Fragment;
import dmitr.bobutils.model.FragmentFragmentsGroup;
import dmitr.bobutils.model.FragmentsGroup;
import dmitr.bobutils.util.Utils;

import java.sql.SQLException;

public class Database {

    private static final String DATA_BASE_URL = getDatabaseUrl();
    ;
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static final ConnectionSource connectionSource;

    private static Dao<FragmentsGroup, String> fragmentsGroupDao;
    private static Dao<Fragment, String> fragmentsDao;
    private static Dao<FragmentFragmentsGroup, String> fragmentFragmentsGroupDao;

    static {
        try {
            connectionSource = new JdbcConnectionSource(DATA_BASE_URL, USER, PASSWORD);
            setupDatabase();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private static String getDatabaseUrl() {
        return "jdbc:h2:file:" + Utils.getHomeFolder() + "/database";
    }

    private static void setupDatabase() throws SQLException {
        fragmentsGroupDao = DaoManager.createDao(connectionSource, FragmentsGroup.class);
        fragmentsDao = DaoManager.createDao(connectionSource, Fragment.class);
        fragmentFragmentsGroupDao = DaoManager.createDao(connectionSource, FragmentFragmentsGroup.class);
        TableUtils.createTableIfNotExists(connectionSource, FragmentsGroup.class);
        TableUtils.createTableIfNotExists(connectionSource, Fragment.class);
        TableUtils.createTableIfNotExists(connectionSource, FragmentFragmentsGroup.class);

        // foo();
    }

    private static void foo() throws SQLException {
        fragmentsDao.create(new Fragment("HELLO"));
        fragmentsDao.create(new Fragment("MONEY"));
        fragmentsDao.create(new Fragment("MARKETING"));
        fragmentsDao.create(new Fragment("YOUR PHONE"));
        fragmentsGroupDao.create(new FragmentsGroup("LikeMania"));
        fragmentFragmentsGroupDao.create(new FragmentFragmentsGroup(1, 1));
        fragmentFragmentsGroupDao.create(new FragmentFragmentsGroup(1, 2));
        fragmentFragmentsGroupDao.create(new FragmentFragmentsGroup(1, 3));
        fragmentFragmentsGroupDao.create(new FragmentFragmentsGroup(1, 4));
    }

    public static Dao<FragmentsGroup, String> getFragmentsGroupDao() {
        return fragmentsGroupDao;
    }

    public static Dao<Fragment, String> getFragmentsDao() {
        return fragmentsDao;
    }

    public static Dao<FragmentFragmentsGroup, String> getFragmentFragmentsGroupDao() {
        return fragmentFragmentsGroupDao;
    }

    public static void close() throws Exception {
        connectionSource.close();
    }

}
