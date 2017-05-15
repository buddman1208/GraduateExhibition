package kr.edcan.sunrinmultimedia2017.models;

/**
 * Created by Junseok Oh on 2017-05-15.
 */

public class Author {
    private String korName, engName, email,  profileImageUrl;

    public Author(String korName, String engName, String email, String profileImageUrl) {
        this.korName = korName;
        this.engName = engName;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
    }

    public String getKorName() {
        return korName;
    }

    public String getEngName() {
        return engName;
    }

    public String getEmail() {
        return email;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
