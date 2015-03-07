package com.nama_gatsuo.dreamplan.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nama_gatsuo.dreamplan.model.SubTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nagamatsuayumu on 15/02/07.
 */
public class SubTaskDao {
    public static final String TABLE_NAME = "subTask";

    // DBカラム名
    static final String COLUMN_subTaskID = "subTaskID";
    static final String COLUMN_taskID = "taskID";
    static final String COLUMN_projectID = "projectID";
    static final String COLUMN_subTaskName = "subTask_name";
    static final String COLUMN_subTaskDescription = "subTask_description";
    static final String COLUMN_subTaskStatus = "subTask_status";
    static final String COLUMN_subTaskStartDate = "subTask_startDate";
    static final String COLUMN_subTaskEndDate = "subTask_endDate";

    // カラム名の配列を静的メンバとして用意
    static final String[] COLUMNS = {
            COLUMN_subTaskID, COLUMN_taskID, COLUMN_projectID, COLUMN_subTaskName, COLUMN_subTaskDescription,
            COLUMN_subTaskStatus, COLUMN_subTaskStartDate, COLUMN_subTaskEndDate
    };

    // CREATE TABLE文を静的メンバとして用意
    public static final String CREATE_SQL = "CREATE TABLE " + TABLE_NAME + " ("
            + COLUMN_subTaskID + " INTEGER PRIMARY KEY, "
            + COLUMN_taskID + " INTEGER, "
            + COLUMN_projectID + " INTEGER, "
            + COLUMN_subTaskName + " TEXT, "
            + COLUMN_subTaskDescription + " TEXT, "
            + COLUMN_subTaskStatus + " INTEGER, "
            + COLUMN_subTaskStartDate + " INTEGER, "
            + COLUMN_subTaskEndDate + " INTEGER)";

    SQLiteDatabase db;

    // Constructor
    public SubTaskDao(SQLiteDatabase db) {
        this.db = db;
    }

    // 全てのSubTaskをリストで取得するメソッド
    public List<SubTask> findAll() {
        List<SubTask> list = new ArrayList<SubTask>();
        // 全件抽出のqueryを生成
        Cursor c = db.query(TABLE_NAME, COLUMNS, null, null, null, null, COLUMN_subTaskID);
        // 一行ずつfetch
        while (c.moveToNext()) {
            SubTask subTask = new SubTask();
            subTask.setSubTaskID(c.getColumnIndex(COLUMN_subTaskID));
            subTask.setTaskID(c.getInt(c.getColumnIndex(COLUMN_taskID)));
            subTask.setProjectID(c.getInt(c.getColumnIndex(COLUMN_projectID)));
            subTask.setName(c.getString(c.getColumnIndex(COLUMN_subTaskName)));
            subTask.setDescription(c.getString(c.getColumnIndexOrThrow(COLUMN_subTaskDescription)));
            subTask.setStatus(c.getInt(c.getColumnIndex(COLUMN_subTaskStatus)));
            subTask.setStartDate(c.getInt(c.getColumnIndex(COLUMN_subTaskStartDate)));
            subTask.setEndDate(c.getInt(c.getColumnIndex(COLUMN_subTaskEndDate)));
            list.add(subTask);
        }
        // Cursorのclose
        c.close();
        return list;
    }

    // 特定のProjectID配下のSubTaskを取得するメソッド
    public List<SubTask> findByProjectID(int projectID) {
        List<SubTask> list = new ArrayList<SubTask>();
        // Where句でProjectIDを指定してquery生成
        Cursor c = db.query(TABLE_NAME, COLUMNS, COLUMN_projectID + " = ?",
                new String[] { String.valueOf(projectID) }, null, null, COLUMN_subTaskID);
        // 一行ずつfetch
        while (c.moveToNext()) {
            SubTask subTask = new SubTask();
            subTask.setSubTaskID(c.getColumnIndex(COLUMN_subTaskID));
            subTask.setTaskID(c.getInt(c.getColumnIndex(COLUMN_taskID)));
            subTask.setProjectID(c.getInt(c.getColumnIndex(COLUMN_projectID)));
            subTask.setName(c.getString(c.getColumnIndex(COLUMN_subTaskName)));
            subTask.setDescription(c.getString(c.getColumnIndexOrThrow(COLUMN_subTaskDescription)));
            subTask.setStatus(c.getInt(c.getColumnIndex(COLUMN_subTaskStatus)));
            subTask.setStartDate(c.getInt(c.getColumnIndex(COLUMN_subTaskStartDate)));
            subTask.setEndDate(c.getInt(c.getColumnIndex(COLUMN_subTaskEndDate)));
            list.add(subTask);
        }
        // Cursorのclose
        c.close();
        return list;
    }

    // 特定のTaskID配下のSubTaskを取り出すメソッド
    public List<SubTask> findByTaskID(int TaskID) {
        List<SubTask> list = new ArrayList<SubTask>();
        // WHERE句でCOLUMN_taskIDを指定してqueryを生成
        Cursor c = db.query(TABLE_NAME, COLUMNS, COLUMN_taskID + " = ?",
                new String[] { String.valueOf(TaskID) }, null, null, COLUMN_subTaskID);
        SubTask subTask = null;

        // 一行ずつfetch
        while (c.moveToNext()) {
            subTask = new SubTask();
            subTask.setSubTaskID(c.getColumnIndex(COLUMN_subTaskID));
            subTask.setTaskID(c.getInt(c.getColumnIndex(COLUMN_taskID)));
            subTask.setProjectID(c.getInt(c.getColumnIndex(COLUMN_projectID)));
            subTask.setName(c.getString(c.getColumnIndex(COLUMN_subTaskName)));
            subTask.setDescription(c.getString(c.getColumnIndexOrThrow(COLUMN_subTaskDescription)));
            subTask.setStatus(c.getInt(c.getColumnIndex(COLUMN_subTaskStatus)));
            subTask.setStartDate(c.getInt(c.getColumnIndex(COLUMN_subTaskStartDate)));
            subTask.setEndDate(c.getInt(c.getColumnIndex(COLUMN_subTaskEndDate)));
            list.add(subTask);
        }
        // Cursorのclose
        c.close();
        return list;
    }

    // SubTaskIDを指定して取り出すメソッド
    public SubTask findBySubTaskID(int subTaskID) {
        // WHERE句でCOLUMN_taskIDを指定してqueryを生成
        Cursor c = db.query(TABLE_NAME, COLUMNS, COLUMN_taskID + " = ?",
                new String[]{String.valueOf(subTaskID)}, null, null, COLUMN_taskID);
        SubTask subTask = null;

        // 一行だけfetch
        if (c.moveToFirst()) {
            subTask = new SubTask();
            subTask.setSubTaskID(c.getColumnIndex(COLUMN_subTaskID));
            subTask.setTaskID(c.getInt(c.getColumnIndex(COLUMN_taskID)));
            subTask.setProjectID(c.getInt(c.getColumnIndex(COLUMN_projectID)));
            subTask.setName(c.getString(c.getColumnIndex(COLUMN_subTaskName)));
            subTask.setDescription(c.getString(c.getColumnIndexOrThrow(COLUMN_subTaskDescription)));
            subTask.setStatus(c.getInt(c.getColumnIndex(COLUMN_subTaskStatus)));
            subTask.setStartDate(c.getInt(c.getColumnIndex(COLUMN_subTaskStartDate)));
            subTask.setEndDate(c.getInt(c.getColumnIndex(COLUMN_subTaskEndDate)));
        }
        // Cursorのclose
        c.close();
        return subTask;
    }

    // SubTaskを保存するメソッド
    public long save(SubTask subTask) {
        if (!subTask.validate()) {
            return -1;
        }
        // 値設定
        ContentValues values = new ContentValues();
        values.put(COLUMN_subTaskName, subTask.getName());
        values.put(COLUMN_subTaskDescription, subTask.getDescription());
        values.put(COLUMN_subTaskStatus, subTask.getStatus());
        values.put(COLUMN_subTaskStartDate, subTask.getStartDate());
        values.put(COLUMN_subTaskEndDate, subTask.getEndDate());

        // 同じSubTaskIDが存在するなら更新
        if (exists(subTask.getSubTaskID())) {
            String where = COLUMN_subTaskID + " = ?";
            String[] arg = { String.valueOf(subTask.getSubTaskID()) };
            return db.update(TABLE_NAME, values, where, arg);
        } else {
            // データがまだ無いなら挿入
            values.put(COLUMN_subTaskID, subTask.getSubTaskID());
            return db.insert(TABLE_NAME, null, values);
        }
    }

    // ProjectIDを指定してSubTaskのデータを削除するメソッド
    public long deleteByProjectID(int projectID) {
        // 値設定
        String where = COLUMN_projectID + " = ?";
        String[] arg = { String.valueOf(projectID) };
        return db.delete(TABLE_NAME, where, arg);
    }

    // TaskIDを指定してSubTaskのデータを削除するメソッド
    public long deleteByTaskID(int taskID) {
        // 値設定
        String where = COLUMN_taskID + " = ?";
        String[] arg = { String.valueOf(taskID) };
        return db.delete(TABLE_NAME, where, arg);
    }

    // SubTaskIDを指定してSubTaskのデータを削除するメソッド
    public long deleteBySubTaskID(int subTaskID) {
        // 値設定
        String where = COLUMN_subTaskID + " = ?";
        String[] arg = { String.valueOf(subTaskID) };
        return db.delete(TABLE_NAME, where, arg);
    }

    // ID値の最大を返すメソッド
    public int getLastID() {
        ArrayList<SubTask> stlist = new ArrayList<SubTask>();
        stlist = (ArrayList<SubTask>)findAll();
        int lastTaskID = (stlist.get(stlist.size())).getTaskID();
        return lastTaskID;
    }

    // SubTaskIDの存在をチェックするメソッド
    public boolean exists(int subTaskID) {
        return findBySubTaskID(subTaskID) != null;
    }

    // データの存在をチェックするメソッド
    public boolean exists() {
        return findAll().size() > 0;
    }

}
