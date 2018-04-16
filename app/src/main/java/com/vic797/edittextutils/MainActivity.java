package com.vic797.edittextutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Toast;

import com.vic797.syntaxhighlight.LineCountLayout;
import com.vic797.syntaxhighlight.SyntaxHighlighter;
import com.vic797.syntaxhighlight.SyntaxListener;

public class MainActivity extends AppCompatActivity implements SyntaxListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LineCountLayout layout = findViewById(R.id.line_layout);
        SyntaxHighlighter editText = findViewById(R.id.edit_text);
        editText.addSyntax(getAssets(), "java.json");
        layout.attachEditText(editText);
        editText.startHighlight(false);
    }

    @SuppressWarnings("unused")
    private String longJavaCode() {
        return  "import android.content.Context;\n" +
                "import android.content.res.TypedArray;\n" +
                "import android.graphics.Canvas;\n" +
                "import android.graphics.Color;\n" +
                "import android.graphics.Paint;\n" +
                "import android.graphics.Rect;\n" +
                "import android.os.Build;\n" +
                "import android.support.annotation.ColorInt;\n" +
                "import android.support.annotation.NonNull;\n" +
                "import android.support.annotation.Nullable;\n" +
                "import android.text.Editable;\n" +
                "import android.text.Layout;\n" +
                "import android.text.TextWatcher;\n" +
                "import android.util.AttributeSet;\n" +
                "import android.widget.EditText;\n" +
                "import android.widget.ScrollView;\n" +
                "\n" +
                "public class LineCountLayout extends ScrollView {\n" +
                "\n" +
                "    private EditText editText;\n" +
                "    private Paint paint, numberLine;\n" +
                "    private Rect rect;\n" +
                "    private @ColorInt int textColor, stripColor;\n" +
                "    private float textSize;\n" +
                "\n" +
                "    private TextWatcher textWatcher = new TextWatcher() {\n" +
                "        @Override\n" +
                "        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        public void afterTextChanged(Editable editable) {\n" +
                "            invalidate();\n" +
                "        }\n" +
                "    };\n" +
                "\n" +
                "    public LineCountLayout(Context context) {\n" +
                "        this(context, null);\n" +
                "    }\n" +
                "\n" +
                "    public LineCountLayout(Context context, @Nullable AttributeSet attrs) {\n" +
                "        this(context, attrs, 0);\n" +
                "    }\n" +
                "\n" +
                "    public LineCountLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {\n" +
                "        super(context, attrs, defStyleAttr);\n" +
                "        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LineCountLayout);\n" +
                "        try {\n" +
                "            textColor = array.getColor(R.styleable.LineCountLayout_numberColor, Color.BLACK);\n" +
                "            stripColor = array.getColor(R.styleable.LineCountLayout_numberColor, Color.parseColor(\"#e0e0e0\"));\n" +
                "            textSize = array.getDimension(R.styleable.LineCountLayout_numberSize, 12.0f);\n" +
                "        } finally {\n" +
                "            array.recycle();\n" +
                "        }\n" +
                "        paint = new Paint();\n" +
                "        rect = new Rect();\n" +
                "\n" +
                "        paint.setStyle(Paint.Style.FILL);\n" +
                "        paint.setTextSize(textSize);\n" +
                "        paint.setColor(textColor);\n" +
                "\n" +
                "        numberLine = new Paint();\n" +
                "        numberLine.setStyle(Paint.Style.FILL);\n" +
                "        numberLine.setColor(stripColor);\n" +
                "\n" +
                "        update();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Set the color of the text\n" +
                "     * @param color a color\n" +
                "     */\n" +
                "    public void setTextColor(@ColorInt int color) {\n" +
                "        this.textColor = color;\n" +
                "        paint.setColor(textColor);\n" +
                "        invalidate();\n" +
                "    }\n" +
                "\n" +
                "    public void setStripColor(@ColorInt int color) {\n" +
                "        this.stripColor = color;\n" +
                "        numberLine.setColor(stripColor);\n" +
                "        invalidate();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Sets the size of the text\n" +
                "     * @param size size of the text\n" +
                "     */\n" +
                "    public void setTextSize(float size) {\n" +
                "        this.textSize = size;\n" +
                "        paint.setTextSize(textSize);\n" +
                "        invalidate();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Returns the background color of the line numbers\n" +
                "     */\n" +
                "    @ColorInt\n" +
                "    public int getStripColor() {\n" +
                "        return stripColor;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Returns the text color of the line numbers\n" +
                "     */\n" +
                "    @ColorInt\n" +
                "    public int getTextColor() {\n" +
                "        return textColor;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Returns the size of the text\n" +
                "     */\n" +
                "    public float getTextSize() {\n" +
                "        return textSize;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Attach a {@link EditText} to the layout\n" +
                "     * @param edit an {@link EditText} contained in the layout\n" +
                "     */\n" +
                "    public void attachEditText(@NonNull EditText edit) {\n" +
                "        editText = edit;\n" +
                "        editText.addTextChangedListener(textWatcher);\n" +
                "        update();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Update the layout\n" +
                "     */\n" +
                "    public void update() {\n" +
                "        invalidate();\n" +
                "        if (paint != null) {\n" +
                "            float width = paint.measureText(\"  \" + (editText == null ? \"1\" : editText.getLineCount()));\n" +
                "            if (Build.VERSION.SDK_INT < 17)\n" +
                "                setPadding((int) width, 0, 0, 0);\n" +
                "            else\n" +
                "                setPaddingRelative((int) width, 0, 0, 0);\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected void onDraw(Canvas canvas) {\n" +
                "        float width = paint.measureText(\"  \" + (editText == null ? \"1\" : editText.getLineCount()));\n" +
                "        canvas.drawRect(0, 0, (int) width, editText.getBottom(), numberLine);\n" +
                "        if (editText != null) {\n" +
                "            int line = editText.getBaseline();\n" +
                "            int[] lines = getVisibleRegion();\n" +
                "            for (int i = lines[0]; i <= lines[1]; i++) {\n" +
                "                canvas.drawText(\"\" + (i + 1), (rect.left + 5), line, paint);\n" +
                "                line += editText.getLineHeight();\n" +
                "            }\n" +
                "            update();\n" +
                "        }\n" +
                "        super.onDraw(canvas);\n" +
                "    }\n" +
                "\n" +
                "    @NonNull\n" +
                "    private int[] getVisibleRegion() {\n" +
                "        int height = editText.getHeight();\n" +
                "        int scrollY = editText.getScrollY();\n" +
                "        Layout layout = editText.getLayout();\n" +
                "        int firstVisibleLineNumber = layout.getLineForVertical(scrollY);\n" +
                "        int lastVisibleLineNumber  = layout.getLineForVertical(scrollY+height);\n" +
                "        return new int[] {firstVisibleLineNumber, lastVisibleLineNumber};\n" +
                "    }\n" +
                "}\n";
    }

    @Override
    public void onLineClick(Editable editable, String text, int line) {
        //Capture click in a line
    }

    @Override
    public void onHighlightStart(Editable editable) {
        //When the highlight process starts
    }

    @Override
    public void onHighlightEnd(Editable editable) {
        //When the highlight process ends
    }

    @Override
    public void onError(Exception e) {
        //When an Exception occurs
        e.printStackTrace();
        Toast.makeText(this, "An error has occurred!", Toast.LENGTH_SHORT).show();
    }
}
