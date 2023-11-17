package Project3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.text.*;
import java.util.*;

public class MySingleWithTailLinkedListTest {
    MySingleWithTailLinkedList list = new MySingleWithTailLinkedList();

    @Test
    public void testAdd() {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        GregorianCalendar g4 = new GregorianCalendar();
        GregorianCalendar g5 = new GregorianCalendar();
        GregorianCalendar g6 = new GregorianCalendar();
        GregorianCalendar g7 = new GregorianCalendar();
        GregorianCalendar g8 = new GregorianCalendar();

        try {
            Date d1 = df.parse("1/20/2020");
            g1.setTime(d1);
            Date d2 = df.parse("12/22/2020");
            g2.setTime(d2);
            Date d3 = df.parse("12/20/2019");
            g3.setTime(d3);
            Date d4 = df.parse("7/02/2020");
            g4.setTime(d4);
            Date d5 = df.parse("1/20/2010");
            g5.setTime(d5);
            Date d6 = df.parse("9/29/2020");
            g6.setTime(d6);
            Date d7 = df.parse("7/25/2020");
            g7.setTime(d7);
            Date d8 = df.parse("7/29/2020");
            g8.setTime(d8);

            Game game1 = new Game("Person1", g3, g2,
                    null, "title1",null);
            Game game2 = new Game("Person1", g3, g1,
                    null, "title2",null);
            Game game3 = new Game("Person1", g5, g3,
                    null, "title2", null);
            Game game4 = new Game("Person7", g4, g8,
                    null, "title2", null);
            Game game5 = new Game("Person3", g3, g1,
                    null, "title2", null);
            Game game6 = new Game("Person6", g4, g7,
                    null, "title1", null);
            Game game7 = new Game("Person5", g4, g8,
                    null, "title1", null);

            Console console1 = new Console("Person1", g4, g6,
                    null, ConsoleTypes.PlayStation4);
            Console console2 = new Console("Person2", g5, g3,
                    null, ConsoleTypes.PlayStation4);
            Console console3 = new Console("Person5", g4, g8,
                    null, ConsoleTypes.SegaGenesisMini);
            Console console4 = new Console("Person6", g4, g7,
                    null, ConsoleTypes.SegaGenesisMini);
            Console console5 = new Console("Person1", g5, g4,
                    null, ConsoleTypes.XBoxOneS);
            Console console6 = new Console("Person3", g4, g3,
                    null, ConsoleTypes.NintendoSwich);


            list.add(game1);
            list.add(game2);
            list.add(game4);
            list.add(game5);
            list.add(game3);
            list.add(game6);
            list.add(game7);
            list.add(console1);
            list.add(console2);
            list.add(console3);
            list.add(console5);
            list.add(console4);
            list.add(console6);

        } catch (ParseException e) {
            throw new RuntimeException("Error in testing, creation of list");
        }
        ArrayList<String> test = new ArrayList<>();
        test.add("Person1");
        test.add("Person1");
        test.add("Person3");
        test.add("Person6");
        test.add("Person5");
        test.add("Person7");
        test.add("Person1");
        test.add("Person2");
        test.add("Person3");
        test.add("Person1");
        test.add("Person6");
        test.add("Person5");
        test.add("Person1");
        for(int i = 0; i < list.size() - 1; i++){
            Assert.assertEquals(test.get(i), list.get(i).nameOfRenter);
        }
    }
}
