package kr.edcan.sunrinmultimedia2017.models;

/**
 * Created by Junseok Oh on 2017-05-13.
 */

public class ExhibitContentHeader {
    private String title, content;
    private int type;
    private String[] typeStringArr = {"일러스트", "편집 / 패키지 디자인", "모션그래픽", "비디오", "웹 / 앱 디자인", "게임그래픽"};
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
