package com.colin.rxjava;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;

public class TransformObsevrablesDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		buffer();
//		map();
//		groupBy();
//		flagMap();
//		scan();
		window();
	}
		//Map
	private static void map(){
			Observable.just(123).map(new Func1<Integer, String>() {

				@Override
				public String call(Integer arg0) {
					// TODO Auto-generated method stub
					return arg0 + "";
				}
			}).subscribe(new Subscriber <String>() {
			@Override
			public void onNext(String arg0) {
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
	//FlatMap
	private static void flagMap(){
		Observable.just(1,2,3,4,5).flatMap(new Func1<Integer, Observable<? extends String>>() {	
			@Override
			public Observable<? extends String>call(Integer arg0) {
					// TODO Auto-generated method stub
					return Observable.just(arg0 + "");
				}
			}).subscribe(new Subscriber <String>() {
			@Override
			public void onNext(String arg0) {
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
		//GroupBy
	private static void groupBy(){
		Observable.just(1,2,3,4,5).groupBy(new Func1<Integer, Integer>() {	
			@Override
			public Integer call(Integer arg0) {
					// TODO Auto-generated method stub
					return arg0 % 2;
				}
			}).subscribe(new Observer<GroupedObservable<Integer, Integer>>() {
				@Override
				public void onCompleted() {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void onError(Throwable arg0) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void onNext(final GroupedObservable<Integer, Integer> arg0) {
					// TODO Auto-generated method stub
					arg0.subscribe(new Subscriber<Integer>(){
						@Override
						public void onCompleted() {
							// TODO Auto-generated method stub
							
						}
						@Override
						public void onError(Throwable arg0) {
							// TODO Auto-generated method stub
							
						}
						@Override
						public void onNext(Integer data) {
							// TODO Auto-generated method stub
							System.out.println("group " + arg0.getKey() + " data:"+data);
						}
						
					});
				}
	
		});
}
		//Buffer
	private static void buffer(){
		Observable.range(1, 5).buffer(2).subscribe(new Observer<List <Integer>>(){
			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub			
			}
			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void onNext(List<Integer> arg0) {
				// TODO Auto-generated method stub
				System.out.println("onNext "+ arg0);
			}	
		});
	};
	//Scan
	private static void scan(){
		Observable.range(1, 5).scan(new Func2<Integer, Integer, Integer>() {
			
			@Override
			public Integer call(Integer sum, Integer arg1) {
				// TODO Auto-generated method stub
				return sum + arg1;
			}
		}).subscribe(new Observer<Integer>(){

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNext(Integer arg0) {
				// TODO Auto-generated method stub
				System.out.println("onNext " + arg0);
			}
			
		});
	}
	//Window
	private static void window(){
		Observable.interval(1,TimeUnit.SECONDS).take(10).window(3,TimeUnit.SECONDS).subscribe(new Observer<Observable<Long>>() {  
            @Override  
            public void onCompleted() {  
            	System.out.println("onCompleted()");  
            }  
  
            @Override  
            public void onError(Throwable e) {  
            	System.out.println("onError()" + e);  
            }  
  
            @Override  
            public void onNext(Observable<Long> integerObservable) {   
                integerObservable.subscribe(new Action1<Long>() {  
                	
                    public void call(Long integer) {  
                    	System.out.println("onNext" + integer);  
                    }  
                });  
            }             
        }); 
		
	}
}


