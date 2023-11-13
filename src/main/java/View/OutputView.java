package View;

import christmas.MenuDetail;

import java.util.List;

import static View.OutputComment.*;


public class OutputView {
    private final Integer date;
    public final List<MenuDetail> menuDetailList;

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
}
