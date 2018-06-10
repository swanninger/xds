package com.xds.services;

import com.xds.config.SwingProperties;
import lombok.Getter;
import org.springframework.stereotype.Service;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

/**
 *
 */

@Service
public class KdsStylesImpl implements KdsStyles {
    private final SwingProperties properties;

    @Getter
    private SimpleAttributeSet headTextStyle;
    @Getter
    private SimpleAttributeSet mainTextStyle;
    @Getter
    private SimpleAttributeSet sideTextStyle;
    @Getter
    private SimpleAttributeSet contTextStyle;
    @Getter
    private Integer fontSize;
    @Getter
    private int maxStringSize;

    public KdsStylesImpl(SwingProperties properties) {
        this.properties = properties;
        this.maxStringSize = properties.getMaxStringSize();
        initStyles();
    }

    private void initStyles(){
        fontSize = properties.getFontSize();

        headTextStyle = new SimpleAttributeSet();
        headTextStyle.addAttribute(StyleConstants.Foreground, Color.YELLOW);
        headTextStyle.addAttribute(StyleConstants.Background, Color.BLACK);
        headTextStyle.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);
        headTextStyle.addAttribute(StyleConstants.FontSize, fontSize);
        //        headTextStyle.addAttribute(StyleConstants.FontFamily, "Helvetica");

        mainTextStyle = new SimpleAttributeSet();
        mainTextStyle.addAttribute(StyleConstants.Foreground, Color.BLACK);
        mainTextStyle.addAttribute(StyleConstants.Background, new Color(81, 177, 220));
        mainTextStyle.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);
        mainTextStyle.addAttribute(StyleConstants.FontSize, fontSize);
        mainTextStyle.addAttribute(StyleConstants.FontFamily, "Helvetica");

        sideTextStyle = new SimpleAttributeSet();
        sideTextStyle.addAttribute(StyleConstants.Foreground, Color.WHITE);
        sideTextStyle.addAttribute(StyleConstants.Background, Color.BLACK);
        sideTextStyle.addAttribute(StyleConstants.FontSize, fontSize);
        sideTextStyle.addAttribute(StyleConstants.FontFamily, "Helvetica");

        contTextStyle = new SimpleAttributeSet();
        contTextStyle.addAttribute(StyleConstants.Foreground, Color.RED);
        contTextStyle.addAttribute(StyleConstants.Background, Color.BLACK);
        contTextStyle.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);
        contTextStyle.addAttribute(StyleConstants.FontSize, 18);
        contTextStyle.addAttribute(StyleConstants.FontFamily, "Helvetica");
    }
}
