package hootor.com.rxjavaclass_2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataStream = Observable.just(10, 20, 30,40);

        val dataObserver = object : Observer<Int>{
            override fun onComplete() {
                println("All data is received....")
            }

            override fun onSubscribe(d: Disposable) {
                println()
            }

            override fun onNext(t: Int) {
                println("new data is received..." + t)
            }

            override fun onError(e: Throwable) {
                println("An ERROR is received " + e?.message)
            }

        }

        dataStream.subscribe(dataObserver)
    }
}
