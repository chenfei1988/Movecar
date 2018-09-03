package xinyiyun.chenfei.com.baselibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import xinyiyun.chenfei.com.baselibrary.R;


/**
 * Created by YangQiang on 2017/8/10.
 */

public class BadgeImage extends ImageView {
    private float mCircleSize;
    private int mTextColor;
    private int mCircleColor;
    private Paint mPaint;
    private int count;
    private RectF rectF;

    public BadgeImage(Context context) {
        this(context, null);
    }

    public BadgeImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BadgeImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.BadgeImage);
        mCircleSize = typedArray.getDimension(R.styleable.BadgeImage_radius, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics())) * 2;
        mCircleColor = typedArray.getColor(R.styleable.BadgeImage_circleColor, Color.RED);
        mTextColor = typedArray.getColor(R.styleable.BadgeImage_textColor, Color.WHITE);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        typedArray.recycle();
    }

    public void showCount(int count) {
        if (this.count != count) {
            this.count = count;
            invalidate();
        }

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.scale((getWidth() - mCircleSize * 2) / (float) getWidth(), (getHeight() - mCircleSize * 2) / (float) getHeight());
        canvas.translate(mCircleSize * 2, mCircleSize);
        super.draw(canvas);
        canvas.restore();
        drawRedCircle(canvas);
    }

    private void drawRedCircle(Canvas canvas) {
        if (count <= 0) return;
        mPaint.setColor(mCircleColor);
        float mOffset = 0;
        if (count < 10) {
            rectF = new RectF(getWidth() - mCircleSize * 2, mOffset, getWidth(), mCircleSize * 2 + mOffset);
        } else if (count < 100) {
            rectF = new RectF(getWidth() - mCircleSize * 2.5f, mOffset, getWidth(), mCircleSize * 2 + mOffset);
        } else {
            rectF = new RectF(getWidth() - mCircleSize * 3 - 5, mOffset, getWidth(), mCircleSize * 2 + mOffset);
        }
        canvas.drawRoundRect(rectF, mCircleSize, mCircleSize, mPaint);
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mCircleSize * 3 / 2);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        float baseline = rectF.bottom / 2 - fontMetrics.descent + (fontMetrics.descent - fontMetrics.ascent) / 2;
        if (count < 100) {
            canvas.drawText(String.valueOf(count), getWidth() - rectF.width() / 2f, baseline, mPaint);
        } else {
            canvas.drawText("99+", getWidth() - rectF.width() / 2f, baseline, mPaint);
        }
    }


}
