package com.aserbao.aserbaosandroid.aaThird.rxJava3;

import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subscribers.DisposableSubscriber;

public class RxJavaActivity extends BaseRecyclerViewActivity {

    private static final String TAG = "RxJavaActivity";


    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Hello World",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable的使用讲解",100));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("RxJava使用三步骤",1));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick) {
        switch (position){
            case 100:
                testFlowable();
                break;
            case 0:
                testJust();
                break;
            case 1:
                /*Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("Hello");
                        subscriber.onNext("Hi");
                        subscriber.onNext("Aloha");
                        subscriber.onCompleted();
                    }
                });*/

                Observable observable = new Observable() {
                    @Override
                    protected void subscribeActual(Observer observer) {

                    }
                };
                observable.subscribe(mObserver);
//                observable.subscribe(mSubscriber);

                break;
        }
    }

    public void testJust() {
        Flowable.just("Hello world").subscribe((Consumer<? super String>) System.out::println);
        //不支持lamdba
        /*Flowable.just("Hello world")
            .subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    System.out.println(s);
                }
            });*/
    }

    public void testFlowable(){
        Disposable d = Flowable.just("Hello world!")
            .delay(1, TimeUnit.SECONDS)
            .subscribeWith(new DisposableSubscriber<String>() {
                @Override public void onStart() {
                    Log.d(TAG, "onStart() called" + Thread.currentThread());
                    request(2);
                }
                @Override public void onNext(String t) {
                    Log.d(TAG, "onNext() called with: t = [" + t + "]"+ Thread.currentThread());
//                    request(1);
                    cancel();
                }
                @Override public void onError(Throwable t) {
                    Log.d(TAG, "onError() called with: t = [" + t + "]"+ Thread.currentThread());
                    t.printStackTrace();
                }
                @Override public void onComplete() {
                    Log.d(TAG, "onComplete() called"+ Thread.currentThread());
                }
            });

        /*try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 调用dispose()方法可以取消当前序列
        d.dispose();*/
    }


    Observer<String> mObserver = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.d(TAG, "onSubscribe() called with: d = [" + d + "]");
        }

        @Override
        public void onNext(String s) {
            Log.d(TAG, "onNext() called with: s = [" + s + "]");
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError() called with: e = [" + e + "]");
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "onComplete() called");
        }
    };
    Subscriber<String> mSubscriber = new Subscriber<String>() {
        @Override
        public void onSubscribe(Subscription s) {
            Log.d(TAG, "onSubscribe() called with: s = [" + s + "]");
        }

        @Override
        public void onNext(String s) {
            Log.d(TAG, "onNext() called with: s = [" + s + "]");
        }

        @Override
        public void onError(Throwable t) {
            Log.d(TAG, "onError() called with: t = [" + t + "]");
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "onComplete() called");
        }
    };


}
