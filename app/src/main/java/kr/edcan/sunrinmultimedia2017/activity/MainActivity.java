package kr.edcan.sunrinmultimedia2017.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.android.databinding.library.baseAdapters.BR;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;
import com.github.nitrico.lastadapter.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

import kr.edcan.sunrinmultimedia2017.R;
import kr.edcan.sunrinmultimedia2017.databinding.ActivityMainBinding;
import kr.edcan.sunrinmultimedia2017.databinding.ExhibitContentBinding;
import kr.edcan.sunrinmultimedia2017.models.ExhibitContent;
import kr.edcan.sunrinmultimedia2017.utils.ImageSingleTon;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    ArrayList<ExhibitContent> dataList = new ArrayList<>();
    ArrayList<ImageView> tabList = new ArrayList<>();
    ArrayList<View> tabIndicatorList = new ArrayList<>();
    ArrayList<Integer> onResList = new ArrayList<>();
    ArrayList<Integer> offResList = new ArrayList<>();

    private RecyclerView mainRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initializeTab();
        setDefault();

    }


    private void initializeTab() {
        Collections.addAll(tabList,
                binding.tabAll, binding.tabGraphics, binding.tabIllust, binding.tabPackage, binding.tabVideo, binding.tab3D);
        Collections.addAll(tabIndicatorList,
                binding.dividerAll, binding.dividerGraphics, binding.dividerIllust, binding.dividerPackage, binding.dividerVideo, binding.divider3D);
        Collections.addAll(onResList,
                R.drawable.btn_tabs_all, R.drawable.btn_tabs_graphics, R.drawable.btn_tabs_illust, R.drawable.btn_tabs_package, R.drawable.btn_tabs_video, R.drawable.btn_tabs_threedimension);
        Collections.addAll(offResList,
                R.drawable.btn_tabs_all_off, R.drawable.btn_tabs_graphics_off, R.drawable.btn_tabs_illust_off, R.drawable.btn_tabs_package_off, R.drawable.btn_tabs_video_off, R.drawable.btn_tabs_threedimension_off);

        binding.container1.setOnClickListener(this);
        binding.container2.setOnClickListener(this);
        binding.container3.setOnClickListener(this);
        binding.container4.setOnClickListener(this);
        binding.container5.setOnClickListener(this);
        binding.container6.setOnClickListener(this);
        setTab(0);
    }

    private void setDefault() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("모든 작품 보기");
        mainRecyclerView = binding.mainRecyclerView;
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataList.add(new ExhibitContent());
        dataList.add(new ExhibitContent());
        LastAdapter.with(dataList, BR.content)
                .map(ExhibitContent.class, new ItemType<ExhibitContentBinding>(R.layout.exhibit_content){
                    @Override
                    public void onBind(@NotNull ViewHolder<ExhibitContentBinding> viewHolder) {
                        super.onBind(viewHolder);
//                        viewHolder.getBinding().image.setImageUrl("", ImageSingleTon.getInstance(getApplicationContext()).getImageLoader());
                    }
                })
                .into(mainRecyclerView);
    }

    private void setTab(int position) {
        for (int i = 0; i < tabList.size(); i++) {
            tabList.get(i).setImageResource((i == position) ? onResList.get(i) : offResList.get(i));
            tabIndicatorList.get(i).setVisibility((i == position) ? View.VISIBLE : View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.container1:
                setTab(0);
                break;
            case R.id.container2:
                setTab(1);
                break;
            case R.id.container3:
                setTab(2);
                break;
            case R.id.container4:
                setTab(3);
                break;
            case R.id.container5:
                setTab(4);
                break;
            case R.id.container6:
                setTab(5);
                break;
        }
    }
}
