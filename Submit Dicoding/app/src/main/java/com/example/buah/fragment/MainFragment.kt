package com.example.buah.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buah.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    private lateinit var adapter: com.example.buah.adapter.ListAdapter
    private lateinit var fragmentfollowers: FragmentFollowers
    private lateinit var fragmentFollowing: FragmentFollowing

    companion object {
        private const val Anumber = "section_number"
        const val Anama = "extra_username"

        fun newInstance(index: Int): MainFragment {
            val fragment = MainFragment()
            val bundle = Bundle()
            bundle.putInt(Anumber, index)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        var index = 1
        if (arguments != null) {
            index = arguments?.getInt(Anama, 0) as Int
        }

        when (index) {
            1 -> {
                tampilanlayout()
                getFollowers()
            }
            else -> {
                tampilanlayout()
                getFollowing()
            }
        }
    }

    private fun tampilanlayout() {
        adapter = com.example.buah.adapter.ListAdapter()
        recyler_follow.layoutManager = LinearLayoutManager(context)
        recyler_follow.adapter = adapter
        recyler_follow.setHasFixedSize(true)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            loading.visibility = View.VISIBLE
        }
        else {
            loading.visibility = View.GONE
        }
    }

    private fun getFollowers() {
        val username = activity?.intent?.getStringExtra(Anama)
        fragmentfollowers = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FragmentFollowers::class.java)
        showLoading(true)
        fragmentfollowers.setFollower(username!!)
        fragmentfollowers.getFollower().observe(viewLifecycleOwner, Observer { followersItem ->
                if (followersItem != null) {
                    adapter.setData(followersItem)
                    showLoading(false)
                }
            })
    }

    private fun getFollowing() {
        val username = activity?.intent?.getStringExtra(Anama)
        fragmentFollowing = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FragmentFollowing::class.java)
        fragmentFollowing.setFollowing(username!!)
        fragmentFollowing.getFollowing()
            .observe(viewLifecycleOwner, Observer { followingItem ->
                if (followingItem != null) {
                    adapter.setData(followingItem)
                    showLoading(false)
                }
            })
    }
}
