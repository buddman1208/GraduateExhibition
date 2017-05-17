package kr.edcan.sunrinmultimedia2017.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.android.volley.toolbox.ImageLoader;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;
import com.github.nitrico.lastadapter.LayoutHandler;
import com.github.nitrico.lastadapter.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import kr.edcan.sunrinmultimedia2017.R;
import kr.edcan.sunrinmultimedia2017.activity.MainActivity;
import kr.edcan.sunrinmultimedia2017.databinding.AuthorContentBinding;
import kr.edcan.sunrinmultimedia2017.databinding.ViewContentBinding;
import kr.edcan.sunrinmultimedia2017.databinding.ViewContentHeaderBinding;
import kr.edcan.sunrinmultimedia2017.databinding.ViewExhibitAuthorBinding;
import kr.edcan.sunrinmultimedia2017.databinding.ViewExhibitContentBinding;
import kr.edcan.sunrinmultimedia2017.models.Author;
import kr.edcan.sunrinmultimedia2017.models.ExhibitContent;
import kr.edcan.sunrinmultimedia2017.models.ExhibitContentHeader;
import kr.edcan.sunrinmultimedia2017.models.ExhibitContentSingleTon;
import kr.edcan.sunrinmultimedia2017.utils.ImageSingleTon;
import kr.edcan.sunrinmultimedia2017.utils.NetworkHelper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Junseok Oh on 2017-05-13.
 */
public class ViewFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "pageNumber";
    private RecyclerView authorRecyclerView;
    private RecyclerView contentRecyclerView;
    private ArrayList<Author> authorList = new ArrayList<>();
    private ArrayList<Object> contentList = new ArrayList<>();
    private ExhibitContent currentContent = null;
    private String currentContentInfo;

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
        getExhibitContent();

    }


    private void initializeAuthorTab(ViewExhibitAuthorBinding authorBinding) {
        authorRecyclerView = authorBinding.writerRecyclerView;
        authorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        NetworkHelper.getNetworkInstance().getAuthorList(ExhibitContentSingleTon.currentSelectedProjectId).enqueue(new Callback<ArrayList<Author>>() {
            @Override
            public void onResponse(Call<ArrayList<Author>> call, Response<ArrayList<Author>> response) {
                switch (response.code()) {
                    case 200:
                        for (Author a : response.body()) {
                            authorList.add(a);
                        }
                        LastAdapter.with(authorList, BR.content)
                                .map(Author.class, new ItemType<AuthorContentBinding>(R.layout.author_content) {
                                    @Override
                                    public void onBind(@NotNull ViewHolder<AuthorContentBinding> viewHolder) {
                                        super.onBind(viewHolder);
                                        viewHolder.getBinding().profileImage.setImageUrl(
                                                "http://13.124.125.184:3000/profile/" + authorList.get(viewHolder.getAdapterPosition()).getKorAuthor() + ".png",
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

                        break;
                    default:
                        Log.e("asdf", response.code() + "");
                        Toast.makeText(getContext(), "데이터를 가져오는 데 문제가 발생했습니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Author>> call, Throwable t) {
                Toast.makeText(getContext(), "데이터를 가져오는 데 문제가 발생했습니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                Log.e("asdf", t.getLocalizedMessage());
            }
        });
    }

    public void getExhibitContent() {
        NetworkHelper.getNetworkInstance().getDescription(ExhibitContentSingleTon.currentSelectedProjectId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                switch (response.code()) {
                    case 200:
                        try {
                            String result = response.body().string();
                            Log.e("getExhibitContent", result);
                            getExhibitInfo(result);
                        } catch (IOException e) {
                            Log.e("asdf", e.getLocalizedMessage());
                        }
                        break;
                    default:
                        Toast.makeText(getContext(), "데이터를 가져오는 데 문제가 발생했습니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("asdf", "getExhibitContent : " + t.getMessage());

            }
        });

    }

    public void getExhibitInfo(final String content) {
        NetworkHelper.getNetworkInstance().getProjectsByProjectId(ExhibitContentSingleTon.currentSelectedProjectId).enqueue(new Callback<ArrayList<ExhibitContent>>() {
            @Override
            public void onResponse(Call<ArrayList<ExhibitContent>> call, Response<ArrayList<ExhibitContent>> response) {
                switch (response.code()) {
                    case 200:
                        currentContent = response.body().get(0);
                        contentList.add(new ExhibitContentHeader(
                                currentContent.getProjectName(),
//                                currentContent.get,
                                content, Integer.parseInt(currentContent.getFileType())
                        ));
                        NetworkHelper.getNetworkInstance().getImageList(ExhibitContentSingleTon.currentSelectedProjectId).enqueue(new Callback<ArrayList<String>>() {
                            @Override
                            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                                switch (response.code()) {
                                    case 200:
                                        for (String s : response.body()) {
                                            contentList.add(s);
                                        }
                                        initializeContentView();
                                        break;
                                    default:
                                        Log.e("asdf", response.code() + "");
                                        Toast.makeText(getContext(), "데이터를 가져오는 데 문제가 발생했습니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }

                            private void initializeContentView() {
                                LastAdapter.with(contentList, BR.content)
                                        .map(ExhibitContentHeader.class, new ItemType<ViewContentHeaderBinding>(R.layout.view_content_header) {
                                            @Override
                                            public void onBind(@NotNull ViewHolder<ViewContentHeaderBinding> viewHolder) {
                                                super.onBind(viewHolder);
                                            }
                                        })
                                        .map(String.class, new ItemType<ViewContentBinding>(R.layout.view_content) {
                                            @Override
                                            public void onBind(@NotNull ViewHolder<ViewContentBinding> viewHolder) {
                                                super.onBind(viewHolder);
                                                viewHolder.getBinding().networkImageView.setImageUrl(
                                                        "http://13.124.125.184:3000/image/" + (String) contentList.get(viewHolder.getAdapterPosition()),
                                                        ImageSingleTon.getInstance(getContext()).getImageLoader()
                                                );
                                            }
                                        })
                                        .handler(new LayoutHandler() {
                                            @Override
                                            public int getItemLayout(@NotNull Object o, int i) {
                                                if (o instanceof ExhibitContentHeader)
                                                    return R.layout.view_content_header;
                                                return R.layout.view_content;
                                            }
                                        })
                                        .into(contentRecyclerView);
                            }

                            @Override
                            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                                Toast.makeText(getContext(), "데이터를 가져오는 데 문제가 발생했습니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                                Log.e("Asdf_string", t.getLocalizedMessage());
                            }
                        });
                        break;
                    default:
                        Toast.makeText(getContext(), "데이터를 가져오는 데 문제가 발생했습니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                        Log.e("asdf", response.code() + "");
                        break;

                }
            }

            @Override
            public void onFailure(Call<ArrayList<ExhibitContent>> call, Throwable t) {
                Toast.makeText(getContext(), "데이터를 가져오는 데 문제가 발생했습니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                Log.e("asdf_exhibitcontent", t.getLocalizedMessage());
            }
        });
    }
}
