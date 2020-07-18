package com.yourssu.anywherelibrary.presentation.library_list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yourssu.anywherelibrary.databinding.ItemLibraryMainBinding
import com.yourssu.anywherelibrary.domain.entity.SimpleLibrary
import org.jetbrains.anko.sdk27.coroutines.onClick

class LibraryListAdapter(
    private val libraryList : ArrayList<SimpleLibrary>,
    private val fragment: LibraryListFragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_ADD = 1
    private val VIEW_TYPE_GENERIC = 2

    override fun getItemViewType(position: Int): Int {
        return if (position == libraryList.size-1) {
            VIEW_TYPE_ADD
        } else {
            VIEW_TYPE_GENERIC
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return AddViewHolder(
                ItemLibraryMainBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
            ).apply {
                itemView.onClick {
                    fragment.startPostFragment()
                }
            }
        } else {
        return ViewHolder(
            ItemLibraryMainBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        ).apply {
            itemView.onClick {
                fragment.startDetailFragment()
            }
        }
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
        private val binding : ItemLibraryMainBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindHolder(itemLibrary: SimpleLibrary) {
            binding.itemCount.text = itemLibrary.totalPersonnel.toString()
            binding.itemLocate.text = itemLibrary.name
        }
    }

    // a library list for add item
    inner class AddViewHolder(
        private val binding : ItemLibraryMainBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindHolder(itemLibrary: SimpleLibrary) {
            binding.itemCount.text = "new"
            binding.itemLocate.text = "add"
        }
    }
}