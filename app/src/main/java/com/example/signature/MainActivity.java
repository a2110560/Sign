package com.example.signature;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private SignView signView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signView=findViewById(R.id.signview);
    }

    public void clear(View view) {
        signView.clear();
    }

    public void undo(View view) {
        signView.undo();
    }

    public void redo(View view) {
        signView.redo();
    }
}
