package com.xds.services;

import javax.swing.text.SimpleAttributeSet;

public interface KdsStyles {
    SimpleAttributeSet getHeadTextStyle();

    SimpleAttributeSet getMainTextStyle();

    SimpleAttributeSet getSideTextStyle();

    SimpleAttributeSet getContTextStyle();

    int getFontSize();

}
