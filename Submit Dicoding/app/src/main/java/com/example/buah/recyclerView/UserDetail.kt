package com.example.buah.recyclerView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buah.MainActivity
import com.example.buah.R
import com.example.buah.aboutMe
import com.example.buah.adapter.DetailAdapter
import com.example.buah.adapter.PenghubungAdapter
import com.example.buah.fragment.FragmentDetail
import kotlinx.android.synthetic.main.activity_user_detail.*
import kotlinx.android.synthetic.main.tabnya.*

class UserDetail : AppCompatActivity() {

    private lateinit var fragmentDetail: FragmentDetail
    private lateinit var detailAdapter: DetailAdapter

    companion object {
        const val Usernamenya = "extra_username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        detailAdapter = DetailAdapter()

        val username = intent.getStringExtra(Usernamenya)

        fragmentDetail = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FragmentDetail::class.java)

        fragmentDetail.setUserDetail(username!!)
        fragmentDetail.getDetail().observe(this, Observer { detailItemUser ->
            if (detailItemUser != null) {
                detailAdapter.setData(detailItemUser)
            }
        })
        recycler()
        toolbar()
        penghubungAdapterr()
    }

    private fun recycler() {
        detaillayout.layoutManager = LinearLayoutManager(this)
        detaillayout.adapter = detailAdapter
    }

    private fun toolbar() {
        val actionBar = supportActionBar
        actionBar!!.title = resources.getString(R.string.title_detail)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    private fun penghubungAdapterr() {
        val penghubungAdapter = PenghubungAdapter(this, supportFragmentManager)
        penghubungview.adapter = penghubungAdapter
        tabs.setupWithViewPager(penghubungview)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menudetail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.like -> {
                Toast.makeText(this,"Daftar Favorite", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_home-> {
                Toast.makeText(this,"Halaman Utama", Toast.LENGTH_SHORT).show()
                val pindah3 = Intent(this, MainActivity::class.java)
                startActivity(pindah3)
            }
            R.id.aboutme->{
                Toast.makeText(this,"About Me", Toast.LENGTH_SHORT).show() // ini buat toast tulisan setelah di klik
                val pindah = Intent(this, aboutMe::class.java) // ini buat pindah aktivity disini make fungsi intent
                startActivity(pindah)
            }

            R.id.Pengaturanbahasa ->{
                Toast.makeText(this,"Pengaturan Bahasa", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}



