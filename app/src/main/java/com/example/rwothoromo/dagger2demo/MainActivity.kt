package com.example.rwothoromo.dagger2demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.Component
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

class Info @Inject constructor() {
    val text = "Hello Dagger2"
}

@Component
interface SomethingComponent {
    fun inject(app: MainActivity)
}