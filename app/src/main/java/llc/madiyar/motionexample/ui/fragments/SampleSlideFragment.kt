package llc.madiyar.motionexample.ui.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import llc.madiyar.motionexample.R

/**
 * Created by ZhenisMadiyar on 05,Август,2019
 */
class SampleSlideFragment : Fragment() {
    companion object {
        fun newInstance(message: String): SampleSlideFragment {
            val f = SampleSlideFragment()
            val bdl = Bundle(1)
            bdl.putString(EXTRA_MESSAGE, message)
            f.arguments = bdl
            return f
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater.inflate(R.layout.fragment_onboarding, container, false)

        val message = arguments!!.getString(EXTRA_MESSAGE)
        view?.findViewById<TextView>(R.id.onboarding_text_title)?.text = message
        return view
    }
}