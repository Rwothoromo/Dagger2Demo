package com.example.rwothoromo.dagger2demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var info: Info // Inform Dagger 2 which member variable it should create magically

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerSomethingComponent.create().inject(this)
        text_view.text = info.text
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
}