package yinhang.sql;

import java.util.ArrayList;
import java.util.List;

import yinhang.entity.BaseEntity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "yinhang_";
    private static final int VERSION = 3;
    public static final String DB_ALL_NAME = DB_NAME + VERSION + ".db";

    public SqlHelper(Context context) {
        super(context, DB_ALL_NAME, null, VERSION);
    }

    public List<BaseEntity> getAll(String[] key, DBEnum dbEnum, boolean isSearchA) {
        SQLiteDatabase openSqlite = getWritableDatabase();
        StringBuilder builder = new StringBuilder();
        builder.append("select * from ");
        builder.append(dbEnum.getDbName());
        Cursor query = null;
        if (key == null) {
            query = openSqlite.rawQuery(builder.toString(), null);
        } else {
            builder.append(" where ");
            if (isSearchA) {
                for (int i = 0; i < key.length; i++) {
                    String tmp = key[i];
                    if (i != 0) {
                        builder.append(" and ");
                    }

                    builder.append(" ( ");
                    builder.append(" content like '%");
                    builder.append(tmp);
                    builder.append("%' ");
                    builder.append(" or ");

                    builder.append(" answer like '%");
                    builder.append(tmp);
                    builder.append("%' ");
                    builder.append(" or ");

                    builder.append(" optionA like '%");
                    builder.append(tmp);
                    builder.append("%' ");
                    builder.append(" or ");

                    builder.append(" optionB like '%");
                    builder.append(tmp);
                    builder.append("%' ");
                    builder.append(" or ");

                    builder.append(" optionC like '%");
                    builder.append(tmp);
                    builder.append("%' ");
                    builder.append(" or ");

                    builder.append(" optionD like '%");
                    builder.append(tmp);
                    builder.append("%' ");
                    builder.append(" ) ");
                }

            } else {
                for (int i = 0; i < key.length; i++) {
                    String tmp = key[i];
                    if (i != 0) {
                        builder.append(" and ");
                    }
                    builder.append(" content like '%");
                    builder.append(tmp);
                    builder.append("%' ");

                }
            }
            query = openSqlite.rawQuery(builder.toString(), null);
            // +"select * from shiti where shiti.content like '%" + key
            // +"%'
            // + "%' or shiti.optionA  like '%" + key
            // + "%' or shiti.optionB like '%" + key
            // + "%'  or shiti.optionC like '%" + key
            // + "%' or shiti.optionD like '%" + key + "%'", null);
        }
        List<BaseEntity> reList = new ArrayList<BaseEntity>();
        while (query.moveToNext()) {
            BaseEntity baseEntity = new BaseEntity();
            baseEntity.type = query.getString(1);
            baseEntity.level = query.getString(2);
            baseEntity.content = query.getString(3);
            baseEntity.answer = query.getString(4);
            baseEntity.optionA = query.getString(5);
            baseEntity.optionB = query.getString(6);
            baseEntity.optionC = query.getString(7);
            baseEntity.optionD = query.getString(8);
            baseEntity.form = query.getString(9);
            baseEntity.forum = query.getString(10);
            baseEntity.konwlege = query.getString(11);
            baseEntity.refernce = query.getString(12);
            baseEntity.author = query.getString(13);
            baseEntity.time = query.getString(14);
            reList.add(baseEntity);
        }
        query.close();
        openSqlite.close();
        return reList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
