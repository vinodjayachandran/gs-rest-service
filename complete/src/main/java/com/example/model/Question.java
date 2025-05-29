package com.example.model;

import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

public class Question {

    /*
     * 
     * * Title (Text)
* Content (Text)
* Creator (User)
     */

    private String title;
    private String content;
    private UserDTO creator;
    private String id;
    private int voteCount = 0;
    private HashSet<UserDTO> upVoters = new HashSet<>();
    
    // Constructor
    public Question(String title, String content, UserDTO creator) {
        this.title = title;
        this.content = content;
        this.creator = creator;
        this.id = UUID.randomUUID().toString(); // Assuming a method to generate a unique ID
    }

    public void upVote(UserDTO user) {
        // 
        this.voteCount++;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Question [title=" + title + ", content=" + content + ", creator=" + creator + ", id=" + id +  " voteCount=" + voteCount + "]";
    }

    
}
