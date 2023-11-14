package Model;

import christmas.Day;
import christmas.MenuDetail;
import christmas.MenuGroup;
import christmas.Week;

import java.util.List;

public class CalculateDiscount {
    private final List<MenuDetail> menuDetailList;
    private final Integer date;
    private final Integer totalPrice;
    private final Day day;
    private final Week week;
    public CalculateDiscount(Integer date, List<MenuDetail> menuDetailList, Integer totalPrice){
        this.date=date;
        this.menuDetailList=menuDetailList;
        this.totalPrice=totalPrice;
        day=Day.findByDate(date);
        week=Week.findByDay(day);
    }
    public int calculateD_dayDiscount(){
        if(date>=1&&date<=25){
            return 1000+100*(date-1);
        }
        return 0;
    }
    public int calculateWeekdayDiscount(){
        if(week==Week.WEEKDAY){
            return calculateDessertNum()*2023;
        }
        return 0;
    }
    public int calculateWeekendDiscount(){
        if(week==Week.WEEKEND){
            return calculateMainNum()*2023;
        }
        return 0;
    }
    public int calculateSpecialStarDiscount(){
        if(date==25||day==Day.SUNDAY){
            return 1000;
        }
        return 0;
    }
    public int calculateGiftDiscount(){
        if(totalPrice>=120000){
            return 25_000;
        }
        return 0;
    }
    public int calculateDessertNum(){
        int dessertNum=0;
        for(MenuDetail menuDetail:menuDetailList){
            if(MenuGroup.findByMenuList(menuDetail)== MenuGroup.DESSERT){
                dessertNum+=menuDetail.num;
            }
        }
        return dessertNum;
    }
    public int calculateMainNum(){
        int mainNum=0;
        for(MenuDetail menuDetail:menuDetailList){
            if(MenuGroup.findByMenuList(menuDetail)== MenuGroup.MAIN){
                mainNum+=menuDetail.num;
            }
        }
        return mainNum;
    }

}
