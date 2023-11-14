package Model;

import christmas.Day;
import christmas.MenuDetail;
import christmas.MenuGroup;
import christmas.Week;

import java.util.List;

public class CalculateDiscount {
    //기본적으로 totalPrice가 만원 이상이어야 할인 계산을 해
    private final List<MenuDetail> menuDetailList;
    private final Integer date;
    private final Integer totalPrice;
    private final Day day;
    private final Week week;
    private int d_dayDiscount=0;
    private int weekdayDiscount=0;
    private int weekendDiscount=0;
    private int specialStarDiscount=0;
    private int giftDiscount=0;
    public CalculateDiscount(Integer date, List<MenuDetail> menuDetailList, Integer totalPrice){
        this.date=date;
        this.menuDetailList=menuDetailList;
        this.totalPrice=totalPrice;
        day=Day.findByDate(date);
        week=Week.findByDay(day);
    }
    public boolean calculateAllDiscount(){
        if(totalPrice>=10_000){
            d_dayDiscount=calculateD_dayDiscount();
            weekdayDiscount=calculateWeekdayDiscount();
            weekendDiscount=calculateWeekendDiscount();
            specialStarDiscount=calculateSpecialStarDiscount();
            giftDiscount=calculateGiftDiscount();
            return (d_dayDiscount!=0||weekdayDiscount!=0||weekendDiscount!=0||specialStarDiscount!=0||giftDiscount!=0);
        }
        return false;
    }
    public int getD_dayDiscount(){
        return d_dayDiscount;
    }
    public int getWeekdayDiscount(){
        return weekdayDiscount;
    }
    public int getWeekendDiscount(){
        return weekendDiscount;
    }
    public int getSpecialStarDiscount(){
        return specialStarDiscount;
    }
    public int getGiftDiscount(){
        return giftDiscount;
    }

    private int calculateD_dayDiscount(){
        if(date>=1&&date<=25){
            return 1000+100*(date-1);
        }
        return 0;
    }
    private int calculateWeekdayDiscount(){
        if(week==Week.WEEKDAY){
            return calculateDessertNum()*2023;
        }
        return 0;
    }
    private int calculateWeekendDiscount(){
        if(week==Week.WEEKEND){
            return calculateMainNum()*2023;
        }
        return 0;
    }
    private int calculateSpecialStarDiscount(){
        if(date==25||day==Day.SUNDAY){
            return 1000;
        }
        return 0;
    }
    private int calculateGiftDiscount(){
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
