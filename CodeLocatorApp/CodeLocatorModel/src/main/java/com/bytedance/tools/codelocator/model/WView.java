package com.bytedance.tools.codelocator.model;

import com.bytedance.tools.codelocator.utils.CodeLocatorUtils;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class WView implements Serializable {

    public interface Type {
        int TYPE_NORMAL = 0;

        int TYPE_TEXT = 1;

        int TYPE_IMAGE = 2;

        int TYPE_LINEAR = 3;

        int TYPE_FRAME = 4;

        int TYPE_RELATIVE = 5;
    }

    public static final int NO_ID = -1;

    private transient WActivity mActivity;

    private transient WView mParentView;

    private transient int mIndexInParent = -1;

    private transient WFragment mFragment;

    private transient Object mImage;

    private transient Object mScaleImage;

    @SerializedName("a")
    private List<WView> mChildren;

    @SerializedName("b")
    private int mTopOffset;

    @SerializedName("c")
    private int mLeftOffset;

    @SerializedName("d")
    private int mLeft;
    @SerializedName("e")
    private int mRight;
    @SerializedName("f")
    private int mTop;
    @SerializedName("g")
    private int mBottom;

    @SerializedName("h")
    private int mScrollX;
    @SerializedName("i")
    private int mScrollY;
    @SerializedName("j")
    private float mScaleX;
    @SerializedName("k")
    private float mScaleY;

    @SerializedName("l")
    private float mTranslationX;
    @SerializedName("m")
    private float mTranslationY;

    @SerializedName("n")
    private int mDrawTop;
    @SerializedName("o")
    private int mDrawBottom;
    @SerializedName("p")
    private int mDrawLeft;
    @SerializedName("q")
    private int mDrawRight;

    @SerializedName("r")
    private int mPaddingTop;
    @SerializedName("s")
    private int mPaddingBottom;
    @SerializedName("t")
    private int mPaddingLeft;
    @SerializedName("u")
    private int mPaddingRight;

    @SerializedName("v")
    private int mMarginTop;
    @SerializedName("w")
    private int mMarginBottom;
    @SerializedName("x")
    private int mMarginLeft;
    @SerializedName("y")
    private int mMarginRight;

    @SerializedName("z")
    private int mLayoutWidth;

    @SerializedName("a0")
    private int mLayoutHeight;

    @SerializedName("a1")
    private boolean mIsClickable;

    @SerializedName("a2")
    private boolean mIsLongClickable;

    @SerializedName("a3")
    private boolean mIsFocusable;

    @SerializedName("a4")
    private boolean mIsPressed;

    @SerializedName("a5")
    private boolean mIsSelected;

    @SerializedName("a6")
    private boolean mIsFocused;

    @SerializedName("a7")
    private boolean mIsEnabled;

    @SerializedName("a8")
    private int mFlags;

    @SerializedName("a9")
    private boolean mCanProviderData;

    @SerializedName("aa")
    private int mType = Type.TYPE_NORMAL;

    @SerializedName("ab")
    private char mVisibility;

    @SerializedName("ac")
    private String mIdStr;

    @SerializedName("ad")
    private int mId;

    @SerializedName("ae")
    private float mAlpha;

    @SerializedName("af")
    private String mMemAddr;

    @SerializedName("ag")
    private String mClassName;

    @SerializedName("ah")
    private String mClickTag;

    @SerializedName("ai")
    private String mTouchTag;

    @SerializedName("aj")
    private String mFindViewByIdTag;

    @SerializedName("ak")
    private String mXmlTag;

    @SerializedName("al")
    private String mDrawableTag;

    @SerializedName("am")
    private int mScaleType;

    @SerializedName("an")
    private String mViewHolderTag;

    @SerializedName("ao")
    private String mAdapterTag;

    @SerializedName("ap")
    private String mBackgroundColor;

    @SerializedName("aq")
    private String mText;

    @SerializedName("ar")
    private String mSpan;

    @SerializedName("as")
    private String mTextColor;

    @SerializedName("at")
    private float mTextSize;

    @SerializedName("au")
    private float mSpacingAdd;

    @SerializedName("av")
    private int mLineHeight;

    @SerializedName("aw")
    private int mTextAlignment;

    @SerializedName("ax")
    private String mZIndex = "001";

    @SerializedName("ay")
    private JumpInfo mXmlJumpInfo = null;

    @SerializedName("az")
    private String mImagePath = null;

    @SerializedName("b0")
    private List<ExtraInfo> mExtraInfos;

    @SerializedName("b1")
    private float mShadowDx = 0;

    @SerializedName("b2")
    private float mShadowDy = 0;

    @SerializedName("b3")
    private float mShadowRadius = 0;

    @SerializedName("b4")
    private String mShadowColor;

    @SerializedName("df")
    private float mPivotX;

    @SerializedName("dg")
    private float mPivotY;

    private transient float mRealScaleX;

    private transient float mRealScaleY;

    private transient ViewClassInfo mViewClassInfo;

    private transient Set<String> mPinyins = null;

    private transient List<JumpInfo> mClickJumpInfo = Collections.EMPTY_LIST;

    private transient List<JumpInfo> mFindViewJumpInfo = Collections.EMPTY_LIST;

    private transient List<JumpInfo> mTouchJumpInfo = Collections.EMPTY_LIST;

    public float getRealScaleX() {
        return mRealScaleX;
    }

    public void setRealScaleX(float realScaleX) {
        this.mRealScaleX = realScaleX;
    }

    public float getRealScaleY() {
        return mRealScaleY;
    }

    public void setRealScaleY(float realScaleY) {
        this.mRealScaleY = realScaleY;
    }

    public float getPivotX() {
        return mPivotX;
    }

    public void setPivotX(float mPivotX) {
        this.mPivotX = mPivotX;
    }

    public float getPivotY() {
        return mPivotY;
    }

    public void setPivotY(float mPivotY) {
        this.mPivotY = mPivotY;
    }

    public ViewClassInfo getViewClassInfo() {
        return mViewClassInfo;
    }

    public void setViewClassInfo(ViewClassInfo mViewClassInfo) {
        this.mViewClassInfo = mViewClassInfo;
    }

    public String getSpan() {
        return mSpan;
    }

    public void setSpan(String span) {
        this.mSpan = span;
    }

    public float getShadowDx() {
        return mShadowDx;
    }

    public void setShadowDx(float shadowDx) {
        this.mShadowDx = shadowDx;
    }

    public float getShadowDy() {
        return mShadowDy;
    }

    public void setShadowDy(float shadowDy) {
        this.mShadowDy = shadowDy;
    }

    public float getShadowRadius() {
        return mShadowRadius;
    }

    public void setShadowRadius(float shadowRadius) {
        this.mShadowRadius = shadowRadius;
    }

    public String getShadowColor() {
        return mShadowColor;
    }

    public void setShadowColor(String shadowColor) {
        this.mShadowColor = shadowColor;
    }

    public int getFlags() {
        return mFlags;
    }

    public void setFlags(int mFlags) {
        this.mFlags = mFlags;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String imagePath) {
        this.mImagePath = imagePath;
    }

    public WActivity getActivity() {
        return mActivity;
    }

    public List<ExtraInfo> getExtraInfos() {
        return mExtraInfos;
    }

    public void setExtraInfos(List<ExtraInfo> mExtraInfos) {
        this.mExtraInfos = mExtraInfos;
    }

    public void setActivity(WActivity mActivity) {
        this.mActivity = mActivity;
    }

    public WView getParentView() {
        return mParentView;
    }

    public void setFragment(WFragment mFragment) {
        this.mFragment = mFragment;
    }

    public Object getImage() {
        return mImage;
    }

    public void setImage(Object mImage) {
        this.mImage = mImage;
    }

    public Object getScaleImage() {
        return mScaleImage;
    }

    public void setScaleImage(Object mScaleImage) {
        this.mScaleImage = mScaleImage;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        this.mType = type;
    }

    public int getScaleType() {
        return mScaleType;
    }

    public void setScaleType(int scaleType) {
        this.mScaleType = scaleType;
    }

    public List<WView> getChildren() {
        return mChildren;
    }

    public void setChildren(List<WView> mChildren) {
        this.mChildren = mChildren;
    }

    public int getIndexInParent() {
        return mIndexInParent;
    }

    public void setIndexInParent(int mIndexInParent) {
        this.mIndexInParent = mIndexInParent;
    }

    public int getChildCount() {
        return mChildren == null ? 0 : mChildren.size();
    }

    public WView getChildAt(int index) {
        if (mChildren == null) {
            return null;
        }
        if (index >= mChildren.size()) {
            return null;
        }
        return mChildren.get(index);
    }

    public int getTopOffset() {
        return mTopOffset;
    }

    public void setTopOffset(int mTopOffset) {
        this.mTopOffset = mTopOffset;
    }

    public int getLeftOffset() {
        return mLeftOffset;
    }

    public void setLeftOffset(int mLeftOffset) {
        this.mLeftOffset = mLeftOffset;
    }

    public int getLeft() {
        return mLeft;
    }

    public void setLeft(int mLeft) {
        this.mLeft = mLeft;
    }

    public int getRight() {
        return mRight;
    }

    public void setRight(int mRight) {
        this.mRight = mRight;
    }

    public int getTop() {
        return mTop;
    }

    public void setTop(int mTop) {
        this.mTop = mTop;
    }

    public int getBottom() {
        return mBottom;
    }

    public void setBottom(int mBottom) {
        this.mBottom = mBottom;
    }

    public int getScrollX() {
        return mScrollX;
    }

    public void setScrollX(int mScrollX) {
        this.mScrollX = mScrollX;
    }

    public float getScaleX() {
        return mScaleX;
    }

    public void setScaleX(float mScaleX) {
        this.mScaleX = mScaleX;
    }

    public float getScaleY() {
        return mScaleY;
    }

    public void setScaleY(float mScaleY) {
        this.mScaleY = mScaleY;
    }

    public int getScrollY() {
        return mScrollY;
    }

    public void setScrollY(int mScrollY) {
        this.mScrollY = mScrollY;
    }

    public float getTranslationX() {
        return mTranslationX;
    }

    public void setTranslationX(float mTranslationX) {
        this.mTranslationX = mTranslationX;
    }

    public float getTranslationY() {
        return mTranslationY;
    }

    public void setTranslationY(float mTranslationY) {
        this.mTranslationY = mTranslationY;
    }

    public int getDrawTop() {
        return mDrawTop;
    }

    public void setDrawTop(int mDrawTop) {
        this.mDrawTop = mDrawTop;
    }

    public int getDrawBottom() {
        return mDrawBottom;
    }

    public void setDrawBottom(int mDrawBottom) {
        this.mDrawBottom = mDrawBottom;
    }

    public int getDrawLeft() {
        return mDrawLeft;
    }

    public void setDrawLeft(int mDrawLeft) {
        this.mDrawLeft = mDrawLeft;
    }

    public String getDrawableTag() {
        return mDrawableTag;
    }

    public void setDrawableTag(String drawableTag) {
        this.mDrawableTag = drawableTag;
    }

    public int getDrawRight() {
        return mDrawRight;
    }

    public void setDrawRight(int mDrawRight) {
        this.mDrawRight = mDrawRight;
    }

    public int getPaddingTop() {
        return mPaddingTop;
    }

    public void setPaddingTop(int mPaddingTop) {
        this.mPaddingTop = mPaddingTop;
    }

    public int getPaddingBottom() {
        return mPaddingBottom;
    }

    public void setPaddingBottom(int mPaddingBottom) {
        this.mPaddingBottom = mPaddingBottom;
    }

    public int getPaddingLeft() {
        return mPaddingLeft;
    }

    public void setPaddingLeft(int mPaddingLeft) {
        this.mPaddingLeft = mPaddingLeft;
    }

    public int getPaddingRight() {
        return mPaddingRight;
    }

    public void setPaddingRight(int mPaddingRight) {
        this.mPaddingRight = mPaddingRight;
    }

    public int getMarginTop() {
        return mMarginTop;
    }

    public void setMarginTop(int mMarginTop) {
        this.mMarginTop = mMarginTop;
    }

    public int getMarginBottom() {
        return mMarginBottom;
    }

    public void setMarginBottom(int mMarginBottom) {
        this.mMarginBottom = mMarginBottom;
    }

    public int getMarginLeft() {
        return mMarginLeft;
    }

    public void setMarginLeft(int mMarginLeft) {
        this.mMarginLeft = mMarginLeft;
    }

    public int getMarginRight() {
        return mMarginRight;
    }

    public void setMarginRight(int mMarginRight) {
        this.mMarginRight = mMarginRight;
    }

    public int getLayoutWidth() {
        return mLayoutWidth;
    }

    public void setLayoutWidth(int mLayoutWidth) {
        this.mLayoutWidth = mLayoutWidth;
    }

    public int getLayoutHeight() {
        return mLayoutHeight;
    }

    public void setLayoutHeight(int mLayoutHeight) {
        this.mLayoutHeight = mLayoutHeight;
    }

    public boolean isLongClickable() {
        return mIsLongClickable;
    }

    public void setLongClickable(boolean mIsLongClickable) {
        this.mIsLongClickable = mIsLongClickable;
    }

    public boolean isPressed() {
        return mIsPressed;
    }

    public void setPressed(boolean mIsPressed) {
        this.mIsPressed = mIsPressed;
    }

    public boolean isSelected() {
        return mIsSelected;
    }

    public void setSelected(boolean mIsSelected) {
        this.mIsSelected = mIsSelected;
    }

    public boolean isFocusable() {
        return mIsFocusable;
    }

    public void setFocusable(boolean mIsFocusable) {
        this.mIsFocusable = mIsFocusable;
    }

    public boolean isFocused() {
        return mIsFocused;
    }

    public void setFocused(boolean mIsFocused) {
        this.mIsFocused = mIsFocused;
    }

    public boolean isClickable() {
        return mIsClickable;
    }

    public void setClickable(boolean mIsClickable) {
        this.mIsClickable = mIsClickable;
    }

    public boolean isRealClickable() {
        if (mIsClickable) {
            return mIsClickable;
        }
        if (mParentView != null) {
            return mParentView.isRealClickable();
        }
        return false;
    }

    public boolean isEnabled() {
        return mIsEnabled;
    }

    public void setEnabled(boolean mIsEnable) {
        this.mIsEnabled = mIsEnable;
    }

    public char getVisibility() {
        return mVisibility;
    }

    public void setVisibility(char mVisibility) {
        this.mVisibility = mVisibility;
    }

    public String getIdStr() {
        return mIdStr;
    }

    public void setIdStr(String mIdStr) {
        this.mIdStr = mIdStr;
    }

    public float getAlpha() {
        return mAlpha;
    }

    public void setAlpha(float mAlpha) {
        this.mAlpha = mAlpha;
    }

    public String getMemAddr() {
        return mMemAddr;
    }

    public void setMemAddr(String memAddr) {
        this.mMemAddr = CodeLocatorUtils.formatHexStr(memAddr);
    }

    public String getClassName() {
        return mClassName;
    }

    public void setClassName(String mClassName) {
        this.mClassName = mClassName;
    }

    public String getClickTag() {
        return mClickTag;
    }

    public void setClickTag(String mClickTag) {
        this.mClickTag = mClickTag;
    }

    public String getTouchTag() {
        return mTouchTag;
    }

    public void setTouchTag(String mTouchTag) {
        this.mTouchTag = mTouchTag;
    }

    public String getFindViewByIdTag() {
        return mFindViewByIdTag;
    }

    public void setFindViewByIdTag(String mFindViewByIdTag) {
        this.mFindViewByIdTag = mFindViewByIdTag;
    }

    public String getXmlTag() {
        return mXmlTag;
    }

    public void setXmlTag(String mXmlTag) {
        this.mXmlTag = mXmlTag;
    }

    public String getViewHolderTag() {
        return mViewHolderTag;
    }

    public void setViewHolderTag(String mViewHolderTag) {
        this.mViewHolderTag = mViewHolderTag;
    }

    public String getAdapterTag() {
        return mAdapterTag;
    }

    public void setAdapterTag(String mAdapterTag) {
        this.mAdapterTag = mAdapterTag;
    }

    public String getBackgroundColor() {
        return mBackgroundColor;
    }

    public void setBackgroundColor(String mBackgroundColor) {
        this.mBackgroundColor = mBackgroundColor;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public String getTextColor() {
        return mTextColor;
    }

    public void setTextColor(String mTextColor) {
        this.mTextColor = mTextColor;
    }

    public int getLineHeight() {
        return mLineHeight;
    }

    public void setLineHeight(int mLineHeight) {
        this.mLineHeight = mLineHeight;
    }

    public float getSpacingAdd() {
        return mSpacingAdd;
    }

    public void setSpacingAdd(float mSpacingAdd) {
        this.mSpacingAdd = mSpacingAdd;
    }

    public int getTextAlignment() {
        return mTextAlignment;
    }

    public void setTextAlignment(int mTextAlignment) {
        this.mTextAlignment = mTextAlignment;
    }

    public float getTextSize() {
        return mTextSize;
    }

    public void setTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
    }

    public String getZIndex() {
        return mZIndex;
    }

    public void setZIndex(String mZIndex) {
        this.mZIndex = mZIndex;
    }

    public JumpInfo getXmlJumpInfo() {
        return mXmlJumpInfo;
    }

    public void setXmlJumpInfo(JumpInfo mXmlJumpInfo) {
        this.mXmlJumpInfo = mXmlJumpInfo;
    }

    public boolean isCanProviderData() {
        return mCanProviderData;
    }

    public void setCanProviderData(boolean mCanProviderData) {
        this.mCanProviderData = mCanProviderData;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public Set<String> getPinyins() {
        return mPinyins;
    }

    public void setPinyins(Set<String> mPinyins) {
        this.mPinyins = mPinyins;
    }

    public List<JumpInfo> getClickJumpInfo() {
        return mClickJumpInfo;
    }

    public void setClickJumpInfo(List<JumpInfo> mClickJumpInfo) {
        this.mClickJumpInfo = mClickJumpInfo;
    }

    public List<JumpInfo> getFindViewJumpInfo() {
        return mFindViewJumpInfo;
    }

    public void setFindViewJumpInfo(List<JumpInfo> mFindViewJumpInfo) {
        this.mFindViewJumpInfo = mFindViewJumpInfo;
    }

    public List<JumpInfo> getTouchJumpInfo() {
        return mTouchJumpInfo;
    }

    public void setTouchJumpInfo(List<JumpInfo> mTouchJumpInfo) {
        this.mTouchJumpInfo = mTouchJumpInfo;
    }

    public boolean contains(int x, int y) {
        return mDrawLeft <= x && mDrawRight >= x && mDrawTop <= y && mDrawBottom >= y;
    }

    public int getArea() {
        return Math.max(0, (getRealWidth() * getRealHeight()));
    }

    public WFragment getFragment() {
        if (mFragment != null) {
            return mFragment;
        }
        if (mParentView != null) {
            return mParentView.getFragment();
        }
        return null;
    }

    public WView findSameView(WView view) {
        if (view == null) {
            return null;
        }
        return findSameView(view.getMemAddr());
    }

    public WView findSameView(String viewMemAddr) {
        if (viewMemAddr == null) {
            return null;
        }
        if (viewMemAddr.equals(this.getMemAddr())) {
            return this;
        }
        for (int i = 0; i < getChildCount(); i++) {
            final WView sameView = getChildAt(i).findSameView(viewMemAddr);
            if (sameView != null) {
                return sameView;
            }
        }
        return null;
    }

    public WView findViewById(String viewId) {
        if (viewId == null) {
            return null;
        }
        if (this.getIdStr() != null && this.getIdStr().contains(viewId) && getRealVisiblity() == 'V') {
            return this;
        }
        for (int i = 0; i < getChildCount(); i++) {
            final WView sameView = getChildAt(i).findViewById(viewId);
            if (sameView != null) {
                return sameView;
            }
        }
        return null;
    }

    public WView findViewByText(String text) {
        if (text == null) {
            return null;
        }
        if (text.equals(this.getText())) {
            return this;
        }
        for (int i = 0; i < getChildCount(); i++) {
            final WView sameView = getChildAt(i).findViewByText(text);
            if (sameView != null) {
                return sameView;
            }
        }
        return null;
    }

    public char getRealVisiblity() {
        if (mParentView == null) {
            return mVisibility;
        }
        if (mVisibility == 'G') {
            return mVisibility;
        }
        if (mVisibility == 'V') {
            return mParentView.getRealVisiblity();
        }
        if (mVisibility == 'I') {
            final char realVisiblity = mParentView.getRealVisiblity();
            if (realVisiblity == 'V') {
                return mVisibility;
            }
            return realVisiblity;
        }
        return mVisibility;
    }

    public void calculateAllViewDrawInfo() {
        mRealScaleX = mScaleX;
        mRealScaleY = mScaleY;
        if (mParentView != null) {
            mDrawLeft = (int) ((mParentView.getDrawLeft() + mLeft - mParentView.getScrollX() + mTranslationX + mLeftOffset));
            mDrawTop = (int) ((mParentView.getDrawTop() + mTop - mParentView.getScrollY() + mTranslationY + mTopOffset));
            mDrawRight = (mDrawLeft + (mRight - mLeft));
            mDrawBottom = (mDrawTop + (mBottom - mTop));
        } else {
            mDrawLeft = (int) (mLeftOffset + mLeft + mTranslationX);
            mDrawRight = (int) (mLeftOffset + mRight + mTranslationX);
            mDrawTop = (int) (mTopOffset + mTop + mTranslationY);
            mDrawBottom = (int) (mTopOffset + mBottom + mTranslationY);
        }
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).calculateAllViewDrawInfo();
        }
    }

    public void adjustForScale() {
        for (int i = 0; i < getChildCount(); i++) {
            final WView child = getChildAt(i);
            child.setRealScaleX(mRealScaleX * child.getRealScaleX());
            child.setRealScaleY(mRealScaleX * child.getRealScaleY());
            child.adjustForScale();
        }
        WView view = this;
        while (view != null && (view.getRealScaleX() != 1 || view.getRealScaleY() != 1)) {
            int width = (int) ((mDrawRight - mDrawLeft) * view.getScaleX());
            int height = (int) ((mDrawBottom - mDrawTop) * view.getScaleY());
            mDrawLeft = (int) (view.getScaleX() * (mDrawLeft - view.getDrawLeft() - view.getPivotX()) + view.getDrawLeft() + view.getPivotX());
            mDrawTop = (int) (view.getScaleY() * (mDrawTop - view.getDrawTop() - view.getPivotY()) + view.getDrawTop() + view.getPivotY());
            mDrawRight = (mDrawLeft + width);
            mDrawBottom = (mDrawTop + height);
            view = view.getParentView();
        }
    }

    public int getWidth() {
        return getRight() - getLeft();
    }

    public int getHeight() {
        return getBottom() - getTop();
    }

    public int getRealWidth() {
        return getDrawRight() - getDrawLeft();
    }

    public int getRealHeight() {
        return getDrawBottom() - getDrawTop();
    }

    public String getPadding() {
        return mPaddingLeft + ", " + mPaddingTop + ", " + mPaddingRight + ", " + mPaddingBottom;
    }

    public String getMargin() {
        return mMarginLeft + ", " + mMarginTop + ", " + mMarginRight + ", " + mMarginBottom;
    }

    public String getLayout() {
        return mLayoutWidth + ", " + mLayoutHeight;
    }

    public boolean isTextView() {
        return mType == Type.TYPE_TEXT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        WView wView = (WView) o;
        return CodeLocatorUtils.equals(mMemAddr, wView.mMemAddr);
    }

    @Override
    public int hashCode() {
        return CodeLocatorUtils.hash(mMemAddr);
    }

    public boolean hasData() {
        return isCanProviderData()
            || (mParentView != null && mParentView.hasData());
    }

    private boolean isStackView() {
        return mType != Type.TYPE_LINEAR;
    }

    public void setParentView(WView parent, int indexInParent) {
        mParentView = parent;
        mIndexInParent = indexInParent;
        if (mParentView == null) {
            return;
        }
        if (!mParentView.isStackView()) {
            mZIndex = mParentView.getZIndex() + "." + formatZIndex(0);
            return;
        }
        mZIndex = mParentView.getZIndex() + "." + formatZIndex(indexInParent);
    }

    public static String formatZIndex(int i) {
        return String.format("%03d", i);
    }

}