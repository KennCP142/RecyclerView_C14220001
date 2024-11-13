package com.c14220001.recyclerview

import android.annotation.SuppressLint
import android.hardware.Camera.PictureCallback
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.squareup.picasso.Picasso

private lateinit var _rvWayang : RecyclerView

class adapterRecView (private val listWayang : ArrayList<wayang>) : RecyclerView
    .Adapter<adapterRecView.ListViewHolder> () {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var _namaWayang = itemView.findViewById<TextView>(R.id.namaWayang)
        var _karakterWayang = itemView.findViewById<TextView>(R.id.karakterWayang)
        var _deskripsiWayang = itemView.findViewById<TextView>(R.id.deskripsiWayang)
        var _gambarWayang = itemView.findViewById<ImageView>(R.id.gambarWayang)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listWayang.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var wayang = listWayang[position]
        holder._namaWayang.setText(wayang.nama)
        holder._deskripsiWayang.setText(wayang.deskripsi)
        holder._karakterWayang.setText(wayang.karakter)
        Log.d("TEST",wayang.foto)
        Picasso.get()
            .load(wayang.foto)
            .into(holder._gambarWayang)
    }
}


class MainActivity : AppCompatActivity() {
    private lateinit var _nama : Array<String>
    private lateinit var _karakter : Array<String>
    private lateinit var _deskripsi : Array<String>
    private lateinit var _gambar : Array<String>

    private var arWayang = arrayListOf<wayang>()

    fun SiapkanData() {
        _nama = resources.getStringArray(R.array.namaWayang)
        _deskripsi = resources.getStringArray(R.array.deskripsiWayang)
        _karakter = resources.getStringArray(R.array.karakterUtamaWayang)
        _gambar = resources.getStringArray(R.array.gambarWayang)
    }

    fun TambahData() {
        for (position in _nama.indices) {
            val data = wayang(
                _gambar[position],
                _nama[position],
                _karakter[position],
                _deskripsi[position]
            )
            arWayang.add(data)
        }
    }

    fun TampilkanData() {
        _rvWayang.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        _rvWayang.adapter = adapterRecView(arWayang)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        _rvWayang = findViewById<RecyclerView>(R.id.rvWayang)
        SiapkanData()
        TambahData()
        TampilkanData()
    }
}