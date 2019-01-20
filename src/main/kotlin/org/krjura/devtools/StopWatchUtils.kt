package org.krjura.devtools

import org.springframework.util.StopWatch

class StopWatchUtils {
    companion object {

        /**
         * executes a function and measures it execution
         *
         * @param supplier the function to execute and measure its execution
         * @return Pair where the first value is the duration and the second is the result of the execution
         */
        fun <T> execute(supplier: () -> T): Pair<Long, T> {

            val stopwatch = StopWatch();
            stopwatch.start();

            val result = supplier()

            stopwatch.stop()

            return Pair(stopwatch.totalTimeMillis, result);
        }
    }
}