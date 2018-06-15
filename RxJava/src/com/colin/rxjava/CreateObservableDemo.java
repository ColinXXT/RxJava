package com.colin.rxjava;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;


public class CreateObservableDemo {

	/**
	 * @param args
	 */
	private static String valueStr;
	public static void main(String[] args) {
		zip();
//		merge();
//		startWith();
//		combinLastest();
//		join();
	};
		// TODO Auto-generated method stub
	/**
	 * 用来合并2个Observable发射的数据项，根据func2函数生成一个新的值发射出去，当其中一个Observable法书数据结束或者出现异常时，
	 * 另外一个也将停止发射数据
	 */
	private static void zip(){
		Observable<Integer> observable1 = Observable.just(10,20,30);
		Observable<Integer> observable2 = Observable.just(4,8,12,16);
		Observable.zip(observable1, observable2, new Func2<Integer, Integer, Integer>() {
			public Integer call(Integer integer, Integer integer2){
				return integer + integer2;
			} 
		}).subscribe(new Subscriber<Integer>() {
			@Override
			public void onNext(Integer value) {
				// TODO Auto-generated method stub
				System.out.println("onNext "+value);
			}	
			
			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				System.out.println("onError " + arg0.getMessage());
			}
	
		});
	};
		/**
		 * 按照序列先后顺序合并2个数据
		 * */
	private static void merge(){
		Observable<Integer> odds = Observable.just(1,3,5);
		Observable<Integer> evens = Observable.just(2,4,6);
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		Observable.merge(odds, evens).subscribe(new Subscriber<Integer>() {
			@Override
			public void onNext(Integer value) {
				// TODO Auto-generated method stub
				System.out.println("onNext "+value);
			}	
			
			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				System.out.println("onError " + arg0.getMessage());
			}
	
		});
	};
	/**
	 * 用于在源Observable发射的数据前插入数据。使用startWith(Iterable<T>)我们还可以在源Observable发射的数据前插入Iterable
	 * */
	private static void startWith(){
		Observable<Integer> first = Observable.just(1,3,5);
		Observable<Integer> second = Observable.just(2,4,6);
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		first.startWith(second).subscribe(new Subscriber<Integer>() {
			@Override
			public void onNext(Integer value) {
				// TODO Auto-generated method stub
				System.out.println("onNext "+value);
			}	
			
			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				System.out.println("onError " + arg0.getMessage());
			}
	
		});
	};
	/**
	 * 用于将两个Observable最近发射的数据已经Func2函数的规则进展组合
	 * 5+2...5+4...5+6
	 * */
	private static void combinLastest(){
		Observable<Integer> frist = Observable.just(1,3,5);
		Observable<Integer> second = Observable.just(2,4,6);
		frist.combineLatest(frist,second, new Func2<Integer, Integer, Integer>() {

			@Override
			public Integer call(Integer arg0, Integer arg1) {
				// TODO Auto-generated method stub
				System.out.println("arg0 "+arg0 + "arg1 "+arg1);
				return arg0 + arg1;
			}
		}).subscribe(new Subscriber<Integer>() {
			@Override
			public void onNext(Integer value) {
				// TODO Auto-generated method stub
				System.out.println("onNext "+value);
			}	
			
			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				System.out.println("onError " + arg0.getMessage());
			}
	
		});
	};
		//Range
	private static void join(){
		Observable<Integer> obs1 = Observable.create(new Observable.OnSubscribe<Integer>() {
		    @Override
		    public void call(Subscriber<? super Integer> subscriber) {
		        for (int i = 1; i < 5; i++) {
		            subscriber.onNext(i);
		            try {
		                Thread.sleep(1000);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		});
		Observable.just("srcObs-")
        .join(obs1,
        //接受从源Observable发射来的数据，并返回一个Observable，
        //这个Observable的生命周期决定了源Observable发射出来数据的有效期
        new Func1<String, Observable<Long>>() {
            @Override
            public Observable<Long> call(String s) {
                return Observable.timer(3000, TimeUnit.MILLISECONDS);
            }
        },
        //接受从目标Observable发射来的数据，并返回一个Observable，
        //这个Observable的生命周期决定了目标Observable发射出来数据的有效期
        new Func1<Integer, Observable<Long>>() {
            public Observable<Long> call(Integer integer) {
                return Observable.timer(2000, TimeUnit.MILLISECONDS);
            }
        },
        //接收从源Observable和目标Observable发射来的数据，并返回最终组合完的数据
        new Func2<String,Integer,String>() {
            @Override
            public String call(String str1, Integer integer) {
                return str1 + integer;
            }
        })
		.subscribe(new Action1<String>() {
		    @Override
		    public void call(String o) {
		        System.out.println("join:"+o);
		    }
		});
	};
}



