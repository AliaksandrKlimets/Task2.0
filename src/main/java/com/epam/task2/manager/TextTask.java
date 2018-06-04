package com.epam.task2.manager;

import com.epam.task2.entity.CompositeEntity;
import com.epam.task2.entity.TextEntity;
import com.epam.task2.entity.TextEntityType;
import com.epam.task2.parser.ParserLogic;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextTask {

    /**
     * This method is used to delete words which have this length and start by consonant letters
     * @param content
     * @param length
     * @return
     */
    public CompositeEntity deleteWordByLength(String content, int length) {
        CompositeEntity text = (CompositeEntity) ParserLogic.parse(content);
        Matcher matcher;
        for (TextEntity paragraph : text.getChilds()) {
            if (paragraph.getType() == TextEntityType.PARAGRAPH) {
                CompositeEntity parag = (CompositeEntity) paragraph;
                for (TextEntity setnences : parag.getChilds()) {
                    CompositeEntity sentence = (CompositeEntity) setnences;
                    ArrayList<TextEntity> bufferLeafList = new ArrayList<>();
                    for (TextEntity word : sentence.getChilds()) {
                        matcher = Pattern.compile("^[qwrtpsdfghklzxcvbnmQWRTPSDFGHJKLZXCVBNM]").matcher(word.getContent());
                        if (matcher.find() && word.getContent().length() == length) {
                            bufferLeafList.add(word);
                        }
                    }
                    for (TextEntity leaf : bufferLeafList) {
                        sentence.getChilds().remove(leaf);
                    }

                }
            }
        }
        return text;
    }

    /**
     * This method is used to delete max substring whit starts by beginning String and finishing by end string
     * @param content
     * @param beginnning
     * @param end
     * @return
     */
    public CompositeEntity deleteSubString( String content, String beginnning, String end){
        CompositeEntity text = (CompositeEntity) ParserLogic.parse(content);
        for (TextEntity paragraph : text.getChilds()) {
            if(paragraph.getType()!=TextEntityType.CODE_LIST) {
                CompositeEntity paragraphEntity = (CompositeEntity) paragraph;
                for (TextEntity sentence : paragraphEntity.getChilds()) {
                    sentence.setContent(findAndDeleteMaxSubstring(sentence.getContent(),beginnning,end));
                }
            }
        }
        return text;
    }

    /**
     * This method makes main work
     * @param content
     * @param begin
     * @param end
     * @return
     */

    public String findAndDeleteMaxSubstring(String content, String begin, String end){
        String regex = "("+begin+").*("+end+")";
        Matcher matcher = Pattern.compile(regex).matcher(content);
        String substring=null;
        if(matcher.find())
        substring = matcher.group(0);

        if(substring!=null) {
            String[] res = content.split(substring);
            substring = "";
            for (String s : res) {
                substring += s;
            }
        }else substring = content;
        return substring;
    }

}

