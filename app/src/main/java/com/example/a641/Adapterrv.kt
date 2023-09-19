package com.example.a641

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a641.databinding.ItemRvBinding

class Adapterrv(var onClik: OnClik): ListAdapter<Contact, Adapterrv.VH>(MyDiffUtill()) {

        inner class VH(var itemview : ItemRvBinding): RecyclerView.ViewHolder(itemview.root){

            fun onBind(user: Contact){

                itemview.contactName.text = user.phone_name
                itemview.contactNumber.text = user.phone_number.toString()
                itemview.btnMenu.setOnClickListener {
                    onClik.clickmenu(user,itemview.root)
                }
                itemview.btnCall.setOnClickListener {
                    onClik.clickcall(user)
                }

                itemview.btnSms.setOnClickListener {
                    onClik.clicksms(user)
                }
            }

        }

        class MyDiffUtill: DiffUtil.ItemCallback<Contact>(){
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return  oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return  oldItem.equals(newItem)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            return VH(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            val item = getItem(position)
            holder.onBind(item)
        }

        interface OnClik{
            fun clickmenu(contact: Contact,btn: View){

            }
            fun clickcall(contact: Contact){

            }fun clicksms(contact: Contact){

            }
        }


    }