package com.yourssu.anywherelibrary.presentation.library_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yourssu.anywherelibrary.databinding.ItemLibraryMainBinding
import com.yourssu.anywherelibrary.domain.entity.SimpleLibrary

class LibraryListAdapter(
    private val libraryList : ArrayList<SimpleLibrary>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLibraryMainBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (libraryList != null) libraryList!!.size else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemLibrary: SimpleLibrary = libraryList!![position]
        (holder as ViewHolder).bindHolder(itemLibrary)
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
        fun bindHolder(itemLibrary : SimpleLibrary) {
            with(binding) {

            }
        }
    }
}