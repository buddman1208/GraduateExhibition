package kr.edcan.sunrinmultimedia2017.models;

import java.util.ArrayList;

import kr.edcan.sunrinmultimedia2017.utils.NetworkHelper;

/**
 * Created by Junseok Oh on 2017-05-13.
 */

public class ExhibitContent {
    private String fileType, projectName, korAuthor, engAuthor, email, projectId;
    private ArrayList<String> fileName;
    private String[] colorStringArr = {"#B388FF", "#43A047", "#8E24AA", "#FBC02D", "#E91E63", "#00BCD4"};

    public ExhibitContent() {
    }

    public ExhibitContent(String fileType, String projectName, String korAuthor, String engAuthor, String email, String projectId, ArrayList<String> fileName) {
        this.fileType = fileType;
        this.projectName = projectName;
        this.korAuthor = korAuthor;
        this.engAuthor = engAuthor;
        this.email = email;
        this.projectId = projectId;
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getKorAuthor() {
        return korAuthor;
    }

    public void setKorAuthor(String korAuthor) {
        this.korAuthor = korAuthor;
    }

    public String getEngAuthor() {
        return engAuthor;
    }

    public void setEngAuthor(String engAuthor) {
        this.engAuthor = engAuthor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public ArrayList<String> getFileName() {
        return fileName;
    }

    public void setFileName(ArrayList<String> fileName) {
        this.fileName = fileName;
    }

    public String getRealType() {
        return new String[]{"일러스트", "그래픽", "패키지 / 에디토리얼 디자인", "영상", "3D / 게임"}[NetworkHelper.getRealRealType(Integer.parseInt(fileType))];
    }
    public String getColor(){
        return colorStringArr[NetworkHelper.getRealRealType(Integer.parseInt(fileType))];
    }
}
