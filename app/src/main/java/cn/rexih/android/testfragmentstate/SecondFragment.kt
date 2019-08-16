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
 * @file SecondFragment
 * @date 2019/8/16
 * @author huangwr
 * @version %I%, %G%
 */
class SecondFragment:Fragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("rexih","SecondFragment onAttach")
    }

    override fun onPause() {
        super.onPause()
        Log.e("rexih","SecondFragment onPause")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("rexih","SecondFragment onActivityCreated")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("rexih","SecondFragment onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e("rexih","SecondFragment onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("rexih","SecondFragment onResume")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("rexih","SecondFragment onDetach")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("rexih","SecondFragment onCreateView")
        val inflate = inflater.inflate(R.layout.fragment_second, container, false)
        return inflate
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.e("rexih","SecondFragment onHiddenChanged:${hidden}")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.e("rexih","SecondFragment setUserVisibleHint:${isVisibleToUser}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("rexih","SecondFragment onDestroyView")
    }

    override fun onStop() {
        super.onStop()
        Log.e("rexih","SecondFragment onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("rexih","SecondFragment onDestroy(")
    }
}