package com.ldc.newsmvvm.ui.news;

import com.google.gson.annotations.SerializedName;

public class NewsBean {

    /**
     * news_date : 2020-04-10 23:05:09
     * date : 56分钟前
     * title : 曼联希望与上海申花重新谈判 续租+买断伊哈洛
     * wap_title : 曼联希望与上海申花重新谈判 续租+买断伊哈洛
     * img : https://k.sinaimg.cn/n/sports/transform/357/w650h507/20200326/bcb1-irkazzv5037471.jpg/w160h120l1t12e4.jpg
     * type : normal
     * pics : {"total":1}
     * link : https://sports.sina.cn/premierleague/manutd/2020-04-10/detail-iircuyvh7080038.d.html?pos=10
     * comment_id : ty:comos-ircuyvh7080038:0:ircuyvh7080038
     * commentid : ty:comos-ircuyvh7080038:0:ircuyvh7080038
     * source : 新浪体育
     * intro : 曼联希望与上海申花重新谈判 续租+买断伊哈洛
     * mediaTypes : normal
     * isSubject : 0
     * isDujia : 0
     * newsType : no
     * docID : ircuyvh7080038
     * newsTag :
     * comment : 1
     */

    @SerializedName("news_date")
    private String newsDate;
    @SerializedName("date")
    private String date;
    @SerializedName("title")
    private String title;
    @SerializedName("wap_title")
    private String wapTitle;
    @SerializedName("img")
    private String img;
    @SerializedName("type")
    private String type;
    @SerializedName("pics")
    private PicsBean pics;
    @SerializedName("link")
    private String link;
    @SerializedName("comment_id")
    private String commentId;
    @SerializedName("commentid")
    private String commentid;
    @SerializedName("source")
    private String source;
    @SerializedName("intro")
    private String intro;
    @SerializedName("mediaTypes")
    private String mediaTypes;
    @SerializedName("isSubject")
    private int isSubject;
    @SerializedName("isDujia")
    private int isDujia;
    @SerializedName("newsType")
    private String newsType;
    @SerializedName("docID")
    private String docID;
    @SerializedName("newsTag")
    private String newsTag;
    @SerializedName("comment")
    private int comment;

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWapTitle() {
        return wapTitle;
    }

    public void setWapTitle(String wapTitle) {
        this.wapTitle = wapTitle;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PicsBean getPics() {
        return pics;
    }

    public void setPics(PicsBean pics) {
        this.pics = pics;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getMediaTypes() {
        return mediaTypes;
    }

    public void setMediaTypes(String mediaTypes) {
        this.mediaTypes = mediaTypes;
    }

    public int getIsSubject() {
        return isSubject;
    }

    public void setIsSubject(int isSubject) {
        this.isSubject = isSubject;
    }

    public int getIsDujia() {
        return isDujia;
    }

    public void setIsDujia(int isDujia) {
        this.isDujia = isDujia;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getNewsTag() {
        return newsTag;
    }

    public void setNewsTag(String newsTag) {
        this.newsTag = newsTag;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public static class PicsBean {
        /**
         * total : 1
         */

        @SerializedName("total")
        private int total;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
}
