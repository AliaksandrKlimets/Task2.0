package com.epam.task2.entity;

import com.epam.task2.entity.intarface.CompositeExtend;

import java.util.ArrayList;
import java.util.List;

public class CompositeEntity extends TextEntity implements CompositeExtend {
    private List<TextEntity> childList;
    private TextEntityType type;

    public CompositeEntity(TextEntityType type) {
        childList = new ArrayList<>();
       // this.content=content;
        this.type = type;
    }

    public CompositeEntity() {
    }


    @Override
    public TextEntityType getType() {
        return this.type;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public int size() {
        return childList.size();
    }

    @Override
    public void setType(TextEntityType type) {
        this.type = type;
    }

    @Override
    public void addChild(TextEntity textComponent) {
        childList.add(textComponent);
    }

    @Override
    public void removeChild(TextEntity textComponent) {
        childList.remove(textComponent);
    }

    @Override
    public List<TextEntity> getChilds() {
        return childList;
    }

    @Override
    public String getContent(){
        if(content!=null) return content;
        String content="";
        for (TextEntity textEntity : childList) {
            if(textEntity.isLeaf()) {
                LeafEntity leaf= (LeafEntity)textEntity;
                content += leaf.getContent();
            }else{
                CompositeEntity entity = (CompositeEntity)textEntity;
                content+=entity.getContent();
            }
        } this.content=content;
    return content;
    }
}
