package christmas;

import java.util.Arrays;

public enum MenuDetail {
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000),
    MUSHROOM_SOUP("양송이수프", 6000),
    T_BONE_STEAK("티본스테이크", 55000),
    BABY_BACK_RIBS("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000),
    CHOCOLATE_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000),
    ZERO_COLA("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000),
    EMPTY("0", 0);
    public final String name;
    public final int price;
    public int num;
    MenuDetail(String name, int price) {
        this.name = name;
        this.price = price;
    }
    public static MenuDetail findByName(String name){
        return Arrays.stream(MenuDetail.values())
                .filter(menu -> menu.name.equals(name))
                .findAny()
                .orElse(EMPTY);
    }
    public void setNum(int num){
        this.num=num;
    }
}
