import aacom.animals.Cat;
import aacom.animals.Dino;
import aacom.animals.Dog;
import aacom.weekday.WeekDay;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        //Cat cat = context.getBean(Cat.class);
        //Dino dino = context.getBean("T-rex", Dino.class);
        //Dog dog = context.getBean(Dog.class);

        WeekDay weekDay = context.getBean(WeekDay.class);

        System.out.println("It`s " + weekDay.getWeekDayName() + " today!");
    }
}
