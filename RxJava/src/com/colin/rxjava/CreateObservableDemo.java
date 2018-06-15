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
	 * �����ϲ�2��Observable��������������func2��������һ���µ�ֵ�����ȥ��������һ��Observable�������ݽ������߳����쳣ʱ��
	 * ����һ��Ҳ��ֹͣ��������
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
		 * ���������Ⱥ�˳��ϲ�2������
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
	 * ������ԴObservable���������ǰ�������ݡ�ʹ��startWith(Iterable<T>)���ǻ�������ԴObservable���������ǰ����Iterable
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
	 * ���ڽ�����Observable�������������Ѿ�Func2�����Ĺ����չ���
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
        //���ܴ�ԴObservable�����������ݣ�������һ��Observable��
        //���Observable���������ھ�����ԴObservable����������ݵ���Ч��
        new Func1<String, Observable<Long>>() {
            @Override
            public Observable<Long> call(String s) {
                return Observable.timer(3000, TimeUnit.MILLISECONDS);
            }
        },
        //���ܴ�Ŀ��Observable�����������ݣ�������һ��Observable��
        //���Observable���������ھ�����Ŀ��Observable����������ݵ���Ч��
        new Func1<Integer, Observable<Long>>() {
            public Observable<Long> call(Integer integer) {
                return Observable.timer(2000, TimeUnit.MILLISECONDS);
            }
        },
        //���մ�ԴObservable��Ŀ��Observable�����������ݣ���������������������
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



