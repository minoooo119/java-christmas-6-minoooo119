package View;

import camp.nextstep.edu.missionutils.Console;
import christmas.MenuDetail;
import christmas.MenuGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class InputView {

    private final List<MenuDetail> menuDetailList=new ArrayList<>();
    private Integer date;
    public List<MenuDetail> getMenuDetailList(){
        return menuDetailList;
    }
    public Integer getDate(){
        return date;
    }
    public void readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        try {
            date=validateDate(validateConvertToInt(input));
        } catch (IllegalArgumentException e) {
            readDate();
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
    public void readMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String menu=Console.readLine();
        try {
            validateMenu(menu);
        } catch (IllegalArgumentException e) {
            readMenu();
        }
    }
    public void validateMenu(String menu){
        String[] menuList=menu.split(",");
        for(String menuDetail:menuList){
            validateMenuDetail(menuDetail);
        }
        validateMenuLength(menuDetailList);
    }
    private void validateMenuDetail(String menuDetail){
        String[] menuDetailListStr=menuDetail.split("-");
        try {
            validateMenuDetailLength(menuDetailListStr);
            validateMenuNum(menuDetailListStr[1]);
            validateMenuName(menuDetailListStr[0]);
            validateMenuOverlap(MenuDetail.findByName(menuDetailListStr[0]));
            MenuDetail menu=MenuDetail.findByName(menuDetailListStr[0]);
            menu.setNum(Integer.parseInt(menuDetailListStr[1]));
            menuDetailList.add(menu);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }
    private void validateMenuLength(List<MenuDetail> menuDetailList){
        int menuLength=0;
        for(MenuDetail menuDetail:menuDetailList){
            menuLength+=menuDetail.num;
        }
        if(menuLength>20){
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            throw new IllegalArgumentException();
        }
    }
    private void validateMenuOverlap(MenuDetail menuDetail){
        if(menuDetailList.contains(menuDetail)){
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            throw new IllegalArgumentException();
        }
    }
    private void validateMenuDetailLength(String[] menuDetailList){
        if(menuDetailList.length!=2){
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            throw new IllegalArgumentException();
        }
    }
    private void validateMenuName(String menuName){
        MenuDetail menuDetail=MenuDetail.findByName(menuName);
        if(menuDetail==MenuDetail.EMPTY){
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            throw new IllegalArgumentException();
        }
    }
    private void validateMenuNum(String menuNum){
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
    }
}
