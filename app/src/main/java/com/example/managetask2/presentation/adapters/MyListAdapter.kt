package com.example.managetask2.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.managetask2.presentation.component_data.ListData
import com.example.managetask2.databinding.ListItemBinding
import com.example.managetask2.databinding.MyListGroupBinding

class MyListAdapter(
    private val context: Context,
    val data: List<ListData>
) : BaseExpandableListAdapter() {
    private lateinit var itemBinding: ListItemBinding
    private lateinit var groupBinding: MyListGroupBinding
    override fun getGroupCount(): Int {
        return this.data.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return this.data[groupPosition].items.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return this.data[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return this.data[groupPosition].items[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        view: View?,
        parent: ViewGroup?
    ): View {
        groupBinding = MyListGroupBinding.inflate(LayoutInflater.from(context))
        val item = data[groupPosition]
        val title = groupBinding.tvTitle
        title.text = item.title

        var icon = groupBinding.ciGroupIcon
        icon.setImageResource(item.icon)
//        icon.circleBackgroundColor = item.backgroundColor
        icon.setCircleBackgroundColorResource(item.backgroundColor)

        return groupBinding.root
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        view: View?,
        viewGroup: ViewGroup?
    ): View {
        var view = view
        val holder: ItemViewHolder

        if (view==null){
            itemBinding = ListItemBinding.inflate(LayoutInflater.from(context))
            view = itemBinding.root
            holder = ItemViewHolder()
            holder.label = itemBinding.children
            view.tag = holder
        }else{
            holder = view.tag as ItemViewHolder
        }

        var expandedList = getChild(groupPosition, childPosition)
        holder.label!!.text = expandedList.toString()

        return view
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    inner class ItemViewHolder {
        var label: TextView? = null
    }


}