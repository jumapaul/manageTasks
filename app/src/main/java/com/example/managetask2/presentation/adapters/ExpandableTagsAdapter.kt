package com.example.managetask2.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.managetask2.presentation.component_data.TagsData
import com.example.managetask2.databinding.ChipsBinding
import com.example.managetask2.databinding.TagsGroupBinding

class ExpandableTagsAdapter(val context: Context, private val dataList: List<TagsData>) :
    BaseExpandableListAdapter() {

    lateinit var itemBinding: ChipsBinding
    lateinit var groupBinding: TagsGroupBinding

    override fun getGroupCount(): Int {
        return dataList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return dataList[groupPosition].chips.size
    }

    override fun getGroup(position: Int): Any {
        return dataList[position]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return dataList[groupPosition].chips[childPosition]
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

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, view: View?, parent: ViewGroup?): View {
        groupBinding = TagsGroupBinding.inflate(LayoutInflater.from(context))

        val item = dataList[groupPosition]
        val titleView = groupBinding.listTitle
        titleView.text = item.title

        var icon = groupBinding.ciTagsIcon
        item.icon?.let { icon.setImageResource(it) }

        return groupBinding.root
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        var view = convertView
        val holder: ItemViewHolder

        if (view ==null){
            itemBinding = ChipsBinding.inflate(LayoutInflater.from(context))
            view = itemBinding.root
            holder = ItemViewHolder()
            holder.label = itemBinding.cptag
            view.tag = holder
        }else{
            holder = convertView?.tag as ItemViewHolder
        }

        val expandedListText = getChild(groupPosition, childPosition)
        holder.label!!.text = expandedListText.toString()

        return view
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    inner class ItemViewHolder{
        internal var label: TextView? = null
    }
}