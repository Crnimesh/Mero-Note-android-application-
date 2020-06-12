package com.org.takeanotes.model;

public class Note {
    private String title;
    private String content;//must be match in the firestore database field

    public Note(){}
    public Note(String title, String content){
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

