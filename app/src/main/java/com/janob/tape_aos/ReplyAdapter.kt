package com.janob.tape_aos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ReplyAdapter(private val replies : List<Reply>) : RecyclerView.Adapter<ReplyAdapter.ReplyItemViewHolder>() {

    private val dataList = replies

    class ReplyItemViewHolder(val view : View) : RecyclerView.ViewHolder(view)
    {
        lateinit var reply : Reply
        var text = view.findViewById<TextView>(R.id.reply_text_tv)

        init{
            var delete = view.findViewById<ImageView>(R.id.reply_delete_btn)
            var edit = view.findViewById<ImageView>(R.id.reply_edit_btn)

            val pref = view.context.getSharedPreferences("reply", Context.MODE_PRIVATE)
            //댓글 삭제
            delete.setOnClickListener {
                text.text = ""

                Reply.removeReplyFromPreferences(pref, absoluteAdapterPosition)
                reply.text = ""

                Toast.makeText(it.context,"댓글이 삭제 되었음 ",Toast.LENGTH_SHORT).show()
            }

        }
        fun bind(reply : Reply){
            this.reply = reply

            text.text = reply.text

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReplyItemViewHolder {
        //item view
        val view = LayoutInflater.from(parent.context).inflate(viewType,parent,false)
        return ReplyItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ReplyItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.reply_list_item
    }
}