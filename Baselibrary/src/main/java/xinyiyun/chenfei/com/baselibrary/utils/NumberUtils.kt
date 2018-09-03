package xinyiyun.chenfei.com.baselibrary.utils

import java.util.regex.Pattern

/**
 * Created by YangQiang on 2017/11/21.
 */
object NumberUtils {

    fun toDouble(value: String): Double {
        if (value == null || value.isEmpty()) {
            return 0.0
        }
        try {
            return value.toDouble()
        } catch (e: Exception) {
            return 0.0
        }
    }

    /**
     * 判断输入是否为身份证号
     * @param num
     * @return
     */
    fun isIdNum(num: String): Boolean {
        val idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])")
        //通过Pattern获得Matcher
        val idNumMatcher = idNumPattern.matcher(num)
        //判断用户输入是否为身份证号
        return if (idNumMatcher.matches()) {
            true
        } else {
            false
        }
    }

    /**
     * 是否为电话号码
     *
     * @param mobiles
     * @return
     */
    fun isMobileNO(mobiles: String): Boolean {
        val p = Pattern
                .compile("^((17[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$")
        val m = p.matcher(mobiles)
        return m.matches()
    }
}