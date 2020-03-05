package com.crossbridge.kernel.dba.bean;


import java.util.Date;

public class News {

    private Integer id;
    private String title;
    private String contant;
    private Date createdate;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContant() {
        return contant;
    }
    public void setContant(String contant) {
        this.contant = contant;
    }
    
    /**
     * @return the createdate
     */
    public Date getCreatedate() {
        return createdate;
    }
    /**
     * @param createdate the createdate to set
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    @Override
    public String toString() {
        return "News [id=" + id + ", title=" + title + ", contant=" + contant + ", createdate=" + createdate + "]";
    }
}