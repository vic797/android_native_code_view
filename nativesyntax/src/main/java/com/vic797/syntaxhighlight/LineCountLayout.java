package com.vic797.syntaxhighlight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ScrollView;


public class LineCountLayout extends ScrollView {

    private EditText editText;
    private Paint paint, numberLine;
    private Rect rect;
    private @ColorInt int textColor, stripColor;
    private float textSize;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            invalidate();
        }
    };

    public LineCountLayout(Context context) {
        this(context, null);
    }

    public LineCountLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineCountLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LineCountLayout);
        try {
            textColor = array.getColor(R.styleable.LineCountLayout_numberColor, Color.BLACK);
            stripColor = array.getColor(R.styleable.LineCountLayout_stripColor, Color.parseColor("#e0e0e0"));
            textSize = array.getDimension(R.styleable.LineCountLayout_numberSize, 12.0f);
        } finally {
            array.recycle();
        }
        paint = new Paint();
        rect = new Rect();

        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(textSize);
        paint.setColor(textColor);

        numberLine = new Paint();
        numberLine.setStyle(Paint.Style.FILL);
        numberLine.setColor(stripColor);

        update();
    }

    /**
     * Set the color of the text
     * @param color a color
     */
    public void setTextColor(@ColorInt int color) {
        this.textColor = color;
        paint.setColor(textColor);
        invalidate();
    }

    /**
     * Set the background color of the strip line
     * @param color a color
     */
    public void setStripColor(@ColorInt int color) {
        this.stripColor = color;
        numberLine.setColor(stripColor);
        invalidate();
    }

    /**
     * Sets the size of the text
     * @param size size of the text
     */
    public void setTextSize(float size) {
        this.textSize = size;
        paint.setTextSize(textSize);
        invalidate();
    }

    /**
     * Returns the background color of the line numbers
     */
    @ColorInt
    public int getStripColor() {
        return stripColor;
    }

    /**
     * Returns the text color of the line numbers
     */
    @ColorInt
    public int getTextColor() {
        return textColor;
    }

    /**
     * Returns the size of the text
     */
    public float getTextSize() {
        return textSize;
    }

    /**
     * Attach a {@link EditText} to the layout
     * @param edit an {@link EditText} contained in the layout
     */
    public void attachEditText(@NonNull EditText edit) {
        editText = edit;
        editText.addTextChangedListener(textWatcher);
        update();
    }

    /**
     * Update the layout
     */
    public void update() {
        invalidate();
        if (paint != null) {
            float width = paint.measureText("  " + (editText == null ? "1" : editText.getLineCount()));
            if (Build.VERSION.SDK_INT < 17)
                setPadding((int) width, 0, 0, 0);
            else
                setPaddingRelative((int) width, 0, 0, 0);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float width = paint.measureText("  " + (editText == null ? "1" : editText.getLineCount()));
        canvas.drawRect(0, 0, (int) width, editText.getBottom(), numberLine);
        if (editText != null) {
            int line = editText.getBaseline();
            int lineCount = editText.getLineCount();
            for (int i = 0; i < lineCount; i++) {
                canvas.drawText("" + (i + 1), (rect.left + 5), line, paint);
                line += editText.getLineHeight();
            }
            update();
        }
        super.onDraw(canvas);
    }
}
