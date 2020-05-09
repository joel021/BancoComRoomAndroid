package com.persistencia.room.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import com.persistencia.room.R;
import com.persistencia.room.model.Headset;
import com.persistencia.room.service.InsertAndGetHeadsetTask;
import com.persistencia.room.ui.adapter.HeadsetAdapter;

import java.util.List;

public class HeadsetActivity extends AppCompatActivity implements InsertAndGetHeadsetTask.ResultHeadsetTaskInterface {
    // dados do estado da activity
    private Headset headset;
    private ProgressBar progress_main;
    private Button buttonSaveHeadset;
    private EditText editTextTitle, editTextModel, editTextPrice;
    private ListView listViewHeadset;
    private List<Headset> headsets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dados iniciais
        headset             = new Headset();
        progress_main       = findViewById(R.id.progress_main);
        buttonSaveHeadset   = findViewById(R.id.buttonSaveHeadset);
        editTextTitle       = findViewById(R.id.editTextTitle);
        editTextModel       = findViewById(R.id.editTextModel);
        editTextPrice       = findViewById(R.id.editTextPrice);
        listViewHeadset     = findViewById(R.id.listViewHeadset);
        consumeData(null);

        buttonSaveHeadset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHeadset();
                consumeData(headset);
            }
        });

        listViewHeadset.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setHeadset(headsets.get(position));
            }
        });
    }

    private void consumeData(Headset headset){
        stateLoading();
        InsertAndGetHeadsetTask insertAndGetHeadsetTask = new InsertAndGetHeadsetTask(HeadsetActivity.this, HeadsetActivity.this, headset);
        insertAndGetHeadsetTask.execute();
    }

    private void setHeadset(Headset headset){
        this.headset = headset;
        editTextTitle.setText(headset.title);
        editTextPrice.setText(headset.price+"");
        editTextModel.setText(headset.model);
    }

    private void getHeadset(){
        headset.model = editTextModel.getText().toString();
        headset.title = editTextTitle.getText().toString();

        if(!editTextPrice.getText().toString().isEmpty())
            headset.price = Float.parseFloat(editTextPrice.getText().toString());
    }
    private void stateLoading(){
        progress_main.setVisibility(ProgressBar.VISIBLE);
        buttonSaveHeadset.setEnabled(false);
    }

    private void stateCompleted(){
        progress_main.setVisibility(ProgressBar.GONE);
        buttonSaveHeadset.setEnabled(true);
    }

    @Override
    public void onResultTaskHeadSet(List<Headset> result) {
        headsets = result;
        headset = new Headset();

        HeadsetAdapter headsetAdapter = new HeadsetAdapter(this, result);
        listViewHeadset.setAdapter(headsetAdapter);
        stateCompleted();
    }
}
