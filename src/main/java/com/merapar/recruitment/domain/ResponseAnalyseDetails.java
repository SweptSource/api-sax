package com.merapar.recruitment.domain;


import com.merapar.recruitment.utils.CustomDateTimeFormatter;

public class ResponseAnalyseDetails {

    private String firstPost;
    private String lastPost;
    private Integer totalPosts = 0;
    private Integer totalAcceptedPosts = 0;
    private Integer avgScore = 0;


    public ResponseAnalyseDetails(AnalyseDetails analyseDetails) {
        this.firstPost = CustomDateTimeFormatter.parseDetailedDate(analyseDetails.getFirstPost());
        this.lastPost = CustomDateTimeFormatter.parseDetailedDate(analyseDetails.getFirstPost());
        this.totalPosts = analyseDetails.getTotalPosts();
        this.totalAcceptedPosts = analyseDetails.getTotalAcceptedPosts();
        this.avgScore = (int) Math.round(((double) analyseDetails.getScore()/ (double) analyseDetails.getTotalPosts()));
    }

    public String getFirstPost() {
        return firstPost;
    }

    public void setFirstPost(String firstPost) {
        this.firstPost = firstPost;
    }

    public String getLastPost() {
        return lastPost;
    }

    public void setLastPost(String lastPost) {
        this.lastPost = lastPost;
    }

    public Integer getTotalPosts() {
        return totalPosts;
    }

    public void setTotalPosts(Integer totalPosts) {
        this.totalPosts = totalPosts;
    }

    public Integer getTotalAcceptedPosts() {
        return totalAcceptedPosts;
    }

    public void setTotalAcceptedPosts(Integer totalAcceptedPosts) {
        this.totalAcceptedPosts = totalAcceptedPosts;
    }

    public Integer getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Integer avgScore) {
        this.avgScore = avgScore;
    }
}
