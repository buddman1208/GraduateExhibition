package kr.edcan.sunrinmultimedia2017.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.android.volley.toolbox.ImageLoader;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;
import com.github.nitrico.lastadapter.LayoutHandler;
import com.github.nitrico.lastadapter.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import kr.edcan.sunrinmultimedia2017.R;
import kr.edcan.sunrinmultimedia2017.databinding.AuthorContentBinding;
import kr.edcan.sunrinmultimedia2017.databinding.ViewContentBinding;
import kr.edcan.sunrinmultimedia2017.databinding.ViewContentHeaderBinding;
import kr.edcan.sunrinmultimedia2017.databinding.ViewExhibitAuthorBinding;
import kr.edcan.sunrinmultimedia2017.databinding.ViewExhibitContentBinding;
import kr.edcan.sunrinmultimedia2017.models.Author;
import kr.edcan.sunrinmultimedia2017.models.ExhibitContentHeader;
import kr.edcan.sunrinmultimedia2017.utils.ImageSingleTon;

/**
 * Created by Junseok Oh on 2017-05-13.
 */
public class ViewFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "pageNumber";

    private RecyclerView authorRecyclerView;
    private RecyclerView contentRecyclerView;
    private ArrayList<Author> authorList = new ArrayList<>();
    private ArrayList<Object> contentList = new ArrayList<>();
    public ViewFragment() {
    }

    public static ViewFragment newInstance(int sectionNumber) {
        ViewFragment fragment = new ViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;
        switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
            case 0:
                ViewExhibitContentBinding contentBinding = DataBindingUtil.inflate(inflater, R.layout.view_exhibit_content, container, false);
                rootView = contentBinding.getRoot();
                initialzeContentTab(contentBinding);
                break;
            case 1:
                ViewExhibitAuthorBinding authorBinding = DataBindingUtil.inflate(inflater, R.layout.view_exhibit_author, container, false);
                rootView = authorBinding.getRoot();
                initializeAuthorTab(authorBinding);
                break;
        }
        return rootView;
    }

    private void initialzeContentTab(final ViewExhibitContentBinding contentBinding) {
        contentRecyclerView = contentBinding.contentRecyclerView;
        contentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get Image List from Server
        int imageLength = 3;
        contentList.add(new ExhibitContentHeader(
                "제목", "Lorem UPsum Lorem UPsum Lorem UPsum Lorem UPsum Lorem UPsum Lorem UPsum Lorem UPsum Lorem UPsum ",
                3
        ));
        for (int i = 0; i < imageLength; i++) {
            contentList.add("https://github.com/Luminon/sunrinmultimedia5th_webdesign/blob/master/Android/screenshots/previewArticle_profile.png?raw=true");
        }

        LastAdapter.with(contentList, BR.content)
                .map(ExhibitContentHeader.class, new ItemType<ViewContentHeaderBinding>(R.layout.view_content_header){
                    @Override
                    public void onBind(@NotNull ViewHolder<ViewContentHeaderBinding> viewHolder) {
                        super.onBind(viewHolder);
                    }
                })
                .map(String.class, new ItemType<ViewContentBinding>(R.layout.view_content){
                    @Override
                    public void onBind(@NotNull ViewHolder<ViewContentBinding> viewHolder) {
                        super.onBind(viewHolder);
                        viewHolder.getBinding().networkImageView.setImageUrl(
                                (String) contentList.get(viewHolder.getAdapterPosition()),
                                ImageSingleTon.getInstance(getContext()).getImageLoader()
                        );
                    }
                })
                .handler(new LayoutHandler() {
                    @Override
                    public int getItemLayout(@NotNull Object o, int i) {
                        if(o instanceof ExhibitContentHeader) return R.layout.view_content_header;
                        return R.layout.view_content;
                    }
                })
                .into(contentRecyclerView);
    }


    private void initializeAuthorTab(ViewExhibitAuthorBinding authorBinding) {
        authorRecyclerView = authorBinding.writerRecyclerView;
        authorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        authorList.add(new Author("오준석", "Junseok Oh", "kotohana5706@edcan.kr", "https://images.discordapp.net/.eJwNwlEKwyAMANC7eACjs1bt7w4yRIMW2kZM9jFG77493le956E21UUGbwB150KzahaauaFuRO3APHbWhU7IIrn0Ey9hcOZhTIo2mRSij2aJ4Kzzdv0PznobU1jh2almfH2QBiPpcTV1_wAD3CV6.0sGGeOb5SfaXH5FTwzSQSDSiGJQ"));
        authorList.add(new Author("오준석", "Junseok Oh", "kotohana5706@edcan.kr", "https://images.discordapp.net/.eJwNwlEKwyAMANC7eACjs1bt7w4yRIMW2kZM9jFG77493le956E21UUGbwB150KzahaauaFuRO3APHbWhU7IIrn0Ey9hcOZhTIo2mRSij2aJ4Kzzdv0PznobU1jh2almfH2QBiPpcTV1_wAD3CV6.0sGGeOb5SfaXH5FTwzSQSDSiGJQ"));
        authorList.add(new Author("오준석", "Junseok Oh", "kotohana5706@edcan.kr", "https://images.discordapp.net/.eJwNwlEKwyAMANC7eACjs1bt7w4yRIMW2kZM9jFG77493le956E21UUGbwB150KzahaauaFuRO3APHbWhU7IIrn0Ey9hcOZhTIo2mRSij2aJ4Kzzdv0PznobU1jh2almfH2QBiPpcTV1_wAD3CV6.0sGGeOb5SfaXH5FTwzSQSDSiGJQ"));
        authorList.add(new Author("오준석", "Junseok Oh", "kotohana5706@edcan.kr", "https://images.discordapp.net/.eJwNwlEKwyAMANC7eACjs1bt7w4yRIMW2kZM9jFG77493le956E21UUGbwB150KzahaauaFuRO3APHbWhU7IIrn0Ey9hcOZhTIo2mRSij2aJ4Kzzdv0PznobU1jh2almfH2QBiPpcTV1_wAD3CV6.0sGGeOb5SfaXH5FTwzSQSDSiGJQ"));

        LastAdapter.with(authorList, BR.content)
                .map(Author.class, new ItemType<AuthorContentBinding>(R.layout.author_content){
                    @Override
                    public void onBind(@NotNull ViewHolder<AuthorContentBinding> viewHolder) {
                        super.onBind(viewHolder);
                        viewHolder.getBinding().profileImage.setImageUrl(
                                authorList.get(viewHolder.getAdapterPosition()).getProfileImageUrl(),
                                ImageSingleTon.getInstance(getContext()).getImageLoader()
                        );

                    }
                })
                .handler(new LayoutHandler() {
                    @Override
                    public int getItemLayout(@NotNull Object o, int i) {
                        return R.layout.author_content;
                    }
                })
                .into(authorRecyclerView);
    }
}
