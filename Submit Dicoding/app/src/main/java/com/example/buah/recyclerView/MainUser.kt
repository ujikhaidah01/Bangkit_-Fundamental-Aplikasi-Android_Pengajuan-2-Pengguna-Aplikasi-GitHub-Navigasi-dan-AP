package com.example.buah.recyclerView

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buah.MainActivity
import com.example.buah.R
import com.example.buah.aboutMe
import com.example.buah.fragment.FragmentUser
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main_user.*
import org.json.JSONObject
import java.lang.Exception

class MainUser : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var listAdapter: com.example.buah.adapter.ListAdapter
    private lateinit var fragmentUser: FragmentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_user)

        listAdapter = com.example.buah.adapter.ListAdapter()
        fragmentUser = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(FragmentUser::class.java)
        fragmentUser.getUser().observe(this, Observer { userItems ->
            if (userItems != null) {
                listAdapter.setData(userItems)
                showLoading(false)
            }
        })
        recycler()
    }

    private fun recycler() {
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = listAdapter
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            loadingProgress.visibility = View.VISIBLE
        } else {
            loadingProgress.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp(appBarConfiguration)
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_isi, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.searchView)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        //membaca teks yng diinput
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //dipanggil saat Submit ditekan
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainUser, query, Toast.LENGTH_SHORT).show()
                //data di sini
                if (query.isNotEmpty()) {
                    fragmentUser.setUser(query)
                }
                return true
            }
            // dipanggil setiap kali user memasukkan atau mengubah query yang ada pada inputan searchview
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
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
            R.id.fav ->{
                Toast.makeText(this,"  Daftar Favorite", Toast.LENGTH_SHORT).show()
//                val pindah4 = Intent(this, aboutMe::class.java) // ini buat pindah aktivity disini make fungsi intent
//                startActivity(pindah4)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}

