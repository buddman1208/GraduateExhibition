package kr.edcan.sunrinmultimedia2017.models;

/**
 * Created by Junseok Oh on 2017-05-13.
 */

public class ExhibitContentHeader {
    private String title, content;
    private int type;
    private String[] typeStringArr = {"일러스트", "그래픽", "패키지 / 에디토리얼 디자인", "영상", "3D / 게임"};
    private String[] colorStringArr = {"#B388FF", "#43A047", "#8E24AA", "#FBC02D", "#E91E63", "#00BCD4"};

    public ExhibitContentHeader(String title, String content, int type) {
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return typeStringArr[type];
    }

    public String getColor() {
        return colorStringArr[type];
    }
}
