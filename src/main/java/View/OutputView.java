package View;

import Model.CalculateDiscount;
import christmas.MenuDetail;

import java.util.List;

import static View.OutputComment.*;


public class OutputView {
    private final Integer date;
    private final List<MenuDetail> menuDetailList;
    private Integer originalTotalPrice;
    CalculateDiscount calculateDiscount;
    public OutputView(Integer date, List<MenuDetail> menuDetailList){
        this.date=date;
        this.menuDetailList=menuDetailList;
        setOriginalTotalPrice();
        this.calculateDiscount=new CalculateDiscount(date,menuDetailList,getOriginalTotalPrice());
    }
    public void printBlankLine(){
        System.out.println();
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
    public void setOriginalTotalPrice(){
        int totalPrice=0;
        for(MenuDetail menuDetail:menuDetailList){
            totalPrice+=menuDetail.price*menuDetail.num;
        }
        originalTotalPrice=totalPrice;
    }
    public Integer getOriginalTotalPrice(){
        return originalTotalPrice;
    }
    public void printGift(){
        if(originalTotalPrice>120000) {
            System.out.println("샴페인 1개");
            return;
        }
        System.out.println("없음");
    }
    public void printDiscountDetails(){
        if(calculateDiscount.calculateAllDiscount()){
            printD_dayDiscount();
            printWeekdayDiscount();
            printWeekendDiscount();
            printSpecialStarDiscount();
            printGiftDiscount();
            return;
        }
        System.out.println("없음");
    }
    public void printD_dayDiscount(){
        if(calculateDiscount.getD_dayDiscount()!=0){
            System.out.printf(CHRISTMAS_D_DAY_DISCOUNT+"%n",calculateDiscount.getD_dayDiscount());
        }
    }
    public void printWeekdayDiscount(){
        if(calculateDiscount.getWeekdayDiscount()!=0){
            System.out.printf(WEEKDAY_DISCOUNT+"%n",calculateDiscount.getWeekdayDiscount());
        }
    }
    public void printWeekendDiscount(){
        if(calculateDiscount.getWeekendDiscount()!=0){
            System.out.printf(WEEKEND_DISCOUNT+"%n",calculateDiscount.getWeekendDiscount());
        }
    }
    public void printSpecialStarDiscount(){
        if(calculateDiscount.getSpecialStarDiscount()!=0){
            System.out.printf(SPECIAL_STAR_DISCOUNT+"%n",calculateDiscount.getSpecialStarDiscount());
        }
    }
    public void printGiftDiscount(){
        if(calculateDiscount.getGiftDiscount()!=0){
            System.out.printf(GIFT_EVENT_DISCOUNT+"%n",calculateDiscount.getGiftDiscount());
        }
    }
}
