package com.xds.UI;

import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Scott Wanninger on 3/12/2018.
 */
@Service
public class TextPaneServiceImpl implements TextPaneService {
    private CopyOnWriteArrayList<JTextPane> panes;

    public TextPaneServiceImpl(){

        panes = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++) {
            JTextPane p = new JTextPane();
            panes.add(p);
        }
    }

    public Iterator<JTextPane> getPanes(){
        return panes.iterator();
    }

    public Dimension getSize(Dimension dimension){
       return panes.get(0).getSize(dimension);
    }
}
