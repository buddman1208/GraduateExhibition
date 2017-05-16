package kr.edcan.sunrinmultimedia2017.utils;

import java.util.ArrayList;

import kr.edcan.sunrinmultimedia2017.models.Author;
import kr.edcan.sunrinmultimedia2017.models.ExhibitContent;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by fluor on 2017-05-16.
 */

public interface APIRequest {
    @GET("/project")
    Call<ArrayList<ExhibitContent>> getAllProjects();

    @GET("/project/type")
    Call<ArrayList<ExhibitContent>> getProjectsByFileType(@Query("fileType") int fileType);

    @GET("/project/id")
    Call<ExhibitContent> getProjectsByProjectId(@Query("projectId") String projectId);

    @GET("/image")
    Call<ArrayList<String>> getImageList(@Query("projectId") String projectId);

    @GET("/profile")
    Call<ArrayList<Author>> getAuthorList(@Query("projectId") String projectId);


}
