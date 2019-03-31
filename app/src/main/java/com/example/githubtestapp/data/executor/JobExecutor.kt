package com.example.githubtestapp.data.executor

import com.upstream.basemvvmimpl.domain.executor.ThreadExecutor

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

import javax.inject.Inject

class JobExecutor @Inject
constructor() : ThreadExecutor {

    private val threadPoolExecutor: ThreadPoolExecutor

    companion object {
        private val NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors()
    }

    init {
        this.threadPoolExecutor = ThreadPoolExecutor(NUMBER_OF_CORES,
                NUMBER_OF_CORES * 2,
                10,
                TimeUnit.SECONDS,
                LinkedBlockingQueue<Runnable>(),
                JobThreadFactory())
    }

    override fun execute(runnable: Runnable) {
        this.threadPoolExecutor.execute(runnable)
    }

    private class JobThreadFactory : ThreadFactory {
        private var counter = 0

        override fun newThread(runnable: Runnable): Thread {
            return Thread(runnable, "android_" + counter++)
        }
    }
}
