package com.colin.rxjava;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

public class HelloWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Step1 创建被观察者
		Observable mObservable = Observable.create(new OnSubscribe<String>(){
			public void call(Subscriber<? super String> subscriber){
					subscriber.onNext("Hello World");
					subscriber.onCompleted();
				}
			});
		//Step2 创建观察者
		Subscriber subscriber = new Subscriber<String>(){
				public void onCompleted(){
					System.out.println("onCompleted");
				}

				@Override
				public void onError(Throwable arg0) {
					// TODO Auto-generated method stub
					System.out.println("onCompleted");
				}

				@Override
				public void onNext(String arg0) {
					// TODO Auto-generated method stub
					System.out.println("onNext======= "+ arg0);
				}
			};
		//Step3 订阅事件	
			mObservable.subscribe(subscriber);	
		}
	
	}


