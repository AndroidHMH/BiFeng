package com.coinwind.bifeng.view;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coinwind.bifeng.R;
import com.coinwind.bifeng.app.BFApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类说明：自定义富文本编辑器
 */
@SuppressLint({"NewApi", "InflateParams"})
public class RichTextEditor extends ScrollView {
    private static final int EDIT_PADDING = 10; // edittext常规padding是10dp
    private static final int EDIT_FIRST_PADDING_TOP = 10; // 第一个EditText的paddingTop值

    private int viewTagIndex = 1; // 新生的view都会打一个tag，对每个view来说，这个tag是唯一的。
    private LinearLayout allLayout; // 这个是所有子view的容器，scrollView内部的唯一一个ViewGroup
    private LayoutInflater inflater;
    private OnKeyListener keyListener; // 所有EditText的软键盘监听器
    private OnClickListener btnListener; // 图片右上角红叉按钮监听器
    private OnFocusChangeListener focusListener; // 所有EditText的焦点监听listener
    private EditText lastFocusEdit; // 最近被聚焦的EditText
    private LayoutTransition mTransitioner; // 只在图片View添加或remove时，触发transition动画
    private int editNormalPadding = 0; //
    private int disappearingImageIndex = 0;
    private int width;
    private int height;
    private String html_content = "";
    private List<String> img_list = new ArrayList<>();

    public RichTextEditor(Context context) {
        this(context, null);
    }

    public RichTextEditor(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RichTextEditor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflater = LayoutInflater.from(context);

        // 1. 初始化allLayout
        allLayout = new LinearLayout(context);
        allLayout.setOrientation(LinearLayout.VERTICAL);
        allLayout.setBackgroundColor(Color.WHITE);
        setupLayoutTransitions();
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        addView(allLayout, layoutParams);

        // 2. 初始化键盘退格监听
        // 主要用来处理点击回删按钮时，view的一些列合并操作
        keyListener = new OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
                    EditText edit = (EditText) v;
                    onBackspacePress(edit);
                }
                return false;
            }
        };

        // 3. 图片叉掉处理
        btnListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                RelativeLayout parentView = (RelativeLayout) v.getParent();
                onImageCloseClick(parentView);
            }
        };

        focusListener = new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    lastFocusEdit = (EditText) v;
                }
            }

        };

        LinearLayout.LayoutParams firstEditParam = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        editNormalPadding = dip2px(EDIT_PADDING);
        final EditText firstEdit = createEditText("正文 文章中请勿出现敏感词汇，请勿在文" +
                        "章中出现微信、手机、邮箱等相关联系方式，" +
                        "图片需保证清晰度，否则可能会影响文章通" +
                        "过率，审核不通过。",
                dip2px(EDIT_FIRST_PADDING_TOP));

        firstEdit.setTag("first");
        firstEdit.setGravity(Gravity.AXIS_PULL_BEFORE);
        firstEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    lastFocusEdit = (EditText) v;
                    firstEdit.setHint("");
                } else {
                    if (allLayout.getChildCount() <= 1) {
                        if (hasFocus) {
                            firstEdit.setHint("");
                        } else if (firstEdit.getText().toString().equals("") && !firstEdit.hasFocus()) {
                            firstEdit.setHint("正文 文章中请勿出现敏感词汇，请勿在文" +
                                    "章中出现微信、手机、邮箱等相关联系方式，" +
                                    "图片需保证清晰度，否则可能会影响文章通" +
                                    "过率，审核不通过。");
                        } else {
                            firstEdit.setHint("");
                        }
                    } else {
                        dissplayHint();
                    }

                }
            }
        });
        firstEdit.setTextColor(context.getResources().getColor(R.color.black_333));
        firstEdit.setTextSize(16);
        firstEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString() != null && !s.toString().equals("")) {
                    firstEdit.setHint("");
                } else {
                    firstEdit.setHint("文章  正文中请勿出现敏感词汇，请勿在文" +
                            "章中出现微信、手机、邮箱等相关联系方式，" +
                            "图片需保证清晰度，否则可能会影响文章通" +
                            "过率，审核不通过。");
                }


                int index = firstEdit.getSelectionStart() - 1;
                if (index > 0) {
                    if (isEmojiCharacter(s.charAt(index))) {
                        Editable edit = firstEdit.getText();
                        edit.delete(s.length() - 2, s.length());
                        Toast.makeText(BFApplication.context.getApplicationContext(), "暂不支持输入表情", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        allLayout.addView(firstEdit, firstEditParam);
        lastFocusEdit = firstEdit;
    }

    //判断是否有表情
    public boolean isEmojiCharacter(char codePoint) {
        return !((codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 处理软键盘backSpace回退事件
     *
     * @param editTxt 光标所在的文本输入框
     */
    private void onBackspacePress(EditText editTxt) {
        int startSelection = editTxt.getSelectionStart();
        // 只有在光标已经顶到文本输入框的最前方，在判定是否删除之前的图片，或两个View合并
        if (startSelection == 0) {
            int editIndex = allLayout.indexOfChild(editTxt);
            View preView = allLayout.getChildAt(editIndex - 1); // 如果editIndex-1<0,
            // 则返回的是null
            if (null != preView) {
                if (preView instanceof RelativeLayout) {
                    // 光标EditText的上一个view对应的是图片
                    onImageCloseClick(preView);
                } else if (preView instanceof EditText) {
                    // 光标EditText的上一个view对应的还是文本框EditText
                    String str1 = editTxt.getText().toString();
                    EditText preEdit = (EditText) preView;
                    String str2 = preEdit.getText().toString();

                    // 合并文本view时，不需要transition动画
                    allLayout.setLayoutTransition(null);
                    allLayout.removeView(editTxt);
                    allLayout.setLayoutTransition(mTransitioner); // 恢复transition动画

                    // 文本合并
                    preEdit.setText(str2 + str1);
                    preEdit.requestFocus();
                    preEdit.setSelection(str2.length(), str2.length());
                    lastFocusEdit = preEdit;
                }
            }
        }
        dissplayHint();

    }

    /**
     * 处理图片叉掉的点击事件
     *
     * @param view 整个image对应的relativeLayout view
     * @type 删除类型 0代表backspace删除 1代表按红叉按钮删除
     */
    private void onImageCloseClick(View view) {
        if (!mTransitioner.isRunning()) {
            disappearingImageIndex = allLayout.indexOfChild(view);
            allLayout.removeView(view);
        }
    }

    /**
     * 生成文本输入框
     */
    private EditText createEditText(String hint, int paddingTop) {
        final EditText editText = (EditText) inflater.inflate(R.layout.edit_item,
                null);
        editText.setOnKeyListener(keyListener);
        editText.setTag(viewTagIndex++);
        editText.setPadding(editNormalPadding, paddingTop, editNormalPadding, paddingTop);
        editText.setHint(hint);
        editText.setOnFocusChangeListener(focusListener);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int index = editText.getSelectionStart() - 1;
                if (index > 0) {
                    if (isEmojiCharacter(s.charAt(index))) {
                        Editable edit = editText.getText();
                        edit.delete(s.length() - 2, s.length());
                        Toast.makeText(BFApplication.context.getApplicationContext(), "暂不支持输入表情", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        return editText;
    }

    /**
     * 生成图片View
     */
    private RelativeLayout createImageLayout() {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(
                R.layout.edit_image_view_layout, null);
        layout.setTag(viewTagIndex++);
        View closeView = layout.findViewById(R.id.image_close);
        closeView.setTag(layout.getTag());
        closeView.setOnClickListener(btnListener);
        return layout;
    }


    /**
     * 插入一张图片
     */
    public void insertImage(String imagePath) {
        String lastEditStr = lastFocusEdit.getText().toString();
        int cursorIndex = lastFocusEdit.getSelectionStart();
        String editStr1 = lastEditStr.substring(0, cursorIndex).trim();
        int lastEditIndex = allLayout.indexOfChild(lastFocusEdit);
        Log.e("RichTextEditor", "lastEditIndex:" + lastEditIndex);

        if (lastEditStr.length() == 0 || editStr1.length() == 0) {
            // 如果EditText为空，或者光标已经顶在了editText的最前面，则直接插入图片，并且EditText下移即可
            addEditTextAtIndex(lastEditIndex, "");
            addImageViewAtIndex(lastEditIndex + 1, imagePath);
        } else {
            // 如果EditText非空且光标不在最顶端，则需要添加新的imageView和EditText
            lastFocusEdit.setText(editStr1);
            String editStr2 = lastEditStr.substring(cursorIndex).trim();
//            if (allLayout.getChildCount() - 1 == lastEditIndex
//                    || editStr2.length() > 0) {
            addEditTextAtIndex(lastEditIndex + 1, editStr2);
//            }

            addImageViewAtIndex(lastEditIndex + 1, imagePath);
            lastFocusEdit.requestFocus();
            lastFocusEdit.setSelection(editStr1.length(), editStr1.length());
        }
        dissplayHint();
        hideKeyBoard();
    }

    /**
     * 隐藏小键盘
     */
    public void hideKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(lastFocusEdit.getWindowToken(), 0);
    }

    /**
     * 在特定位置插入EditText
     *
     * @param index   位置
     * @param editStr EditText显示的文字
     */
    private void addEditTextAtIndex(final int index, String editStr) {
        EditText editText2 = createEditText("", 10);
        editText2.setText(editStr);
        editText2.setTextColor(this.getResources().getColor(R.color.black_333));


        // 请注意此处，EditText添加、或删除不触动Transition动画
        allLayout.setLayoutTransition(null);
        allLayout.addView(editText2, index);
        allLayout.setLayoutTransition(mTransitioner); // remove之后恢复transition动画
    }

    /**
     * 显示或者隐藏hint
     */
    private void dissplayHint() {

        if (allLayout.getChildCount() <= 1) {//如果子view只有一个，那必定是edittext，然后就直接判断这个view是不是edittext，是的话就对他做焦点监听
            View childAt = allLayout.getChildAt(0);
            if (childAt instanceof EditText) {
                final EditText editText = (EditText) childAt;
                if (editText.getTag() != "first") {
                    editText.setOnFocusChangeListener(new OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (hasFocus) {
                                lastFocusEdit = (EditText) v;
                                editText.setHint("");
                            } else if (editText.getText().toString().equals("") && !editText.hasFocus()) {
                                editText.setHint("正文 文章中请勿出现敏感词汇，请勿在文" +
                                        "章中出现微信、手机、邮箱等相关联系方式，" +
                                        "图片需保证清晰度，否则可能会影响文章通" +
                                        "过率，审核不通过。");
                            } else {
                                editText.setHint("");
                            }
                        }
                    });

                }


            }

        } else {//如果子view大于1，那么肯定会有imageview，然后遍历第一个edittext，并对他做焦点监听
            for (int i = 0; i < allLayout.getChildCount(); i++) {
                View childAt = allLayout.getChildAt(0);
                if (childAt instanceof EditText) {
                    final EditText editText = (EditText) childAt;
                    editText.setHint("");
                }

            }
        }


    }

    /**
     * 在特定位置添加ImageView
     */
    private void addImageViewAtIndex(final int index,
                                     String imageurl) {
        final RelativeLayout imageLayout = createImageLayout();
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.edit_imageView);
        Log.e("RichTextEditor", imageurl);
        Glide.with(BFApplication.context.getApplicationContext())
                .load(imageurl)
                .into(imageView);
        //将url设为tag，方便取数据
        imageView.setTag(imageurl + "");
        // onActivityResult无法触发动画，此处post处理
        allLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                allLayout.addView(imageLayout, index);
            }
        }, 200);
    }

    /**
     * 根据view的宽度，动态缩放bitmap尺寸
     *
     * @param width view的宽度
     */
    private Bitmap getScaledBitmap(String filePath, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        int sampleSize = options.outWidth > width ? options.outWidth / width
                + 1 : 1;
        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 初始化transition动画
     */
    private void setupLayoutTransitions() {
        mTransitioner = new LayoutTransition();
        allLayout.setLayoutTransition(mTransitioner);
        mTransitioner.addTransitionListener(new LayoutTransition.TransitionListener() {

            @Override
            public void startTransition(LayoutTransition transition,
                                        ViewGroup container, View view, int transitionType) {

            }

            @Override
            public void endTransition(LayoutTransition transition,
                                      ViewGroup container, View view, int transitionType) {
                if (!transition.isRunning()
                        && transitionType == LayoutTransition.CHANGE_DISAPPEARING) {
                    // transition动画结束，合并EditText
                }
            }
        });
        mTransitioner.setDuration(300);
    }

    /**
     * 图片删除的时候，如果上下方都是EditText，则合并处理
     */
    private void mergeEditText() {
        View preView = allLayout.getChildAt(disappearingImageIndex - 1);
        View nextView = allLayout.getChildAt(disappearingImageIndex);
        if (preView != null && preView instanceof EditText && null != nextView
                && nextView instanceof EditText) {
            Log.d("LeiTest", "合并EditText");
            EditText preEdit = (EditText) preView;
            EditText nextEdit = (EditText) nextView;
            String str1 = preEdit.getText().toString();
            String str2 = nextEdit.getText().toString();
            String mergeText = "";
            if (str2.length() > 0) {
                mergeText = str1 + "\n" + str2;
            } else {
                mergeText = str1;
            }

            allLayout.setLayoutTransition(null);
            allLayout.removeView(nextEdit);
            preEdit.setText(mergeText);
            preEdit.requestFocus();
            preEdit.setSelection(str1.length(), str1.length());
            allLayout.setLayoutTransition(mTransitioner);
        }
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
     * 对外提供的接口, 生成编辑数据上传
     */
    public List<EditData> buildEditData() {
        html_content = "";
        List<EditData> dataList = new ArrayList<EditData>();
        int num = allLayout.getChildCount();
        for (int index = 0; index < num; index++) {
            View itemView = allLayout.getChildAt(index);
            EditData itemData = new EditData();
            if (itemView instanceof EditText) {
                EditText item = (EditText) itemView;
                if (!item.getText().toString().trim().equals("")) {
                    itemData.setInputStr(item.getText().toString().trim());
                    html_content = html_content + strtoHtml(item.getText().toString());
                }
            } else if (itemView instanceof RelativeLayout) {
                ImageView item = (ImageView) itemView
                        .findViewById(R.id.edit_imageView);
                String tag = (String) item.getTag();
                img_list.add(tag);
                html_content = html_content + toHtmlImg(tag);
                itemData.setImagePath(tag);
            }

            dataList.add(itemData);

        }

        return dataList;
    }


    /**
     * 将url拼接为 html图片标签
     * <p>
     * <p>
     * < img src="http://jyydyl.oss-cn-beijing.aliyuncs.com/20180321/ede7c7423f666d3acad264d0caa1dcc7.jpeg"/>
     */

    private String toHtmlImg(String img_url) {
        if (img_url != null) {
            return "<img src=" + "\"" + img_url + "\"" + "/" + ">";

        } else {
            return null;
        }


    }

    /**
     * 对外提供方法，遍历控件中的imageview数量
     */
    public int getImgcount() {

        int childCount = allLayout.getChildCount();
        int count = 0;
        for (int index = 0; index < childCount; index++) {
            View itemView = allLayout.getChildAt(index);

            if (itemView instanceof RelativeLayout) {
                count++;
            }
        }
        return count;

    }

    //将String转为html标签
    private String strtoHtml(String str) {
        if (str != null) {
            SpannableString spanString = new SpannableString(str);
            String html = Html.toHtml(spanString);
            return parseUnicodeToStr(html);
        } else {
            return null;
        }


    }

    //unicode转String,十进制转换
    private String parseUnicodeToStr(String unicodeStr) {
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

    //得到所有图片的url
    public List<String> getImglist() {
        buildEditData();
        return img_list;
    }

    /**
     * 输出html代码数据
     */
    public String getHtmlContent() {
        buildEditData();
        return this.html_content;
    }

    public class EditData {
        String inputStr;
        String imagePath;

        public String getInputStr() {
            return inputStr;
        }

        public void setInputStr(String inputStr) {
            this.inputStr = inputStr;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

    }
}