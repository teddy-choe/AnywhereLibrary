package com.yourssu.anywherelibrary.presentation.library_list

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yourssu.anywherelibrary.R
import com.yourssu.anywherelibrary.data.model.ChooseSeatResponse
import com.yourssu.anywherelibrary.databinding.ItemLibraryDetailBinding
import com.yourssu.anywherelibrary.domain.entity.SimpleSeat
import com.yourssu.anywherelibrary.domain.usecase.DeleteSeatUsecase
import com.yourssu.anywherelibrary.domain.usecase.SelectSeatUsecase
import com.yourssu.anywherelibrary.util.ConstValue.CONST_ACCESS_TOKEN
import com.yourssu.anywherelibrary.util.ConstValue.CONST_USER_ID
import com.yourssu.anywherelibrary.util.SharedPreferenceManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.internal.notify
import okhttp3.internal.notifyAll
import org.jetbrains.anko.sdk27.coroutines.onClick


class LibraryDetailAdapter(
    private val seatList : ArrayList<SimpleSeat>,
    private val totalCount : Int,
    private val fragment: LibraryDetailFragment,
    private val selectSeatUsecase : SelectSeatUsecase = SelectSeatUsecase()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_EMPTY = 1
    private val VIEW_TYPE_MINE = 2
    private val VIEW_TYPE_GENERIC = 3
    val emptyNum = totalCount - seatList.size

    override fun getItemViewType(position: Int): Int {
        val userId = SharedPreferenceManager.getLongPref(CONST_USER_ID)

        if (seatList.size <= position) {
            return VIEW_TYPE_EMPTY
        }

        return if (userId == seatList.get(position).user.id) {
            VIEW_TYPE_MINE
        } else {
            VIEW_TYPE_GENERIC
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 3) {
            return OtherViewHolder(
                ItemLibraryDetailBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
            )
        } else if (viewType == 2) {
            return MyViewHolder(
                ItemLibraryDetailBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            return EmptyViewHolder(
                ItemLibraryDetailBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return if (totalCount != 0) totalCount else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (seatList.size <= position) {
            (holder as EmptyViewHolder).bindHolder(position, this)
        } else {
            val itemSeat: SimpleSeat = seatList!![position]
            when (holder) {
                is MyViewHolder -> {
                    holder.bindHolder(itemSeat, position)
                }
                is OtherViewHolder -> {
                    holder.bindHolder(itemSeat, position)
                }
        }
        }
    }

    fun updateLibraryAdapter(updatedList: SimpleSeat) {
        seatList.add(updatedList)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(
        private val binding : ItemLibraryDetailBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindHolder(itemSeat: SimpleSeat, position: Int) {
            binding.detailCount.text = (position+1).toString()
            binding.detailTime.text = itemSeat.learningTime
            binding.detailRect.background = binding.root.resources.getDrawable(R.drawable.bg_library_mine)
            binding.root.onClick {
                fragment.startSeatFragment(itemSeat.id)
            }
        }
    }

    inner class OtherViewHolder(
        private val binding : ItemLibraryDetailBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindHolder(itemSeat: SimpleSeat, position: Int) {
            binding.detailCount.text = (position+1).toString()
            binding.detailTime.text = itemSeat.learningTime
            binding.detailRect.background = binding.root.resources.getDrawable(R.drawable.bg_library_other)
            binding.root.onClick {
                val builder = AlertDialog.Builder(fragment.context)
                builder.setTitle("${itemSeat.user.nickname} 님의 공부 시간").setMessage("공부시간 ${itemSeat.learningTime} \n휴식시간 ${itemSeat.restTime}")
                val alertDialog = builder.create()
                alertDialog.show()
            }
        }
    }

    inner class EmptyViewHolder(
        private val binding : ItemLibraryDetailBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindHolder(position: Int, adapter: LibraryDetailAdapter) {
            binding.detailCount.text = (position+1).toString()
            binding.detailRect.background = binding.root.resources.getDrawable(R.drawable.bg_library_empty)
            binding.root.onClick {
                selectSeatUsecase.selectSeat(SharedPreferenceManager.getStringPref(
                    CONST_ACCESS_TOKEN), fragment.libraryId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("SelectSeat", it.toString())
                        adapter.updateLibraryAdapter(it.seat)
                    }, {
                        Log.d("SeatSelect", it.localizedMessage)
                    })
            }
        }
    }
}