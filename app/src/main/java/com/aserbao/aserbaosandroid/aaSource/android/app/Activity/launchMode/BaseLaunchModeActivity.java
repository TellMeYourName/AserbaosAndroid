package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.launchMode;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Intent.FLAG_ACTIVITY_MULTIPLE_TASK;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/2/13 11:39 AM
 * @email: this is empty email
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaSource.android.app.Activity.launchMode
 */
public abstract class BaseLaunchModeActivity extends AppCompatActivity {
    private static final String mCuurActivityName = "BaseLaunchModeActivity";
    public String mActivityName = "";

    @BindView(R.id.launch_mode_tv)
    public TextView mLaunchModeTv;
    @BindView(R.id.jump_activity_tv)
    TextView mJumpActivityTv;
    @BindView(R.id.back_activity_tv)
    TextView mBackActivityTv;
    private ActivityManager mActivityManager;

    public static List<String> sJumpList = new ArrayList<>();
    public static List<String> sBackList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode);
        ButterKnife.bind(this);
        setTextViewContent();
        mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTasks = mActivityManager.getRunningTasks(10);

        for (ActivityManager.RunningTaskInfo runningTask : runningTasks) {
            String shortClassName = runningTask.topActivity.getShortClassName();
            String baseShortClassName = runningTask.baseActivity.getShortClassName();
//            Log.e(mActivityName, "onCreate: " + shortClassName + "  baseShortClassName =  " + baseShortClassName + " num = " + runningTask.numActivities + " numRuning = " + runningTask.numRunning);
        }
        Log.e(mActivityName, "onCreate: " );
    }



    @Override
    protected void onResume() {
        super.onResume();
        Log.e(mActivityName, "onResume: " );
        StringBuffer sJump = new StringBuffer();
        for (String s : sJumpList) {
            sJump.append(s).append(" --> \n");
        }
        mJumpActivityTv.setText(sJump);
        StringBuffer sBack = new StringBuffer();
        for (String s : sBackList) {
            sBack.append("Back ").append(s).append(" --> \n");
        }
        mBackActivityTv.setText(sBack);
    }

    public abstract void setTextViewContent();



    @Override
    protected void onStart() {
        super.onStart();
        Log.e(mActivityName, "onStart() called");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(mActivityName, "onNewIntent() called with: intent = [" + intent + "]");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(mActivityName, "onPause() called" + " \n ================================");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(mActivityName, "onStop() called" + " \n ================================");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(mActivityName, "onDestroy() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(mActivityName, "onRestart: " );
    }

    @Override
    public void finish() {
        super.finish();
        Log.e(mActivityName, "finish() called" + " \n ================================");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(mActivityName, "onRestoreInstanceState: ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(mActivityName, "onSaveInstanceState: ");
    }

    @OnClick({R.id.clear_all_activity_btn, R.id.standard_btn, R.id.singleInstance_btn, R.id.singletop_btn, R.id.singletask_btn})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.clear_all_activity_btn:
                List<ActivityManager.AppTask> appTasks = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    appTasks = mActivityManager.getAppTasks();
                }
                for (ActivityManager.AppTask appTask : appTasks) {
//                    appTask.finishAndRemoveTask();
                    ActivityManager.RecentTaskInfo taskInfo = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        taskInfo = appTask.getTaskInfo();
                    }
                    CharSequence description = taskInfo.description;
                    String topActivityClassName = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        topActivityClassName = taskInfo.topActivity.getClassName();
                    }
                    int persistentId = taskInfo.persistentId;
                    Log.e(mActivityName, "description = " + description + "onViewClicked: " + " topActivityClassName = " + topActivityClassName + " persistentId = " + String.valueOf(persistentId));
                }
                /*List<Activity> activitiesByApplication = getActivitiesByApplication(AserbaoApplication.instance);
                for (Activity activity : activitiesByApplication) {
                    Log.e(mActivityName, "onViewClicked: " + activity.getLocalClassName()  + " TaskId =  " + activity.getTaskId() );
                }*/
               /* sJumpList.add("LMA");
                intent = new Intent(getApplicationContext(), LaunchModeActivity.class);
                startActivity(intent);*/
                break;
            case R.id.standard_btn:
                sJumpList.add("A");
                intent = new Intent(getApplicationContext(), StandardActivity.class);
                intent.setFlags(FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(intent);
                break;
            case R.id.singleInstance_btn:
                sJumpList.add("B");
                intent = new Intent(getApplicationContext(), SingleInstanceActivity.class);
                startActivity(intent);
                break;
            case R.id.singletop_btn:
                sJumpList.add("C");
                intent = new Intent(getApplicationContext(), SingleTopActivity.class);
                startActivity(intent);
                break;
            case R.id.singletask_btn:
                sJumpList.add("D");
                intent = new Intent(getApplicationContext(), SingleTaskActivity.class);
                startActivity(intent);
                break;
        }
    }


    private static List<Activity> getActivitiesByApplication(Application application) {
        List<Activity> list = new ArrayList<>();
        try {
            Class<Application> applicationClass = Application.class;
            Field mLoadedApkField = applicationClass.getDeclaredField("mLoadedApk");
            mLoadedApkField.setAccessible(true);
            Object mLoadedApk = mLoadedApkField.get(application);
            Class<?> mLoadedApkClass = mLoadedApk.getClass();
            Field mActivityThreadField = mLoadedApkClass.getDeclaredField("mActivityThread");
            mActivityThreadField.setAccessible(true);
            Object mActivityThread = mActivityThreadField.get(mLoadedApk);
            Class<?> mActivityThreadClass = mActivityThread.getClass();
            Field mActivitiesField = mActivityThreadClass.getDeclaredField("mActivities");
            mActivitiesField.setAccessible(true);
            Object mActivities = mActivitiesField.get(mActivityThread);
            // 注意这里一定写成Map，低版本这里用的是HashMap，高版本用的是ArrayMap
            if (mActivities instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<Object, Object> arrayMap = (Map<Object, Object>) mActivities;
                for (Map.Entry<Object, Object> entry : arrayMap.entrySet()) {
                    Object value = entry.getValue();
                    Class<?> activityClientRecordClass = value.getClass();
                    Field activityField = activityClientRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Object o = activityField.get(value);
                    list.add((Activity) o);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

}
