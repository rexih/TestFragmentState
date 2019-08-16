package cn.rexih.android.testfragmentstate

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


/**
 *
 * @package cn.rexih.android.testfragmentstate
 * @file MainFragment
 * @date 2019/8/16
 * @author huangwr
 * @version %I%, %G%
 */
class MainFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("rexih","MainFragment onAttach")
    }
    override fun onPause() {
        super.onPause()
        Log.e("rexih","MainFragment onPause")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("rexih","MainFragment onActivityCreated")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("rexih","MainFragment onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e("rexih","MainFragment onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("rexih","MainFragment onResume")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("rexih","MainFragment onDetach")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("rexih","MainFragment onCreateView")
        val inflate = inflater.inflate(R.layout.fragment_main, container, false)
        return inflate
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.e("rexih","MainFragment onHiddenChanged:${hidden}")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.e("rexih","MainFragment setUserVisibleHint:${isVisibleToUser}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("rexih","MainFragment onDestroyView")
    }

    override fun onStop() {
        super.onStop()
        Log.e("rexih","MainFragment onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("rexih","MainFragment onDestroy(")
    }



}