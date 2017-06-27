import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by laileon on 2017/6/26.
 */
public class Launcher {
    public static void main(String[] args) {
        //create by just
        Observable<String> observable = Observable.just("ad", "2123", "31");
        //left - args, right - action
        observable.map(String::length).subscribe(System.out::println);
        observable.subscribe(System.out::println);

        Observable<Long> secondIntervals = Observable.interval(1, TimeUnit.SECONDS);
        secondIntervals.subscribe(s -> System.out.println(s));
        sleep(5000);

        //create by create
        Observable<String> source = Observable.create(emitter -> {
            emitter.onNext("A");
            emitter.onNext("B");
            emitter.onNext("C");
            emitter.onComplete();
        });
        source.subscribe(System.out::println);

        //create by fromIterable
        List<String> items = Arrays.asList("Alpha", "Beta", "Gama");
        Observable<String> item = Observable.fromIterable(items);
        item.map(String::length).filter(i -> i > 4).subscribe(System.out::println);

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Got " + integer);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println();
            }

            @Override
            public void onComplete() {
                System.out.println("END");
            }
        };

        item.map(String::length).filter(i -> i > 4).subscribe(observer);

        //multicasting
        ConnectableObservable<String> stringConnectableObservable = Observable.just("AS", "BD", "CFG").publish();
        stringConnectableObservable.subscribe(System.out::println);
        stringConnectableObservable.map(String::length).subscribe(System.out::println);
        //fire
        stringConnectableObservable.connect();


    }

    public static void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
