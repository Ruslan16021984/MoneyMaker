package carbit3333333.gmail.com.moneytracker;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tablayout);

       MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), this);
       pager.setAdapter(adapter);
       tabLayout.setupWithViewPager(pager);


    }
}
