package View;

import christmas.MenuDetail;

import java.util.List;

import static View.OutputComment.*;


public class OutputView {
    private final Integer date;
    public final List<MenuDetail> menuDetailList;
    private Integer originalTotalPrice;

    public OutputView(Integer date, List<MenuDetail> menuDetailList){
        this.date=date;
        this.menuDetailList=menuDetailList;
    }
    public void printOpeningTitle(){
        System.out.printf((OPENING_TITLE) + "%n", date);
    }
    public void printMenuTitle(){
        System.out.println(MENU_TITLE);
    }
    public void printOriginalPriceTitle(){
        System.out.println(ORIGINAL_PRICE_TITLE);
    }
    public void printGiftMenuTitle(){
        System.out.println(GIFT_MENU_TITLE);
    }
    public void printBenefitDetailsTitle(){
        System.out.println(BENEFIT_DETAILS_TITLE);
    }
    public void printTotalBenefitPriceTitle(){
        System.out.println(TOTAL_BENEFIT_PRICE_TITLE);
    }
    public void printFinalPriceTitle(){
        System.out.println(FINAL_PRICE_TITLE);
    }
    public void printDecemberEventBadgeTitle(){
        System.out.println(DECEMBER_EVENT_BADGE_TITLE);
    }
    public void printMenuDetail(){
        for(MenuDetail menuDetail:menuDetailList){
            System.out.println(menuDetail.name+" "+menuDetail.num+"개");
        }
    }
    public void printOriginalTotalPrice(){
        System.out.printf("%,d원%n",getOriginalTotalPrice());
    }
    public Integer getOriginalTotalPrice(){
        int totalPrice=0;
        for(MenuDetail menuDetail:menuDetailList){
            totalPrice+=menuDetail.price*menuDetail.num;
        }
        originalTotalPrice=totalPrice;
        return totalPrice;
    }
    public void printGift(){
        if(originalTotalPrice>120000) {
            System.out.println("샴페인 1개");
            return;
        }
        System.out.println("없음");
    }
}
