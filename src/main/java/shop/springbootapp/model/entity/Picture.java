package shop.springbootapp.model.entity;

import jakarta.persistence.*;

import java.util.Base64;
import java.util.Date;

@Entity
public class Picture extends BaseEntity {
    @Basic
    private String filename;
    @Basic
    private String contentType;
    @Basic
    private Integer filesize;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] content;
    @Basic
    private Date createdDate;

    public Picture() {
    }
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Transient
    public String getEncodedContent() {
        return Base64.getEncoder().encodeToString(this.content);
    }

}
