package com.nomad5.demologging;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;

import com.nomad5.log.Annotation.DebugLog;
import com.nomad5.log.Log;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("Check logcat!");
        this.setContentView(tv);
        Log.d(this, "Log without @DebugLog and non null caller object");
        Log.d(null, "Log without @DebugLog and null caller object");
        this.test("aaaaa", "bbbbbb", 33333);
        /*
        this.printArgs("The", "Quick", "Brown", "Fox");

        Log.i("Fibonacci", "fibonacci's 4th number is " + this.fibonacci(4));

        Greeter greeter = new Greeter("Jake");
        Log.d("Greeting", greeter.sayHello());

        Charmer charmer = new Charmer("Jake");
        Log.d("Charming", charmer.askHowAreYou());

        this.startSleepyThread();*/
    }

    @DebugLog
    private void test(String a, String b, int c)
    {
        Log.d(this, "Log with @DebugLog, non null call");
        Log.d(null, "Log with @DebugLog, null call");
    }

    @DebugLog
    private void printArgs(String... args) {
        for (String arg : args) {
            Log.i("Args", arg);
        }
    }

    @DebugLog
    private int fibonacci(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be greater than zero.");
        }
        if (number == 1 || number == 2) {
            return 1;
        }
        // NOTE: Don't ever do this. Use the iterative approach!
        return this.fibonacci(number - 1) + this.fibonacci(number - 2);
    }

    private void startSleepyThread() {
        new Thread(new Runnable() {
            private static final long SOME_POINTLESS_AMOUNT_OF_TIME = 50;

            @Override public void run() {
                sleepyMethod(SOME_POINTLESS_AMOUNT_OF_TIME);
            }

            @DebugLog
            private void sleepyMethod(long milliseconds) {
                SystemClock.sleep(milliseconds);
            }
        }, "I'm a lazy thr.. bah! whatever!").start();
    }

    static class Greeter {
        private final String name;

        @DebugLog
        Greeter(String name) {
            this.name = name;
        }

        @DebugLog
        public String sayHello() {
            return "Hello, " + this.name;
        }
    }

    @DebugLog
    static class Charmer {
        private final String name;

        Charmer(String name) {
            this.name = name;
        }

        public String askHowAreYou() {
            return "How are you " + this.name + "?";
        }
    }
}
