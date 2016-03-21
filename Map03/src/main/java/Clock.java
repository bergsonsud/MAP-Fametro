
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BKTECH
 */
   class Clock extends TimerTask
   {
      JTextField textField;
      SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
      public Clock(JTextField textField)
      {
         this.textField = textField;
      }
      @Override
      public void run()
      {
         textField.setText(sdf.format(new Date()));
      }
   }
