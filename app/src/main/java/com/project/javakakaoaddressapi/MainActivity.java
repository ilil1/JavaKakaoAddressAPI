package com.project.javakakaoaddressapi;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_address = (EditText) findViewById(R.id.et_address);
        Button btn_search = (Button) findViewById(R.id.button);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                getSearchResult.launch(intent);
            }
        });
    }

    private final ActivityResultLauncher<Intent> getSearchResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                //callback을 받는다
                if(result.getResultCode() == RESULT_OK) {
                    if(result.getData() != null) {
                        String data = result.getData().getStringExtra("data");
                        et_address.setText(data);
                    }
                }
            }
    );
}