package llc.madiyar.motionexample.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import kotlinx.android.synthetic.main.activity_onboarding.*
import llc.madiyar.motionexample.R
import llc.madiyar.motionexample.adapter.MyFragmentPagerAdapter
import llc.madiyar.motionexample.ui.fragments.SampleSlideFragment

class OnboardingActivity : AppCompatActivity(), View.OnClickListener {

    private var adapter: MyFragmentPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        setupViewPager()
        clickListeners()
    }

    private fun clickListeners() {
        onboarding_btn_next.setOnClickListener(this)
        onboarding_btn_skip.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.onboarding_btn_next -> {
                if (onboarding_view_pager?.currentItem?.plus(1)!! == adapter?.count) {
                    onboarding_view_pager?.currentItem = adapter!!.count
                } else {
                    onboarding_view_pager?.currentItem?.plus(1)?.let { onboarding_view_pager?.setCurrentItem(it) }
                }
            }
            R.id.onboarding_btn_skip -> {

            }
        }
    }

    private fun updatePage(isLast: Boolean) {
        if (isLast) {
            onboarding_btn_next_text.text = "Готово"
            onboarding_btn_skip.visibility = View.INVISIBLE
        } else {
            onboarding_btn_next_text.text = "Далее"
            onboarding_btn_skip.visibility = View.VISIBLE
        }
    }

    private fun setupViewPager() {

        adapter = MyFragmentPagerAdapter(supportFragmentManager)

        val firstFragment: SampleSlideFragment =
            SampleSlideFragment.newInstance("Добро пожаловать")
        val secondFragment: SampleSlideFragment =
            SampleSlideFragment.newInstance("Ваша карта")
        val thirdFragment: SampleSlideFragment =
            SampleSlideFragment.newInstance("NFC Pay")
        val fourFragment: SampleSlideFragment =
            SampleSlideFragment.newInstance("Платежи 0₸")
        val fiveFragment: SampleSlideFragment =
            SampleSlideFragment.newInstance("Переводы 0₸")

        adapter!!.apply {
            addFragment(firstFragment, "ONE")
            addFragment(secondFragment, "TWO")
            addFragment(thirdFragment, "THREE")
            addFragment(fourFragment, "FOUR")
            addFragment(fiveFragment, "FIVE")
        }

        onboarding_view_pager!!.adapter = adapter
        val dotsIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)
        dotsIndicator.setViewPager(onboarding_view_pager)

        onboarding_view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                updatePage(p0.plus(1) == adapter!!.count)
                onboarding_view_pager.currentItem = p0
            }
        })
    }
}
