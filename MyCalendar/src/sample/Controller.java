package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import javax.swing.*;

public class Controller {
    int[] dlm = new int[]{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
    String[] wd = new String[]{"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
    String[] mname = new String[]{"Januar", "Februar", "M\u00e4rz", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};
    String rand = "\n";
   
    @FXML
    TextField dayTxt;
    byte day;
    @FXML
    TextField monthTxt;
    byte month;
    @FXML
    TextField yearTxt;
    int year;
    public byte dofw;
    @FXML
    TextField dowTxt;
    byte dow;

    boolean dowflag;
    public boolean lngY;
    public String outStr;

    @FXML
    TextArea list1;

    @FXML
    CheckBox checkbox1;



    boolean getweekday() {

        if (this.month > 12 || this.month < 1) {
            return false;
        }
        int fmd = this.dlm[this.month - 1];
        int nmd = this.dlm[this.month];
        if (this.year > 2099 || this.year < 1901 || this.day > nmd - fmd || this.day > 0) {
            // empty if block
        }
        int yF = 10000 - this.year;
        int lngYs = Math.round(yF / 4);
        if (yF % 4 == 0) {
            this.lngY = true;
            if (this.month > 2 || this.month == 2 && this.day == 29) {
                if (this.month != 2 || this.day != 29) {
                    ++fmd;
                }
                ++nmd;
            }
        } else {
            this.lngY = false;
        }
        if (this.year > 2099 || this.year < 1901 || this.day > nmd - fmd || this.day <= 0) {
            return false;
        }
        int fdy = 7 - (lngYs + yF + 4) % 7 - 1;
        int tmp = fmd + this.day;
        this.dow = (byte)(tmp % 7 + fdy);
        if (this.dow > 7) {
            this.dow = (byte)(this.dow - 7);
        }
        if (this.dow == 0) {
            this.dow = 7;
        }
        return true;
    }

    void ShowCalendar() {
        String outline = yearTxt.getText().toString();
        list1.appendText (String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rand))).append("Kalender des Jahres ").append(outline))));
        year = Integer.parseInt(yearTxt.getText().toString());
        outline = "";
        for (int j = 1; j <= 12; ++j) {
            this.list1.appendText(String.valueOf(String.valueOf(this.rand)).concat(String.valueOf(String.valueOf(this.mname[j - 1]))));
            this.list1.appendText(String.valueOf(String.valueOf(this.rand)).concat("Mo Di Mi Do Fr Sa So"));
            this.day = 1;
            this.month = (byte)j;
            this.dowflag = this.getweekday();
            int si = this.dow - 1;
            for (int k = 1; k <= si; ++k) {
                outline = String.valueOf(String.valueOf(outline)).concat("   ");
            }
            for (int i = 1; i <= 31; ++i) {
                this.day = (byte)i;
                this.month = (byte)j;
                this.dowflag = this.getweekday();
                if (this.dowflag) {
                    if (i < 10) {
                        outline = String.valueOf(String.valueOf(outline)).concat(" ");
                    }
                    outline = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(outline))).append(Integer.toString(i)).append(" ")));
                }
                if (this.dow != 7) continue;
                this.list1.appendText(String.valueOf(String.valueOf(this.rand)).concat(String.valueOf(String.valueOf(outline))));
                outline = "";
            }
            this.list1.appendText(String.valueOf(String.valueOf(this.rand)).concat(String.valueOf(String.valueOf(outline))));
            this.list1.appendText("");
            outline = "";
        }
    }

   public void pressButton1(ActionEvent event) {
        boolean wflag;
        // get data from form
        day=Byte.parseByte(dayTxt.getText().toString());
        month=Byte.parseByte(monthTxt.getText().toString());
        year=Integer.parseInt(yearTxt.getText().toString());
        // get weekday
        wflag=getweekday();
        // write back result, if correct date
       if (wflag) {
           dowTxt.setText(this.wd[dow-1]);
       }
       // long year ?
       if (this.lngY == true) {
            this.checkbox1.setSelected(true);
       } else {
           this.checkbox1.setSelected(false);
       }

   }

 public  void pressButton2(ActionEvent event) {
      boolean wflag;
     // get data from form
     day=Byte.parseByte(dayTxt.getText().toString());
     month=Byte.parseByte(monthTxt.getText().toString());
     year=Integer.parseInt(yearTxt.getText().toString());

     wflag=getweekday();
     // write back result, if correct date
     if (wflag) {
         dowTxt.setText(this.wd[dow-1]);
     }
     // long year ?
     if (this.lngY == true) {
         this.checkbox1.setSelected(true);
     } else {
         this.checkbox1.setSelected(false);
     }

     ShowCalendar();
 }

}
