package com.coinwind.bifeng.view;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZhuanFaCountView extends ScrollView {
    private LayoutInflater inflater;
    private LinearLayout allLayout;// 这个是所有子view的容器，scrollView内部的唯一一个ViewGroup

    public ZhuanFaCountView(Context context) {
        this(context, null);
    }

    public ZhuanFaCountView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZhuanFaCountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflater = LayoutInflater.from(context);

        // 1. 初始化allLayout
        allLayout = new LinearLayout(context);
        allLayout.setOrientation(LinearLayout.VERTICAL);
        allLayout.setBackgroundColor(Color.WHITE);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        addView(allLayout, layoutParams);


    }

    /**
     * 创建文本内容的View
     */
    private void createTextView() {

    }

    /**
     * 创建图片的View
     */
    private void createImgView() {
    }

    /**
     * 隐藏小键盘
     */
    public void hideKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(lastFocusEdit.getWindowToken(), 0);
    }


    /**
     * dp和pixel转换
     *
     * @param dipValue dp值
     * @return 像素值
     */
    public int dip2px(float dipValue) {
        float m = getContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * m + 0.5f);
    }


    /**
     * 将url拼接为 html图片标签
     * <p>
     * <p>
     * < img src="http://jyydyl.oss-cn-beijing.aliyuncs.com/20180321/ede7c7423f666d3acad264d0caa1dcc7.jpeg"style="width:100%"/>
     */

    private String toHtmlImg(String img_url) {
        //<img src="http://asdasdasd.jpg" style="width:100%">
        if (img_url != null) {
            return "<img src=\"" + img_url + "\" style=\"width:100%\"/>";
        } else {
            return null;
        }
    }

    //将String转为html标签

    /**
     * 将文本转为 html标签
     *
     * @param str
     * @return
     */
    private String str2Html(String str) {
        if (str != null) {
            SpannableString spanString = new SpannableString(str);
            String html = Html.toHtml(spanString);
            return parseUnicode2Str(html);
        } else {
            return null;
        }
    }

    //unicode转String,十进制转换
    private String parseUnicode2Str(String unicodeStr) {
        String regExp = "&#\\d*;";
        Matcher m = Pattern.compile(regExp).matcher(unicodeStr);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String s = m.group(0);
            s = s.replaceAll("(&#)|;", "");
            char c = (char) Integer.parseInt(s);
            m.appendReplacement(sb, Character.toString(c));
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
