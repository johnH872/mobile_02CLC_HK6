package hcmute.edu.readsqlite_week8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ViewCourses extends AppCompatActivity {

    private ArrayList<CourseModel> courseModelArrayList;
    private DBHandler dbHandler;
    private CourseRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courses);

        courseModelArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewCourses.this);

        courseModelArrayList = dbHandler.readCourses();
        courseRVAdapter = new CourseRVAdapter(courseModelArrayList, ViewCourses.this);
        coursesRV = findViewById(R.id.idRVCourses);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewCourses.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);
        coursesRV.setAdapter(courseRVAdapter);
    }
}