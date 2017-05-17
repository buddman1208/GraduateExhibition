package kr.edcan.sunrinmultimedia2017.models;

/**
 * Created by Junseok Oh on 2017-05-15.
 */

public class Author {
    private String korAuthor, engAuthor, email,  profileImageUrl;

    public Author(String korAuthor, String engAuthor, String email, String profileImageUrl) {
        this.korAuthor = korAuthor;
        this.engAuthor = engAuthor;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
    }

    public String getKorAuthor() {
        return korAuthor;
    }

    public String getEngAuthor() {
        return engAuthor;
    }

    public String getEmail() {
        return email;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
