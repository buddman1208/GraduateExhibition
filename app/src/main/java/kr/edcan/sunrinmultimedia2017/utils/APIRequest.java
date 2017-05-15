package kr.edcan.sunrinmultimedia2017.utils;

import java.util.ArrayList;

import kr.edcan.sunrinmultimedia2017.models.Author;
import kr.edcan.sunrinmultimedia2017.models.ExhibitContent;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by fluor on 2017-05-16.
 */

public interface APIRequest {
    @GET("/project")
    Call<ArrayList<ExhibitContent>> getAllProjects();

    @GET("/project/type/{fileType}")
    Call<ArrayList<ExhibitContent>> getProjectsByFileType(@Path("fileType") int fileType);


    @GET("/project/id/{projectId}")
    Call<ExhibitContent> getProjectsByProjectId(@Path("projectId") String projectId);

    @GET("/image/{projectId}")
    Call<ArrayList<String>> getImageList(@Path("projectId") String projectId);

    @GET("/profile/{projectId}")
    Call<ArrayList<Author>> getAuthorList(@Path("projectId") String projectId);


}
