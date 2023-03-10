package com.rnappfragment

import android.util.Log
import android.view.Choreographer
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.viewmanagers.RTNCenteredTextManagerDelegate
import com.facebook.react.viewmanagers.RTNCenteredTextManagerInterface

class CenteredTextManager(private val context: ReactApplicationContext) :
    ViewGroupManager<FrameLayout>() {

    //    private val mDelegate: ViewManagerDelegate<CenteredText>
//
//    init {
//        mDelegate = RTNCenteredTextManagerDelegate(this)
//    }
//
//    override fun getDelegate(): ViewManagerDelegate<CenteredText> {
//        return mDelegate
//    }
    private val create = "create"
    private var propWidth: Int? = null
    private var propHeight: Int? = null

    override fun getName(): String {
        return NAME
    }

    override fun createViewInstance(reactContext: ThemedReactContext) = FrameLayout(reactContext)

    companion object {
        const val NAME = "RTNCenteredText"
        private const val COMMAND_CREATE = 1
    }

    override fun getCommandsMap() = mapOf(create to COMMAND_CREATE)

    override fun receiveCommand(root: FrameLayout, commandId: String?, args: ReadableArray?) {
        super.receiveCommand(root, commandId, args)
        val reactNativeViewId = requireNotNull(args).getInt(0)
        Log.i("argumentsare", args.toString())

        when (commandId?.toInt()) {
            COMMAND_CREATE -> createFragment(root, reactNativeViewId)
        }
    }

    private fun setupLayout(view: View) {
        Choreographer.getInstance().postFrameCallback(object : Choreographer.FrameCallback {
            override fun doFrame(frameTimeNanos: Long) {
                manuallyLayoutChildren(view)
                view.viewTreeObserver.dispatchOnGlobalLayout()
                Choreographer.getInstance().postFrameCallback(this)
            }
        })
    }

    private fun manuallyLayoutChildren(view: View) {
        // propWidth and propHeight coming from react-native props
        val width = requireNotNull(propWidth)
        val height = requireNotNull(propHeight)

        view.measure(
            View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
        )

        view.layout(0, 0, width, height)
    }

    private fun createFragment(root: FrameLayout, reactNativeViewId: Int) {
        val parentView = root.findViewById<ViewGroup>(reactNativeViewId)
        setupLayout(parentView)

        val myFragment = CenteredTextFragment()
        val activity = context.currentActivity as FragmentActivity
        activity.supportFragmentManager
            .beginTransaction()
            .replace(reactNativeViewId, myFragment, reactNativeViewId.toString())
            .commit()
    }


//    override fun createViewInstance(p0: ThemedReactContext): CenteredText {
//        return CenteredText(context)
//    }

//    override fun setText(view: FrameLayout?, value: String?) {
//        TODO("Not yet implemented")
//    }


//    @ReactProp(name = "text")
//    override fun setText(view: CenteredText?, value: String?) {
//        view!!.configureText(value!!)
//    }
}