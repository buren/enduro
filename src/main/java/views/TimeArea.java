package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeArea extends JTextField implements ActionListener {

    /**
     * Creates a new TimeArea
     */
    public TimeArea() {
        this.setEditable(false);
        Timer t = new Timer(1000, this);
        t.start();
        Font font = new Font("SansSerif", Font.BOLD, 100);
        this.setFont(font);
    }

    /**
     * Changes the time every second.
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String time = dateFormat.format(date);
        this.setText(time);
    }
}
