package com.pipe.www.pipe.model;

import com.pipe.www.pipe.R;

public class Post {

    private String imageurl;
    private String videourl;
    private String textmessage;
    private String capture;

    private String userid;
    private String userphotourl;
    private String username;

    private long date;
    private long time;

    private String type;

    private String postid;


    private String videothumbnailurl;

    private int likecount;
    private int commentcount;

    private String commentname;
    private String commentcontent;

    public String getCommentname() {
        return commentname;
    }

    public void setCommentname(String commentname) {
        this.commentname = commentname;
    }

    public String getCommentcontent() {
        return commentcontent;
    }

    public void setCommentcontent(String commentcontent) {
        this.commentcontent = commentcontent;
    }

    public int getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(int commentcount) {
        this.commentcount = commentcount;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getTextmessage() {
        return textmessage;
    }

    public void setTextmessage(String textmessage) {
        this.textmessage = textmessage;
    }

    public String getCapture() {
        return capture;
    }

    public void setCapture(String capture) {
        this.capture = capture;
    }


    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserphotourl() {
        return userphotourl;
    }

    public void setUserphotourl(String userphotourl) {
        this.userphotourl = userphotourl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    private long getTime() {
        return time;
    }

    private void setTime(long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getVideothumbnailurl() {
        return videothumbnailurl;
    }

    public void setVideothumbnailurl(String videothumbnailurl) {
        this.videothumbnailurl = videothumbnailurl;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }
}
