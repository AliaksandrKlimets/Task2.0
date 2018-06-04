package com.epam.task2.entity;

/**
 * Leaf is a class that can't has children :(
 */
public class LeafEntity extends TextEntity {
    private TextEntityType type;


    public LeafEntity(TextEntityType type, String content) {
        this.type = type;
        this.content = content;
    }

    public LeafEntity() {
    }

    @Override
    public TextEntityType getType() {
        return this.type;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public int size() {
        return  this.content.length();
    }

    @Override
    public void setType(TextEntityType type){
        this.type=type;
    }

    @Override
    public String getContent(){
        return content;
    }
}
