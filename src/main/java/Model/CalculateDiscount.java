package Model;

import christmas.Day;
import christmas.MenuDetail;
import christmas.MenuGroup;
import christmas.Week;

import java.util.List;

public class CalculateDiscount {
//    크리스마스 디데이 할인
//    이벤트 기간: 2023.12.1 ~ 2023.12.25
//            1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
//    총주문 금액에서 해당 금액만큼 할인
//            (e.g. 시작일인 12월 1일에 1,000원, 2일에 1,100원, ..., 25일엔 3,400원 할인)
//    평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
//    주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
//    특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
//    증정 이벤트: 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정
//    이벤트 기간: '크리스마스 디데이 할인'을 제외한 다른 이벤트는 2023.12.1 ~ 2023.12.31 동안 적용
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
