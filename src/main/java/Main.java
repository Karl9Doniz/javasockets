import aacom.animals.Cat;
import aacom.animals.Dino;
import aacom.animals.Dog;
import aacom.figures.FigureName;
import aacom.weekday.WeekDay;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {

    public static int angle = 6;
    //private int angle = 5;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        //Cat cat = context.getBean(Cat.class);
        //Dino dino = context.getBean("T-rex", Dino.class);
        //Dog dog = context.getBean(Dog.class);

        WeekDay weekDay = context.getBean(WeekDay.class);
        FigureName figureName = context.getBean(FigureName.class);

        System.out.println("It`s " + weekDay.getWeekDayName() + " today!");
        System.out.println("It`s a " + figureName.getFigureName());
    }
}
