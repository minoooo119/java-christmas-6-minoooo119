package View;

import Model.CalculateBenefit;
import christmas.MenuDetail;

import java.util.List;

import static View.OutputComment.*;


public class OutputView {
    private final Integer date;
    private final List<MenuDetail> menuDetailList;
    private Integer originalTotalPrice;
    CalculateBenefit calculateDiscount;
    public OutputView(Integer date, List<MenuDetail> menuDetailList){
        this.date=date;
        this.menuDetailList=menuDetailList;
        setOriginalTotalPrice();
        this.calculateDiscount=new CalculateBenefit(date,menuDetailList,getOriginalTotalPrice());
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
        if(calculateDiscount.calculateAllBenefit()){
            printD_dayBenefit();
            printWeekdayBenefit();
            printWeekendBenefit();
            printSpecialStarBenefit();
            printGiftBenefit();
            return;
        }
        System.out.println("없음");
    }
    public void printTotalBenefitPrice(){
        if(calculateDiscount.calculateAllBenefit()){
            System.out.printf("-%,d원%n",calculateDiscount.getTotalBenefit());
            return;
        }
        System.out.println("없음");
    }
    public void printFinalPrice(){
        if(calculateDiscount.calculateAllBenefit()){
            System.out.printf("%,d원%n",getOriginalTotalPrice()-calculateDiscount.getTotalBenefit());
            return;
        }
        System.out.printf("%,d원%n",getOriginalTotalPrice());
    }
    public void printDecemberEventBadge(){
        if(calculateDiscount.calculateAllBenefit()){
            System.out.println(getBadge(calculateDiscount.getTotalBenefit()));
            return;
        }
        System.out.println("없음");
    }
    public String getBadge(int price){
        if(price>=5000&&price<10000){
            return "별";
        }
        if(price>=10000&&price<20000){
            return "트리";
        }
        if(price>=20000){
            return "산타";
        }
        return "없음";

    }

    private void printD_dayBenefit(){
        if(calculateDiscount.getD_dayBenefit()!=0){
            System.out.printf(CHRISTMAS_D_DAY_DISCOUNT+"%n",calculateDiscount.getD_dayBenefit());
        }
    }
    private void printWeekdayBenefit(){
        if(calculateDiscount.getWeekdayBenefit()!=0){
            System.out.printf(WEEKDAY_DISCOUNT+"%n",calculateDiscount.getWeekdayBenefit());
        }
    }
    private void printWeekendBenefit(){
        if(calculateDiscount.getWeekendBenefit()!=0){
            System.out.printf(WEEKEND_DISCOUNT+"%n",calculateDiscount.getWeekendBenefit());
        }
    }
    private void printSpecialStarBenefit(){
        if(calculateDiscount.getSpecialStarBenefit()!=0){
            System.out.printf(SPECIAL_STAR_DISCOUNT+"%n",calculateDiscount.getSpecialStarBenefit());
        }
    }
    private void printGiftBenefit(){
        if(calculateDiscount.getGiftBenefit()!=0){
            System.out.printf(GIFT_EVENT_DISCOUNT+"%n",calculateDiscount.getGiftBenefit());
        }
    }
}
