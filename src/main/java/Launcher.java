import io.reactivex.Observable;

/**
 * Created by laileon on 2017/6/26.
 */
public class Launcher {
    public static void main(String[] args) {
        Observable<String> observable = Observable.just("1", "2", "3");
        observable.subscribe(s -> System.out.println(s));
    }
}
