package com.epam.task2.runner;

import java.util.ResourceBundle;

import com.epam.task2.entity.CompositeEntity;
import com.epam.task2.manager.TextTask;
import com.epam.task2.parser.ParserLogic;
import org.apache.log4j.Logger;

public class Runner {
    public static final Logger logger = Logger.getLogger("com.epam.task2.runner");
    public static void main(String[] args) {
        logger.setResourceBundle(ResourceBundle.getBundle("log4j"));
        String content = "\n" +
                "1.                              The if-then and if-then-else Statements\n" +
                "1.1.\tThe if-then Statement\n" +
                "The if-then statement is the most basic of all the control flow statements. It tells your program to execute a certain section of code only if a particular test evaluates to true. " +
                "For example, the Bicycle class could allow the brakes to decrease the bicycle's speed only if the bicycle is already in motion. One possible implementation of the applyBrakes method could be as follows:\n" +
                "\n" +
                "void applyBrakes() {\n" +
                "    // the \"if\" clause: bicycle must be moving\n" +
                "    if (isMoving){\n" +
                "        // the \"then\" clause: decrease current speed\n" +
                "        currentSpeed--;\n" +
                "    }\n" +
                "}\n" +
                "If this test evaluates to false (meaning that the bicycle is not in motion), control jumps to the end of the if-then statement.\n" +
                "In addition, the opening and closing braces are optional, provided that the \"then\" clause contains only one statement:\n" +
                "void applyBrakes() {\n" +
                "    // same as above, but without braces\n" +
                "    if (isMoving)\n" +
                "        currentSpeed--;\n" +
                "}\n" +
                "Deciding when to omit the braces is a matter of personal taste. Omitting them can make the code more brittle." +
                " If a second statement is later added to the \"then\" clause, a common mistake would be forgetting to add the newly required braces. " +
                "The compiler cannot catch this sort of error; you'll just get the wrong results.\n";
        CompositeEntity text =  (CompositeEntity) ParserLogic.parse(content);
        logger.info(text.getContent());
        TextTask task = new TextTask();
        CompositeEntity result = task.deleteWordByLength(content,3);
        logger.info(result.getContent());
        String content2 = "qqqsahaqqqqrsashawwwwww\n" +
                "qwiyetqwoieytwqeyiqwtesashaewirouweropew\n" +
                "498374394sasha23847328\n";
        logger.info(content2);
        CompositeEntity restext = task.deleteSubString(content2,"sa","ha");
        logger.info(restext.getContent());
    }

}
