package View;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        try {
            return validateDate(validateConvertToInt(input));
        } catch (IllegalArgumentException e) {
            return readDate();
        }
    }
    private Integer validateConvertToInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자만 기입해주세요");
            throw new IllegalArgumentException();
        }
    }
    private Integer validateDate(Integer date) {
        if (date < 1 || date > 31) {
            System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            throw new IllegalArgumentException();
        }
        return date;
    }
    public String readMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return Console.readLine();
    }
}
