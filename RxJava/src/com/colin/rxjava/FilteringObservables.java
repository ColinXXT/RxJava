package com.colin.rxjava;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.functions.Func1;
import rx.Subscriber;

public class FilteringObservables {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		debounce();
//		distinct();
//		elementAt();
//		filter();
//		first();
//		last();
//		ignoreElements();
//		sample();
//		skip();
//		skipLast();
//		take();
		takeLast();
		
	}
	//debounce �ڲ������1sʱ����û����������������ᷢ�͸��۲���
	private static void debounce(){
		Observable.create(new OnSubscribe<Integer>() {
			public void call(Subscriber<? super Integer> arg0){
				try{
					for(int i = 0; i < 10; i++){
						Thread.sleep(1000);
						arg0.onNext(i);
					}
					arg0.onCompleted();
				} catch (Exception e){
					arg0.onError(e);
				}
			}
		}).debounce(1, TimeUnit.SECONDS).subscribe(new Subscriber<Integer>() {
			@Override
			public void onNext(Integer arg0) {
				// TODO Auto-generated method stub
				System.out.println("onNext "+arg0);
			}				
			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("onCompleted");
			}
			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				System.out.println("onError");
			}
	
		});
	}
	//distinct
	private static void distinct(){
		Observable.just(1,2,3,2,3).distinct().subscribe(new Subscriber<Integer>() {

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				System.out.println("onError");
			}

			@Override
			public void onNext(Integer arg0) {
				// TODO Auto-generated method stub
				System.out.println("onNext " + arg0);
			}			
		});
	}
	//ElementAt
	private static void elementAt(){
		Observable.just(1,2,3,2,3).elementAt(3).subscribe(new Subscriber<Integer>() {

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				System.out.println("onError");
			}

			@Override
			public void onNext(Integer arg0) {
				// TODO Auto-generated method stub
				System.out.println("onNext " + arg0);
			}			
		});
	}
	//filter
	private static void filter(){
		Observable.just(1,2,3).filter(new Func1<Integer, Boolean>() {
			@Override
			public Boolean call(Integer arg0) {
				// TODO Auto-generated method stub
				return arg0 > 2;
			}
		}).subscribe(new Subscriber<Integer>() {

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				System.out.println("onError");
			}

			@Override
			public void onNext(Integer arg0) {
				// TODO Auto-generated method stub
				System.out.println("onNext " + arg0);
			}			
		});
	}
	//first
	private static void first(){
		Observable.just(9,2,3,2,3).distinct().first().subscribe(new Subscriber<Integer>() {

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				System.out.println("onError");
			}

			@Override
			public void onNext(Integer arg0) {
				// TODO Auto-generated method stub
				System.out.println("onNext " + arg0);
			}			
		});
	}
	//last
	private static void last(){
		Observable.just(9,2,3,2,3).distinct().last().subscribe(new Subscriber<Integer>() {

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				System.out.println("onError");
			}

			@Override
			public void onNext(Integer arg0) {
				// TODO Auto-generated method stub
				System.out.println("onNext " + arg0);
			}			
		});
	}
	//IgnoreElements ��ִ��onNext������ֱ�ӵ���onCompleted����onError�����¼�
	private static void ignoreElements(){
		Observable.create(new OnSubscribe<Integer>() {
			public void call(Subscriber<? super Integer> arg0){
				arg0.onNext(123);
				throw new NullPointerException();
			}
		}).ignoreElements().subscribe(new Subscriber<Integer>() {
			@Override
			public void onNext(Integer arg0) {
				// TODO Auto-generated method stub
				System.out.println("onNext "+arg0);
			}				
			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("onCompleted");
			}
			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				System.out.println("onError " + arg0);
			}
	
		});
	}
	//Sample  ���4s�ɼ�����Ȼ�������ݵ��۲���
	private static void sample(){
		Observable.create(new OnSubscribe<Integer>() {
			public void call(Subscriber<? super Integer> arg0){
				try{
					for(int i = 0; i < 10; i++){
						Thread.sleep(1000);
						arg0.onNext(i);
					}
					arg0.onCompleted();
				} catch (Exception e){
					arg0.onError(e);
				}
			}
		}).sample(5, TimeUnit.SECONDS).subscribe(new Subscriber<Integer>() {
			@Override
			public void onNext(Integer arg0) {
				// TODO Auto-generated method stub
				System.out.println("onNext "+arg0);
			}				
			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("onCompleted");
			}
			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				System.out.println("onError");
			}
	
		});
	}
	//Skip  
	private static void skip(){
		Observable.just(1,2,3,4).skip(2).subscribe(new Subscriber<Integer>() {

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				System.out.println("onError");
			}

			@Override
			public void onNext(Integer arg0) {
				// TODO Auto-generated method stub
				System.out.println("onNext " + arg0);
			}			
		});
	}
	//SkipLast   
	private static void skipLast(){
		Observable.just(1,2,3,4).skipLast(2).subscribe(new Subscriber<Integer>() {

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				System.out.println("onError");
			}

			@Override
			public void onNext(Integer arg0) {
				// TODO Auto-generated method stub
				System.out.println("onNext " + arg0);
			}			
		});
	}
	//Take ȡǰ2��   
	private static void take(){
		Observable.just(1,2,3,4).take(2).subscribe(new Subscriber<Integer>() {

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				System.out.println("onError");
			}

			@Override
			public void onNext(Integer arg0) {
				// TODO Auto-generated method stub
				System.out.println("onNext " + arg0);
			}			
		});
	}
	//Take/TakeLast 
	private static void takeLast(){
		Observable.just(1,2,3,4).takeLast(2).subscribe(new Subscriber<Integer>() {

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				System.out.println("onError");
			}

			@Override
			public void onNext(Integer arg0) {
				// TODO Auto-generated method stub
				System.out.println("onNext " + arg0);
			}			
		});
	}
}
