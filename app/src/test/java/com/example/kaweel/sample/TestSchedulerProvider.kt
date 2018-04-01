package com.example.kaweel.sample

import com.example.kaweel.sample.app.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider : SchedulerProvider{

    val testScheduler: TestScheduler = TestScheduler()

    override fun uiScheduler(): Scheduler {
       return testScheduler
    }

    override fun ioScheduler(): Scheduler {
        return testScheduler
    }

}