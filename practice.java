// public class practice {
//     public static int fun(int a) {
//         if(a!=10){
//             return 1;
//         }else{
//             return 0;
//         }

//     }
//     public static void main(String[] args) {
//         // System.out.println("hello");
//         int a = 10;
//         int x = fun(a);
//         System.out.println(x);
//     }
// }

import java.time.DayOfWeek;
import java.time.LocalDate;

public class practice {
    public static LocalDate findThanksgiving(int year) {
        LocalDate thanksgiving = LocalDate.of(year, 11, 1);
        int daysToAdd = (3 - thanksgiving.getDayOfWeek().getValue() + 7) % 7;
        thanksgiving = thanksgiving.plusDays(daysToAdd);
        return thanksgiving;
    }

    public static void main(String[] args) {
        int year = 2023;
        LocalDate thanksgiving = findThanksgiving(year);
        System.out.printf("Thanksgiving in the United States in %d is on %s.%n",
                year, thanksgiving.toString());
    }
}
