package com.yourssu.anywherelibrary.presentation.library_list

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.yourssu.anywherelibrary.R
import com.yourssu.anywherelibrary.databinding.FragmentSeatBinding
import org.jetbrains.anko.sdk27.coroutines.onClick


class SeatFragment: Fragment() {
    private lateinit var viewModel: SeatViewModel
    private lateinit var binding : FragmentSeatBinding

    val INIT = 0 //처음
    val RUN = 1 //실행중
    val PAUSE = 2 //정지
    var timerState = INIT
    var studyTime = 0
    var breakTime = 0
    private var cnt = 1

    private var studyBaseTime: Long = 0
    private var studyPauseTime: Long = 0

    private var breakBaseTime: Long = 0
    private var breakPauseTime: Long = 0

    private var libraryId: Long = 0
    private var seatId: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (container != null) {
            setDataBinding(inflater, container)
        }
        setLiveDataObserver()
        setListenerBinding()

        binding.seatBtnStudy.setEnabled(true)
        binding.seatBtnRest.setEnabled(true)

        return binding.root
    }

    private fun setListenerBinding() {
        binding.seatBtnStudy.onClick {
            staButton()
            /*if (timerState == 3) {
                startService<TimerIntentService>()
                activity.registerReceiver()
            } else if (timerState == 4) {
                stopService<TimerIntentService>()
            }*/
        }

        binding.seatBtnRest.onClick {
            restButton()
        }

        binding.seatBtnFinish.onClick {
            libraryId = arguments?.getLong("libraryId")!!
            seatId = arguments?.getLong("seatId")!!
            viewModel.deleteSeat(
                binding.seatStudyTime.text.toString(), binding.seatBreakTime.text.toString(),
                libraryId, seatId
            )
        }
    }

    private fun setLiveDataObserver() {
        viewModel.deleteSuccess.observe(this, Observer {
            backDetailFragment()
        })
    }

    private fun setDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup
    ) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_seat, container, false)
        viewModel =
            ViewModelProviders.of(this).get(SeatViewModel::class.java)
        binding.viewModel = viewModel
    }

    private fun staButton() {
        when (timerState) {
            INIT -> {
                studyBaseTime = SystemClock.elapsedRealtime()
                handler.sendEmptyMessage(0)
                binding.seatBtnRest.setEnabled(false)
                timerState = RUN
            }
            RUN -> {
                handler.removeMessages(0)
                studyPauseTime = SystemClock.elapsedRealtime()
                binding.seatBtnRest.setEnabled(true)
                timerState = PAUSE
            }
            PAUSE -> {
                val reStart = SystemClock.elapsedRealtime()
                studyBaseTime += reStart - studyPauseTime
                handler.sendEmptyMessage(0)
                binding.seatBtnRest.setEnabled(false)
                timerState = RUN
            }
        }
    }

    private fun restButton() {
        when (timerState) {
            INIT -> {
                breakBaseTime = SystemClock.elapsedRealtime()
                restHandler.sendEmptyMessage(0)
                binding.seatBtnStudy.setEnabled(false)
                timerState = RUN
            }
            RUN -> {
                restHandler.removeMessages(0)
                breakPauseTime = SystemClock.elapsedRealtime()
                binding.seatBtnStudy.setEnabled(true)
                timerState = PAUSE
            }
            PAUSE -> {
                val reStart = SystemClock.elapsedRealtime()
                breakBaseTime += reStart - breakPauseTime
                restHandler.sendEmptyMessage(0)
                binding.seatBtnStudy.setEnabled(false)
                timerState = RUN
            }
        }
    }

    private fun getTime(): String? { //경과된 시간 체크
        val nowTime = SystemClock.elapsedRealtime()
        //시스템이 부팅된 이후의 시간?
        val overTime: Long = nowTime - studyBaseTime
        val hour = overTime / 1000 / 360
        val m = overTime / 1000 / 60
        val s = overTime / 1000 % 60
        return String.format("%02d:%02d:%02d", hour, m, s)
    }

    private fun breakGetTime(): String? { //경과된 시간 체크
        val nowTime = SystemClock.elapsedRealtime()
        //시스템이 부팅된 이후의 시간?
        val overTime: Long = nowTime - breakBaseTime
        val hour = overTime / 1000 / 360
        val m = overTime / 1000 / 60
        val s = overTime / 1000 % 60
        return String.format("%02d:%02d:%02d", hour, m, s)
    }

    var handler: Handler = object : Handler() {
        override fun handleMessage(@NonNull msg: Message?) {
            binding.seatStudyTime.text= getTime()
            this.sendEmptyMessage(0)
        }
    }

    var restHandler: Handler = object : Handler() {
        override fun handleMessage(@NonNull msg: Message?) {
            binding.seatBreakTime.text= breakGetTime()
            this.sendEmptyMessage(0)
        }
    }

    fun backDetailFragment() {
        findNavController().navigateUp()
    }
}
