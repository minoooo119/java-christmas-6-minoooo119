package christmas;

import View.InputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView=new InputView();
        int date=inputView.readDate();
        List<MenuDetail> menuDetailList=inputView.readMenu();
    }
}
