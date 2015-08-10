package com.gobliip.chronos.client.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lsamayoa on 9/08/15.
 */
public class Attachment {
    private Long id;
    public enum AttachmentStatus {
        MANTAINANCE,
        AVAILABLE
    }

    private AttachmentStatus status;
    private String url;

    @SerializedName("public")
    private boolean isPublic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AttachmentStatus getStatus() {
        return status;
    }

    public void setStatus(AttachmentStatus status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
}
