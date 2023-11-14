package christmas;

import java.util.Arrays;
import java.util.List;

public enum Week {
    WEEKEND("주말", List.of(Day.FRIDAY,Day.SATURDAY, Day.SUNDAY)),
    WEEKDAY("평일", List.of(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY));

    private final String day;
    private final List<Day> days;

    Week(String day, List<Day> days) {
        this.day = day;
        this.days = days;
    }

    public static Week findByDay(Day day){
        return Arrays.stream(Week.values())
                .filter(week -> week.hasDay(day))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 날짜입니다."));
    }
    private boolean hasDay(Day day){
        return days.stream()
                .anyMatch(week -> week==day);
    }

}
