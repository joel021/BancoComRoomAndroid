package com.persistencia.room.service;

import android.content.Context;
import android.os.AsyncTask;

import com.persistencia.room.model.Headset;

import java.util.List;

// <DoinBackground, onProgressUpdate, onPosExecute (retorno do doinbackground)
public class InsertAndGetHeadsetTask extends AsyncTask <List<Headset>, Void, List<Headset>> {
    private Context context;
    private Headset headset;
    private ResultHeadsetTaskInterface resultHeadsetTaskInterface;

    public InsertAndGetHeadsetTask(Context context, ResultHeadsetTaskInterface resultHeadsetTaskInterface, Headset headset){
        this.context = context;
        this.resultHeadsetTaskInterface = resultHeadsetTaskInterface;
        this.headset = headset;
    }

    @Override
    protected List<Headset> doInBackground(List<Headset>... objects) {

        HeadsetService headsetService = new HeadsetService(context);
        headsetService.save(headset);

        return headsetService.getAll();
    }

    @Override
    protected void onPostExecute(List<Headset> result) {
        //super.onPostExecute(result);
        resultHeadsetTaskInterface.onResultTaskHeadSet(result);
    }

    public interface ResultHeadsetTaskInterface {
        public void onResultTaskHeadSet(List<Headset> result);
    }
}
