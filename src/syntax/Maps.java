

import java.io.BufferedWriter;
import java.io.Console;
import java.io.Writer;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.DoubleFunction;
import java.util.function.IntFunction;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Maps {
    static enum Caffe{ESSPRESSO("very strong"), MOCHA, LATTE;
        Caffe(){}
        public String strength ="light";
        Caffe(String strength){
            this.strength = strength;
        }
    };
    public static void main(String[] args){

     //   System.out.println('j'-'a');
        LocalDateTime ldt = LocalDateTime.of(2017,12,02,6,0,0);
        ZonedDateTime ny = ldt.atZone(ZoneId.of("GMT-5"));
        ZonedDateTime la = ldt.atZone(ZoneId.of("GMT-8"));
        Duration d = Duration.between(ny,la);
        DoubleStream.of(1,2,3,4.5,6).filter(x->x>0).average().getAsDouble();
        Console console = System.console();
        if(console!=null) {
            System.console().writer().write("what?");
            // System.console().printf()
            System.console().flush();
            char[] pass = System.console().readPassword();
            for (char c : pass) {
                System.console().writer().write(c);
            }
            System.console().flush();
        }

        DoubleFunction df = x->x+10;
        DoubleStream.of(1,2,3,4.5,6).mapToObj(df).forEach((x)->System.out.println(x));
       //ToIntegerFunction

        Stream.of(Caffe.values()).map(x->x.strength).forEach(System.out::println);

        Caffe c = Caffe.ESSPRESSO;

       // String::substring

    }
}
