package com.Futebol_TV.AO.VIVO.NodoGO.Package_of_Activities;


import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


public class Bg_Worker_Class extends Worker {

    Context appContext;
    static String LOG_TAG = "irondev_worker_backg";

    public Bg_Worker_Class(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        appContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        return startWorker();
    }

    private Result startWorker() {
        Intent i = new Intent(appContext, Worker_Bg_Activity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appContext.startActivity(i);
        return Result.success();
    }

    interface MyCallBack {
        void done();
    }
}
