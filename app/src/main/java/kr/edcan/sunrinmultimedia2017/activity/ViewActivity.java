package kr.edcan.sunrinmultimedia2017.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import kr.edcan.sunrinmultimedia2017.R;
import kr.edcan.sunrinmultimedia2017.databinding.ActivityViewBinding;
import kr.edcan.sunrinmultimedia2017.fragment.ViewFragment;
import kr.edcan.sunrinmultimedia2017.models.ExhibitContentSingleTon;

public class ViewActivity extends AppCompatActivity {

    private ActivityViewBinding binding;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private String projectId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view);
        setDefault();
        setToolbar();
        projectId = getIntent().getStringExtra("projectId");
    }

    private void setDefault() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = binding.viewPager;
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setToolbar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("작품 보기");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.e("Asdf", ExhibitContentSingleTon.isCurrentProjectVideo+"");
        if (ExhibitContentSingleTon.isCurrentProjectVideo)
            getMenuInflater().inflate(R.menu.view_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        if (item.getItemId() == R.id.showVideo) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://sunrin.graphics/2017")));
        }
        return super.onOptionsItemSelected(item);
    }

    class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ViewFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "작품";
                case 1:
                    return "만든이";
            }
            return null;
        }
    }

}