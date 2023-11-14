package Model;

import christmas.Day;
import christmas.MenuDetail;
import christmas.MenuGroup;
import christmas.Week;

import java.util.List;

public class CalculateBenefit {
    private final List<MenuDetail> menuDetailList;
    private final Integer date;
    private final Integer totalPrice;
    private final Day day;
    private final Week week;
    private int d_dayBenefit=0;
    private int weekdayBenefit=0;
    private int weekendBenefit=0;
    private int specialStarBenefit=0;
    private int giftBenefit=0;
    private int totalBenefit=0;
    public CalculateBenefit(Integer date, List<MenuDetail> menuDetailList, Integer totalPrice){
        this.date=date;
        this.menuDetailList=menuDetailList;
        this.totalPrice=totalPrice;
        day=Day.findByDate(date);
        week=Week.findByDay(day);
    }
    public boolean calculateAllBenefit(){
        if(totalPrice>=10_000){
            d_dayBenefit=calculateD_dayBenefit();
            weekdayBenefit=calculateWeekdayBenefit();
            weekendBenefit=calculateWeekendBenefit();
            specialStarBenefit=calculateSpecialStarBenefit();
            giftBenefit=calculateGiftBenefit();
            calculateTotalBenefit();
            return totalBenefit!=0;
        }
        return false;
    }
    private void calculateTotalBenefit(){
        totalBenefit=d_dayBenefit+weekdayBenefit+weekendBenefit+specialStarBenefit+giftBenefit;
    }
    public int getTotalBenefit(){
        return totalBenefit;
    }
    public int getD_dayBenefit(){
        return d_dayBenefit;
    }
    public int getWeekdayBenefit(){
        return weekdayBenefit;
    }
    public int getWeekendBenefit(){
        return weekendBenefit;
    }
    public int getSpecialStarBenefit(){
        return specialStarBenefit;
    }
    public int getGiftBenefit(){
        return giftBenefit;
    }

    private int calculateD_dayBenefit(){
        if(date>=1&&date<=25){
            return 1000+100*(date-1);
        }
        return 0;
    }
    private int calculateWeekdayBenefit(){
        if(week==Week.WEEKDAY){
            return calculateDessertNum()*2023;
        }
        return 0;
    }
    private int calculateWeekendBenefit(){
        if(week==Week.WEEKEND){
            return calculateMainNum()*2023;
        }
        return 0;
    }
    private int calculateSpecialStarBenefit(){
        if(date==25||day==Day.SUNDAY){
            return 1000;
        }
        return 0;
    }
    private int calculateGiftBenefit(){
        if(totalPrice>=120000){
            return 25_000;
        }
        return 0;
    }
    private int calculateDessertNum(){
        int dessertNum=0;
        for(MenuDetail menuDetail:menuDetailList){
            if(MenuGroup.findByMenuList(menuDetail)== MenuGroup.DESSERT){
                dessertNum+=menuDetail.num;
            }
        }
        return dessertNum;
    }
    private int calculateMainNum(){
        int mainNum=0;
        for(MenuDetail menuDetail:menuDetailList){
            if(MenuGroup.findByMenuList(menuDetail)== MenuGroup.MAIN){
                mainNum+=menuDetail.num;
            }
        }
        return mainNum;
    }

}
