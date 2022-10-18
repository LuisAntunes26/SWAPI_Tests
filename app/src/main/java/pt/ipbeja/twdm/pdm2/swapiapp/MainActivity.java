package pt.ipbeja.twdm.pdm2.swapiapp;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String firstRequest = "https://swapi.dev/api/people/?page=1&format=json";
    private PersonListAdapter adapter;
    private String next;
    private String previous;
    private Context context;
    private TextView textViewPageNumber;
    private Button buttonPrevious;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        setContentView(R.layout.activity_main);

        this.textViewPageNumber = findViewById(R.id.textViewPageNumber);
        this.buttonPrevious = findViewById(R.id.buttonPrevious);
        this.buttonNext = findViewById(R.id.buttonNext);


        RecyclerView recyclerView = findViewById(R.id.recyclerViewPersons);
        adapter = new PersonListAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        BackgroundTasks.run(new Runnable() {
            @Override
            public void run() {
                APIResponse apiResponse = null;
                try {
                    String pageNumber = DataSource.getPageNumber(firstRequest);
                    apiResponse = DataSource.getFromURL(firstRequest);
                    List<Person> persons = apiResponse.getResults();
                    next = apiResponse.getNext();
                    previous = apiResponse.getPrevious();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            buttonNext.setEnabled(true);
                            buttonPrevious.setEnabled(true);
                            if(previous == null) buttonPrevious.setEnabled(false);
                            textViewPageNumber.setText(String.format("Page: %s", pageNumber));
                            adapter.updateData(persons);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onNextClick(View view) {
        BackgroundTasks.run(new Runnable() {
            @Override
            public void run() {
                APIResponse apiResponse = null;
                try {
                    if (next == null){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context,
                                        "There is no next",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                        return;
                    }
                    String pageNumber = DataSource.getPageNumber(next);

                    apiResponse = DataSource.getFromURL(next);
                    List<Person> persons = apiResponse.getResults();
                    next = apiResponse.getNext();
                    previous = apiResponse.getPrevious();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            buttonNext.setEnabled(true);
                            buttonPrevious.setEnabled(true);
                            if(next == null) buttonNext.setEnabled(false);
                            textViewPageNumber.setText(String.format("Page: %s", pageNumber));
                            adapter.updateData(persons);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onPreviousClick(View view) {
        BackgroundTasks.run(new Runnable() {
            @Override
            public void run() {
                APIResponse apiResponse = null;
                try {
                    if (previous == null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context,
                                        "There is no previous",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                        return;
                    }
                    String pageNumber = DataSource.getPageNumber(previous);

                    apiResponse = DataSource.getFromURL(previous);
                    List<Person> persons = apiResponse.getResults();
                    next = apiResponse.getNext();
                    previous = apiResponse.getPrevious();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            buttonNext.setEnabled(true);
                            buttonPrevious.setEnabled(true);
                            if(previous == null) buttonPrevious.setEnabled(false);
                            textViewPageNumber.setText(String.format("Page: %s", pageNumber));
                            adapter.updateData(persons);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}