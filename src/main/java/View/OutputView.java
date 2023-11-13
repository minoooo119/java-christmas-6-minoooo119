package View;

public class OutputView {
    private final Integer date;
    public OutputView(Integer date){
        this.date=date;
    }
    public void printOpeningTitle(){
        System.out.println("12월 "+this.date+"일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }
}
