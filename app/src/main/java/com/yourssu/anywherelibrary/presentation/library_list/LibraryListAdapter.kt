package com.yourssu.anywherelibrary.presentation.library_list

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yourssu.anywherelibrary.R
import com.yourssu.anywherelibrary.databinding.ItemLibraryMainBinding
import com.yourssu.anywherelibrary.domain.entity.SimpleLibrary
import org.jetbrains.anko.image
import org.jetbrains.anko.sdk27.coroutines.onClick

class LibraryListAdapter(
    private val libraryList: ArrayList<SimpleLibrary>,
    private val fragment: LibraryListFragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_ADD = 1
    private val VIEW_TYPE_GENERIC = 2

    override fun getItemViewType(position: Int): Int {
        return if (position == libraryList.size - 1) {
            VIEW_TYPE_ADD
        } else {
            VIEW_TYPE_GENERIC
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return AddViewHolder(
                ItemLibraryMainBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            ).apply {
                itemView.onClick {
                    fragment.startPostFragment()
                }
            }
        } else {
            return ViewHolder(
                ItemLibraryMainBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return if (libraryList != null) libraryList!!.size else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemLibrary: SimpleLibrary = libraryList!![position]
        when (holder) {
            is ViewHolder -> {
                holder.bindHolder(itemLibrary)
            }
            else -> {
                (holder as AddViewHolder).bindHolder(itemLibrary)
            }
        }
    }

    inner class ViewHolder(
        private val binding: ItemLibraryMainBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindHolder(itemLibrary: SimpleLibrary) {
            binding.itemCount.text = "${itemLibrary.seats?.size} / ${itemLibrary.totalPersonnel}"
            binding.itemLocate.text = itemLibrary.name
            binding.itemCircle.image = binding.root.resources.getDrawable(R.drawable.ig_library_blue)
            if (itemLibrary.hangout != "") {
                binding.itemIcon.image = binding.root.resources.getDrawable(R.drawable.ig_hangout)
            }
            binding.root.onClick {
                if (itemLibrary.hangout != "") {
                    val builder = AlertDialog.Builder(fragment.context)
                    builder.setTitle("행아웃 사용 도서관입니다.")
                    builder.setMessage("\n1. 마이크는 끄고 참여해주세요. \n2.공부하는 모습만을 잘 비춰주세요.\n" +
                            "3.휴식하는 등 자리를 비울 때는 꼭 휴식타이머를 작동시켜주세요.\n4.채팅에서는 필요한 소통만 해주세요.")
                    val alertDialog = builder.create()
                    alertDialog.show()
                }
                fragment.startDetailFragment(itemLibrary)
            }
        }
    }

    // a library list for add item
    inner class AddViewHolder(
        private val binding: ItemLibraryMainBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindHolder(itemLibrary: SimpleLibrary) {
            binding.itemCount.text = "new"
            binding.itemLocate.text = "add"
            binding.itemCircle.image = binding.root.resources.getDrawable(R.drawable.ig_library)
            binding.itemIcon.image = binding.root.resources.getDrawable(R.drawable.ig_add)
        }
    }
}