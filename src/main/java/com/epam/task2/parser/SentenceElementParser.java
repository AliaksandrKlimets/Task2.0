package com.epam.task2.parser;

import com.epam.task2.entity.CompositeEntity;
import com.epam.task2.entity.LeafEntity;
import com.epam.task2.entity.TextEntity;
import com.epam.task2.entity.TextEntityType;
import com.epam.task2.manager.ManagerBundle;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class parses sentences into words and punctuation marks
 */
public class SentenceElementParser extends BaseParser {
    private static final String REGEX_WORD = "regex.word";
    private static final String REGEX_PUNCT = "regex.punctuation";
    @Override
    public TextEntity parse(String content){
        CompositeEntity sentenceEntity = new CompositeEntity(TextEntityType.SENTENCE);
        List<String> sentenceElement = new ArrayList<>();
        Matcher matcher = Pattern.compile(ManagerBundle.getProperty(REGEX_WORD)).matcher(content);
        while (matcher.find()){
            sentenceElement.add(matcher.group());
        }
        for(String sentenceElem: sentenceElement){
            matcher = Pattern.compile(ManagerBundle.getProperty(REGEX_PUNCT)).matcher(sentenceElem);
            if(matcher.find())
            sentenceEntity.addChild(new LeafEntity(TextEntityType.PUNCTUATION_MARK,sentenceElem));
            else {
                String str=sentenceElem;
                if ((Pattern.compile("[\\s]+").matcher(sentenceElem).find())) {
                    str = " ";
                }
                sentenceEntity.addChild(new LeafEntity(TextEntityType.WORD,str));
            }
        }
        return sentenceEntity;
    }

}
