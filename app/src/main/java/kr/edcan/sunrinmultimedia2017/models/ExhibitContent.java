package kr.edcan.sunrinmultimedia2017.models;

import java.util.ArrayList;

/**
 * Created by Junseok Oh on 2017-05-13.
 */

public class ExhibitContent {
    private String fileType, projectName, korAutor, engAutor, email, projectId;
    private ArrayList<String> fileName;

    public ExhibitContent(String fileType, String projectName, String korAutor, String engAutor, String email, String projectId, ArrayList<String> fileName) {
        this.fileType = fileType;
        this.projectName = projectName;
        this.korAutor = korAutor;
        this.engAutor = engAutor;
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

    public String getKorAutor() {
        return korAutor;
    }

    public void setKorAutor(String korAutor) {
        this.korAutor = korAutor;
    }

    public String getEngAutor() {
        return engAutor;
    }

    public void setEngAutor(String engAutor) {
        this.engAutor = engAutor;
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
        return "그래픽";
    }
}
