package xinyiyun.chenfei.com.movecar.ui.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import kotlinx.android.synthetic.main.activity_welcome.*
import org.jetbrains.anko.startActivity
import xinyiyun.chenfei.com.baselibrary.annotation.ContentView
import xinyiyun.chenfei.com.baselibrary.basic.BaseActivity
import xinyiyun.chenfei.com.baselibrary.utils.SPreferencesUtils
import xinyiyun.chenfei.com.movecar.R

@ContentView(R.layout.activity_welcome, fullScreen = true)
class WelcomeActivity : BaseActivity() {
    override fun onViewCreated() {

        img_welcome.apply {
            scaleX = 1.5f
            scaleY = 1.5f
            animate().scaleX(1f).scaleY(1f).setDuration(4 * 1000)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            startActivity<LoginActivity>()
                            finish()
                        }
                    }).start()

        }
    }

}
