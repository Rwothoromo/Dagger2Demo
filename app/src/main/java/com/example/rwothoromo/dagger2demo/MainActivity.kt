package com.example.rwothoromo.dagger2demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Named

const val LOVE = "Love"
const val HELLO = "Hello"

class MainActivity : AppCompatActivity() {

    @Inject
    @field:Named("Love") // @field is a Kotlin way of adding a field annotation when using it
    lateinit var infoLove: Info // Inform Dagger 2 which member variable it should create magically

    @Inject
    @field:Named("Hello")
    lateinit var infoHello: Info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerSomethingComponent.create().inject(this)
        text_view.text = "${infoLove.text} ${infoHello.text}"
    }
}

class Info(val text: String)

@Component(modules = [SomethingModule::class]) // hook SomethingModule into SomethingComponent by listing it inside the modules param
interface SomethingComponent {
    fun inject(app: MainActivity) // inject can actually be named something else
}

@Module
class SomethingModule { // a bag of sorts to store a repository of provided objects e.g. the Info provider, for Injection
    @Provides
    fun providesInfo(): Info {
        return Info("This function is here to replace the text!")
    }

    @Provides
    @Named(LOVE) // @Named is an inbuilt Qualifier, we can use a String to differentiate the Provider
    fun sayLoveDagger2(): Info {
        return Info("Love Dagger 2")
    }

    @Provides
    @Named(HELLO)
    fun sayHelloDagger2(): Info {
        return Info("Hello Dagger 2")
    }
}