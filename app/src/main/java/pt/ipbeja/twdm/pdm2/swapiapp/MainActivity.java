package pt.ipbeja.twdm.pdm2.swapiapp;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.resources.TextAppearance;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String firstRequest = "https://swapi.dev/api/people/?page=1&format=json";
    private PersonListAdapter adapter;
    private Context context;
    private List<Person> persons;
    private String next;
    private TextView textView;
    private String pageNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        setContentView(R.layout.activity_main);
        this.textView = findViewById(R.id.textViewPageNumber);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewPersons);
        adapter = new PersonListAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        BackgroundTasks.run(new Runnable() {
            @Override
            public void run() {
                try {
                    APIResponse apiResponse = DataSource.getFromURL(firstRequest);
                    pageNumber = DataSource.getPageNumber(firstRequest);
                    persons = apiResponse.getResults();
                    next = apiResponse.getNext();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(pageNumber);
                        adapter.updateData(persons);
                    }
                });
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    BackgroundTasks.run(new Runnable() {
                        @Override
                        public void run() {
                            if(next == null) return;
                            pageNumber = DataSource.getPageNumber(next);
                            try {
                                APIResponse apiResponse = DataSource.getFromURL(next);
                                persons = apiResponse.getResults();
                                next = apiResponse.getNext();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(pageNumber);
                                    adapter.insertData(persons);
                                }
                            });
                        }
                    });
                }
            }
        });
    }

}