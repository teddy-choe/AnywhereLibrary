package com.yourssu.anywherelibrary.presentation

import android.app.IntentService
import android.content.Intent
import android.os.SystemClock
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class TimerIntentService : IntentService("Timer") {
    val baseTime = 0

    override fun onHandleIntent(intent: Intent?) {
        try {
            Thread.sleep(1000)
            val intent = Intent("timer")
            intent.putExtra("timer_result", getTime())
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent)
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }

    private fun getTime(): String? { //경과된 시간 체크
        val nowTime = SystemClock.elapsedRealtime()
        //시스템이 부팅된 이후의 시간?
        val overTime: Long = nowTime - baseTime
        val m = overTime / 1000 / 60
        val s = overTime / 1000 % 60
        val ms = overTime % 1000
        return String.format("%02d:%02d:%03d", m, s, ms)
    }

}