package View;

import camp.nextstep.edu.missionutils.Console;
import christmas.MenuDetail;
import christmas.MenuGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class InputView {

    private final List<MenuDetail> menuDetailList=new ArrayList<>();
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
            System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
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
//    고객이 메뉴판에 없는 메뉴를 입력하는 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 주세요.
//    메뉴의 개수는 1 이상의 숫자만 입력되도록 해주세요. 이외의 입력값은 "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 주세요.
//    메뉴 형식이 예시와 다른 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 주세요.
//    중복 메뉴를 입력한 경우(e.g. 시저샐러드-1,시저샐러드-1), "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 주세요.
//    모든 에러 메시지는 "[ERROR]"로 시작하도록 작성해 주세요.
    public List<MenuDetail> readMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String menu=Console.readLine();
        try {
            return validateMenu(menu);
        } catch (IllegalArgumentException e) {
            return readMenu();
        }
    }
    private List<MenuDetail> validateMenu(String menu){
        String[] menuList=menu.split(",");
        for(String menuDetail:menuList){
            try {
                validateMenuDetail(menuDetail);
            }catch (IllegalArgumentException e){
                return readMenu();
            }
        }
        return menuDetailList;
    }
    private void validateMenuDetail(String menuDetail){
        String[] menuDetailListStr=menuDetail.split("-");
        if(!validateMenuDetailLength(menuDetailListStr)){
            throw new IllegalArgumentException();
        }
        if(!validateMenuName(menuDetailListStr[0])){
            throw new IllegalArgumentException();
        }
        if(!validateMenuNum(menuDetailListStr[1])){
            throw new IllegalArgumentException();
        }
        if(!validateMenuOverlap(MenuDetail.findByName(menuDetailListStr[0]))){
            throw new IllegalArgumentException();
        }
        MenuDetail menu=MenuDetail.findByName(menuDetailListStr[0]);
        menu.setNum(Integer.parseInt(menuDetailListStr[1]));
        menuDetailList.add(menu);
    }
    private boolean validateMenuOverlap(MenuDetail menuDetail){
        if(menuDetailList.contains(menuDetail)){
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            return false;
        }
        return true;
    }
    private boolean validateMenuDetailLength(String[] menuDetailList){
        if(menuDetailList.length!=2){
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            return false;
        }
        return true;
    }
    private boolean validateMenuName(String menuName){
        MenuDetail menuDetail=MenuDetail.findByName(menuName);
        if(menuDetail==MenuDetail.EMPTY){
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            return false;
        }
        return true;
    }
    private boolean validateMenuNum(String menuNum){
        try{
            int num=Integer.parseInt(menuNum);
            if(num<1){
                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                throw new IllegalArgumentException();
            }
        }catch (NumberFormatException e){
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            throw new IllegalArgumentException();
        }
        return true;
    }
}
