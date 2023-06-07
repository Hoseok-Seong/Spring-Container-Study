package hello.core.singleton;

public class StatefulService {

    // 상태를 유지하는 필드
    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 문제되는 부분
    }

    public int getPrice() {
        return price;
    }

    // stateless
    public int order2(String name, int price) {
        return price;
    }
}
