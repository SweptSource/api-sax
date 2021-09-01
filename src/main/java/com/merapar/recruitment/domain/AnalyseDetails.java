package com.merapar.recruitment.domain;

import java.time.ZonedDateTime;

public class AnalyseDetails {
    private ZonedDateTime firstPost;
    private ZonedDateTime lastPost;
    private Integer totalPosts = 0;
    private Integer totalAcceptedPosts = 0;
    private Integer scoreSum = 0;

    public ZonedDateTime getFirstPost() {
        return firstPost;
    }

    public void setFirstPost(ZonedDateTime firstPost) {
        this.firstPost = firstPost;
    }

    public ZonedDateTime getLastPost() {
        return lastPost;
    }

    public void setLastPost(ZonedDateTime lastPost) {
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

    public Integer getScore() {
        return scoreSum;
    }

    public void setScore(Integer score) {
        this.scoreSum = score;
    }

    public void incrementTotalPosts() {
        this.totalPosts++;
    }
    public void incrementTotalAcceptedPosts() {
        this.totalAcceptedPosts++;
    }

    public void increaseScoreSum(Integer score) {
        this.scoreSum += score ;
    }



}
