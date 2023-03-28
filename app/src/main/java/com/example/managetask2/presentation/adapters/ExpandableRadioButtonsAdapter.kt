package com.example.managetask2.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.managetask2.presentation.component_data.RadioButtonsData
import com.example.managetask2.databinding.RadioButtonGroupBinding
import com.example.managetask2.databinding.RadioButtonItemBinding

class ExpandableRadioButtonsAdapter(val context: Context, val buttonList: List<RadioButtonsData>) :
    BaseExpandableListAdapter() {
    lateinit var groupBinding: RadioButtonGroupBinding
    lateinit var itemBinding: RadioButtonItemBinding

    override fun getGroupCount(): Int {
        return buttonList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return buttonList[groupPosition].buttons.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return buttonList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return buttonList[groupPosition].buttons[childPosition]
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
        viewGroup: ViewGroup?
    ): View {
        groupBinding = RadioButtonGroupBinding.inflate(LayoutInflater.from(context))
        val item = buttonList[groupPosition]
        val titleView = groupBinding.tvRepeatType
        titleView.text = item.title

        var icon = groupBinding.ciRepeat
        icon.setImageResource(item.icon)
        return groupBinding.root
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        view: View?,
        parent: ViewGroup?
    ): View {
        var view = view
        val holder: ItemViewHolder
        if (view == null) {
            itemBinding = RadioButtonItemBinding.inflate(LayoutInflater.from(context))
            view = itemBinding.root
            holder = ItemViewHolder()
            holder.label = itemBinding.rbRepeatType
            view.tag = holder
        } else {
            holder = view.tag as ItemViewHolder
        }

        val expandedListOfButton = getChild(groupPosition, childPosition)
        holder.label!!.text = expandedListOfButton.toString()
        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    inner class ItemViewHolder {
        internal var label: TextView? = null
    }
}