package com.example.rwothoromo.dagger2demo

import dagger.Component
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class MainActivityTest {

    @Inject
    @field:PreferToSay(LOVE)
    lateinit var infoLove: Info
    @Inject
    @field:PreferToSay(HELLO)
    lateinit var infoHello: Info

    @Before
    fun setUp() {
        DaggerTestSomethingComponent.builder().somethingModule(TestSomethingModule()).build().inject(this)
    }

    @Test
    fun simpleTest() {
        assertEquals("Test Love", infoLove.text)
        assertEquals("Hello Dagger 2", infoHello.text)
    }
}


class TestSomethingModule : SomethingModule() { // to have an exact replicate of 'SomethingModule', inherit from it
    override fun sayLoveDagger2(): Info {
        return Info("Test Love")
    }
}

@Component(modules = [SomethingModule::class]) // not modules = [TestSomethingComponent::class], because it doesn’t have @Module set on it.
interface TestSomethingComponent :
    SomethingComponent { // another SomethingComponent to inject MainActivityTest and use any injections from 'SomethingComponent'
    fun inject(app: MainActivityTest)
}
