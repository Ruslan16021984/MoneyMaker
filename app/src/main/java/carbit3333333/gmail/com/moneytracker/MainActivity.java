package carbit3333333.gmail.com.moneytracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager pager;
    private TabLayout tabLayout;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tablayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.i(TAG, "onClick: FAB");
                int currentPage = pager.getCurrentItem();
                String type = null;
                if (currentPage== MainPagerAdapter.PAGE_INCOMES){
                    type = Item.TYPE_INCOMES;
                }else if (currentPage== MainPagerAdapter.PAGE_EXPENSES){
                        type = Item.TYPE_EXPENSES;
                }
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                intent.putExtra(AddItemActivity.TYPE_KEY, type);
                startActivityForResult(intent, ItemsFragment.ADD_ITEM_REQEST_CODE);
            }
        });


        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), this);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(this);
        tabLayout.setupWithViewPager(pager);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case MainPagerAdapter.PAGE_INCOMES:
            case MainPagerAdapter.PAGE_EXPENSES:
                fab.show();
                break;
            case MainPagerAdapter.PAGE_BALANCE:
                fab.hide();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_IDLE:
                fab.setEnabled(true);
                break;
            case ViewPager.SCROLL_STATE_SETTLING:
                fab.setEnabled(false);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment: getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode,resultCode, data);
        }
    }
}
