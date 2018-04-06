package com.xds.UI;

import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Scott Wanninger on 5/12/2017.
 */
@Service
public class KTextPanesImpl implements KTextPanes {
    private CopyOnWriteArrayList<JTextPane> panes;

    KTextPanesImpl(){
        panes = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            panes.add(new JTextPane());
        }
    }

    public Iterator<JTextPane> getPanes(){
        return panes.iterator();
    }

}
