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
    private Benefit benefit;
    public CalculateBenefit(Integer date, List<MenuDetail> menuDetailList, Integer totalPrice,Benefit benefit){
        this.date=date;
        this.menuDetailList=menuDetailList;
        this.totalPrice=totalPrice;
        day=Day.findByDate(date);
        week=Week.findByDay(day);
        this.benefit=benefit;
    }
    public boolean calculateAllBenefit(){
        if(totalPrice>=10_000){
            benefit.setD_dayBenefit(calculateD_dayBenefit());
            benefit.setWeekdayBenefit(calculateWeekdayBenefit());
            benefit.setWeekendBenefit(calculateWeekendBenefit());
            benefit.setSpecialStarBenefit(calculateSpecialStarBenefit());
            benefit.setGiftBenefit(calculateGiftBenefit());
            benefit.setTotalBenefit();
            return benefit.getTotalBenefit()!=0;
        }
        return false;
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
