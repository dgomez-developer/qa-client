package com.dgomez.developer.architecture.components.qa_client.presentation.view

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.dgomez.developer.architecture.components.qa_client.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), CreateQuestionFragment.OnFragmentInteractionListener,
    BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener,
    BottomNavigationView.OnNavigationItemReselectedListener {

    private val fragments = listOf(
        BaseFragment.newInstance(
            R.layout.content_questions_base,
            R.id.toolbar_questions,
            R.id.nav_host_questions_fragment
        ),
        BaseFragment.newInstance(
            R.layout.content_other_base,
            R.id.toolbar_other,
            R.id.nav_host_other_fragment
        )
    )

    // overall back stack of containers
    private val backStack = Stack<Int>()
    // map of navigation_id to container index
    private val indexToPage = mapOf(0 to R.id.questions, 1 to R.id.other)

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup main view pager
        main_view_pager.adapter = ViewPagerAdapter()

        main_view_pager.addOnPageChangeListener(this)
        bottom_nav.setOnNavigationItemSelectedListener(this)
        bottom_nav.setOnNavigationItemReselectedListener(this)

        // check deeplink only after viewPager is setup
        main_view_pager.post(this::checkDeepLink)

        // force viewPager to create all fragments
        main_view_pager.offscreenPageLimit = fragments.size

        // initialize backStack with home page index
        if (backStack.empty()) backStack.push(0)
    }

    private fun checkDeepLink() {
        fragments.forEachIndexed { index, fragment ->
            val hasDeepLink = fragment.handleDeepLink(intent)
            if (hasDeepLink) setItem(index)
        }
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        val itemId = indexToPage[position] ?: R.id.questions
        if (bottom_nav.selectedItemId != itemId)
            bottom_nav.selectedItemId = itemId
    }

    // control the backStack when back button is pressed
    override fun onBackPressed() {
        // get the current page
        val fragment = fragments[main_view_pager.currentItem]
        // check if the page navigates up
        val navigatedUp = fragment.onBackPressed()
        // if no fragments were popped
        if (!navigatedUp) {
            if (backStack.size > 1) {
                // remove current position from stack
                backStack.pop()
                // set the next item in stack as current
                main_view_pager.currentItem = backStack.peek()

            } else super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val position = indexToPage.values.indexOf(item.itemId)
        if (main_view_pager.currentItem != position) {
            setItem(position)
        }
        return true
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        val position = indexToPage.values.indexOf(item.itemId)
        val fragment = fragments[position]
        fragment.popToRoot()
    }

    private fun setItem(position: Int) {
        main_view_pager.currentItem = position
        backStack.push(position)
    }

    inner class ViewPagerAdapter : FragmentPagerAdapter(supportFragmentManager) {

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = fragments.size

    }
}
