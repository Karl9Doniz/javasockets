import aacom.animals.Cat;
import aacom.animals.Dino;
import aacom.animals.Dog;
import aacom.figures.*;
import aacom.weekday.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.DayOfWeek;
import java.time.LocalDate;

@Configuration

public class Config {

    //так бин будет иметь название кдасса Cat
    @Bean
    public Cat getCat() {
        return new Cat();
    }

    //либо можно так, то есть указать в аннотации
    @Bean("trex")
    public Dino getDino() {
        return new Dino();
    }

    @Bean
    public Dog getDog(Cat cat) {
        Dog dog = new Dog();
        dog.setName(cat.getName() + " enemy");
        return dog;
    }

    @Bean
    public WeekDay getDay() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        switch (dayOfWeek) {
            case MONDAY: return new Monday();
            case TUESDAY: return new Tuesday();
            case WEDNESDAY: return new Wednesday();
            case THURSDAY: return new Thursday();
            case FRIDAY: return new Friday();
            case SATURDAY: return new Saturday();
            case SUNDAY: return new Sunday();
            default: return new Sunday();
        }
    }

    @Bean
    public FigureName getFigure() {
        Main getAngles = new Main();
        int angles = getAngles.angle;
        switch (angles) {
            case 0: return new Circle();
            case 3: return new Triangle();
            case 4: return new Rectangle();
            case 5: return new Pentagon();
            case 6: return new Hexagon();
            default:
                return new Circle();
        }
    }
}
