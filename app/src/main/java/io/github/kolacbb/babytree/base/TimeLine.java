package io.github.kolacbb.babytree.base;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhangd on 2017/5/23.
 */

public class TimeLine implements Serializable {
    private Date sendTime;
    private String content;
    private String imgs;

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }
}
