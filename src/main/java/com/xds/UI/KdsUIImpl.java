package com.xds.UI;

import com.xds.config.SwingProperties;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 * Created by PhazedOut on 2/26/2018.
 */

@Service
public class KdsUIImpl extends JFrame implements KdsUI{

    private final SwingProperties swingProperties;
    private final TextPaneService textPaneService;
    private final TimerService timerService;

    private JLabel alert;
    private JPanel mainPanel;
    private JLabel page;

    private JLabel displayLabel;


    public KdsUIImpl(SwingProperties swingProperties, TextPaneService textPaneService, TimerService timerService){
        super();
        this.swingProperties = swingProperties;
        this.textPaneService = textPaneService;
        this.timerService = timerService;

        initComponents();
        setVisible(true);
    }

    private void initComponents(){
        setTitle("Hello");
        this.setUndecorated(true);
        setExtendedState(Frame.MAXIMIZED_BOTH);

        mainPanel = new JPanel();

        JPanel bottomPanel = new JPanel();
        displayLabel = new JLabel();
        alert = new JLabel();
        page = new JLabel();
        Box.Filler filler1 = new Box.Filler(new Dimension(0, 20), new Dimension(0, 20), new Dimension(32767, 20));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(0, 0, 0));
        setForeground(Color.black);
        setUndecorated(true);

        Color kdsGrey = new Color(99, 101, 102);

        mainPanel.setBackground(kdsGrey);
        mainPanel.setLayout(new GridLayout(2, 5, 3, 3));
        mainPanel.setFont(new Font("Helvetica", Font.PLAIN, 12));

        Font font = new Font("Helvetica", Font.PLAIN, 12);

        Iterator<JTextPane> textPaneIterator = textPaneService.getPanes();
        Iterator<JLabel> timerIterator = timerService.getTimers();

        for (int i = 0; i < 10; i++) {
            JPanel panel = new JPanel();
            JLabel name = new JLabel();
            JLabel timer = timerIterator.next();
            JTextPane text = textPaneIterator.next();


            panel.setBackground(kdsGrey);
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.setOpaque(false);
            panel.setPreferredSize(new Dimension(100, 314));

            text.setEditable(false);
            text.setBackground(Color.BLACK);
            text.setForeground(Color.WHITE);
            text.setMargin(new Insets(3, 10, 0, 10));

            name.setBackground(kdsGrey);
            name.setForeground(Color.WHITE);
            name.setText(" " + (i + 1));
            name.setFont(font);

            timer.setBackground(kdsGrey);
            timer.setForeground(Color.WHITE);
            timer.setHorizontalAlignment(SwingConstants.CENTER);
//            timer.setText("00 : 00 : 00 ");
            timer.setOpaque(true);
            timer.setFont(font);

            javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel);
            panel.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(timer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(text, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(timer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(text))
            );

            mainPanel.add(panel);
        }

        bottomPanel.setBackground(kdsGrey);
        bottomPanel.setLayout(new GridLayout(1, 3, 5, 0));

        displayLabel.setBackground(kdsGrey);
        displayLabel.setFont(new Font("Helvetica", Font.BOLD, 18)); // NOI18N
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setText(swingProperties.getDisplayLabelText());
        displayLabel.setMaximumSize(new Dimension(40, 20));
        displayLabel.setMinimumSize(new Dimension(40, 20));
        displayLabel.setPreferredSize(new Dimension(40, 20));
        bottomPanel.add(displayLabel);

        alert.setBackground(kdsGrey);
        alert.setFont(new Font("Helvetica", Font.BOLD, 12)); // NOI18N
        alert.setForeground(Color.WHITE);
        alert.setHorizontalAlignment(SwingConstants.CENTER);
//        alert.setText("ALERT!!!");
        bottomPanel.add(alert);

        page.setBackground(kdsGrey);
        page.setFont(new Font("Helvetica", Font.BOLD, 18)); // NOI18N
        page.setForeground(Color.WHITE);
        page.setHorizontalAlignment(SwingConstants.TRAILING);
        page.setText("Page 1 ");
        page.setPreferredSize(new Dimension(14, 14));
        bottomPanel.add(page);

        filler1.setBackground(kdsGrey);

        javax.swing.GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filler1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 656, Short.MAX_VALUE)
                                        .addComponent(bottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }

    public void setDisplayLabel(String displayLabel) {
        this.displayLabel.setText(displayLabel);
    }

    public void setAlert(String alertString) {
        this.alert.setText(alertString);
    }

    public void setPageNum(String pageNum) {
        this.page.setText(pageNum);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
