package io.github.kolacbb.babytree.model;

import java.util.Date;

/**
 * Created by kolab on 2016/11/13.
 */

public class ResponsBody {
    private Date createdAt;
    private String objectId;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
