package com.example.tracingapp.ui.menu.faq

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tracingapp.R

class FaqAdapter(val faq: Array<Faq>): RecyclerView.Adapter<FaqAdapter.FaqViewHolder>() {
    class FaqViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val question = itemView.findViewById<TextView>(R.id.tv_faq_question)
        val answer = itemView.findViewById<TextView>(R.id.tv_faq_answer)

        fun bind(faq: Faq) {
            question.text = faq.question
            answer.text = faq.answer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_faq, parent, false)

        return FaqViewHolder(view)
    }

    override fun getItemCount(): Int {
        return faq.size
    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        holder.bind(faq[position])
        val icon = holder.question

        val isExpandable: Boolean = faq[position].expandable
        holder.answer.visibility = if(isExpandable) {
            icon.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_up, 0)
            View.VISIBLE
        } else {
            icon.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0)
            View.GONE
        }

        holder.question.setOnClickListener {
            val faqs = faq[position]
            faqs.expandable = !faqs.expandable
            notifyItemChanged(position)
        }
    }
}