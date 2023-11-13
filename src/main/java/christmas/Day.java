package christmas;

import java.util.Arrays;
import java.util.List;

public enum Day {
    MONDAY("월요일", Arrays.asList(4, 11, 18, 25)),
    TUESDAY("화요일", Arrays.asList(5, 12, 19, 26)),
    WEDNESDAY("수요일", Arrays.asList(6, 13, 20, 27)),
    THURSDAY("목요일", Arrays.asList(7, 14, 21, 28)),
    FRIDAY("금요일", Arrays.asList(1, 8, 15, 22, 29)),
    SATURDAY("토요일", Arrays.asList(2, 9, 16, 23, 30)),
    SUNDAY("일요일", Arrays.asList(3, 10, 17, 24, 31));

    private final String day;
    private final List<Integer> dates;

    Day(String day, List<Integer> dates) {
        this.day = day;
        this.dates = dates;
    }

    public static Day findByDate(int date) {
        return Arrays.stream(Day.values())
                .filter(day -> day.hasDate(date))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 날짜입니다."));
    }
    private boolean hasDate(int date) {
        return dates.stream()
                .anyMatch(day -> day == date);
    }
}
